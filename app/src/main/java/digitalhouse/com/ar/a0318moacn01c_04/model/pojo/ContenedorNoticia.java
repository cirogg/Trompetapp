package digitalhouse.com.ar.a0318moacn01c_04.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DH on 9/6/2018.
 */

public class ContenedorNoticia implements Serializable {
    @SerializedName("articles")
    private List<Noticia> listaDeNoticias;

    public ContenedorNoticia(List<Noticia> listaDeNoticias) {
        this.listaDeNoticias = listaDeNoticias;
    }

    public List<Noticia> getListaDeNoticias() {
        return listaDeNoticias;
    }

}
