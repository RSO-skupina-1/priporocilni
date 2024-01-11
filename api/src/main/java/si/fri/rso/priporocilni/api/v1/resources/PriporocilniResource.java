package si.fri.rso.priporocilni.api.v1.resources;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.logs.cdi.Log;
import okhttp3.Request;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.priporocilni.lib.Komentar;
import si.fri.rso.priporocilni.lib.Uporabnik;
import si.fri.rso.priporocilni.lib.KatalogDestinacij;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Log
@ApplicationScoped
@Path("/priporocilni")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(name = "priporocilni", allowOrigin = "*")
public class PriporocilniResource {

    private Logger log = Logger.getLogger(PriporocilniResource.class.getName());


    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get priporocilo for user.", summary = "Returns priporocilo for user.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Priporocilo, array of destinacije of length <= 10.",
                    content = @Content(schema = @Schema(implementation = KatalogDestinacij.class, type = SchemaType.ARRAY,
                            example = """
                    [
                        {
                            "accessibility": "Metro, tram",
                            "description": "Picturesque city in northwest Portugal.",
                            "id": 7,
                            "infrastructure": "Ribeira, Port wine cellars",
                            "latitude": 41.157944,
                            "location": "Northern Portugal",
                            "longitude": -8.629105,
                            "price": 26.5,
                            "title": "Porto"
                        },
                        {
                            "accessibility": "Car, bus",
                            "description": "Coastal town with Venetian architecture.",
                            "id": 8,
                            "infrastructure": "Old town, seaside promenade",
                            "latitude": 45.527435,
                            "location": "SW Slovenia",
                            "longitude": 13.568627,
                            "price": 19.9,
                            "title": "Piran"
                        },
                        {
                            "accessibility": "Car, bus",
                            "description": "Serene lake surrounded by mountains.",
                            "id": 6,
                            "infrastructure": "Hiking trails, boat tours",
                            "latitude": 46.273983,
                            "location": "NW Slovenia",
                            "longitude": 13.886146,
                            "price": 22.75,
                            "title": "Lake Bohinj"
                        }
                    ]
                    """))),
            @APIResponse(responseCode = "404",
                    description = "User with given id does not exist.",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "500",
                    description = "Internal server error.")
    })

    @Timed(name = "get_priporocilo_timer")
    @Counted(name = "get_priporocilo_count")
    @Log
    @GET
    @Path("/{userId}")
    public Response getPriporocilo(@Parameter(description = "User ID", required = true)
                                       @PathParam("userId") Integer userId) {
        log.info("Get priporocilo for user with id: " + userId);
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        Gson gson = new Gson();
        Uporabnik uporabnik;
        KatalogDestinacij[] destinacije;


        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://localhost:8083/v1/uporabnik/" + userId)
                .method("GET", null)
                .build();
        try{
            okhttp3.Response response = client.newCall(request).execute();
            if(response.code() == 200){
                String responseBody = response.body().string();
                response.body().close();
                try{
                    uporabnik = gson.fromJson(responseBody, Uporabnik.class);
                }catch (Exception e){
                    log.info(responseBody);
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }

            }
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("User with id: " + userId + " does not exist.").build();
            }
        }catch (IOException e){
            log.warning(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        log.info("Get priporocilo za uporabnika: " + uporabnik.getUsername());

        okhttp3.Request request2 = new okhttp3.Request.Builder()
                .url("http://katalog-destinacij.426c0549c45c4600b961.switzerlandnorth.aksapp.io/v1/katalogDestinacij/")
                .build();
        try{
            okhttp3.Response response = client.newCall(request2).execute();
            if(response.code() == 200){
                String responseBody = response.body().string();
                response.body().close();
                try{
                    destinacije = gson.fromJson(responseBody, KatalogDestinacij[].class);
                }catch (Exception e){
                    log.warning("could not parse json from kataogDestinacij[]. String: " + responseBody);
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }

            }
            else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        List<KatalogDestinacij> neobiskaneDestinacije = new ArrayList<KatalogDestinacij>();
        int[] obiskaneDestinacije = uporabnik.getVisitedLocations();

        for(int i = 0; i < destinacije.length; i++){
            boolean found = false;
            for(int j = 0; j < obiskaneDestinacije.length; j++){
                if(destinacije[i].getId() == obiskaneDestinacije[j]){
                    found = true;
                    break;
                }
            }
            if(!found){
                neobiskaneDestinacije.add(destinacije[i]);
            }
        }

        double[] ocene = new double[neobiskaneDestinacije.size()];
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        for(int i = 0; i < neobiskaneDestinacije.size(); i++){
            int neobiskanaId = neobiskaneDestinacije.get(i).getId();
            double ocena = 0;
            Komentar[] komentarji = {};
            okhttp3.Request request3 = new okhttp3.Request.Builder()
                    .url("http://katalog-destinacij.426c0549c45c4600b961.switzerlandnorth.aksapp.io/v1/komentar/destinacija/" + neobiskanaId)
                    .build();
            try{
                okhttp3.Response response = client.newCall(request3).execute();
                if(response.code() == 200){
                    String responseBody = response.body().string();
                    response.body().close();
                    try{
                        komentarji = gson.fromJson(responseBody, Komentar[].class);
                    }catch (Exception e){
                        log.warning("could not parse json from komentar[]." + e.getMessage());
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }


                }
                else{
                    log.warning("Response code: " + response.code());
                    //return Response.status(Response.Status.NOT_FOUND).build();
                }

            } catch (IOException e) {
                log.warning("Destinacija " + neobiskaneDestinacije.get(i).getTitle() + " has no comments.");
            }
            for (int j = 0; j < komentarji.length; j++){
                ocena += komentarji[j].getOcena();
            }
            if(komentarji.length != 0){
                ocena = ocena / komentarji.length;
            }
            ocene[i] = ocena;
        }
        String ocene_string = "";
        for(int i = 0; i < ocene.length; i++){
            ocene_string += ocene[i] + " ";
        }
        // find the 10 highest values in ocena and return the corresponding destinacije
        int stDestinacij = (int)Math.min(neobiskaneDestinacije.size(), 10);
        double[] najboljseOcene = new double[stDestinacij];
        int[] najboljseDestinacije = new int[stDestinacij];
        for(int i = 0; i < stDestinacij; i++){
            najboljseOcene[i] = 0;
            najboljseDestinacije[i] = 0;
        }
        for(int i = 0; i < ocene.length; i++){
            for(int j = 0; j < stDestinacij; j++){
                if(ocene[i] > najboljseOcene[j]){
                    for(int k = stDestinacij-1; k > j; k--){
                        najboljseOcene[k] = najboljseOcene[k-1];
                        najboljseDestinacije[k] = najboljseDestinacije[k-1];
                    }
                    najboljseOcene[j] = ocene[i];
                    najboljseDestinacije[j] = neobiskaneDestinacije.get(i).getId();
                    break;
                }
            }
        }
        String najocene_string = "";
        for(int i = 0; i < najboljseOcene.length; i++){
            najocene_string += najboljseOcene[i] + " ";
        }
        log.info("Najboljse ocene: " + najocene_string);

        List<KatalogDestinacij> priporoceneDestinacije = new ArrayList<KatalogDestinacij>();
        for (int i = 0; i < stDestinacij; i++){
            if (najboljseOcene[i] == 0){
                break;
            }
            int najDest = najboljseDestinacije[i];
            priporoceneDestinacije.add(neobiskaneDestinacije.get(najDest));
        }


        return Response.status(Response.Status.OK).entity(priporoceneDestinacije).build();
    }





}
