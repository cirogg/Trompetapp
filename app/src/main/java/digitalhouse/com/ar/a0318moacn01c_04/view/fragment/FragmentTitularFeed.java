package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterRecyclerViewTitulares;

public class FragmentTitularFeed extends Fragment implements AdapterRecyclerViewTitulares.ComunicacionAdapterRecyclerTitular{
    private static final String CATEGORIA = "CATEGORIA";
    private TapTitular tapTitular;
    private RecyclerView recyclerView;
    private AdapterRecyclerViewTitulares adapterRecyclerViewTitulares;
    private String categoria;

    public FragmentTitularFeed() {
        // Required empty public constructor
    }

    public static FragmentTitularFeed crearFragmentOpinionDetalle(String categoria) {
        FragmentTitularFeed fragmentTitular = new FragmentTitularFeed();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORIA, categoria);
        fragmentTitular.setArguments(bundle);
        return fragmentTitular;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.tapTitular = (TapTitular) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        categoria = bundle.getString(CATEGORIA);

        View view = inflater.inflate(R.layout.fragment_titulares_feed, container, false);
        recyclerView = view.findViewById(R.id.contenedorRecyclerViewTitulares);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        List<Noticia> listaDeNoticiasDelAdapter = new ArrayList<>();
        adapterRecyclerViewTitulares = new AdapterRecyclerViewTitulares(listaDeNoticiasDelAdapter,this);
        recyclerView.setAdapter(adapterRecyclerViewTitulares);
        cargarNoticias(categoria);

        return view;
    }

    private void cargarNoticias(String categoria) {
        ResultListener<List<Noticia>> escuchadorVista = new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                adapterRecyclerViewTitulares.setList(resultado);
            }
        };
        NoticiaController noticiaController = new NoticiaController();
        noticiaController.obtenerNoticiasPorCategoria(categoria, escuchadorVista, getContext());
    }

    @Override
    public void seleccionaronLaCeldaTitular(Integer posicion, String urlNoticia) {
        tapTitular.clickearonTitular(posicion,urlNoticia);
    }

    public interface TapTitular{
        void clickearonTitular(Integer posicion, String urlNoticia);
    }
}
