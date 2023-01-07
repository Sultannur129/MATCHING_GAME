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

public class OyunOynama6_6Activity extends AppCompatActivity {

    FirebaseFirestore mfirestore;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11,
            img12, img13, img14, img15, img16,img17,img18,img19,img20,img21,img22,
            img23,img24,img25,img26,img27,img28,img29,img30,img31,img32,img33,img34,img35,img36;
    TextView sure, skor;
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

    int image1, image2, image3, image4, image5, image6, image7, image8, image9,
    image10, image11, image12, image13, image14, image15, image16,image17,image18,
            image19,image20,image21,image22,image23,image24,image25,image26,image27,
            image28,image29,image30,image31,image32,image33,image34,image35,image36;
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    Runnable runnable;
    int zaman = 46;

    int total = 0;
    int[] a = new int[18];
    int[] control=new int[36];

    int sayi;
    int kontrol = 0;
    int tut;

    int Gryffindor = 2;
    int Slytherin = 2;
    int Hufflepuff = 1;
    int Ravenclaw = 1;
    int frk = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_oynama66);

        img1 = findViewById(R.id.ip_1);
        img2 = findViewById(R.id.ip_2);
        img3 = findViewById(R.id.ip_3);
        img4 = findViewById(R.id.ip_4);
        img5 = findViewById(R.id.ip_5);
        img6 = findViewById(R.id.ip_6);
        img7 = findViewById(R.id.ip_7);
        img8 = findViewById(R.id.ip_8);
        img9 = findViewById(R.id.ip_9);
        img10 = findViewById(R.id.ip_10);
        img11 = findViewById(R.id.ip_11);
        img12 = findViewById(R.id.ip_12);
        img13 = findViewById(R.id.ip_13);
        img14 = findViewById(R.id.ip_14);
        img15 = findViewById(R.id.ip_15);
        img16 = findViewById(R.id.ip_16);
        img17 = findViewById(R.id.ip_17);
        img18 = findViewById(R.id.ip_18);
        img19 = findViewById(R.id.ip_19);
        img20 = findViewById(R.id.ip_20);
        img21 = findViewById(R.id.ip_21);
        img22 = findViewById(R.id.ip_22);
        img23 = findViewById(R.id.ip_23);
        img24 = findViewById(R.id.ip_24);
        img25 = findViewById(R.id.ip_25);
        img26 = findViewById(R.id.ip_26);
        img27 = findViewById(R.id.ip_27);
        img28 = findViewById(R.id.ip_28);
        img29 = findViewById(R.id.ip_29);
        img30 = findViewById(R.id.ip_30);
        img31 = findViewById(R.id.ip_31);
        img32 = findViewById(R.id.ip_32);
        img33 = findViewById(R.id.ip_33);
        img34 = findViewById(R.id.ip_34);
        img35 = findViewById(R.id.ip_35);
        img36 = findViewById(R.id.ip_36);
        sure = findViewById(R.id.sure2Goster);
        skor = findViewById(R.id.skor2Goster);
        anah = findViewById(R.id.switch4);

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
        img17.setTag("16");
        img18.setTag("17");
        img19.setTag("18");
        img20.setTag("19");
        img21.setTag("20");
        img22.setTag("21");
        img23.setTag("22");
        img24.setTag("23");
        img25.setTag("24");
        img26.setTag("25");
        img27.setTag("26");
        img28.setTag("27");
        img29.setTag("28");
        img30.setTag("29");
        img31.setTag("30");
        img32.setTag("31");
        img33.setTag("32");
        img34.setTag("33");
        img35.setTag("34");
        img36.setTag("35");

        Toast.makeText(OyunOynama6_6Activity.this, "KARTLAR YÜKLENİYOR", Toast.LENGTH_SHORT).show();

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
                        if (crd.House.equals("GRYFFINDOR")) {
                            gryffindor.add(crd);
                        } else if (crd.House.equals("SLYTHERIN")) {
                            slytherin.add(crd);
                        } else if (crd.House.equals("RAVENCLAW")) {
                            ravenclaw.add(crd);
                        } else if (crd.House.equals("HUFFLEPUFF")) {
                            hufflepuff.add(crd);
                        }

                    }

                    if(gryffindor.size()==11 && slytherin.size()==11 && ravenclaw.size()==11 && hufflepuff.size()==11) {
                        for (int i = 0; i < 11; i++) {
                            genel.add(gryffindor.get(i));
                        }
                        for (int i = 0; i < 11; i++) {
                            genel.add(slytherin.get(i));
                        }
                        for (int i = 0; i < 11; i++) {
                            genel.add(hufflepuff.get(i));
                        }
                        for (int i = 0; i < 11; i++) {
                            genel.add(ravenclaw.get(i));
                        }

                        a[0]=0;
                        Random r=new Random();
                        for (int i = 1; i < 5; i++) {
                            sayi = r.nextInt(gryffindor.size());

                            for (int j = 0; j <= i; j++)

                            {
                                if (a[j] == sayi) {

                                    sayi = r.nextInt(gryffindor.size());
                                    j=0;
                               }
                           }

                            a[i] = sayi;

                        }

                        /*for(int i=0;i<5;i++){
                            cek[i]=a[i];
                        }*///GRYFFİNDOR 5 TANE ALINDI VE ATANDI CEK ARRAYINE

                        a[5]=11;
                        for (int i = 6; i < 10; i++) {
                            sayi = ThreadLocalRandom.current().nextInt(11, 22);

                            for (int j = 5; j <= i; j++)

                            {
                                if (a[j] == sayi) {

                                    sayi = ThreadLocalRandom.current().nextInt(11, 22);
                                    j=5;
                                }
                            }

                            a[i] = sayi;

                        }
                        a[10]=22;
                        for (int i =11; i < 14; i++) {
                            sayi = ThreadLocalRandom.current().nextInt(22, 33);

                            for (int j = 10; j <= i; j++)

                            {
                                if (a[j] == sayi) {

                                    sayi = ThreadLocalRandom.current().nextInt(22, 33);
                                    j=10;
                                }
                            }

                            a[i] = sayi;

                        }
                        a[14]=33;
                        for (int i =15; i < 18; i++) {
                            sayi = ThreadLocalRandom.current().nextInt(33, 44);

                            for (int j = 14; j <= i; j++)

                            {
                                if (a[j] == sayi) {

                                    sayi = ThreadLocalRandom.current().nextInt(33, 44);
                                    j=14;
                                }
                            }

                            a[i] = sayi;

                        }
                        for(int i=0;i<18;i++){
                            System.out.println(a[i]);
                        }


                        player=MediaPlayer.create(OyunOynama6_6Activity.this,R.raw.prologue);

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
                                    AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(OyunOynama6_6Activity.this);
                                    alertDialogBuilder.setMessage("Oyun Bitti").setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent=new Intent(getApplicationContext(),OyunOynama6_6Activity.class);
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
                                    player4=MediaPlayer.create(OyunOynama6_6Activity.this,R.raw.shocked);
                                    if(anah.isChecked()){
                                        player.pause();
                                        player.seekTo(0);
                                        player4.start();
                                    }
                                    else{
                                        player.pause();
                                        player4.pause();
                                        player4.seekTo(0);
                                        player.seekTo(0);
                                    }

                                    hnd.removeCallbacks(runnable);

                                }
                                hnd.postDelayed(runnable, 1000);
                            }
                        };
                        runnable.run();

                        cardsArray=new Integer[]{a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9],a[10],a[11],a[12],a[13],a[14],a[15],a[16],a[17],
                                a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9],a[10],a[11],a[12],a[13],a[14],a[15],a[16],a[17]};

                        Collections.shuffle(Arrays.asList(cardsArray));
                        writeToFile("Kartlar");

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
                        img17.setImageBitmap(genel.get(cardsArray[16]).card);
                        img18.setImageBitmap(genel.get(cardsArray[17]).card);
                        img19.setImageBitmap(genel.get(cardsArray[18]).card);
                        img20.setImageBitmap(genel.get(cardsArray[19]).card);
                        img21.setImageBitmap(genel.get(cardsArray[20]).card);
                        img22.setImageBitmap(genel.get(cardsArray[21]).card);
                        img23.setImageBitmap(genel.get(cardsArray[22]).card);
                        img24.setImageBitmap(genel.get(cardsArray[23]).card);
                        img25.setImageBitmap(genel.get(cardsArray[24]).card);
                        img26.setImageBitmap(genel.get(cardsArray[25]).card);
                        img27.setImageBitmap(genel.get(cardsArray[26]).card);
                        img28.setImageBitmap(genel.get(cardsArray[27]).card);
                        img29.setImageBitmap(genel.get(cardsArray[28]).card);
                        img30.setImageBitmap(genel.get(cardsArray[29]).card);
                        img31.setImageBitmap(genel.get(cardsArray[30]).card);
                        img32.setImageBitmap(genel.get(cardsArray[31]).card);
                        img33.setImageBitmap(genel.get(cardsArray[32]).card);
                        img34.setImageBitmap(genel.get(cardsArray[33]).card);
                        img35.setImageBitmap(genel.get(cardsArray[34]).card);
                        img36.setImageBitmap(genel.get(cardsArray[35]).card);

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

                        img17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img17,theCard);
                            }
                        });

                        img18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img18,theCard);
                            }
                        });

                        img19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img19,theCard);
                            }
                        });

                        img20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img20,theCard);
                            }
                        });

                        img21.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img21,theCard);
                            }
                        });

                        img22.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img22,theCard);
                            }
                        });
                        img23.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img23,theCard);
                            }
                        });

                        img24.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img24,theCard);
                            }
                        });

                        img25.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img25,theCard);
                            }
                        });

                        img26.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img26,theCard);
                            }
                        });

                        img27.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img27,theCard);
                            }
                        });

                        img28.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img28,theCard);
                            }
                        });

                        img29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img29,theCard);
                            }
                        });

                        img30.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img30,theCard);
                            }
                        });
                        img31.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img31,theCard);
                            }
                        });

                        img32.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img32,theCard);
                            }
                        });

                        img33.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img33,theCard);
                            }
                        });

                        img34.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img34,theCard);
                            }
                        });
                        img35.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img35,theCard);
                            }
                        });

                        img36.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer theCard=Integer.parseInt((String) v.getTag());
                                doStuff(img36,theCard);
                            }
                        });












                        /*for(int i=0;i<8;i++){
                            System.out.println(a[i]);
                        }*/

                    }

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
        else if(cardsArray[theCard].equals(cardsArray[16]))
            img_all.setImageBitmap(genel.get(cardsArray[16]).Img);
        else if(cardsArray[theCard].equals(cardsArray[17]))
            img_all.setImageBitmap(genel.get(cardsArray[17]).Img);
        else if(cardsArray[theCard].equals(cardsArray[18]))
            img_all.setImageBitmap(genel.get(cardsArray[18]).Img);
        else if(cardsArray[theCard].equals(cardsArray[19]))
            img_all.setImageBitmap(genel.get(cardsArray[19]).Img);
        else if(cardsArray[theCard].equals(cardsArray[20]))
            img_all.setImageBitmap(genel.get(cardsArray[20]).Img);
        else if(cardsArray[theCard].equals(cardsArray[21]))
            img_all.setImageBitmap(genel.get(cardsArray[21]).Img);
        else if(cardsArray[theCard].equals(cardsArray[22]))
            img_all.setImageBitmap(genel.get(cardsArray[22]).Img);
        else if(cardsArray[theCard].equals(cardsArray[23]))
            img_all.setImageBitmap(genel.get(cardsArray[23]).Img);
        else if(cardsArray[theCard].equals(cardsArray[24]))
            img_all.setImageBitmap(genel.get(cardsArray[24]).Img);
        else if(cardsArray[theCard].equals(cardsArray[25]))
            img_all.setImageBitmap(genel.get(cardsArray[25]).Img);
        else if(cardsArray[theCard].equals(cardsArray[26]))
            img_all.setImageBitmap(genel.get(cardsArray[26]).Img);
        else if(cardsArray[theCard].equals(cardsArray[27]))
            img_all.setImageBitmap(genel.get(cardsArray[27]).Img);
        else if(cardsArray[theCard].equals(cardsArray[28]))
            img_all.setImageBitmap(genel.get(cardsArray[28]).Img);
        else if(cardsArray[theCard].equals(cardsArray[29]))
            img_all.setImageBitmap(genel.get(cardsArray[29]).Img);
        else if(cardsArray[theCard].equals(cardsArray[30]))
            img_all.setImageBitmap(genel.get(cardsArray[30]).Img);
        else if(cardsArray[theCard].equals(cardsArray[31]))
            img_all.setImageBitmap(genel.get(cardsArray[31]).Img);
        else if(cardsArray[theCard].equals(cardsArray[32]))
            img_all.setImageBitmap(genel.get(cardsArray[32]).Img);
        else if(cardsArray[theCard].equals(cardsArray[33]))
            img_all.setImageBitmap(genel.get(cardsArray[33]).Img);
        else if(cardsArray[theCard].equals(cardsArray[34]))
            img_all.setImageBitmap(genel.get(cardsArray[34]).Img);
        else if(cardsArray[theCard].equals(cardsArray[35]))
            img_all.setImageBitmap(genel.get(cardsArray[35]).Img);

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
            img17.setEnabled(false);
            img18.setEnabled(false);
            img19.setEnabled(false);
            img20.setEnabled(false);
            img21.setEnabled(false);
            img22.setEnabled(false);
            img23.setEnabled(false);
            img24.setEnabled(false);
            img25.setEnabled(false);
            img26.setEnabled(false);
            img27.setEnabled(false);
            img28.setEnabled(false);
            img29.setEnabled(false);
            img30.setEnabled(false);
            img31.setEnabled(false);
            img32.setEnabled(false);
            img33.setEnabled(false);
            img34.setEnabled(false);
            img35.setEnabled(false);
            img36.setEnabled(false);

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
        player2=MediaPlayer.create(OyunOynama6_6Activity.this,R.raw.victory);
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
            else if(clickedFirst==16){
                img17.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[16]=17;
            }
            else if(clickedFirst==17){
                img18.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[17]=18;
            }
            else if(clickedFirst==18){
                img19.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[18]=19;
            }
            else if(clickedFirst==19){
                img20.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[19]=20;
            }
            else if(clickedFirst==20){
                img21.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[20]=21;
            }
            else if(clickedFirst==21){
                img22.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[21]=22;
            }
            else if(clickedFirst==22){
                img23.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[22]=23;
            }
            else if(clickedFirst==23){
                img24.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[23]=24;
            }
            else if(clickedFirst==24){
                img25.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[24]=25;
            }
            else if(clickedFirst==25){
                img26.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[25]=26;
            }
            else if(clickedFirst==26){
                img27.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[26]=27;
            }
            else if(clickedFirst==27){
                img28.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[27]=28;
            }
            else if(clickedFirst==28){
                img29.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[28]=29;
            }
            else if(clickedFirst==29){
                img30.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[29]=30;
            }
            else if(clickedFirst==30){
                img31.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[30]=31;
            }
            else if(clickedFirst==31){
                img32.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[31]=32;
            }
            else if(clickedFirst==32){
                img33.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[32]=33;
            }
            else if(clickedFirst==33){
                img34.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[33]=34;
            }
            else if(clickedFirst==34){
                img35.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[34]=35;
            }
            else if(clickedFirst==35){
                img36.setImageBitmap(genel.get(cardsArray[clickedFirst]).Img);
                control[35]=36;
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
            else if(clickedSecond==16){
                img17.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[16]=17;
            }
            else if(clickedSecond==17){
                img18.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[17]=18;
            }
            else if(clickedSecond==18){
                img19.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[18]=19;
            }
            else if(clickedSecond==19){
                img20.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[19]=20;
            }
            else if(clickedSecond==20){
                img21.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[20]=21;
            }
            else if(clickedSecond==21){
                img22.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[21]=22;
            }
            else if(clickedSecond==22){
                img23.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[22]=23;
            }
            else if(clickedSecond==23){
                img24.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[23]=24;
            }
            else if(clickedSecond==24){
                img25.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[24]=25;
            }
            else if(clickedSecond==25){
                img26.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[25]=26;
            }
            else if(clickedSecond==26){
                img27.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[26]=27;
            }
            else if(clickedSecond==27){
                img28.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[27]=28;
            }
            else if(clickedSecond==28){
                img29.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[28]=29;
            }
            else if(clickedSecond==29){
                img30.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[29]=30;
            }
            else if(clickedSecond==30) {
                img31.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[30] = 31;
            }
            else if(clickedSecond==31){
                img32.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[31]=32;
            }
            else if(clickedSecond==32){
                img33.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[32]=33;
            }
            else if(clickedSecond==33){
                img34.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[33]=34;
            }
            else if(clickedSecond==34){
                img35.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[34]=35;
            }
            else if(clickedSecond==35) {
                img36.setImageBitmap(genel.get(cardsArray[clickedSecond]).Img);
                control[35] = 36;
            }


            if(genel.get(firstCard).House.equals("GRYFFINDOR")){
                total+=(int)((2*Float.parseFloat(String.valueOf(genel.get(firstCard).Point))*Gryffindor)*(zaman/10));
            }
            else if(genel.get(firstCard).House.equals("SLYTHERIN")){
                total+=(int)((2*Float.parseFloat(String.valueOf(genel.get(firstCard).Point))*Slytherin)*(zaman/10));
            }
            else if(genel.get(firstCard).House.equals("HUFFLEPUFF")){
                total+= (int) ((2*Float.parseFloat(String.valueOf(genel.get(firstCard).Point))*Hufflepuff)*(zaman/10));
            }
            else if(genel.get(firstCard).House.equals("RAVENCLAW")){
                total+= (int) ((2*Float.parseFloat(String.valueOf(genel.get(firstCard).Point))*Hufflepuff)*(zaman/10));
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
            else if(clickedFirst==16){
                img17.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==17){
                img18.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==18){
                img19.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==19){
                img20.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==20){
                img21.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==21){
                img22.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==22){
                img23.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==23){
                img24.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==24){
                img25.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==25){
                img26.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==26){
                img27.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==27){
                img28.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==28){
                img29.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==29){
                img30.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==30){
                img31.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==31){
                img32.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==32){
                img33.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==33){
                img34.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==34){
                img35.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
            }
            else if(clickedFirst==35){
                img36.setImageBitmap(genel.get(cardsArray[clickedFirst]).card);
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
            else if(clickedSecond==16){
                img17.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==17){
                img18.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==18){
                img19.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==19){
                img20.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==20){
                img21.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==21){
                img22.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==22){
                img23.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==23){
                img24.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==24){
                img25.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==25){
                img26.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==26){
                img27.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==27){
                img28.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==28){
                img29.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==29){
                img30.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==30){
                img31.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==31){
                img32.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==32){
                img33.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==33){
                img34.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==34){
                img35.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }
            else if(clickedSecond==35){
                img36.setImageBitmap(genel.get(cardsArray[clickedSecond]).card);
            }

            int v = (int) ((45.0 - zaman) / 10);
            if(genel.get(cardsArray[clickedFirst]).House.equals(genel.get(cardsArray[clickedSecond]).House)){
                total-=((genel.get(cardsArray[clickedFirst]).Point+genel.get(cardsArray[clickedSecond]).Point)/genel.get(cardsArray[clickedFirst]).Katsayi)*v;
            }

            else {
                total -= (genel.get(cardsArray[clickedFirst]).Point + genel.get(cardsArray[clickedSecond]).Point) / 2 * genel.get(cardsArray[clickedFirst]).Katsayi * genel.get(cardsArray[clickedSecond]).Katsayi * v;
            }

        }
        skor.setText(String.valueOf(total));
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
        img17.setEnabled(true);
        img18.setEnabled(true);
        img19.setEnabled(true);
        img20.setEnabled(true);
        img21.setEnabled(true);
        img22.setEnabled(true);
        img23.setEnabled(true);
        img24.setEnabled(true);
        img25.setEnabled(true);
        img26.setEnabled(true);
        img27.setEnabled(true);
        img28.setEnabled(true);
        img29.setEnabled(true);
        img30.setEnabled(true);
        img31.setEnabled(true);
        img32.setEnabled(true);
        img33.setEnabled(true);
        img34.setEnabled(true);
        img35.setEnabled(true);
        img36.setEnabled(true);
        checkEnd();



    }

    private void checkEnd() {

        if(control[0]==1 && control[1]==2 && control[2]==3 &&
                control[3]==4 && control[4]==5 && control[5]==6 && control[7]==8 && control[8]==9 &&
                control[9]==10 && control[10]==11 && control[11]==12 && control[12]==13 && control[13]==14 && control[14]==15 && control[15]==16 &&
                control[16]==17 && control[17]==18 && control[18]==19 &&
                control[19]==20 && control[20]==21 && control[21]==22 && control[22]==23 && control[23]==24 &&
                control[24]==25 && control[25]==26 && control[26]==27 && control[27]==28 && control[28]==29 && control[29]==30 && control[30]==31 && control[31]==32 && control[32]==33 && control[33]==34 && control[35]==36){

            player3=MediaPlayer.create(OyunOynama6_6Activity.this,R.raw.congratulations);
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
            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(OyunOynama6_6Activity.this);
            sure.setText(String.valueOf(zaman));

            alertDialogBuilder.setMessage("Oyun Bitti. Skor:"+total).setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(getApplicationContext(),OyunOynama6_6Activity.class);
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
        image17=cardsArray[16];
        image18=cardsArray[17];
        image19=cardsArray[18];
        image20=cardsArray[19];
        image21=cardsArray[20];
        image22=cardsArray[21];
        image23=cardsArray[22];
        image24=cardsArray[23];
        image25=cardsArray[24];
        image26=cardsArray[25];
        image27=cardsArray[26];
        image28=cardsArray[27];
        image29=cardsArray[28];
        image30=cardsArray[29];
        image31=cardsArray[30];
        image32=cardsArray[31];
        image33=cardsArray[32];
        image34=cardsArray[33];
        image35=cardsArray[34];
        image36=cardsArray[35];
    }

    public  void writeToFile(String fileName)
    {
        int i=0;
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

