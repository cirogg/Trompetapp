package digitalhouse.com.ar.a0318moacn01c_04.controller;

import android.content.Context;
import android.util.Log;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.dao.DAONoticiaRoom;
import digitalhouse.com.ar.a0318moacn01c_04.model.dao.DAONoticiasRetrofit;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;
import digitalhouse.com.ar.a0318moacn01c_04.util.HTTPConnectionManager;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;


/**
 * Created by DH on 9/6/2018.
 */

public class NoticiaController {

    private Integer page = 0;
    private Integer pageSize = 20;

    public void obtenerNoticias(final ResultListener<List<Noticia>> escuchadorDeLaVista, final Context context) {

        DAONoticiaRoom daoNoticiaRoom = new DAONoticiaRoom(context);
        List<Noticia> listaDeNoticiasDeRoom = daoNoticiaRoom.dameTodasLasNoticias();
        escuchadorDeLaVista.finish(listaDeNoticiasDeRoom);


        ResultListener<List<Noticia>> escuchadorDelControlador = new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                persistirListaDeNoticiasRecibidas(context, resultado);
                escuchadorDeLaVista.finish(resultado);
                guardarNoticiasFeed(resultado, context);
            }
        };

        DAONoticiasRetrofit daoNoticiasRetrofit = new DAONoticiasRetrofit();
        daoNoticiasRetrofit.obtenerNoticiasDeInternet(escuchadorDelControlador);
    }

    public void guardarNoticiasFeed(List<Noticia> noticiaList, final Context context) {

        DAONoticiaRoom daoNoticiaRoom = new DAONoticiaRoom(context);
        for (Noticia noticia : noticiaList) {
            daoNoticiaRoom.insertarNoticia(noticia);
        }

        Log.i("Guardado en ROOM", "noticias del feed guardadas");

    }


    public void obetenerNoticiasGaming(String term, final ResultListener<List<Noticia>> escuchadorDeLaVista, Context context) {

        ResultListener<List<Noticia>> escuchadorDelControlador = new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        };

        DAONoticiaRoom daoNoticiaRoom = new DAONoticiaRoom(context);
        daoNoticiaRoom.dameNoticiaPorTitulo(term);

        DAONoticiasRetrofit daoNoticiasRetrofit = new DAONoticiasRetrofit();
        daoNoticiasRetrofit.obtenerNoticiasGaming(escuchadorDelControlador);

    }

    /**
     * Método que devuelve lista de Noticias a partir de una categoria.
     * @param categoria
     * @param escuchadorDeLaVista
     * @param context
     */
    public void obtenerNoticiasPorCategoria(String categoria, final ResultListener<List<Noticia>> escuchadorDeLaVista, Context context) {

        ResultListener<List<Noticia>> escuchadorDelControlador = new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        };

        DAONoticiasRetrofit daoNoticiasRetrofit = new DAONoticiasRetrofit();
        daoNoticiasRetrofit.obtenerNoticasPorCategory(escuchadorDelControlador, categoria, page, pageSize);
    }


    public Boolean hayInternet(Context context) {
        return HTTPConnectionManager.isNetworkingOnline(context);
    }

    public void persistirListaDeNoticiasRecibidas(Context context, List<Noticia> lista) {
        DAONoticiaRoom daoNoticiaRoom = new DAONoticiaRoom(context);
        daoNoticiaRoom.insertarNoticias(lista);
    }

    public void favearNoticia(final Noticia noticia, final Context context) {

        NoticiaFavorita noticiaFavorita = new NoticiaFavorita(noticia);
        DAONoticiaRoom daoNoticiaRoom = new DAONoticiaRoom(context);
        daoNoticiaRoom.insertarNoticiaFavorita(noticiaFavorita);

    }

    public List<NoticiaFavorita> obtenerNoticiasFavoritas(Context context) {
        DAONoticiaRoom daoNoticiaRoom = new DAONoticiaRoom(context);
        return  daoNoticiaRoom.dameTodasLasNoticiasFavoritas();

    }


}
