package com.example.firebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OyunOynamaCoklu4_4Activity extends AppCompatActivity {

    FirebaseFirestore mfirestore;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16;
    TextView sure, skor1,skor2;
    SwitchCompat anah;
    MediaPlayer player, player2, player3, player4;
    byte[] bytes;
    byte[] bytes1;

    Integer[] cardsArray;
    ArrayList<Card> cards = new ArrayList<>();

    ArrayList<Card> gryffindor = new ArrayList<>();
    ArrayList<Card> slytherin = new ArrayList<>();
    ArrayList<Card> hufflepuff = new ArrayList<>();
    ArrayList<Card> ravenclaw = new ArrayList<>();
    ArrayList<Card> genel = new ArrayList<>();

    int image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16;
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    Runnable runnable;
    int zaman = 61;
    int total1 = 0;
    int total2=0;
    int turn1=1;
    int[] array1 = new int[4];
    int[] array2 = new int[4];
    int[] array3 = new int[4];
    int[] array4 = new int[4];
    int[] control = new int[16];
    int[] al = new int[8];
    int temp1 = 0;
    int temp2 = 11;
    int temp3 = 22;
    int temp4 = 33;
    int sayi;
    int kontrol1 = 0;
    int kontrol2 = 0;
    int kontrol3 = 0;
    int kontrol4 = 0;

    int Gryffindor = 2;
    int Slytherin = 2;
    int Hufflepuff = 1;
    int Ravenclaw = 1;
    int frk = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_oynama_coklu44);
        img1 = findViewById(R.id.im_1);
        img2 = findViewById(R.id.im_2);
        img3 = findViewById(R.id.im_3);
        img4 = findViewById(R.id.im_4);
        img5 = findViewById(R.id.im_5);
        img6 = findViewById(R.id.im_6);
        img7 = findViewById(R.id.im_7);
        img8 = findViewById(R.id.im_8);
        img9 = findViewById(R.id.im_9);
        img10 = findViewById(R.id.im_10);
        img11 = findViewById(R.id.im_11);
        img12 = findViewById(R.id.im_12);
        img13 = findViewById(R.id.im_13);
        img14 = findViewById(R.id.im_14);
        img15 = findViewById(R.id.im_15);
        img16 = findViewById(R.id.im_16);
        sure = findViewById(R.id.sure3Goster);
        skor1 = findViewById(R.id.skor3Goster);
        skor2 = findViewById(R.id.skor4Goster);
        anah = findViewById(R.id.switch2);

        mfirestore = FirebaseFirestore.getInstance();

        img1.setTag("0");
        img2.setTag("1");
        img3.setTag("2");
        img4.setTag("3");
        img5.setTag("4");
        img6.setTag("5");
        img7.setTag("6");
        img8.setTag("7");
        img9.setTag("8");
        img10.setTag("9");
        img11.setTag("10");
        img12.setTag("11");
        img13.setTag("12");
        img14.setTag("13");
        img15.setTag("14");
        img16.setTag("15");

        skor1.setText("Skor 1: ");
        skor2.setText("Skor 2: ");

        Toast.makeText(OyunOynamaCoklu4_4Activity.this, "KARTLAR YÜKLENİYOR", Toast.LENGTH_SHORT).show();

        mfirestore.collection("CardCollection").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        crd.Katsayi = Integer.parseInt(String.valueOf(document.getData().get("Katsayi")));

                        bytes = Base64.decode((String) document.getData().get("Img1"), Base64.DEFAULT);
                        Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        bytes1 = Base64.decode((String) document.getData().get("Img2"), Base64.DEFAULT);
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
                        crd.card = bitmap1;
                        crd.Img = bitmap2;
                        if(crd.House.equals("GRYFFINDOR")){
                            gryffindor.add(crd);
                        }
                        else if(crd.House.equals("SLYTHERIN")){
                            slytherin.add(crd);
                        }
                        else if(crd.House.equals("RAVENCLAW")){
                            ravenclaw.add(crd);
                        }
                        else if(crd.House.equals("HUFFLEPUFF")){
                            hufflepuff.add(crd);
                        }

                    }

                    if(gryffindor.size()==11 && slytherin.size()==11 && ravenclaw.size()==11 && hufflepuff.size()==11){
                        for(int i=0;i<11;i++){
                            genel.add(gryffindor.get(i));
                        }
                        for(int i=0;i<11;i++){
                            genel.add(slytherin.get(i));
                        }
                        for(int i=0;i<11;i++){
                            genel.add(hufflepuff.get(i));
                        }
                        for(int i=0;i<11;i++){
                            genel.add(ravenclaw.get(i));
                        }


                        for (int i = 0; i < 2; i++) {
                            Random uret = new Random();
                            sayi = uret.nextInt(gryffindor.size());
                            if(temp1!=sayi){
                                al[0]=sayi;
                                al[1]=temp1;
                                break;

                            }
                            else{
                                temp1=sayi;
                                i=0;
                            }
                        }


                        for (int i = 0; i < 2; i++) {
                            //Random uret = new Random();
                            sayi = ThreadLocalRandom.current().nextInt(11, 22);
                            //System.out.println(sayi);
                            if(temp2!=sayi){
                                al[2]=sayi;
                                al[3]=temp2;
                                break;

                            }
                            else{
                                temp2=sayi;
                                i=0;
                            }
                        }

                        for (int i = 0; i < 2; i++) {
                            //Random uret = new Random();
                            sayi = ThreadLocalRandom.current().nextInt(22, 33);
                            if(temp3!=sayi){
                                al[4]=sayi;
                                al[5]=temp3;
                                break;

                            }
                            else{
                                temp3=sayi;
                                i=0;
                            }
                        }


                        for (int i = 0; i < 2; i++) {
                            //Random uret = new Random();
                            sayi = ThreadLocalRandom.current().nextInt(33, 44);
                            if(temp4!=sayi){
                                al[6]=sayi;
                                al[7]=temp4;

                            }
                            else{
                                temp4=sayi;
                                i=0;
                            }
                        }
                    }

                    for(int i=0;i<8;i++){
                        System.out.println(al[i]);
                    }


                    player=MediaPlayer.create(OyunOynamaCoklu4_4Activity.this,R.raw.prologue);

                    anah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            /*if(isChecked){

                                player.start();
                                //anah.setTag("1");
                                Toast.makeText(OyunOynamaActivity.this, "Ses açıldı.", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(OyunOynamaActivity.this, "Ses kapatıldı.", Toast.LENGTH_SHORT).show();
                                //kontrol=isChecked;
                                //anah.setTag("0");
                                player.pause();
                                player.seekTo(0);
                            }*/
                        }
                    });

                    if(anah.isChecked()){
                        player.start();
                    }
                    else{
                        player.pause();
                        player.seekTo(0);
                    }

                    final Handler hnd=new Handler();
                    sure.setText("");
                    runnable=new Runnable() {
                        @Override
                        public void run() {
                            zaman--;
                            if (zaman != 0 && zaman>0) {

                                sure.setText(String.valueOf(zaman));
                                if(anah.isChecked()){
                                    player.start();
                                }
                                else{
                                    player.pause();
                                    player.seekTo(0);
                                }

                            } else if(zaman==0) {


                                //player.seekTo(0);
                                //player2.seekTo(0);
                                //player3.seekTo(0);
                                sure.setText(String.valueOf(0));
                                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(OyunOynamaCoklu4_4Activity.this);
                                alertDialogBuilder.setMessage("Oyun Bitti. Skor 1: "+total1+" "+"Skor 2: "+total2).setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(getApplicationContext(),OyunOynamaCoklu4_4Activity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });

                                AlertDialog alertDialog=alertDialogBuilder.create();
                                alertDialog.show();
                                player4=MediaPlayer.create(OyunOynamaCoklu4_4Activity.this,R.raw.shocked);
                                if(anah.isChecked()){
                                    player.pause();
                                    player.seekTo(0);
                                    player4.start();
                                }
                                else{
                                    player4.pause();
                                    player4.seekTo(0);
                                }

                                hnd.removeCallbacks(runnable);

                            }
                            hnd.postDelayed(runnable, 1000);
                        }
                    };
                    runnable.run();

                    cardsArray= new Integer[]{al[0], al[1], al[2], al[3],
                            al[4], al[5], al[6], al[7],
                            al[0], al[1], al[2], al[3],
                            al[4], al[5], al[6], al[7]};

                    Collections.shuffle(Arrays.asList(cardsArray));
                    writeToFile("Kartlar");
                    skor2.setTextColor(Color.GRAY);

                    img1.setImageBitmap(genel.get(cardsArray[0]).card);
                    img2.setImageBitmap(genel.get(cardsArray[1]).card);
                    img3.setImageBitmap(genel.get(cardsArray[2]).card);
                    img4.setImageBitmap(genel.get(cardsArray[3]).card);
                    img5.setImageBitmap(genel.get(cardsArray[4]).card);
                    img6.setImageBitmap(genel.get(cardsArray[5]).card);
                    img7.setImageBitmap(genel.get(cardsArray[6]).card);
                    img8.setImageBitmap(genel.get(cardsArray[7]).card);
                    img9.setImageBitmap(genel.get(cardsArray[8]).card);
                    img10.setImageBitmap(genel.get(cardsArray[9]).card);
                    img11.setImageBitmap(genel.get(cardsArray[10]).card);
                    img12.setImageBitmap(genel.get(cardsArray[11]).card);
                    img13.setImageBitmap(genel.get(cardsArray[12]).card);
                    img14.setImageBitmap(genel.get(cardsArray[13]).card);
                    img15.setImageBitmap(genel.get(cardsArray[14]).card);
                    img16.setImageBitmap(genel.get(cardsArray[15]).card);

                    frontOfCardResources();

                    img1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img1,theCard);
                        }
                    });

                    img2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img2,theCard);
                        }
                    });

                    img3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img3,theCard);
                        }
                    });

                    img4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img4,theCard);
                        }
                    });

                    img5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img5,theCard);
                        }
                    });

                    img6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img6,theCard);
                        }
                    });
                    img7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img7,theCard);
                        }
                    });

                    img8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img8,theCard);
                        }
                    });

                    img9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img9,theCard);
                        }
                    });

                    img10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img10,theCard);
                        }
                    });

                    img11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img11,theCard);
                        }
                    });

                    img12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img12,theCard);
                        }
                    });

                    img13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img13,theCard);
                        }
                    });

                    img14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img14,theCard);
                        }
                    });
                    img15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img15,theCard);
                        }
                    });

                    img16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer theCard=Integer.parseInt((String) v.getTag());
                            doStuff(img16,theCard);
                        }
                    });






                }
                else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }

            }

        });

    }

    private void doStuff(ImageView img_all, Integer theCard) {
        if(cardsArray[theCard].equals(cardsArray[0]))
            img_all.setImageBitmap(genel.get(cardsArray[0]).Img);
        else if(cardsArray[theCard].equals(cardsArray[1]))
            img_all.setImageBitmap(genel.get(cardsArray[1]).Img);
        else if(cardsArray[theCard].equals(cardsArray[2]))
            img_all.setImageBitmap(genel.get(cardsArray[2]).Img);
        else if(cardsArray[theCard].equals(cardsArray[3]))
            img_all.setImageBitmap(genel.get(cardsArray[3]).Img);
        else if(cardsArray[theCard].equals(cardsArray[4]))
            img_all.setImageBitmap(genel.get(cardsArray[4]).Img);
        else if(cardsArray[theCard].equals(cardsArray[5]))
            img_all.setImageBitmap(genel.get(cardsArray[5]).Img);
        else if(cardsArray[theCard].equals(cardsArray[6]))
            img_all.setImageBitmap(genel.get(cardsArray[6]).Img);
        else if(cardsArray[theCard].equals(cardsArray[7]))
            img_all.setImageBitmap(genel.get(cardsArray[7]).Img);
        else if(cardsArray[theCard].equals(cardsArray[8]))
            img_all.setImageBitmap(genel.get(cardsArray[8]).Img);
        else if(cardsArray[theCard].equals(cardsArray[9]))
            img_all.setImageBitmap(genel.get(cardsArray[9]).Img);
        else if(cardsArray[theCard].equals(cardsArray[10]))
            img_all.setImageBitmap(genel.get(cardsArray[10]).Img);
        else if(cardsArray[theCard].equals(cardsArray[11]))
            img_all.setImageBitmap(genel.get(cardsArray[11]).Img);
        else if(cardsArray[theCard].equals(cardsArray[12]))
            img_all.setImageBitmap(genel.get(cardsArray[12]).Img);
        else if(cardsArray[theCard].equals(cardsArray[13]))
            img_all.setImageBitmap(genel.get(cardsArray[13]).Img);
        else if(cardsArray[theCard].equals(cardsArray[14]))
            img_all.setImageBitmap(genel.get(cardsArray[14]).Img);
        else if(cardsArray[theCard].equals(cardsArray[15]))
            img_all.setImageBitmap(genel.get(cardsArray[15]).Img);

        if(cardNumber==1){
            firstCard=cardsArray[theCard];

            cardNumber=2;
            clickedFirst=theCard;

            img_all.setEnabled(false);
        }
        else if(cardNumber==2){
            secondCard=cardsArray[theCard];

            cardNumber=1;
            clickedSecond=theCard;

            img1.setEnabled(false);
            img2.setEnabled(false);
            img3.setEnabled(false);
            img4.setEnabled(false);
            img5.setEnabled(false);
            img6.setEnabled(false);
            img7.setEnabled(false);
            img8.setEnabled(false);
            img9.setEnabled(false);
            img10.setEnabled(false);
            img11.setEnabled(false);
            img12.setEnabled(false);
            img13.setEnabled(false);
            img14.setEnabled(false);
            img15.setEnabled(false);
            img16.setEnabled(false);

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            },1000);
        }

    }



    private void calculate() {

        System.out.println("frst:"+firstCard+"second:"+secondCard);
        player2=MediaPlayer.create(OyunOynamaCoklu4_4Activity.this,R.raw.victory);
        if(firstCard==secondCard){
            if(anah.isChecked()){
                player.pause();
                player.seekTo(0);
                player2.start();
            }

            if(!anah.isChecked()){
                player2.pause();
                //player2.seekTo(0);
            }




            if(clickedFirst==0){

                img1.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[0]=1;
            }
            else if(clickedFirst==1){
                img2.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[1]=2;
            }
            else if(clickedFirst==2){
                img3.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[2]=3;
            }
            else if(clickedFirst==3){
                img4.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[3]=4;
            }
            else if(clickedFirst==4){
                img5.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[4]=5;
            }
            else if(clickedFirst==5){
                img6.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[5]=6;
            }
            else if(clickedFirst==6){
                img7.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[6]=7;
            }
            else if(clickedFirst==7){
                img8.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[7]=8;
            }
            else if(clickedFirst==8){
                img9.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[8]=9;
            }
            else if(clickedFirst==9){
                img10.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[9]=10;
            }
            else if(clickedFirst==10){
                img11.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[10]=11;
            }
            else if(clickedFirst==11){
                img12.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[11]=12;
            }
            else if(clickedFirst==12){
                img13.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[12]=13;
            }
            else if(clickedFirst==13){
                img14.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[13]=14;
            }
            else if(clickedFirst==14){
                img15.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[14]=15;
            }
            else if(clickedFirst==15){
                img16.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[15]=16;
            }


            if(clickedSecond==0){
                img1.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[0]=1;
            }
            else if(clickedSecond==1){
                img2.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[1]=2;
            }
            else if(clickedSecond==2){
                img3.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[2]=3;
            }
            else if(clickedSecond==3){
                img4.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[3]=4;
            }
            else if(clickedSecond==4){
                img5.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[4]=5;
            }
            else if(clickedSecond==5){
                img6.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[5]=6;
            }
            else if(clickedSecond==6){
                img7.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[6]=7;
            }
            else if(clickedSecond==7){
                img8.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[7]=8;
            }
            else if(clickedSecond==8){
                img9.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[8]=9;
            }
            else if(clickedSecond==9){
                img10.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[9]=10;
            }
            else if(clickedSecond==10){
                img11.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[10]=11;
            }
            else if(clickedSecond==11){
                img12.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[11]=12;
            }
            else if(clickedSecond==12){
                img13.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[12]=13;
            }
            else if(clickedSecond==13){
                img14.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[13]=14;
            }
            else if(clickedSecond==14){
                img15.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[14]=15;
            }
            else if(clickedSecond==15){
                img16.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[15]=16;
            }

            if(turn1==1){
                total1+=2*genel.get(firstCard).Point*genel.get(firstCard).Katsayi;
                skor1.setText("Skor 1: "+total1);
            }
            else if(turn1==2){
                total2+=2*genel.get(firstCard).Point*genel.get(firstCard).Katsayi;
                skor2.setText("Skor 2: "+total2);
            }




        }

        else {


            if(clickedFirst==0){
                img1.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==1){
                img2.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==2){
                img3.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==3){
                img4.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==4){
                img5.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==5){
                img6.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==6){
                img7.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==7){
                img8.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==8){
                img9.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==9){
                img10.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==10){
                img11.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==11){
                img12.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==12){
                img13.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==13){
                img14.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==14){
                img15.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==15){
                img16.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }


            if(clickedSecond==0){
                img1.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==1){
                img2.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==2){
                img3.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==3){
                img4.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==4){
                img5.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==5){
                img6.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==6){
                img7.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==7){
                img8.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==8){
                img9.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==9){
                img10.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==10){
                img11.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==11){
                img12.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==12){
                img13.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==13){
                img14.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==14){
                img15.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==15){
                img16.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }

            if(turn1==1){
                turn1=2;
                if(genel.get(firstCard).House.equals(genel.get(secondCard).House))
                    total1 -= ((genel.get(firstCard).Point + genel.get(secondCard).Point) / genel.get(firstCard).Katsayi);
                else
                    total1-=(genel.get(firstCard).Point + genel.get(secondCard).Point)/2*genel.get(firstCard).Katsayi*genel.get(secondCard).Katsayi;
                skor1.setTextColor(Color.GRAY);
                skor2.setTextColor(Color.BLACK);

                skor1.setText("Skor 1: "+total1);
            }
            else if(turn1==2){
                turn1=1;
                if(genel.get(firstCard).House.equals(genel.get(secondCard).House))
                    total2 -= ((genel.get(firstCard).Point + genel.get(secondCard).Point) / genel.get(firstCard).Katsayi);
                else
                    total2-=(genel.get(firstCard).Point + genel.get(secondCard).Point)/2*genel.get(firstCard).Katsayi*genel.get(secondCard).Katsayi;
                skor2.setTextColor(Color.GRAY);
                skor1.setTextColor(Color.BLACK);
                skor2.setText("Skor 2: "+total2);

            }

        }

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);
        img5.setEnabled(true);
        img6.setEnabled(true);
        img7.setEnabled(true);
        img8.setEnabled(true);
        img9.setEnabled(true);
        img10.setEnabled(true);
        img11.setEnabled(true);
        img12.setEnabled(true);
        img13.setEnabled(true);
        img14.setEnabled(true);
        img15.setEnabled(true);
        img16.setEnabled(true);
        checkEnd();



    }

    private void checkEnd() {
        if(control[0]==1 && control[1]==2 && control[2]==3 &&
                control[3]==4 && control[4]==5 && control[5]==6 && control[7]==8 && control[8]==9 &&
                control[9]==10 && control[10]==11 && control[11]==12 && control[12]==13 && control[13]==14 && control[14]==15 && control[15]==16){

            player3=MediaPlayer.create(OyunOynamaCoklu4_4Activity.this,R.raw.congratulations);
            /*player.pause();
            player2.pause();
            player3.start();
            player.seekTo(0);
            player2.seekTo(0);*/

            if(anah.isChecked()){
                player.pause();
                player2.pause();
                player3.start();
                player.seekTo(0);
                player2.seekTo(0);

            }
            else{

                player3.pause();
                player3.seekTo(0);
            }
            /*anah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        player.pause();
                        player2.pause();
                        player3.start();
                        player.seekTo(0);
                        player2.seekTo(0);

                        Toast.makeText(OyunOynamaActivity.this, "Ses açıldı.", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(OyunOynamaActivity.this, "Ses kapatıldı.", Toast.LENGTH_SHORT).show();
                        //kontrol=0;
                        player3.pause();
                        player3.seekTo(0);
                    }
                }
            });*/




            zaman=0;
            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(OyunOynamaCoklu4_4Activity.this);
            sure.setText(String.valueOf(zaman));

            alertDialogBuilder.setMessage("Oyun Bitti. Skor 1: "+total1+" "+"Skor 2: "+total2).setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(getApplicationContext(),OyunOynamaCoklu4_4Activity.class);
                    startActivity(intent);
                    finish();

                }
            }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alertDialog=alertDialogBuilder.create();
            alertDialog.show();

        }
        else{
            //player.start();

            if(anah.isChecked()){
                player.start();
            }
            else{
                player.pause();
                player.seekTo(0);
            }
            /*anah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        player.start();

                        Toast.makeText(OyunOynama4_4Activity.this, "Ses açıldı.", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(OyunOynama4_4Activity.this, "Ses kapatıldı.", Toast.LENGTH_SHORT).show();
                        //kontrol=0;
                        player.pause();
                        player.seekTo(0);
                    }
                }
            });*/
        }




    }

    private void frontOfCardResources() {
        image1=cardsArray[0];
        image2=cardsArray[1];
        image3=cardsArray[2];
        image4=cardsArray[3];
        image5=cardsArray[4];
        image6=cardsArray[5];
        image7=cardsArray[6];
        image8=cardsArray[7];
        image9=cardsArray[8];
        image10=cardsArray[9];
        image11=cardsArray[10];
        image12=cardsArray[11];
        image13=cardsArray[12];
        image14=cardsArray[13];
        image15=cardsArray[14];
        image16=cardsArray[15];
    }

    public  void writeToFile(String fileName)
    {   int i=0;
        try {
            final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/" );
            System.out.println(dir);

            if (!dir.exists())
            {
                if(!dir.mkdirs()){
                    Log.e("TAG","could not create the directories");
                }
            }

            final File myFile = new File(dir, fileName + ".txt");
            if(myFile.exists()) {
                myFile.delete();
                myFile.createNewFile();
            }
            else
                myFile.createNewFile();



            FileWriter writer = null;
            try {
                writer = new FileWriter(myFile);
                //writer.write("Yazıldı 2.");
                for(int crdd: cardsArray) {
                    i++;
                    writer.write(i+"-"+"Id: "+genel.get(crdd).Id+" "+"Isim:"+genel.get(crdd).Name+" "+"Ev:"+genel.get(crdd).House+" "+"Puan:"+genel.get(crdd).Point+"\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

