package com.example.demofirebasetorecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText remail;
    TextInputEditText rpassword;
    TextView proceedlog;
    Button btnregister;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        remail = findViewById(R.id.remail);
        rpassword = findViewById(R.id.rpassword);
        proceedlog = findViewById(R.id.proceedlog);
        btnregister = findViewById(R.id.btnregister);

        mAuth = FirebaseAuth.getInstance();

        btnregister.setOnClickListener(view ->{
            createUser();
        });

        proceedlog.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void createUser(){
        String email = remail.getText().toString();
        String password = rpassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            remail.setError("Email cannot be empty");
            remail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            rpassword.setError("Password cannot be empty");
            rpassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}