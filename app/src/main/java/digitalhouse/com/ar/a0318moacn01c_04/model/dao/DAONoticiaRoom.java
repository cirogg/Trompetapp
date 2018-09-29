package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;

/**
 * Created by ciro_ on 20/7/2018.
 */

public class DAONoticiaRoom implements RoomServiceNoticias {

    private  DatabaseAbstract roomDatabase;

    public DAONoticiaRoom(Context context){
        roomDatabase = Room.databaseBuilder(context,DatabaseAbstract.class, "database-name").allowMainThreadQueries().build();
    }


    @Override
    public List<Noticia> dameTodasLasNoticias() {
        return roomDatabase.noticiaDao().dameTodasLasNoticias();
    }

    @Override
    public List<NoticiaFavorita> dameTodasLasNoticiasFavoritas() {
        return roomDatabase.noticiaDao().dameTodasLasNoticiasFavoritas();
    }

    @Override
    public List<Noticia> dameNoticiaPorTitulo(String terms) {
        return roomDatabase.noticiaDao().dameNoticiaPorTitulo(terms);
    }

    @Override
    public void insertarNoticia(Noticia noticia) {
        roomDatabase.noticiaDao().insertarNoticia(noticia);
    }

    @Override
    public void insertarNoticias(List<Noticia> listaDeNoticias) {
        roomDatabase.noticiaDao().insertarNoticias(listaDeNoticias);
    }

    @Override
    public void insertarNoticiaFavorita(NoticiaFavorita noticiaFavorita){
        roomDatabase.noticiaDao().insertarNoticiaFavorita(noticiaFavorita);
    }

    @Override
    public void borrarNoticia(Noticia noticiaABorrar) {

    }


}
