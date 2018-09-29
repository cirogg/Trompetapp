package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import digitalhouse.com.ar.a0318moacn01c_04.R;

public class FragmentHomeNoticia extends Fragment {


    public FragmentHomeNoticia() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_noticia, container, false);
    }

}
