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
import java.util.List;
import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterRecyclerViewNoticia;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

public class FragmentFeed extends Fragment implements AdapterRecyclerViewNoticia.ComunicacionAdapterRecyclerNoticia {

    private TapNoticia tapNoticia;
    private RecyclerView recyclerViewFeed;
    private FloatingActionButton floatingActionButton;
    public static List<Noticia> listaProvDeNoticias;

    public FragmentFeed() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.tapNoticia = (TapNoticia)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        floatingActionButton = view.findViewById(R.id.FABMain);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Proximamente",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewFeed = view.findViewById(R.id.contenedorMainRecycler);

        cargarNoticias(getContext());
        return view;
    }

    @Override
    public void seleccionaronLaCeldaFeedNoticia(Integer posicion, String urlNoticia) {
        tapNoticia.clickearonLaNoticia(posicion,urlNoticia);
    }

    private void cargarNoticias(final Context unContext){
        NoticiaController noticiaController = new NoticiaController();
        noticiaController.obtenerNoticias(new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                AdapterRecyclerViewNoticia adapterRecyclerViewNoticia = new AdapterRecyclerViewNoticia
                        (resultado,FragmentFeed.this);
                recyclerViewFeed.setAdapter(adapterRecyclerViewNoticia);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewFeed.setLayoutManager(layoutManager);
                listaProvDeNoticias = resultado;
            }
        }, unContext);
    }

    public interface TapNoticia{
        void clickearonLaNoticia(Integer posicion, String urlNoticia);
    }
}

