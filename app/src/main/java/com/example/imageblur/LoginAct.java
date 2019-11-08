package com.example.imageblur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAct extends AppCompatActivity {
    TextView txtRegister;
    EditText email, password;
    Button lgButton;
    private FirebaseAuth mAuth;
    String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.lgnEmail);
        password = findViewById(R.id.lgnPass);
        txtRegister = findViewById(R.id.lgnDontHave);
        lgButton = findViewById(R.id.lgnBtn);

        mAuth = FirebaseAuth.getInstance();

        lgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String lEmail = email.getText().toString();
            String lPass = password.getText().toString();

                mAuth.signInWithEmailAndPassword(lEmail, lPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent mvAct = new Intent(LoginAct.this, MainActivity.class);
                                    startActivity(mvAct);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginAct.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(LoginAct.this, RegisterAct.class);
                startActivity(reg);
            }
        });


    }
}
