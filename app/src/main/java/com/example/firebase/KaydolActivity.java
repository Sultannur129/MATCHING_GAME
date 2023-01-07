package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class KaydolActivity extends AppCompatActivity {

    EditText editUsername,editEmail,editSifre;
    Button btnKaydol;
    TextView txtHesapVar;
    private DatabaseReference mreference;
    private HashMap<String,Object> mData;
    private FirebaseUser mUser;
    FirebaseDatabase db;
    //Firebase
    FirebaseAuth yetki;

    //Tutucular
    String emailTutucu;
    String sifreTutucu;
    String kullaniciAdiTutucu;
    int say=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        //Firebase'i başlat
        yetki=FirebaseAuth.getInstance();
        mreference= FirebaseDatabase.getInstance().getReference();
        db=FirebaseDatabase.getInstance();

        editUsername=findViewById(R.id.edit_usernamek);
        editEmail=findViewById(R.id.edit_emailk);
        editSifre=findViewById(R.id.edit_sifrek);
        btnKaydol=findViewById(R.id.btn_Kaydol);
        txtHesapVar=findViewById(R.id.txt_HesapVar);

        txtHesapVar.setOnClickListener(v -> {
            startActivity(new Intent(KaydolActivity.this,GirisEkraniActivity.class));
            finish();
        });

        btnKaydol.setOnClickListener(v -> {

            emailTutucu=editEmail.getText().toString();
            sifreTutucu=editSifre.getText().toString();
            kullaniciAdiTutucu=editUsername.getText().toString();

            if(emailTutucu.isEmpty() || sifreTutucu.isEmpty() || kullaniciAdiTutucu.isEmpty() ){

                Toast.makeText(KaydolActivity.this,"Alanlar boş olamaz", Toast.LENGTH_LONG).show();
            }
            else{
                //Kaydolsun Yap
                kaydol();
            }
        });
    }

    private void kaydol() {

                DatabaseReference ref=db.getReference("Kullanicilar");
                String key=ref.push().getKey();
                DatabaseReference rf=db.getReference("Kullanicilar/"+key);
                List<User> users=new ArrayList<>();
                say=0;
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snap:snapshot.getChildren()){
                            User u=new User();
                            u=snap.getValue(User.class);
                            users.add(u);
                            if(u.Username.equals(kullaniciAdiTutucu)){
                                say+=1;

                            }



                        }

                         if(say ==0){
                             rf.setValue(new User(kullaniciAdiTutucu,sifreTutucu,emailTutucu));

                             Toast.makeText(KaydolActivity.this, "Başarıyla kayıt işlemi gerçekleştirildi.", Toast.LENGTH_SHORT).show();
                         }
                         else{
                             Toast.makeText(KaydolActivity.this, "Bu kullanıcı adı kullanılmıştır.", Toast.LENGTH_SHORT).show();
                         }

                        }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(KaydolActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

    }



    }

