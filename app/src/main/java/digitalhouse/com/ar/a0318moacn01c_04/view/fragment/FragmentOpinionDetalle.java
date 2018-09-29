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
import digitalhouse.com.ar.a0318moacn01c_04.controller.OpinionController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Usuario;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOpinionDetalle extends Fragment {

    private interfazFragmentOpinionDetalle interfazFragmentOpinionDetalle;
    private Opinion opinion;
    ImageView imageViewPerfil;
    TextView textViewNombre;
    TextView textViewCuerpo;
    Button buttonShare;

    public static FragmentOpinionDetalle crearFragmentOpinionDetalle(Integer posicion) {
        FragmentOpinionDetalle fragmentOpinionDetalle = new FragmentOpinionDetalle();
        Bundle bundle = new Bundle();
        bundle.putInt("posicion", posicion);
        fragmentOpinionDetalle.setArguments(bundle);

        return fragmentOpinionDetalle;
    }

    public FragmentOpinionDetalle() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.interfazFragmentOpinionDetalle = (interfazFragmentOpinionDetalle) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opinion_detalle, container, false);
        final Bundle bundle = getArguments();

        textViewCuerpo = view.findViewById(R.id.text_view_cuerpo);
        textViewNombre = view.findViewById(R.id.text_view_nombre);
        imageViewPerfil = view.findViewById(R.id.image_view_perfil);
        buttonShare = view.findViewById(R.id.button_share_opinion);

        buttonShare.setEnabled(Boolean.FALSE); //Quiero que el botón esté deshabilitado hasta que llegue la opinión

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfazFragmentOpinionDetalle.clickearonShare(opinion);
            }
        });

        ResultListener<Opinion> escuchadorDeLaVista = new ResultListener<Opinion>() {
            @Override
            public void finish(Opinion resultado) {
                opinion = resultado;
                textViewCuerpo.setText(resultado.getCuerpo());
                textViewNombre.setText(resultado.getUsuarioNombre());
                buttonShare.setEnabled(Boolean.TRUE);
            }
        };

        OpinionController opinionController = new OpinionController();
        opinionController.obtenerOpinion(bundle.getInt("id"), view.getContext(), escuchadorDeLaVista);

        return view;
    }

    public interface interfazFragmentOpinionDetalle {
        void clickearonShare(Opinion opinion);
    }

}
