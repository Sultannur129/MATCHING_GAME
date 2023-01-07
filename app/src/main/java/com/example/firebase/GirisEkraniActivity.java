package com.example.firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GirisEkraniActivity extends AppCompatActivity {

    EditText editUsername,editSifre;
    Button btnGirisYap;
    TextView txtHesapYok,txtVeri,SifremiUnuttum;
    FirebaseDatabase db;
    FirebaseAuth yetki;

    //Tutucular
    String usernameTutucu;
    String sifreTutucu;
    List<User> users=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekrani);
        editUsername=findViewById(R.id.edit_username);
        editSifre=findViewById(R.id.edit_sifre);
        btnGirisYap=findViewById(R.id.btn_girisYap);
        txtHesapYok=findViewById(R.id.txt_HesapYok);
        txtVeri=findViewById(R.id.txt_Veri);
        SifremiUnuttum=findViewById(R.id.txt_SifremiUnuttum);

        //Firebase'i başlat
        yetki=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();

        btnGirisYap.setOnClickListener(v ->  {


            usernameTutucu=editUsername.getText().toString();
            sifreTutucu=editSifre.getText().toString();

            if(usernameTutucu.isEmpty() || sifreTutucu.isEmpty()){
                Toast.makeText(GirisEkraniActivity.this,"Alanlar boş olamaz", Toast.LENGTH_LONG).show();
            }
            else{
                //giris yap
                girisYap();
                Toast.makeText(this, "Giriş Yapa girdi", Toast.LENGTH_SHORT).show();
            }
        });

        txtHesapYok.setOnClickListener(v -> startActivity(new Intent(GirisEkraniActivity.this,KaydolActivity.class)));
        SifremiUnuttum.setOnClickListener(v -> startActivity(new Intent(GirisEkraniActivity.this,SifreYenilemeActivity.class)));

    }

    private void girisYap() {

        DatabaseReference read= db.getReference("Kullanicilar");

        read.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot key:snapshot.getChildren()){
                    User user;
                    user=key.getValue(User.class);
                    users.add(user);


                }
                Toast.makeText(GirisEkraniActivity.this, "Datasnapshot fonksiyonunda", Toast.LENGTH_SHORT).show();

                for(User u:users){
                    if (u.Username.equals(usernameTutucu) && u.Password.equals(sifreTutucu)) {
                        startActivity(new Intent(GirisEkraniActivity.this, OyunEkraniActivity.class));
                        finish();
                        break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GirisEkraniActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });


    }
}

