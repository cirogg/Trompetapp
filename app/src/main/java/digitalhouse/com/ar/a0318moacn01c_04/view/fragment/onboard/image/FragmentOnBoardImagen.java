package digitalhouse.com.ar.a0318moacn01c_04.view.fragment.onboard.image;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import digitalhouse.com.ar.a0318moacn01c_04.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOnBoardImagen extends Fragment {


    public FragmentOnBoardImagen() {
        // Required empty public constructor
    }

    static public FragmentOnBoardImagen crearFragmentOnBoardImagen(String titulo) {
        FragmentOnBoardImagen fragmentOnBoardImagen = new FragmentOnBoardImagen();
        Bundle unBundle = new Bundle();
        unBundle.putString("titulo", titulo );
        fragmentOnBoardImagen.setArguments(unBundle);
        return fragmentOnBoardImagen;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentViewOnBoard = inflater.inflate(R.layout.fragment_on_board, container, false);
        Bundle bundle = getArguments();
        String unTitulo = bundle.getString("titulo");

        View layoutPrincipal = fragmentViewOnBoard.findViewById(R.id.layoutPrincipalOnBoard);
       // layoutPrincipal.setBackgroundResource(imagen);


        return fragmentViewOnBoard;


    }

}
