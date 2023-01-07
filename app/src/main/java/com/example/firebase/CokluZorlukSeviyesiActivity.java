package com.example.firebase;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CokluZorlukSeviyesiActivity extends AppCompatActivity {

    Button basla;
    CheckBox check1,check2,check3;
    FirebaseFirestore mFirestore;
    byte[] bytes;
    byte[] bytes1;
    ArrayList<Card> cards=new ArrayList<>();
    ArrayList<Bundle> bd=new ArrayList<>();
    int count;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coklu_zorluk_seviyesi);
        basla= findViewById(R.id.btn_Basla_1);
        check1 = findViewById(R.id.checkBox2_1);
        check2 = findViewById(R.id.checkBox2_2);
        check3 = findViewById(R.id.checkBox2_3);
        mFirestore = FirebaseFirestore.getInstance();

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check1.isChecked()) {
                    Toast.makeText(CokluZorlukSeviyesiActivity.this, "" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check2.isChecked()) {
                    Toast.makeText(CokluZorlukSeviyesiActivity.this, "" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check3.isChecked()) {
                    Toast.makeText(CokluZorlukSeviyesiActivity.this, "" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });



        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked() && !check2.isChecked() && !check3.isChecked()) {
                    startActivity(new Intent(CokluZorlukSeviyesiActivity.this, OyunOynamaCokluActivity.class));
                } else if (!check1.isChecked() && check2.isChecked() && !check3.isChecked()) {
                    startActivity(new Intent(CokluZorlukSeviyesiActivity.this, OyunOynamaCoklu4_4Activity.class));
                } else if (!check1.isChecked() && !check2.isChecked() && check3.isChecked()) {
                    startActivity(new Intent(CokluZorlukSeviyesiActivity.this, OyunOynamaCoklu6_6Activity.class));
                } else {
                    Toast.makeText(CokluZorlukSeviyesiActivity.this, "Birden fazla seçenek seçilemez.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

