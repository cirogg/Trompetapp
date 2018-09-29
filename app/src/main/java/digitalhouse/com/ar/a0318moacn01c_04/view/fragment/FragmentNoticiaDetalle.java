package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.dao.DAONoticiaRoom;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity;

import static com.facebook.FacebookSdk.getApplicationContext;
import static digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity.URL_NOTICIA;
import static digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity.linearLayoutContenedorURL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoticiaDetalle extends Fragment {

    private static final String POSICION = "POSICION";
    private TextView textTituloNoticiaDetalle;
    private TextView textDetalleNoticiaDetalle;
    private TextView textFuenteNoticiaDetalle;
    private TextView textHoraNoticiaDetalle;
    private ImageView imgImagenNoticiaDetalle;
    private TapVistaUrl tapVistaUrl;
    private Button cmdAgregarFavoritos;
    private Button cmdToUrl;
    private Button cmdToVistaApp;
    private FragmentNoticiaDetalleURL fragmentNoticiaDetalleURL;
    private FragmentManager fragmentManager;
    private Noticia noticiaParaMostrar;

    public static FragmentNoticiaDetalle crearFragmentNoticiaDetalle(Integer posicion){

        FragmentNoticiaDetalle fragmentNoticiaDetalle = new FragmentNoticiaDetalle();
        Bundle unBundle = new Bundle();
        unBundle.putInt(POSICION,posicion);
        fragmentNoticiaDetalle.setArguments(unBundle);
        return fragmentNoticiaDetalle;
    }

    public FragmentNoticiaDetalle() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.tapVistaUrl = (TapVistaUrl) context;
        //NoticiaActivity.menuItemVistaAPI.setVisible(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticia_detalle, container, false);
        final Bundle unBundle = getArguments();
        //NoticiaActivity.menuItemVistaAPI.setVisible(false);
        final Context context = getContext();
        textTituloNoticiaDetalle = view.findViewById(R.id.textTituloNoticia);
        textDetalleNoticiaDetalle = view.findViewById(R.id.textCuerpoNoticia);
        textHoraNoticiaDetalle = view.findViewById(R.id.textHoraPublicado);
        textFuenteNoticiaDetalle = view.findViewById(R.id.textURLSite);
        imgImagenNoticiaDetalle = view.findViewById(R.id.imagenNoticiaDetalle);

        cmdAgregarFavoritos = view.findViewById(R.id.cmdFavoritos);
        cmdToUrl = view.findViewById(R.id.cmdVistaWeb);
        cmdToUrl.setVisibility(View.GONE);
        cmdAgregarFavoritos.setVisibility(View.GONE);

        //Noticia noticiaParaMostrar= FragmentFeed.listaProvDeNoticias.get(unBundle.getInt("posicion"));
        final NoticiaController noticiaController = new NoticiaController();
        noticiaController.obtenerNoticias(new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                List<Noticia> listaDeNoticias = resultado;
                noticiaParaMostrar = listaDeNoticias.get(unBundle.getInt(POSICION));
                textTituloNoticiaDetalle.setText(noticiaParaMostrar.getTitle());
                textDetalleNoticiaDetalle.setText(noticiaParaMostrar.getDescription());
                textHoraNoticiaDetalle.setText(noticiaParaMostrar.getPublishedAt());
                //TODO Revisar como pide la Fuente de la noticia (Como le pega a la api)
                textFuenteNoticiaDetalle.setText(noticiaParaMostrar.getName());
                try {
                    Glide.with(context).load(noticiaParaMostrar.getUrlToImage()).into(imgImagenNoticiaDetalle);
                } catch (Exception ex) {
                    Log.e("Glide", "el context no existe mas");
                }

                cmdToUrl.setVisibility(View.VISIBLE);
                cmdAgregarFavoritos.setVisibility(View.VISIBLE);

            }
        },context);
        /*textTituloNoticiaDetalle = view.findViewById(R.id.textTituloNoticia);
        textDetalleNoticiaDetalle = view.findViewById(R.id.textCuerpoNoticia);
        textHoraNoticiaDetalle = view.findViewById(R.id.textHoraPublicado);
        textFuenteNoticiaDetalle = view.findViewById(R.id.textURLSite);
        imgImagenNoticiaDetalle = view.findViewById(R.id.imagenNoticiaDetalle);

        textTituloNoticiaDetalle.setText(noticiaParaMostrar.getTitle());
        textDetalleNoticiaDetalle.setText(noticiaParaMostrar.getDescription());
        textHoraNoticiaDetalle.setText(noticiaParaMostrar.getPublishedAt());
        //TODO Revisar como pide la Fuente de la noticia (Como le pega a la api)
        textFuenteNoticiaDetalle.setText(noticiaParaMostrar.getName());
        Glide.with(this).load(noticiaParaMostrar.getUrlToImage()).into(imgImagenNoticiaDetalle);*/

        cmdAgregarFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticiaController noticiaController = new NoticiaController();
                noticiaController.favearNoticia(noticiaParaMostrar, getContext());
                Toast.makeText(getContext(), "Noticia guardada", Toast.LENGTH_SHORT).show();
            }
        });

        cmdToUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutContenedorURL.animate().alpha(1.0f);
                String urlDeLaNoticia = noticiaParaMostrar.getUrl();
                Bundle unBundle = new Bundle();
                unBundle.putString(URL_NOTICIA,urlDeLaNoticia);
                linearLayoutContenedorURL.setVisibility(View.VISIBLE);
                tapVistaUrl.clickearonEnVerURL(urlDeLaNoticia);
                NoticiaActivity.menuItemVistaAPI.setVisible(true);
            }
        });

        return view;
    }

    public interface TapVistaUrl{
        void clickearonEnVerURL(String urlNoticia);
    }
}
