package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentNoticiaDetalle;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentNoticiaFavoritoDetalle;

/**
 * Created by ciro_ on 23/7/2018.
 */

public class AdapterViewPagerNoticiaFavorita extends FragmentStatePagerAdapter {

    List<Fragment> listaDeFragmentNoticiasFavoritas = new ArrayList<>();

    public AdapterViewPagerNoticiaFavorita(FragmentManager fm, List<NoticiaFavorita> listaDeNoticiasFavoritas) {
        super(fm);

        for (NoticiaFavorita noticiaDeLaListaFavorita : listaDeNoticiasFavoritas) {
            listaDeFragmentNoticiasFavoritas.add(FragmentNoticiaFavoritoDetalle.crearFragmentNoticiaFavoritaDetalle(listaDeNoticiasFavoritas.indexOf(noticiaDeLaListaFavorita)));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragmentNoticiasFavoritas.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragmentNoticiasFavoritas.size();
    }
}
