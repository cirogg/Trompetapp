package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;
import digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoticiaFavoritoDetalle extends Fragment {

    private TextView textTituloNoticiaDetalle;
    private TextView textDetalleNoticiaDetalle;
    private TextView textFuenteNoticiaDetalle;
    private TextView textHoraNoticiaDetalle;
    private ImageView imgImagenNoticiaDetalle;

    NoticiaFavorita noticiaFavoritaParaMostrar;

    public FragmentNoticiaFavoritoDetalle() {
        // Required empty public constructor
    }

    public static FragmentNoticiaFavoritoDetalle crearFragmentNoticiaFavoritaDetalle(Integer posicion){


        FragmentNoticiaFavoritoDetalle fragmentNoticiaFavoritoDetalle = new FragmentNoticiaFavoritoDetalle();
        Bundle unBundle = new Bundle();

        unBundle.putInt("posicion",posicion);
        fragmentNoticiaFavoritoDetalle.setArguments(unBundle);
        return fragmentNoticiaFavoritoDetalle;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticia_favorito_detalle, container, false);

        final Bundle unBundle = getArguments();

        final Context context = getContext();
        textTituloNoticiaDetalle = view.findViewById(R.id.textTituloNoticiaFavorita);
        textDetalleNoticiaDetalle = view.findViewById(R.id.textCuerpoNoticiaFavorita);
        textHoraNoticiaDetalle = view.findViewById(R.id.textHoraPublicadoFavorita);
        textFuenteNoticiaDetalle = view.findViewById(R.id.textURLSiteFavorita);
        imgImagenNoticiaDetalle = view.findViewById(R.id.imagenNoticiaDetalleFavorita);

        NoticiaController noticiaController = new NoticiaController();
        List<NoticiaFavorita> listaDeNoticiasFavoritas = new ArrayList<>();
        listaDeNoticiasFavoritas = noticiaController.obtenerNoticiasFavoritas(getContext());
        noticiaFavoritaParaMostrar = listaDeNoticiasFavoritas.get(unBundle.getInt("posicion"));

        textTituloNoticiaDetalle.setText(noticiaFavoritaParaMostrar.getTitle());
        textDetalleNoticiaDetalle.setText(noticiaFavoritaParaMostrar.getDescription());
        textHoraNoticiaDetalle.setText(noticiaFavoritaParaMostrar.getPublishedAt());
        //TODO Revisar como pide la Fuente de la noticia (Como le pega a la api)
        textFuenteNoticiaDetalle.setText(noticiaFavoritaParaMostrar.getName());
        Glide.with(context).load(noticiaFavoritaParaMostrar.getUrlToImage()).into(imgImagenNoticiaDetalle);
        //NoticiaActivity.menuItemVistaURL.setVisible(true);
        //NoticiaActivity.menuItemBookmark.setVisible(true);


        return view;
    }

}
