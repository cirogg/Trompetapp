package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.OpinionController;
import digitalhouse.com.ar.a0318moacn01c_04.model.dao.DAOOpinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorOpinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import digitalhouse.com.ar.a0318moacn01c_04.view.activity.MainActivity;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterRecyclerViewOpiniones;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOpiniones extends Fragment implements AdapterRecyclerViewOpiniones.ComunicacionAdapterRecyclerOpinion{

    private ContenedorOpinion contenedorOpinion;
    private interfazFragmentOpiniones interfazFragmentOpiniones;
    private RecyclerView recyclerView;
    private FloatingActionButton fabEscribirOpinion;

    public static ContenedorOpinion contenedorOpinionProvisorio;


    public FragmentOpiniones() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.interfazFragmentOpiniones = (interfazFragmentOpiniones)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opiniones, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_opiniones);
        fabEscribirOpinion = view.findViewById(R.id.fab_escribir_opinion);


        fabEscribirOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpinionController opinionController = new OpinionController();
                if (opinionController.puedeEscribir()) {
                    interfazFragmentOpiniones.clickearonEscribirOpinion();
                } else {
                    Toast.makeText(view.getContext(), "Debe estar logeado para realizar esta acción", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setHasFixedSize(Boolean.TRUE);
        cargarOpiniones(getContext());
        DAOOpinion daoOpinion = new DAOOpinion();
        //daoOpinion.obtenerOpiniones();

        return view;
    }

    @Override
    public void seleccionaronLaCelda(Integer id) {
        interfazFragmentOpiniones.clickearonLaOpinion(id);
    }

    public void clickearonEscribirOpinion() {
        interfazFragmentOpiniones.clickearonEscribirOpinion();
    }

    private void cargarOpiniones(final Context context) {

        final AdapterRecyclerViewOpiniones adapterRecyclerViewOpiniones = new AdapterRecyclerViewOpiniones(
                new ContenedorOpinion(new ArrayList<Opinion>()), FragmentOpiniones.this);
        recyclerView.setAdapter(adapterRecyclerViewOpiniones);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        OpinionController opinionController = new OpinionController();
        opinionController.obtenerOpiniones(context, new ResultListener<ContenedorOpinion>() {
            @Override
            public void finish(ContenedorOpinion resultado) {
                contenedorOpinion = resultado;
                adapterRecyclerViewOpiniones.setContenedorOpinion(contenedorOpinion);
            }
        });
    }

    public interface interfazFragmentOpiniones {
        void clickearonLaOpinion(Integer id);
        void clickearonEscribirOpinion();
    }

}
