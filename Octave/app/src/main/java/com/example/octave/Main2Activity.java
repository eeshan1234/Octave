package com.example.octave;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<FireModel> list;
    RecyclerView recyclerView;

    int i=0;
    String title,desc,link;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recycle);

        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Audio");
      //  progressBar=findViewById(R.id.pbar);


        recyclerView.clearOnScrollListeners();
        recyclerView.clearOnChildAttachStateChangeListeners();
       // list.clear();
        list=new ArrayList<FireModel>();

        db.addValueEventListener(new ValueEventListener() {
            FireModel fireModel=new FireModel();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FireModel value;
                list.clear();
                for(DataSnapshot x:dataSnapshot.getChildren())
                {
                  value=x.getValue(FireModel.class);
                  list.add(value);
                   // Toast.makeText(Main2Activity.this, "item: "+list.get(i).title, Toast.LENGTH_LONG).show();

                }
                recyclerView.clearOnScrollListeners();
                recyclerView.clearOnChildAttachStateChangeListeners();


                MyAdapter myAdapter= new MyAdapter(list);
                RecyclerView.LayoutManager recyce=new GridLayoutManager(recyclerView.getContext(),1);
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
