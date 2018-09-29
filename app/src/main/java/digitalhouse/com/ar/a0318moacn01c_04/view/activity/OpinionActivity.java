package digitalhouse.com.ar.a0318moacn01c_04.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.controller.OpinionController;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Opinion;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

public class OpinionActivity extends AppCompatActivity {

    EditText editTextCuerpo;
    Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);

        editTextCuerpo = findViewById(R.id.edit_text_cuerpo);
        buttonEnviar = findViewById(R.id.button_enviar);


        final ResultListener<Boolean> escuchadorEscribirOpinion = new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                Toast.makeText(OpinionActivity.this, "Opinion escrita", Toast.LENGTH_LONG).show();
                OpinionActivity.this.finish();
            }
        };

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Opinion opinion = new Opinion   (FirebaseAuth.getInstance().getCurrentUser().getUid(),
                                                FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                                editTextCuerpo.getText().toString());
                OpinionController opinionController = new OpinionController();
                opinionController.escribirOpinion(OpinionActivity.this, opinion, escuchadorEscribirOpinion);
            }
        });
    }
}
