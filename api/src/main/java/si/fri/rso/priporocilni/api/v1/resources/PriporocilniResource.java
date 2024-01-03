package si.fri.rso.priporocilni.api.v1.resources;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.JSONObject;
import si.fri.rso.priporocilni.lib.Priporocilni;
import si.fri.rso.priporocilni.services.beans.PriporocilniBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;



@ApplicationScoped
@Path("/priporocilni")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PriporocilniResource {

    private Logger log = Logger.getLogger(PriporocilniResource.class.getName());

    @Inject
    private PriporocilniBean priporocilniBean;


    @Context
    protected UriInfo uriInfo;

    @Counted(name = "get_all_komentar_count")
    @Operation(description = "Get all comments.", summary = "Returns all comments present in the database.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Array of comments",
                    content = @Content(schema = @Schema(implementation = Priporocilni.class, type = SchemaType.ARRAY))
            )})
    @GET
    public Response getKomentar() {
        log.info("Get all comments.") ;
        List<Priporocilni> priporocilni = priporocilniBean.getKomentarFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(priporocilni).build();
    }


    @Operation(description = "Get comment by ID.", summary = "Returns comment with corresponding ID.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Successfully returns chosen comment.",
                    content = @Content(
                            schema = @Schema(implementation = Priporocilni.class))
            ),
            @APIResponse(responseCode = "404",
                    description = "Comment with given ID doesn't exist.")
    })
    @GET
    @Path("/{priporocilniId}")
    public Response getKomentar(@Parameter(description = "Metadata ID.", required = true)
                                     @PathParam("priporocilniId") Integer priporocilniId) {

        log.info("Get comment with id: " + priporocilniId);

        Priporocilni priporocilni = priporocilniBean.getKomentar(priporocilniId);

        if (priporocilni == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(priporocilni).build();
    }

    @Operation(description = "Get comments by user ID.", summary = "Returns all comments posted by user with coresponding user ID.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Successfully returns chosen users comments.",
                    content = @Content(
                            schema = @Schema(implementation = Priporocilni.class, type = SchemaType.ARRAY))
            ),
            @APIResponse(responseCode = "404",
                    description = "User with given ID doesn't exist.")
    })
    @GET
    @Path("user/{userId}")
    public Response getKomentarByUser(@Parameter(description = "User ID.", required = true)
                                 @PathParam("userId") Integer userId) {

        log.info("Get all comments posted by user with id: " + userId);

        List<Priporocilni> priporocilni = priporocilniBean.getKomentarByUser(userId);

        if (priporocilni == null || priporocilni.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(priporocilni).build();
    }
    @Operation(description = "Get comments by destinacija ID.", summary = "Returns all comments posted under destinacija with coresponding destinacija ID.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Successfully returns chosen destinations comments.",
                    content = @Content(
                            schema = @Schema(implementation = Priporocilni.class, type = SchemaType.ARRAY))
            ),
            @APIResponse(responseCode = "404",
                    description = "Destinacija with given ID doesn't exist.")
    })
    @GET
    @Path("destinacija/{destinacijaId}")
    public Response getKomentarByDestinacija(@Parameter(description = "Destinacija ID.", required = true)
                                 @PathParam("destinacijaId") Integer destinacijaId) {

        log.info("Get all comments posted under destination with id: " + destinacijaId);

        List<Priporocilni> priporocilni = priporocilniBean.getKomentarByDestinacija(destinacijaId);

        if (priporocilni == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(priporocilni).build();
    }


    @Operation(description = "Add new comment from given user to a destination.", summary = "Add comment")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Comment successfully added.",
                    content = @Content(
                            schema = @Schema(implementation = Priporocilni.class)
                    )
            ),
            @APIResponse(responseCode = "405",
                        description = "Either user ID or destinacija ID was not given")
    })
    @Counted(name = "num_of_posted_comments")
    @POST
    public Response postKomentarByDestinacija(@RequestBody(description = "DTO object with comment metadata and text",
                                                           required = true,
                                                           content = @Content(
                                                                   schema = @Schema(implementation = Priporocilni.class)
                                                           )) Priporocilni priporocilni) throws IOException {

        log.info("Post new comment.");

        if (priporocilni.getLokacija_id() == null || priporocilni.getUser_id() == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(priporocilni.getUstvarjen() == null){
            priporocilni.setUstvarjen(Instant.now());
        }

        String text = priporocilni.getKomentar();
        okhttp3.RequestBody body = okhttp3.RequestBody
                .create(okhttp3.MediaType.get("application/x-www-form-urlencoded"), text);

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/bad_words?censor_character=*")
                .addHeader("apiKey", "VC7y8FdT1gEcdGuoOTZBWSBPN05mq4ds")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Call call = client.newCall(request);
        okhttp3.Response response1 = call.execute();

        JSONObject jo = new JSONObject(response1.body().string());

        priporocilni.setKomentar(jo.get("censored_content").toString());

        // kdaj dobim exception Internal Exception: org.postgresql.util.PSQLException: ERROR: prepared statement "S_2" already exists
        // bi bilo idealno za error prevention.
        return Response.status(Response.Status.CREATED).entity(priporocilniBean.createKomentar(priporocilni)).build();
    }

    @Operation(description = "Update comment from user on destinacija.", summary = "Update comment with corresponding komentar ID.")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Comment successfully updated.",
                    content = @Content(
                            schema = @Schema(implementation = Priporocilni.class)
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Comment with given komentar ID was not found, hence cannot be updated."
            )
            })
    @PUT
    @Counted(name = "number_of_updated_comments")
    @Path("{priporocilniId}")
    public Response putImageMetadata(@Parameter(description = "Metadata ID.", required = true)
                                     @PathParam("priporocilniId") Integer priporocilniId,
                                     @RequestBody(
                                             description = "DTO object with comment.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Priporocilni.class)))
                                     Priporocilni priporocilni) throws IOException{

        log.info("Update comment.");

        if(priporocilni.getUstvarjen() == null){
            priporocilni.setUstvarjen(Instant.now());
        }

        priporocilni = priporocilniBean.putKomentar(priporocilniId, priporocilni);

        if (priporocilni == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        String text = priporocilni.getKomentar();
        okhttp3.RequestBody body = okhttp3.RequestBody
                .create(okhttp3.MediaType.get("application/x-www-form-urlencoded"), text);

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/bad_words?censor_character=*")
                .addHeader("apiKey", "VC7y8FdT1gEcdGuoOTZBWSBPN05mq4ds")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Call call = client.newCall(request);
        okhttp3.Response response1 = call.execute();

        JSONObject jo = new JSONObject(response1.body().string());

        priporocilni.setKomentar(jo.get("censored_content").toString());

        return Response.status(Response.Status.CREATED).build();

    }

    @Operation(description = "Delete comment with given id.", summary = "Delete comment with corresponding komentar ID.")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Comment successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Comment with given comment ID was not found."
            )
    })
    @DELETE
    @Counted(name = "number_of_deleted_comments")
    @Path("{priporocilniId}")
    public Response deleteKomentar(@Parameter(description = "Comment ID.", required = true)
                                        @PathParam("priporocilniId") Integer priporocilniId){

        log.info("Delete comment with id: " + priporocilniId);

        boolean deleted = priporocilniBean.deleteKomentar(priporocilniId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }





}
