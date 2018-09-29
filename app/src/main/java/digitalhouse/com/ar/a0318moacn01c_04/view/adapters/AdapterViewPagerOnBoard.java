package digitalhouse.com.ar.a0318moacn01c_04.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerOnBoard extends FragmentStatePagerAdapter {

    private List<Fragment> listaDeFragmentOnBoardImagen = new ArrayList<>();

    public AdapterViewPagerOnBoard(FragmentManager fm, List<Fragment> listaDeFragmentOnBoardImagen){
        super(fm);
        this.listaDeFragmentOnBoardImagen = listaDeFragmentOnBoardImagen;

        //for (Fragment unFragmentOnBoardImagen:listaDeFragmentOnBoardImagen)
            //this.listaDeFragmentOnBoardImagen.add(FragmentOnBoardImagen.crearFragmentOnBoardImagen("titulo"));
    }

    @Override
    public Fragment getItem(int position) {
        return this.listaDeFragmentOnBoardImagen.get(position);
    }
    @Override
    public int getCount() {
        return this.listaDeFragmentOnBoardImagen.size();
    }





}

