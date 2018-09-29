package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.ContenedorOpinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Usuario;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

/**
 * Created by DH on 10/7/2018.
 */

public class DAOOpinion {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static final String OPINIONES = "opiniones";

    public void obtenerOpiniones(final ResultListener<ContenedorOpinion> escuchadorDelController) {
        DatabaseReference reference = database.getReference();
        reference.child(OPINIONES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContenedorOpinion contenedorOpinion = new ContenedorOpinion(new ArrayList<Opinion>());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Opinion opinion = snapshot.getValue(Opinion.class);
                    contenedorOpinion.getListaDeOpiniones().add(opinion);
                }
                escuchadorDelController.finish(contenedorOpinion);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void escribirOpinion(Opinion opinion, final ResultListener<Boolean> escuchadorDelController) {
        DatabaseReference reference = database.getReference();

        reference.child(OPINIONES).push().setValue(opinion);
        escuchadorDelController.finish(Boolean.TRUE);
    }

}
