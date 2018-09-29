package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentNoticiaDetalle;

public class AdapterViewPagerNoticia extends FragmentStatePagerAdapter {

    List<Fragment> listaDeFragmentNoticias = new ArrayList<>();


    public AdapterViewPagerNoticia(FragmentManager fm, List<Noticia> listaDeNoticias) {
        super(fm);

        for (Noticia noticiaDeLaLista : listaDeNoticias) {
            listaDeFragmentNoticias.add(FragmentNoticiaDetalle.crearFragmentNoticiaDetalle(listaDeNoticias.indexOf(noticiaDeLaLista)));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragmentNoticias.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragmentNoticias.size();
    }

    public List<Fragment> getListaDeFragmentNoticias() {
        return listaDeFragmentNoticias;
    }
}
