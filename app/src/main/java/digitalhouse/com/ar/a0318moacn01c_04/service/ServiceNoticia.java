package digitalhouse.com.ar.a0318moacn01c_04.service;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorNoticia;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DH on 9/6/2018.
 */
public interface ServiceNoticia {

    /*
    @GET("post/paginated")
    Call<Object> getObjectPaginated(@Query("apiKey") String apiKey,
                                    @Query("offset") String offset,
                                    @Query("max") String max)
     */

    @GET("top-headlines")
    Call<ContenedorNoticia> getArticulosPorCategoria(@Query("apiKey") String apiKey,
                                                     @Query("sources") String sources);

    @GET("everything")
    Call<ContenedorNoticia> getArticulosPorTema(@Query("apiKey") String apiKey,
    @Query("q") String tema,
    @Query("language") String idioma);

    @GET("sources")
    Call<ContenedorNoticia> getNoticiasCategoria(@Query("apiKey") String apiKey,
                                          @Query("category") String category);

    @GET("top-headlines")
    Call<ContenedorNoticia> getByTitle(@Query("apiKey") String apiKey,
                                       @Query("q") String q,
                                       @Query("offset") Integer page,
                                       @Query("max") Integer pageSize);

    @GET("top-headlines")
    Call<ContenedorNoticia> getByCategoryAndLanguage(@Query("apiKey") String apiKey,
                                                     @Query("category") String category,
                                                     @Query("language") String language,
                                                     @Query("offset") Integer page,
                                                     @Query("max") Integer pageSize);


}
