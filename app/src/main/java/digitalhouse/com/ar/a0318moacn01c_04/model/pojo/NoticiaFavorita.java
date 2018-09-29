package digitalhouse.com.ar.a0318moacn01c_04.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ciro_ on 21/7/2018.
 */

@Entity
public class NoticiaFavorita {



    @SerializedName("source.id")
    private Integer id;

    @SerializedName("source.name")
    private String name;

    private String author;

    private String title;

    private String description;

    @PrimaryKey
    @NonNull
    private String url;

    private String urlToImage;

    /** Confirmar si mappearlo como Strign o como DateTime */
    private String publishedAt;


    //CONSTRUCTOR VACIO PARA LA API
    public NoticiaFavorita() {
    }

    public NoticiaFavorita(Noticia noticia) {
        this.id = noticia.getId();
        this.name = noticia.getName();
        this.author = noticia.getAuthor();
        this.title = noticia.getTitle();
        this.description = noticia.getDescription();
        this.url = noticia.getUrl();
        this.urlToImage = noticia.getUrlToImage();
        this.publishedAt = noticia.getPublishedAt();
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}





