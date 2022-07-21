package com.example.demofirebasetorecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText lemail;
    TextInputEditText lpassword;
    TextView proceedreg;
    Button btnlogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemail = findViewById(R.id.lemail);
        lpassword = findViewById(R.id.lpassword);
        proceedreg = findViewById(R.id.proceedreg);
        btnlogin = findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(view -> {
            loginUser();
        });
        proceedreg.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){
        String email = lemail.getText().toString();
        String password = lpassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            lemail.setError("Email cannot be empty");
            lemail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            lpassword.setError("Password cannot be empty");
            lpassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainMenu.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}