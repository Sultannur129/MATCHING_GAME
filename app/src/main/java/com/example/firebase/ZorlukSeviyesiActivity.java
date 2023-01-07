package com.example.firebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class ZorlukSeviyesiActivity extends AppCompatActivity {

    Button basla;
    CheckBox check1,check2,check3;
    FirebaseFirestore mFirestore;
    byte[] bytes;
    byte[] bytes1;
    ArrayList<Card> cards=new ArrayList<>();
    ArrayList<Bundle> bd=new ArrayList<>();
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zorluk_seviyesi);
        basla = findViewById(R.id.btn_Basla);
        check1 = findViewById(R.id.checkBox1);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);
        mFirestore = FirebaseFirestore.getInstance();
        OyunOynamaActivity oy=new OyunOynamaActivity();
        //cards.clear();

        /*mFirestore.collection("CardCollection").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Card crd = new Card();
                        crd.Id = Integer.parseInt(String.valueOf(document.getData().get("Id")));
                        crd.Name = (String) document.getData().get("Name");
                        crd.House = (String) document.getData().get("House");
                        crd.Point = Float.parseFloat(String.valueOf(document.getData().get("Point")));

                        bytes = Base64.decode((String) document.getData().get("Img1"), Base64.DEFAULT);
                        Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        bytes1 = Base64.decode((String) document.getData().get("Img2"), Base64.DEFAULT);
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
                        crd.card = bitmap1;
                        crd.Img = bitmap2;
                        cards.add(crd);
                        //oy.cards1.add(crd);
                        count++;
                        //img1.setImageBitmap(bitmap);
                    }
                }
            }
        });*/


        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check1.isChecked()) {
                    Toast.makeText(ZorlukSeviyesiActivity.this, "" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check2.isChecked()) {
                    Toast.makeText(ZorlukSeviyesiActivity.this, "" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check3.isChecked()) {
                    Toast.makeText(ZorlukSeviyesiActivity.this, "" + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });



              basla.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (check1.isChecked() && !check2.isChecked() && !check3.isChecked()) {
                          startActivity(new Intent(ZorlukSeviyesiActivity.this, OyunOynamaActivity.class));
                      } else if (!check1.isChecked() && check2.isChecked() && !check3.isChecked()) {
                          startActivity(new Intent(ZorlukSeviyesiActivity.this, OyunOynama4_4Activity.class));
                      } else if (!check1.isChecked() && !check2.isChecked() && check3.isChecked()) {
                          startActivity(new Intent(ZorlukSeviyesiActivity.this, OyunOynama6_6Activity.class));
                      } else {
                          Toast.makeText(ZorlukSeviyesiActivity.this, "Birden fazla seçenek seçilemez.", Toast.LENGTH_SHORT).show();
                      }
                  }
              });


    }
}


