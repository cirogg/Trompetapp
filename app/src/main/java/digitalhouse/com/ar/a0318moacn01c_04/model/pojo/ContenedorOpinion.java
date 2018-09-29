package digitalhouse.com.ar.a0318moacn01c_04.model.pojo;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DH on 4/7/2018.
 */

public class ContenedorOpinion implements Serializable {

    private List<Opinion> opiniones;

    // Constructor para Firebase
    public ContenedorOpinion () {
    }

    public ContenedorOpinion (List<Opinion> listaDeOpiniones){
        this.opiniones = listaDeOpiniones;
    }

    public List<Opinion> getListaDeOpiniones() { return opiniones; }
    public void setOpiniones(List<Opinion> opiniones) { this.opiniones = opiniones; }
}
