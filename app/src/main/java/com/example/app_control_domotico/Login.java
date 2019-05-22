package com.example.app_control_domotico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txtEmail, txtPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se Genera la instancia de firebase Autn
        mAuth = FirebaseAuth.getInstance();
        //Set vaiables
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        progressDialog = new ProgressDialog(this);
    }

    //Chek if ready Login
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    //Redirecciona a la aplicacion si el login es correcto
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Toast.makeText(Login.this, "Bienvenido: " + name
                    +"\nCorreo: "+email, Toast.LENGTH_LONG).show();
            Intent MainActivityInt = new Intent(Login.this, MainActivity.class);
            MainActivityInt.putExtra("NAME",name);
            MainActivityInt.putExtra("EMAIL",email);
            startActivity(MainActivityInt);
            finish();
        }
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogin:
                //Invocamos al método:
                LoginUsuario();
                break;
        }


    }

    private void LoginUsuario() {
        String email = txtEmail.getText().toString().trim();
        if(TextUtils.isEmpty(email))
            return;
        String password = txtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(password))
            return;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
}
