package com.example.firebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tekli2_2Activity extends AppCompatActivity {

    ImageView curView=null;
    private int countPair=0;
    final int[] drawable=new int[4];
    FirebaseFirestore mFirestore;
    byte[] bytes;
    int sayi;
    int[] array2=new int[4];
    int[] pos={0,1,0,1};
    int currentPos=-1;
    int temp=0;
    List<Card> cards=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tekli22);
        GridView grid=findViewById(R.id.gridView);
        ImageAdapter img=new ImageAdapter(this);

        mFirestore.collection("CardCollection").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        bytes = Base64.decode((String) document.getData().get("Img2"), Base64.DEFAULT);
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        crd.card = bitmap1;
                        crd.Img = bitmap2;
                        cards.add(crd);
                        //img1.setImageBitmap(bitmap);*/
                    }
                    for (int i = 0; i < 2; i++) {
                        Random üret = new Random();
                        sayi = üret.nextInt(cards.size() + 1);
                        if (!cards.get(temp).House.equals(cards.get(sayi).House)) {
                            array2[0] = temp;
                            array2[1] = sayi;
                            array2[2] = temp;
                            array2[3] = sayi;
                            break;
                        } else {
                            temp = sayi;
                            continue;
                        }

                    }

                    Collections.shuffle(Arrays.asList(array2));
                    for(int i=0;i<4;i++){
                        drawable[i]=array2[i];
                    }

                }


            }

        });

        grid.setAdapter(img);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentPos<0){
                    currentPos=position;
                    curView=(ImageView) view;
                    ((ImageView)view).setImageBitmap(cards.get(drawable[pos[position]]).Img);
                }
                else {
                    if(currentPos==position){
                        ((ImageView)view).setImageBitmap(cards.get(0).card);
                    }
                    else if(pos[currentPos]!=pos[position]){
                        curView.setImageBitmap(cards.get(0).card);
                        Toast.makeText(Tekli2_2Activity.this, "Not Match", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ((ImageView)view).setImageBitmap(cards.get(drawable[pos[position]]).Img);
                        countPair++;
                        if(countPair==0){
                            Toast.makeText(Tekli2_2Activity.this, "You win", Toast.LENGTH_SHORT).show();
                        }
                    }
                    currentPos=-1;
                }
            }
        });
    }
}

