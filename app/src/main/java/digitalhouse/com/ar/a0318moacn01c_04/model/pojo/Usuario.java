package digitalhouse.com.ar.a0318moacn01c_04.model.pojo;


import java.util.ArrayList;

/**
 * Created by ciro_ on 10/7/2018.
 */

public class Usuario {

    private String usuarioID;
    private String nombre;
    private ArrayList<String> urlNoticiasFavoritas;

    // Constructor vacio para Firebase
    public Usuario() { }

    public Usuario(String usuarioID, String nombre, ArrayList<String> urlNoticiasFavoritas) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.urlNoticiasFavoritas = urlNoticiasFavoritas;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getUrlNoticiasFavoritas() {
        return urlNoticiasFavoritas;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrlNoticiasFavoritas(ArrayList<String> urlNoticiasFavoritas) {
        this.urlNoticiasFavoritas = urlNoticiasFavoritas;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }
}


