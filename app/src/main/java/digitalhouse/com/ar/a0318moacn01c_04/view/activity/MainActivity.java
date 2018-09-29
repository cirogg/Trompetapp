package digitalhouse.com.ar.a0318moacn01c_04.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentNoticiasGuardadas;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentOpinionDetalle;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentOpiniones;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentFeed;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentTitularFeed;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentTitularesContenedor;

public class MainActivity extends AppCompatActivity implements FragmentFeed.TapNoticia,
        FragmentOpiniones.interfazFragmentOpiniones, FragmentNoticiasGuardadas.TapNoticiaFavorita,
        FragmentOpinionDetalle.interfazFragmentOpinionDetalle, FragmentTitularFeed.TapTitular {

    private static final String TITULARES_TITULO = "TITULARES";
    private static final String OPINIONES_TITULO = "OPINIONES";
    private static final String FAVORITOS_TTULO = "FAVORITOS";
    private static final String HOME_TITULO = "TROMPETA";
    public static String POSICION = "posicion";
    public static String URL = "url";
    private static final String ID_OPINION = "id";
    private FragmentManager fragmentManager;
    private FragmentTitularFeed  fragmentTitularFeed;
    //NavigationBottom
    private Integer itemIdPreviamenteSeleccionado;
    // App bar
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // App bar
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        fragmentManager = getSupportFragmentManager();
        FragmentFeed fragmentFeed = new FragmentFeed();
        cargarFragmentEnContenedorMain(fragmentFeed);

        //Navigation Chino Bar
        BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        MenuItem menuItemHome = bnve.getMenu().findItem(R.id.homeFeed);
        itemIdPreviamenteSeleccionado = menuItemHome.getItemId();
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.titulares:
                        if (itemIdPreviamenteSeleccionado != item.getItemId()) {
                            cargarFragmentEnContenedorMain(fragmentTitularFeed = new FragmentTitularFeed());
                            itemIdPreviamenteSeleccionado = item.getItemId();
                            myToolbar.getMenu().getItem(0).setVisible(true);
                            myToolbar.getMenu().getItem(1).setVisible(true);
                            myToolbar.getMenu().getItem(2).setVisible(true);
                            myToolbar.getMenu().getItem(3).setVisible(true);
                            myToolbar.setTitle(TITULARES_TITULO);
                        }
                        break;

                    case R.id.opiniones:
                        if (itemIdPreviamenteSeleccionado != item.getItemId()) {
                            cargarFragmentEnContenedorMain(new FragmentOpiniones());
                            itemIdPreviamenteSeleccionado = item.getItemId();
                            myToolbar.getMenu().getItem(0).setVisible(true);
                            myToolbar.getMenu().getItem(1).setVisible(true);
                            myToolbar.getMenu().getItem(2).setVisible(true);
                            myToolbar.getMenu().getItem(3).setVisible(true);
                            myToolbar.setTitle(OPINIONES_TITULO);
                        }
                        break;

                    case R.id.favoritos:
                        if (itemIdPreviamenteSeleccionado != item.getItemId()) {
                            cargarFragmentEnContenedorMain(new FragmentNoticiasGuardadas());
                            itemIdPreviamenteSeleccionado = item.getItemId();
                            cargarFragmentEnContenedorMain(new FragmentNoticiasGuardadas());
                            myToolbar.setTitle(FAVORITOS_TTULO);
                        }
                        break;

                    case R.id.homeFeed:
                        if (itemIdPreviamenteSeleccionado != item.getItemId()) {
                            cargarFragmentEnContenedorMain(new FragmentFeed());
                            itemIdPreviamenteSeleccionado = item.getItemId();
                            myToolbar.setTitle(HOME_TITULO);
                        }

                        break;
                }
                return true;
            }
        });
        // Buttom Navigation View Chino - Configuraciones segun documentacion
        bnve.enableAnimation(true);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextSize(10);
        bnve.setIconSize(20, 25);
    }

    @Override
    public void clickearonLaNoticia(Integer posicion, String urlNoticia) {
        Intent unIntent = new Intent(this, NoticiaActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putInt(POSICION, posicion);
        unBundle.putString(URL, urlNoticia);
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }

    @Override
    public void clickearonLaOpinion(Integer id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_OPINION, id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentOpinionDetalle fragmentOpinionDetalle = new FragmentOpinionDetalle();
        fragmentOpinionDetalle.setArguments(bundle);
        fragmentTransaction.replace(R.id.contenedorMainRecycler, fragmentOpinionDetalle);
        fragmentTransaction.commit();
    }

    @Override
    public void clickearonEscribirOpinion() {
        Intent intent = new Intent(this, OpinionActivity.class);
        startActivity(intent);
    }

    // App bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    // App bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.miPerfil:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.buscador:
                Toast.makeText(this, "yendo a buscador", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cargarFragmentEnContenedorMain(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.animation_bounce,R.anim.animation_slide_up);
        fragmentTransaction.replace(R.id.contenedorMainRecycler, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void clickearonLaNoticiaFavorita(Integer posicion, String urlNoticia) {
        Intent unIntent = new Intent(this, NoticiaFavoritaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(URL,urlNoticia);
        bundle.putInt(POSICION,posicion);
        unIntent.putExtras(bundle);
        startActivity(unIntent);

    }

    @Override
    public void clickearonShare(Opinion opinion) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "¡Mirá esta opinión!");
        share.putExtra(Intent.EXTRA_TEXT, opinion.getCuerpo());
        startActivity(Intent.createChooser(share, "Share link!"));
    }

    @Override
    public void clickearonTitular(Integer posicion, String urlNoticia) {
        // Ir al detalle de titularfeed
        Bundle bundle = new Bundle();
        bundle.putString(URL,urlNoticia);
        bundle.putInt(POSICION,posicion);
        fragmentTitularFeed.setArguments(bundle);

    }
}

