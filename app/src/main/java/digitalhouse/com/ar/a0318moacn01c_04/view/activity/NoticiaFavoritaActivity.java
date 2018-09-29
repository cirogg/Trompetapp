package digitalhouse.com.ar.a0318moacn01c_04.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterViewPagerNoticiaFavorita;

import static digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity.POSICION;

public class NoticiaFavoritaActivity extends AppCompatActivity {

    ViewPager viewPager;
    FragmentManager fragmentManager;
    AdapterViewPagerNoticiaFavorita adapterViewPagerNoticiaFavorita;
    private List<NoticiaFavorita> listaDeNoticiasRecibidasDelControlador;
    NoticiaFavorita currentNoticiaFavorita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_favorita);
        Intent unIntent = getIntent();
        final Bundle unBundle = unIntent.getExtras();

        viewPager = findViewById(R.id.contenedorMainViewPagerFavorita);
        fragmentManager = getSupportFragmentManager();

        NoticiaController noticiaController = new NoticiaController();
        listaDeNoticiasRecibidasDelControlador = noticiaController.obtenerNoticiasFavoritas(getApplicationContext());
        adapterViewPagerNoticiaFavorita = new AdapterViewPagerNoticiaFavorita(fragmentManager,listaDeNoticiasRecibidasDelControlador);
        viewPager.setAdapter(adapterViewPagerNoticiaFavorita);
        currentNoticiaFavorita = listaDeNoticiasRecibidasDelControlador.get(unBundle.getInt(POSICION));
        viewPager.setCurrentItem(unBundle.getInt(POSICION));

    }
}
