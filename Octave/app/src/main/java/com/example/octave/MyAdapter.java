package com.example.octave;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<FireModel> userList;
    LinearLayout linearLayout;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Audio");
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc=view.findViewById(R.id.desc);
            linearLayout=view.findViewById(R.id.id);


        }
    }


    public MyAdapter(List<FireModel> moviesList) {
        this.userList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        FireModel user = userList.get(position);
        holder.title.setText(user.getTitle());
        holder.desc.setText(user.getDesc());

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(v.getContext(), "Opening! Please wait.....", Toast.LENGTH_LONG).show();

                Intent i=new Intent(v.getContext(),MainActivity.class);
                i.putExtra("position",""+(position+1));
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}