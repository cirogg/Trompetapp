package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;

@Database(entities = {Noticia.class, Opinion.class, NoticiaFavorita.class},version = 1,exportSchema = false)
public abstract class DatabaseAbstract extends RoomDatabase {
    public abstract RoomServiceNoticias noticiaDao();
    public abstract RoomServiceOpinion opinionDao();
}
