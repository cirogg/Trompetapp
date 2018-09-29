package digitalhouse.com.ar.a0318moacn01c_04.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Arrays;

import digitalhouse.com.ar.a0318moacn01c_04.R;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Noticia;
import digitalhouse.com.ar.a0318moacn01c_04.model.pojo.Usuario;
import digitalhouse.com.ar.a0318moacn01c_04.util.ResultListener;

public class LoginActivity extends AppCompatActivity {

    // Facebook
    private CallbackManager callbackManager;
    private static final String TAG = "FACELOG";
    private LoginButton loginButton;
    private Button loginButtonNormal;
    private static final String USUARIOS = "usuarios";

    private EditText usuario;
    private EditText contraseña;

    //FireBase
    private FirebaseAuth mAuth;
    private boolean estaLogueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButtonNormal = findViewById(R.id.fabLogin);
        usuario = findViewById(R.id.editTextUsuario);
        contraseña = findViewById(R.id.editTextContraseña);

        //Facebook
        callbackManager = CallbackManager.Factory.create();


        //todo Corregir la direccion de este button
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                Toast.makeText(LoginActivity.this, "login FACEBOOK", Toast.LENGTH_SHORT).show();
                // FireBase - Pasar Token de facebook
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

        loginButtonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString().isEmpty() || contraseña.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Por favor llene ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }else //Si los campos estan llenos
                {
                    existeElUsuario(usuario.getText().toString(), new ResultListener<Boolean>() {
                    @Override
                    public void finish(Boolean resultado) {
                        if (resultado){
                            Toast.makeText(LoginActivity.this, "Usuario ya existe, no grabara", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            FirebaseAuth.getInstance().signOut();
                            LoginManager.getInstance().logOut();
                            ArrayList<String> nuevasNoticiasFavoritas = new ArrayList<>();
                            escribirUserEnFirebase(usuario.getText().toString()+"0303",usuario.getText().toString() , nuevasNoticiasFavoritas);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        ////////Autentificador de Facebook w/ Firebase
        // Initialize Firebase Auth
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth = FirebaseAuth.getInstance();
        ///////Fin Autentificador de Facebook w/ Firebase

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            final String userNombre = user.getDisplayName();
                            final String userID = user.getUid();

                            //Chequea si el usuario ya existe.
                            existeElUsuario(userID, new ResultListener<Boolean>() {
                                @Override
                                public void finish(Boolean resultado) {
                                    if (resultado) { //Si existe el usuario
                                        Toast.makeText(LoginActivity.this, "Usuario Existente. No grabará", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else { //No exite el usuario
                                        ArrayList<String> nuevasNoticiasFavoritas = new ArrayList<>();
                                        escribirUserEnFirebase(userID, userNombre, nuevasNoticiasFavoritas);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    public void escribirUserEnFirebase(final String userID, final String userNombre, final ArrayList<String> urlNoticiasFavoritas) {

        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        final DatabaseReference referenciaUsuario = mDatabase.child(USUARIOS).child(userID);

        Usuario unUsuario = new Usuario(userID, userNombre, urlNoticiasFavoritas);
        referenciaUsuario.setValue(unUsuario);
        Toast.makeText(LoginActivity.this, "Usuario Nuevo Escrito", Toast.LENGTH_SHORT).show();
    }

    public void existeElUsuario(final String userID, final ResultListener<Boolean> booleanResultListener) {
        estaLogueado = false;
        final DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referenciaUsuario = mDatabase.child(USUARIOS);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (userID.equals(dataSnapshot1.getKey())) {
                        estaLogueado = true;
                        break;
                    }
                }
                booleanResultListener.finish(estaLogueado);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Error en metodo existeUsuario", Toast.LENGTH_SHORT).show();
            }
        };
        referenciaUsuario.addListenerForSingleValueEvent(valueEventListener);
    }
}
