package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;

/**
 * Created by ciro_ on 20/7/2018.
 */
@Dao
public interface RoomServiceNoticias {
    @Query("Select * from noticia")
    List<Noticia> dameTodasLasNoticias();

    @Query("Select * from noticiafavorita")
    List<NoticiaFavorita> dameTodasLasNoticiasFavoritas();

    @Query("SELECT * FROM noticia WHERE title LIKE :terms")
    List<Noticia> dameNoticiaPorTitulo(String terms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarNoticia(Noticia noticia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarNoticias(List<Noticia>listaDeNoticias);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertarNoticiaFavorita(NoticiaFavorita noticiaFavorita);

    @Delete
    void borrarNoticia(Noticia noticiaABorrar);
}
