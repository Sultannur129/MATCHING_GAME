package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OyunEkraniActivity extends AppCompatActivity {
    Button tekliOyuncu,CokluOyuncu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);
        tekliOyuncu=findViewById(R.id.btn_TekliOyuncu);
        CokluOyuncu=findViewById(R.id.btn_CokluOyuncu);

        tekliOyuncu.setOnClickListener(v -> startActivity(new Intent(OyunEkraniActivity.this,ZorlukSeviyesiActivity.class)));

        CokluOyuncu.setOnClickListener(v -> startActivity(new Intent(OyunEkraniActivity.this,CokluZorlukSeviyesiActivity.class)));
    }
}

