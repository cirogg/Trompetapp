package digitalhouse.com.ar.a0318moacn01c_04.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterViewPagerOnBoard;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.onboard.image.FragmentOnBoardImagen1;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.onboard.image.FragmentOnBoardImagen2;
import digitalhouse.com.ar.a0318moacn01c_04.view.fragment.onboard.image.FragmentOnBoardImagen3;

public class ActivityOnBoard extends AppCompatActivity {

    private static final String USUARIO_APRENDIO = "usuario aprendio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        FragmentManager fm = getSupportFragmentManager();
        List<android.support.v4.app.Fragment> fragmentOnBoardImagenes = new ArrayList<>();
        fragmentOnBoardImagenes.add(new FragmentOnBoardImagen1());
        fragmentOnBoardImagenes.add(new FragmentOnBoardImagen2());
        fragmentOnBoardImagenes.add(new FragmentOnBoardImagen3());
        AdapterViewPagerOnBoard adapterViewPagerOnBoard = new AdapterViewPagerOnBoard(fm, fragmentOnBoardImagenes);
        ViewPager viewPager = findViewById(R.id.ViewPagerOnBoard);
        viewPager.setAdapter(adapterViewPagerOnBoard);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);
        ViewPager mImageViewPager = (ViewPager) findViewById(R.id.ViewPagerOnBoard);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mImageViewPager, true);
    }

    public void Comenzar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String usuarioAprendio = "usuarioAprendio";
        Bundle bundleOnBoard = new Bundle();
        bundleOnBoard.putString(USUARIO_APRENDIO, usuarioAprendio);
        intent.putExtras(bundleOnBoard);
        startActivity(intent);
    }


}

