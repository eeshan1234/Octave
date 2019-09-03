package com.example.octave;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
ImageView b1,b2;
 MediaPlayer mediaPlayer=new MediaPlayer();
DatabaseReference db= FirebaseDatabase.getInstance().getReference("Audio");
    DatabaseReference db1= FirebaseDatabase.getInstance().getReference("Audio");
    int flag=0;
    TextView t1,t2;
    Uri mediauri,imageuri;
    ProgressBar progressBar;
    SeekBar seekBar;
    ImageView img;
    String a;
    String title,desc,ilink,mlink;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.pbar);
       // seekBar=findViewById(R.id.sbar);
        img=findViewById(R.id.img);
        b1=findViewById(R.id.play);
        b2=findViewById(R.id.pause);
        t1=(TextView) findViewById(R.id.title);
        t2=findViewById(R.id.desc);
        progressBar.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.INVISIBLE);


    a=getIntent().getStringExtra("position");
     // int pos=Integer.parseInt(a);
       // Toast.makeText(this, "Buffering ! Please wait .....", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
       db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot x: dataSnapshot.getChildren())
                {

                    title=dataSnapshot.child(a).child("title").getValue().toString();
                    desc=dataSnapshot.child(a).child("desc").getValue().toString();
                  // ilink=dataSnapshot.child("image").getValue().toString();

                }


               // imageuri=Uri.parse(ilink);

                t1.setText(title);
                t2.setText(desc);
                if(t1.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);

                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);

//                    b1.setVisibility(View.INVISIBLE);
//                    b2.setVisibility(View.VISIBLE);
                }
              //  img.setImageURI(imageuri);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mlink=dataSnapshot.child(a).child("link").getValue().toString();

                mediauri=Uri.parse(mlink);
               try {
                  // mediaPlayer.setDataSource("");
                   progressBar.setVisibility(View.INVISIBLE);
                   mediaPlayer=MediaPlayer.create(MainActivity.this,mediauri);
                  // seekBar.setMax(mediaPlayer.getDuration());

                   b1.setOnClickListener(new View.OnClickListener() {


                       @Override
                       public void onClick(View v) {
                          // Toast.makeText(MainActivity.this, "Playing...", Toast.LENGTH_SHORT).show();



                            final Handler handler=new Handler();

                           mediaPlayer.start();

//                            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                                @Override
//                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//                                        mediaPlayer.seekTo(progress*1000);
//                                        seekBar.setProgress(progress);
//                                }
//
//                                @Override
//                                public void onStartTrackingTouch(SeekBar seekBar) {
//                                   // seekBar.setVisibility(View.VISIBLE);
//                                }
//
//                                @Override
//                                public void onStopTrackingTouch(SeekBar seekBar) {
//
//                                }
//                            });
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    while (mediaPlayer!=null)
//                                    {
//                                       try{
//
//                                           if(mediaPlayer.isPlaying())
//                                           {
//                                               Message message=new Message();
//                                               message.what=mediaPlayer.getCurrentPosition();
//                                               handler.sendMessage(message);
//                                               Thread.sleep(1000);
//                                           }
//                                       }
//                                       catch (InterruptedException e)
//                                       {
//                                           Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
//                                       }
//                                    }
//                                }
//                            }).run();
//



                           b1.setVisibility(View.INVISIBLE);
                           b2.setVisibility(View.VISIBLE);
                       }
                   });
                   b2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if(mediaPlayer.isPlaying())
                           {
                               mediaPlayer.pause();
                               b1.setVisibility(View.VISIBLE);
                               b2.setVisibility(View.INVISIBLE);
                           }

                       }
                   });
               }
               catch (Exception e)
               {
                   Toast.makeText(MainActivity.this,"Err= "+e,Toast.LENGTH_LONG).show();
               }
//

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
