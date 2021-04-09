package com.example.pms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList guest_id, guest_title, guest_phone, guest_type;

    CustomAdapter(Activity activity, Context context, ArrayList guest_id, ArrayList guest_title, ArrayList guest_phone,
                  ArrayList guest_type){
        this.activity = activity;
        this.context = context;
        this.guest_id = guest_id;
        this.guest_title = guest_title;
        this.guest_phone = guest_phone;
        this.guest_type = guest_type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.guest_id_txt.setText(String.valueOf(guest_id.get(position)));
        holder.guest_title_txt.setText(String.valueOf(guest_title.get(position)));
        holder.guest_phone_txt.setText(String.valueOf(guest_phone.get(position)));
        holder.guest_type_txt.setText(String.valueOf(guest_type.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(guest_id.get(position)));
                intent.putExtra("title", String.valueOf(guest_title.get(position)));
                intent.putExtra("phone", String.valueOf(guest_phone.get(position)));
                intent.putExtra("type", String.valueOf(guest_type.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return guest_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView guest_id_txt, guest_title_txt, guest_phone_txt, guest_type_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            guest_id_txt = itemView.findViewById(R.id.guest_id_txt);
            guest_title_txt = itemView.findViewById(R.id.guest_title_txt);
            guest_phone_txt = itemView.findViewById(R.id.guest_phone_txt);
            guest_type_txt = itemView.findViewById(R.id.guest_type_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
