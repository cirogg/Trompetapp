package digitalhouse.com.ar.a0318moacn01c_04.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ciro_ on 21/5/2018.
 */
@Entity
public class Noticia implements Serializable {


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
    public Noticia() {
    }

    @Ignore
    public Noticia(Integer id, String name, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
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
