package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoticiaDetalleURL extends Fragment {

    WebView webView;

    public FragmentNoticiaDetalleURL() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticia_detalle_url, container, false);
        Bundle bundle = getArguments();

        String urlMostrar = bundle.getString("urlDeLaNoticia");
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.animationLottieCargandoVistaUrl);
        //lottieAnimationView.playAnimation();


        webView = view.findViewById(R.id.webViewNoticia);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVisibility(View.GONE);
        //webView.setWebViewClient(new WebViewClient());
        //TODO CHEQUEAR ESTO.
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(webView, url);

                webView.setVisibility(View.VISIBLE);
            }

        });

        webView.loadUrl(urlMostrar);

        NoticiaActivity.menuItemVistaAPI.setVisible(true);




        //TextView textView = view.findViewById(R.id.textViewUrlDeLaNoticia);
        //textView.setText(urlMostrar.toString());



        return view;
    }



}
