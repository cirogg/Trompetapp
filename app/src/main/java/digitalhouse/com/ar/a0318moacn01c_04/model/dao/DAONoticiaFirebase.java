package digitalhouse.com.ar.a0318moacn01c_04.model.dao;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;
import digitalhouse.com.ar.a0318moacn01c_04.view.activity.NoticiaActivity;

/**
 * Created by DH on 20/7/2018.
 */

public class DAONoticiaFirebase {

    public void favearNoticiaEnFirebase(Noticia noticia, final ResultListener<Boolean> escuchadorBoolDelControlador){


        DatabaseReference mDatabase;
        String firebaseUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referenciaUsuario = mDatabase.child(NoticiaActivity.USUARIOS).child(firebaseUserId).child(NoticiaActivity.URL_FAVORITOS).push();

        referenciaUsuario.setValue(noticia).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                escuchadorBoolDelControlador.finish(true);

            }
        });

    }

}
