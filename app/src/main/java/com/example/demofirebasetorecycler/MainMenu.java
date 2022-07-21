package com.example.demofirebasetorecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button btnaddemployee, btnviewinfo,btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnaddemployee=findViewById(R.id.btnaddemployee);
        btnviewinfo=findViewById(R.id.btnviewinfo);
        btnlogout=findViewById(R.id.btnlogout);

        btnaddemployee.setOnClickListener(view ->{
            startActivity(new Intent(MainMenu.this,InsertEmployees.class));
        });
        btnviewinfo.setOnClickListener(view ->{
            startActivity(new Intent(MainMenu.this,MainActivity.class));
        });
        btnlogout.setOnClickListener(view -> {
            startActivity(new Intent(MainMenu.this,LoginActivity.class));
        });

    }
}