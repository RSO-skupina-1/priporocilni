package si.fri.rso.priporocilni.lib;

import java.time.Instant;
import com.google.gson.annotations.Expose;

public class Komentar {

    /* same attributes as in Komentar Entity */
    @Expose
    private Integer id;
    @Expose
    private String komentar;
    @Expose
    private Integer user_id;
    @Expose
    private Integer lokacija_id;
    @Expose
    private Integer ocena;
    private Instant ustvarjen;

    /* getter and setter methods for the entity's attributes */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getKomentar() {
        return komentar;
    }
    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id= user_id;
    }
    public Integer getLokacija_id() {
        return lokacija_id;
    }
    public void setLokacija_id(Integer lokacija_id) {
        this.lokacija_id = lokacija_id;
    }
    public Integer getOcena() {
        return ocena;
    }
    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
    public Instant getUstvarjen() {
        return ustvarjen;
    }
    public void setUstvarjen(Instant ustvarjen) {
        this.ustvarjen = ustvarjen;
    }
}
