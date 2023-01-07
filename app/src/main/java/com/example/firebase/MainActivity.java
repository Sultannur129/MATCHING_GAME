package com.example.firebase;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editVeri;
    Button btnGonder, btnOku,btnCikis;
    TextView txtGoster;
    //veri tabanı aç
    FirebaseDatabase veritabani;
    //veri yolu
    DatabaseReference veriYolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editVeri = findViewById(R.id.edit_veri);
        btnGonder = findViewById(R.id.btn_gonder);
        btnOku = findViewById(R.id.btn_oku);
        txtGoster = findViewById(R.id.txt_oku);
        btnCikis=findViewById(R.id.btn_cikis);
        veritabani = FirebaseDatabase.getInstance();
        veriYolu = veritabani.getReference("Veriler");

        btnGonder.setOnClickListener(v -> {
            String veriTutucu = editVeri.getText().toString();

            //veri tabanının değerlerini ayarla
            veriYolu.setValue(veriTutucu);
        });

        btnOku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read from the database
                veriYolu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot veriTabaniVerisi) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        //Veri tabanı değerini veri tutucuda saklar.
                        String degerTutucu = veriTabaniVerisi.getValue(String.class);

                        txtGoster.setText(degerTutucu);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(MainActivity.this,""+error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,GirisEkraniActivity.class));
                finish();
            }
        });
    }
}


