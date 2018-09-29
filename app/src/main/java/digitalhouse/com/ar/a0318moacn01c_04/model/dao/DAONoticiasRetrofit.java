package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.NewsHelper;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorNoticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.service.ServiceNoticia;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DH on 9/6/2018.
 */

public class DAONoticiasRetrofit {

    private String baseURL;
    private Retrofit retrofit;
    private ServiceNoticia serviceNoticia;

    public DAONoticiasRetrofit(){
        baseURL = "https://newsapi.org/v2/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceNoticia = retrofit.create(ServiceNoticia.class);
    }

   public void obtenerNoticiasDeInternet(final ResultListener<List<Noticia>> escuchadorDelControlador){

        Call<ContenedorNoticia> retrofitListener = serviceNoticia.getArticulosPorTema("6cc9dd144ae6445886c4667f1d8332cf", NewsHelper.category_GAMING,"es");
        retrofitListener.enqueue(new Callback<ContenedorNoticia>() {
            @Override
            public void onResponse(Call<ContenedorNoticia> call, Response<ContenedorNoticia> response) {

                ContenedorNoticia contenedorNoticia = response.body();
                escuchadorDelControlador.finish(contenedorNoticia.getListaDeNoticias());
            }

            @Override
            public void onFailure(Call<ContenedorNoticia> call, Throwable t) {

                escuchadorDelControlador.finish(new ArrayList<Noticia>());
            }
        });
    }

    public void obtenerNoticiasGaming(final ResultListener<List<Noticia>> escuchadorDelControlador){

        Call<ContenedorNoticia> retrofitListener = serviceNoticia.getNoticiasCategoria("6cc9dd144ae6445886c4667f1d8332cf", NewsHelper.category_GAMING);
        retrofitListener.enqueue(new Callback<ContenedorNoticia>() {
            @Override
            public void onResponse(Call<ContenedorNoticia> call, Response<ContenedorNoticia> response) {

                ContenedorNoticia contenedorNoticia = response.body();
                escuchadorDelControlador.finish(contenedorNoticia.getListaDeNoticias());
            }

            @Override
            public void onFailure(Call<ContenedorNoticia> call, Throwable t) {

                escuchadorDelControlador.finish(new ArrayList<Noticia>());
            }
        });
    }

    /**
     * Método utilizado para filtrar por titulo
     * @param escuchadorDelControlador
     * @param q
     * @param page
     * @param pageSize
     */
    public void obtenerNoticiasPorTitulo(final ResultListener<List<Noticia>> escuchadorDelControlador, String q, Integer page, Integer pageSize) {

        Call<ContenedorNoticia> retrofitListener = serviceNoticia.getByTitle(NewsHelper.apiKey, q, page, pageSize);


    }

    public void obtenerNoticasPorCategory(final ResultListener<List<Noticia>> escuchadorDelCotnrolador, String category, Integer page, Integer pageSize) {

        Call<ContenedorNoticia> retrofitListener = serviceNoticia.getByCategoryAndLanguage(NewsHelper.apiKey, category, NewsHelper.language_INGLES, page, pageSize);
        retrofitListener.enqueue(new Callback<ContenedorNoticia>() {
            @Override
            public void onResponse(Call<ContenedorNoticia> call, Response<ContenedorNoticia> response) {
                escuchadorDelCotnrolador.finish(response.body().getListaDeNoticias());
            }

            @Override
            public void onFailure(Call<ContenedorNoticia> call, Throwable t) {

            }
        });
    }



}
