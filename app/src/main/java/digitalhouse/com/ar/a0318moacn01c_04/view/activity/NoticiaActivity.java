package digitalhouse.com.ar.a0318moacn01c_04.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterViewPagerNoticia;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentNoticiaDetalle;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentNoticiaDetalleURL;

public class NoticiaActivity extends AppCompatActivity implements FragmentNoticiaDetalle.TapVistaUrl {

    ViewPager viewPager;
    FragmentManager fragmentManager;
    private Toolbar myToolbar;
    public static String POSICION = "posicion";
    public static String URL_NOTICIA = "urlDeLaNoticia";
    public static String USUARIOS = "usuarios";
    public static String URL_FAVORITOS = "urlNoticiasFavoritas";


    public static MenuItem menuItemVistaAPI;

    private FragmentNoticiaDetalleURL fragmentNoticiaDetalleURL;
    private List<Noticia>listaDeNoticiasRecibidasDelControlador;
    private Noticia currentNoticia;
    AdapterViewPagerNoticia adapterViewPagerNoticia;
    public static LinearLayout linearLayoutContenedorURL;

    public static Integer intCOntenedr = R.id.contenedorNoticiaDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        Intent unIntent = getIntent();
        final Bundle unBundle = unIntent.getExtras();



        //ContenedorURL testing
        linearLayoutContenedorURL = findViewById(R.id.contenedorNoticiaDetalle);
        linearLayoutContenedorURL.animate().alpha(0.0f);
        linearLayoutContenedorURL.setVisibility(View.GONE);

        viewPager = findViewById(R.id.contenedorMainViewPager);
        fragmentManager = getSupportFragmentManager();
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar_detalle);
        setSupportActionBar(myToolbar);

        NoticiaController noticiaController = new NoticiaController();
        noticiaController.obtenerNoticias(new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                adapterViewPagerNoticia = new AdapterViewPagerNoticia(fragmentManager,resultado);
                viewPager.setAdapter(adapterViewPagerNoticia);
                viewPager.setCurrentItem(unBundle.getInt(POSICION));
                listaDeNoticiasRecibidasDelControlador = resultado;
                currentNoticia = resultado.get(unBundle.getInt(POSICION));
            }
        }, getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_detalle_fragment, menu);
        menuItemVistaAPI = menu.getItem(3);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.compartir:
                Toast.makeText(this,"compartiendo",Toast.LENGTH_SHORT).show();
                break;

            /*case R.id.guardarEnFavoritos:
                //Toast.makeText(this, "guardando", Toast.LENGTH_SHORT).show();
                currentNoticia = listaDeNoticiasRecibidasDelControlador.get(viewPager.getCurrentItem());
                //escribirNoticiasFavoritasEnFirebase(currentNoticia);
                NoticiaController noticiaController = new NoticiaController();
                noticiaController.favearNoticia(currentNoticia, getApplicationContext());
                Toast.makeText(NoticiaActivity.this, "Noticia guardada", Toast.LENGTH_SHORT).show();


                break;

            case R.id.crearOpinion:
                Toast.makeText(this, "creando opinion", Toast.LENGTH_SHORT).show();
                break;*/

            case R.id.ayuda:
                Toast.makeText(this, "aiuda!", Toast.LENGTH_SHORT).show();
                break;

           /* case R.id.toUrl:
                linearLayoutContenedorURL.animate().alpha(1.0f);
                String urlDeLaNoticia = listaDeNoticiasRecibidasDelControlador.get(viewPager.getCurrentItem()).getUrl();
                Bundle unBundle = new Bundle();
                unBundle.putString(URL_NOTICIA,urlDeLaNoticia);
                linearLayoutContenedorURL.setVisibility(View.VISIBLE);

                menuItemVistaAPI.setVisible(true);

                fragmentNoticiaDetalleURL = new FragmentNoticiaDetalleURL();
                fragmentNoticiaDetalleURL.setArguments(unBundle);
                cargarFragmentEnContenedorMain(fragmentNoticiaDetalleURL);
                break;*/

            case R.id.toVistaAPI:
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.remove(fragmentNoticiaDetalleURL);
                //menuItemVistaAPI.setVisible(false);

                linearLayoutContenedorURL.animate().alpha(0.0f);
                linearLayoutContenedorURL.setVisibility(View.GONE);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cargarFragmentEnContenedorMain(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.animation_bounce,R.anim.animation_slide_up);
        fragmentTransaction.replace(R.id.contenedorNoticiaDetalle, fragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

    @Override
    public void clickearonEnVerURL(String urlNoticia) {
        Bundle unBundle = new Bundle();
        unBundle.putString(URL_NOTICIA,urlNoticia);
        fragmentNoticiaDetalleURL = new FragmentNoticiaDetalleURL();
        fragmentNoticiaDetalleURL.setArguments(unBundle);
        cargarFragmentEnContenedorMain(fragmentNoticiaDetalleURL);
    }
}
