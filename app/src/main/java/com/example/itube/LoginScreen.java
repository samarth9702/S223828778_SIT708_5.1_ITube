package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {
    Button btnLogin, btnReg;
    TextInputEditText lEmail, lPassword;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
            intent.putExtra("user",currentUser);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        btnLogin = findViewById(R.id.btn_login);
        lEmail = findViewById(R.id.login_email);
        lPassword = findViewById((R.id.login_password));
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.login_progressbar);
        btnReg = findViewById(R.id.registerNow);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,RegisterScreen.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;

                email = String.valueOf(lEmail.getText());
                password = String.valueOf(lPassword.getText());

                if(TextUtils.isEmpty(email)){
                    lEmail.setError("Please enter email");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    lPassword.setError("Please enter password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginScreen.this, "Login Successful.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                    intent.putExtra("user",user);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginScreen.this, task.getException().getLocalizedMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}