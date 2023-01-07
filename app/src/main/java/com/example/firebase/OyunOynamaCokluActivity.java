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

public class OyunOynamaCokluActivity extends AppCompatActivity {

    ImageView img1,img2,img3,img4;
    TextView sure,skor1,skor2;
    SwitchCompat anah;
    FirebaseFirestore mFirestore;
    MediaPlayer player,player2,player3,player4;
    byte[] bytes;
    byte[] bytes1;

    int sayi1;

    int temp=0;
    int[] control=new int[4];
    int[] array2=new int[4];

    Integer[] cardsArray;
    ArrayList<Card> cards=new ArrayList<>();
    int image1,image2,image3,image4;
    int firstCard,secondCard;
    int clickedFirst,clickedSecond;
    int cardNumber=1;
    Runnable runnable;
    int zaman=61;
    int Gryffindor=2;
    int Slytherin=2;
    int Hufflepuff=1;
    int Ravenclaw=1;
    int total1=0;
    int total2=0;
    boolean kontrol;
    int turn1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_oynama_coklu);

        img1=findViewById(R.id.iv_1);
        img2=findViewById(R.id.iv_2);
        img3=findViewById(R.id.iv_3);
        img4=findViewById(R.id.iv_4);
        sure=findViewById(R.id.sure3Goster);
        skor1=findViewById(R.id.skor3Goster);
        skor2=findViewById(R.id.skor4Goster);

        anah=findViewById(R.id.switch2);

        //Firestore başlatma
        mFirestore=FirebaseFirestore.getInstance();


        img1.setTag("0");
        img2.setTag("1");
        img3.setTag("2");
        img4.setTag("3");

        skor1.setText("Skor 1: ");
        skor2.setText("Skor 2: ");

        Toast.makeText(OyunOynamaCokluActivity.this, "KARTLAR YÜKLENİYOR", Toast.LENGTH_SHORT).show();

        mFirestore.collection("CardCollection").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Card crd=new Card();
                        crd.Id=Integer.parseInt(String.valueOf(document.getData().get("Id")));
                        crd.Name= (String) document.getData().get("Name");
                        crd.House= (String) document.getData().get("House");
                        crd.Point=Float.parseFloat(String.valueOf(document.getData().get("Point"))) ;
                        crd.Katsayi=Integer.parseInt(String.valueOf(document.getData().get("Katsayi")));

                        bytes = Base64.decode((String) document.getData().get("Img1"), Base64.DEFAULT);
                        Bitmap bitmap1= BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                        bytes1 = Base64.decode((String) document.getData().get("Img2"), Base64.DEFAULT);
                        Bitmap bitmap2= BitmapFactory.decodeByteArray(bytes1,0, bytes1.length);
                        crd.card=bitmap1;
                        crd.Img=bitmap2;

                        cards.add(crd);
                        //img1.setImageBitmap(bitmap);*/
                    }

                    if(cards.size()==44) {



                        for (int i = 0; i < 2; i++) {
                            Random uret = new Random();
                            sayi1 = uret.nextInt(cards.size());
                            if (!cards.get(temp).House.equals(cards.get(sayi1).House)) {
                                array2[0] = temp;
                                array2[1] = sayi1;
                                array2[2] = temp;
                                array2[3] = sayi1;
                                break;
                            } else {
                                temp = sayi1;
                                i = 0;

                            }

                        }
                    }
                    player=MediaPlayer.create(OyunOynamaCokluActivity.this,R.raw.prologue);
                    //player.start();




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
                                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(OyunOynamaCokluActivity.this);
                                alertDialogBuilder.setMessage("Oyun Bitti. Skor 1: "+total1+" "+"Skor 2: "+total2).setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(getApplicationContext(),OyunOynamaCokluActivity.class);
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
                                player4=MediaPlayer.create(OyunOynamaCokluActivity.this,R.raw.shocked);
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

                    cardsArray= new Integer[]{array2[0], array2[1], array2[2], array2[3]};
                    Collections.shuffle(Arrays.asList(cardsArray));
                    writeToFile("Kartlar");
                    skor2.setTextColor(Color.GRAY);
                    img1.setImageBitmap(cards.get(cardsArray[0]).card);
                    img2.setImageBitmap(cards.get(cardsArray[1]).card);
                    img3.setImageBitmap(cards.get(cardsArray[2]).card);
                    img4.setImageBitmap(cards.get(cardsArray[3]).card);
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




                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }


            }
        });


    }

    private void doStuff(ImageView img_all, Integer theCard) {
        if(cardsArray[theCard]==cardsArray[0])
            img_all.setImageBitmap(cards.get(cardsArray[0]).Img);
        else if(cardsArray[theCard]==cardsArray[1])
            img_all.setImageBitmap(cards.get(cardsArray[1]).Img);
        else if(cardsArray[theCard]==cardsArray[2])
            img_all.setImageBitmap(cards.get(cardsArray[2]).Img);
        else if(cardsArray[theCard]==cardsArray[3])
            img_all.setImageBitmap(cards.get(cardsArray[3]).Img);

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

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            },1000);
        }

    }

    private void calculate(){
        System.out.println("frst:"+firstCard+"second:"+secondCard);
        player2=MediaPlayer.create(OyunOynamaCokluActivity.this,R.raw.victory);
        if(firstCard==secondCard){
            if(anah.isChecked()==true){
                player.pause();
                player.seekTo(0);
                player2.start();
            }

            if(anah.isChecked()==false){
                player2.pause();
                //player2.seekTo(0);
            }




            if(clickedFirst==0){

                img1.setImageBitmap(cards.get(cardsArray[clickedFirst]).Img);
                control[0]=1;
            }
            else if(clickedFirst==1){
                img2.setImageBitmap(cards.get(cardsArray[clickedFirst]).Img);
                control[1]=2;
            }
            else if(clickedFirst==2){
                img3.setImageBitmap(cards.get(cardsArray[clickedFirst]).Img);
                control[2]=3;
            }
            else if(clickedFirst==3){
                img4.setImageBitmap(cards.get(cardsArray[clickedFirst]).Img);
                control[3]=4;
            }


            if(clickedSecond==0){
                img1.setImageBitmap(cards.get(cardsArray[clickedSecond]).Img);
                control[0]=1;
            }
            else if(clickedSecond==1){
                img2.setImageBitmap(cards.get(cardsArray[clickedSecond]).Img);
                control[1]=2;
            }
            else if(clickedSecond==2){
                img3.setImageBitmap(cards.get(cardsArray[clickedSecond]).Img);
                control[2]=3;
            }
            else if(clickedSecond==3){
                img4.setImageBitmap(cards.get(cardsArray[clickedSecond]).Img);
                control[3]=4;
            }

            /*if(cards.get(firstCard).House.equals("GRYFFINDOR")){
                total+=(int)((2*Float.parseFloat(String.valueOf(cards.get(firstCard).Point))*Gryffindor)*(zaman/10));
            }
            else if(cards.get(firstCard).House.equals("SLYTHERIN")){
                total+=(int)((2*Float.parseFloat(String.valueOf(cards.get(firstCard).Point))*Slytherin)*(zaman/10));
            }
            else if(cards.get(firstCard).House.equals("HUFFLEPUFF")){
                total+= (int) ((2*Float.parseFloat(String.valueOf(cards.get(firstCard).Point))*Hufflepuff)*(zaman/10));
            }
            else if(cards.get(firstCard).House.equals("RAVENCLAW")){
                total+= (int) ((2*Float.parseFloat(String.valueOf(cards.get(firstCard).Point))*Ravenclaw)*(zaman/10));
                }*/

            if(turn1==1){
                total1+=2*cards.get(firstCard).Point*cards.get(firstCard).Katsayi;
                skor1.setText("Skor 1: "+total1);
            }
            else if(turn1==2){
                total2+=2*cards.get(firstCard).Point*cards.get(firstCard).Katsayi;
                skor2.setText("Skor 2: "+total2);
            }



        }

        else {


            if(clickedFirst==0){
                img1.setImageBitmap(cards.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==1){
                img2.setImageBitmap(cards.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==2){
                img3.setImageBitmap(cards.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==3){
                img4.setImageBitmap(cards.get(cardsArray[clickedFirst]).card);
            }


            if(clickedSecond==0){
                img1.setImageBitmap(cards.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==1){
                img2.setImageBitmap(cards.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==2){
                img3.setImageBitmap(cards.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==3){
                img4.setImageBitmap(cards.get(cardsArray[clickedSecond]).card);
            }

            /*int v = (int) ((45.0 - zaman) / 10);
            total-=(cards.get(cardsArray[clickedFirst]).Point+cards.get(cardsArray[clickedSecond]).Point)/2*cards.get(cardsArray[clickedFirst]).Katsayi*cards.get(cardsArray[clickedSecond]).Katsayi*v;*/
            if(turn1==1){
                turn1=2;
                if(cards.get(firstCard).House.equals(cards.get(secondCard).House))
                    total1 -= ((cards.get(firstCard).Point + cards.get(secondCard).Point) / cards.get(firstCard).Katsayi);
                else
                    total1-=(cards.get(firstCard).Point + cards.get(secondCard).Point)/2*cards.get(firstCard).Katsayi*cards.get(secondCard).Katsayi;
                skor1.setTextColor(Color.GRAY);
                skor2.setTextColor(Color.BLACK);

                skor1.setText("Skor 1: "+total1);
            }
            else if(turn1==2){
                turn1=1;
                if(cards.get(firstCard).House.equals(cards.get(secondCard).House))
                    total2 -= ((cards.get(firstCard).Point + cards.get(secondCard).Point) / cards.get(firstCard).Katsayi);
                else
                    total2-=(cards.get(firstCard).Point + cards.get(secondCard).Point)/2*cards.get(firstCard).Katsayi*cards.get(secondCard).Katsayi;
                skor2.setTextColor(Color.GRAY);
                skor1.setTextColor(Color.BLACK);
                skor2.setText("Skor 2: "+total2);

            }



        }

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);
        checkEnd();

    }


    private void checkEnd() {

        if(control[0]==1 && control[1]==2 && control[2]==3 &&
                control[3]==4){

            player3=MediaPlayer.create(OyunOynamaCokluActivity.this,R.raw.congratulations);
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
            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(OyunOynamaCokluActivity.this);
            sure.setText(String.valueOf(zaman));

            alertDialogBuilder.setMessage("Oyun Bitti. Skor 1: "+total1+" "+"Skor 2: "+total2).setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(getApplicationContext(),OyunOynamaCokluActivity.class);
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

                        Toast.makeText(OyunOynamaActivity.this, "Ses açıldı.", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(OyunOynamaActivity.this, "Ses kapatıldı.", Toast.LENGTH_SHORT).show();
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
                    writer.write(i+"-"+"Id: "+cards.get(crdd).Id+" "+"Isim:"+cards.get(crdd).Name+" "+"Ev:"+cards.get(crdd).House+" "+"Puan:"+cards.get(crdd).Point+"\n");
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