package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SifreYenilemeActivity extends AppCompatActivity {

    EditText kullaniciAdi,Email,Sifre1,Sifre2;
    Button SifreDegis;
    String emailTut,kullaniciAdiTut,sifre1Tut,sifre2Tut;
    FirebaseDatabase db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_yenileme);
        kullaniciAdi=findViewById(R.id.edit_usernameD);
        Email=findViewById(R.id.edit_emailD);
        Sifre1=findViewById(R.id.edit_sifre1);
        Sifre2=findViewById(R.id.edit_sifre2);
        SifreDegis=findViewById(R.id.btn_SifreDegistir);

        db=FirebaseDatabase.getInstance();

        SifreDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTut=Email.getText().toString();
                kullaniciAdiTut=kullaniciAdi.getText().toString();
                sifre1Tut=Sifre1.getText().toString();
                sifre2Tut=Sifre2.getText().toString();
                UpdatePassword();
            }
        });


    }

    private void UpdatePassword() {
        DatabaseReference ref =db.getReference("Kullanicilar");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot key:snapshot.getChildren()){
                    User use=new User();
                    use=key.getValue(User.class);
                    if(use.Username.equals(kullaniciAdiTut) && use.Email.equals(emailTut)){
                        if(sifre1Tut.equals(sifre2Tut)){
                            use.Email=emailTut;
                            use.Password=sifre1Tut;
                            use.Username=kullaniciAdiTut;
                            String key1=key.getKey();
                            DatabaseReference ref1 =db.getReference("Kullanicilar").child(key1);
                            ref1.setValue(use);
                            Toast.makeText(SifreYenilemeActivity.this, "Şifre Başarıyla Değiştirildi.", Toast.LENGTH_SHORT).show();


                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SifreYenilemeActivity.this, "Okuyamadı", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

