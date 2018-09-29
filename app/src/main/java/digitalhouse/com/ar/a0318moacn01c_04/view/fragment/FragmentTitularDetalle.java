package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTitularDetalle extends Fragment {

    private static final String POSICION ="POSICION";
    private TextView textTituloNoticiaDetalleTitular;
    private TextView textDetalleNoticiaDetalleTitular;
    private TextView textFuenteNoticiaDetalleTitular;
    private TextView textHoraNoticiaDetalleTitular;
    private ImageView imgImageNoticiaDetalle;
    private Button cmdAgregarFavoritos;
    private Button cmdToUrl;
    private Noticia noticiaParaMostrar;

    private TapVistaUrlTitular tapVistaUrlTitular;
    public FragmentTitularDetalle() {
        // Required empty public constructor
    }

    public FragmentTitularDetalle crearFragmentoNoticiaDetalleTitular(){
        return null;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.tapVistaUrlTitular = (TapVistaUrlTitular) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_titular_detalle, container, false);
    }

    public interface TapVistaUrlTitular{
        void clickearonEnVerURLTitular(String urlNoticia);
    }

}
