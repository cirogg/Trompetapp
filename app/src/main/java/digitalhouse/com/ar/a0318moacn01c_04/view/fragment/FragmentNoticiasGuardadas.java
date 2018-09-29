package digitalhouse.com.ar.a0318moacn01c_04.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.NoticiaController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.NoticiaFavorita;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterRecyclerViewNoticia;
import digitalhouse.com.ar.a0318moacn01c_04.view.adapters.AdapterRecyclerViewNoticiaFavoritas;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoticiasGuardadas extends Fragment implements AdapterRecyclerViewNoticiaFavoritas.ComunicacionAdapterRecyclerNoticiaFavorita {

    private RecyclerView recyclerViewFeed;
    List<NoticiaFavorita> arrayDeNoticiasFavoritasAMostrar;

    private TapNoticiaFavorita tapNoticiaFavorita;

    public FragmentNoticiasGuardadas() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.tapNoticiaFavorita = (TapNoticiaFavorita) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias_guardadas, container, false);

        //BUSCO EL RW
        recyclerViewFeed = view.findViewById(R.id.recyclerViewNoticiasGuardadas);
        arrayDeNoticiasFavoritasAMostrar = obtenerNoticiasFavoritasDelUsuarioRoom();
        AdapterRecyclerViewNoticiaFavoritas adapterRecyclerViewNoticiaFavoritas = new AdapterRecyclerViewNoticiaFavoritas(arrayDeNoticiasFavoritasAMostrar,FragmentNoticiasGuardadas.this);
        recyclerViewFeed.setAdapter(adapterRecyclerViewNoticiaFavoritas);
        //CREAMOS Y SETEAMOS layoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewFeed.setLayoutManager(layoutManager);




        /*DatabaseReference mDatabase;

        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference databaseReference = mDatabase.child("usuarios").child(userID).child("urlNoticiasFavoritas");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayDeNoticiasFavoritasAMostrar = obtenerNoticiasFavoritasDelUsuarioDeFirebase(dataSnapshot,userID);

                AdapterRecyclerViewNoticiaFavoritas adapterRecyclerViewNoticiaFavoritas = new AdapterRecyclerViewNoticiaFavoritas(arrayDeNoticiasFavoritasAMostrar,FragmentNoticiasGuardadas.this);
                recyclerViewFeed.setAdapter(adapterRecyclerViewNoticiaFavoritas);

                //CREAMOS Y SETEAMOS layoutManager
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewFeed.setLayoutManager(layoutManager);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);*/
        return view;
    }

    public ArrayList<Noticia> obtenerNoticiasFavoritasDelUsuarioDeFirebase(DataSnapshot dataSnapshot, String idDeUsuario) {
        ArrayList<Noticia> noticiasARetornar= new ArrayList<>();
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            Noticia noticia = dataSnapshot1.getValue(Noticia.class);
            noticiasARetornar.add(noticia);
        }
        return noticiasARetornar;
    }

    public List<NoticiaFavorita> obtenerNoticiasFavoritasDelUsuarioRoom(){
        NoticiaController noticiaController = new NoticiaController();
        return noticiaController.obtenerNoticiasFavoritas(getContext());
    }

    @Override
    public void seleccionaronLaCelda(Integer posicion, String urlNoticia) {
        tapNoticiaFavorita.clickearonLaNoticiaFavorita(posicion,urlNoticia);
    }

    public interface TapNoticiaFavorita{
        void clickearonLaNoticiaFavorita(Integer posicion, String urlNoticia);
    }
}
