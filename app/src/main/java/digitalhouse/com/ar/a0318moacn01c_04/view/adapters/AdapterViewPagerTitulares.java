package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.FragmentTitularFeed;

public class AdapterViewPagerTitulares extends FragmentStatePagerAdapter  {


    private List<Fragment> fragmentos = new ArrayList<>();
    private List<String> titulos = new ArrayList<>();

    public AdapterViewPagerTitulares(FragmentManager fm, List<Noticia> listaDeNoticias, List<String> titulos, List<String> listaDeCategorias) {
        super(fm);
        this.titulos = titulos;
        for (String categoria : listaDeCategorias) {
            fragmentos.add(FragmentTitularFeed.crearFragmentOpinionDetalle(categoria));
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }
}
