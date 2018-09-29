package digitalhouse.com.ar.a0318moacn01c_04.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

/**
 * Created by GUIDO on 22/6/2018.
 */
@Entity
public class Opinion implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String usuarioID;
    private String usuarioNombre;
    private String cuerpo;

    public Opinion() { }

    @Ignore
    public Opinion(String usuarioID, String usuarioNombre, String cuerpo) {
        this.usuarioNombre = usuarioNombre;
        this.usuarioID = usuarioID;
        this.cuerpo = cuerpo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
