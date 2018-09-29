package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.NewsHelper;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterRecyclerViewTitulares;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterViewPagerTitulares;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class FragmentTitularesContenedor extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AdapterViewPagerTitulares adapterViewPagerTitulares;
    public FragmentTitularesContenedor() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titulares_contenedor, container, false);
        viewPager = view.findViewById(R.id.contenedorViewPagerTitular);
        tabLayout = view.findViewById(R.id.tabViewPager);
        tabLayout.setTabMode(MODE_SCROLLABLE);
        cargarNoticias(getContext());

        return view;
    }

    private void cargarNoticias(final Context unContext){
        NoticiaController noticiaController = new NoticiaController();
        noticiaController.obtenerNoticias(new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {

                List<String>titulos = new ArrayList<>();
                titulos.add("Negocios");
                titulos.add("Entretenimiento");
                titulos.add("Juegos");
                titulos.add("General");
                titulos.add("Musica");
                titulos.add("Ciencia");
                titulos.add("Deporte");
                titulos.add("tecnologia");

                List<String> categorias = new ArrayList<>();
                categorias.add(NewsHelper.category_BUSINESS);
                categorias.add(NewsHelper.category_ENTERTAINMENT);
                categorias.add(NewsHelper.category_GAMING);
                categorias.add(NewsHelper.category_GENERAL);
                categorias.add(NewsHelper.category_MUSIC);
                categorias.add((NewsHelper.category_SCIENCENATURE));
                categorias.add((NewsHelper.category_SPORT));
                categorias.add((NewsHelper.category_TECHNOLOGY));

                adapterViewPagerTitulares =  new AdapterViewPagerTitulares(getChildFragmentManager(),resultado, titulos, categorias);
                viewPager.setAdapter(adapterViewPagerTitulares);
                tabLayout.setupWithViewPager(viewPager);
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    tabLayout.getTabAt(i).setText(titulos.get(i));
                }
            }
        }, unContext);
    }
}
