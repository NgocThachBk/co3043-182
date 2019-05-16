package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.List;

public class ListPlaceOfMonth extends RecyclerView.Adapter<ListPlaceOfMonth.ViewHolder> {

    private List<DataItemPopular> placeOfMonth;

    public static Context context;

    public ListPlaceOfMonth(List<DataItemPopular> place){
        this.placeOfMonth = place;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_placeofmonth, viewGroup, false);
        ListPlaceOfMonth.ViewHolder viewHolder = new ListPlaceOfMonth.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {

        ImageView imageView;
        TextView textView;
        imageView = viewHolder.imgPlace;
        textView = viewHolder.textPlace;
        Glide.with(context).load(placeOfMonth.get(i).getLinkImage()).into(imageView);
        textView.setText(placeOfMonth.get(i).getName());

        viewHolder.itemPlaceOfMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NumberSchedule.class);
                Bundle bundle = new Bundle();

                intent.putExtra("namePlace",placeOfMonth.get(i).getName());
                context.startActivity(intent);
                //Toast.makeText(context,"xxx",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return placeOfMonth.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textPlace;
        public ImageView imgPlace;
        public LinearLayout itemPlaceOfMonth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPlace = itemView.findViewById(R.id.imgPlace);
            textPlace = itemView.findViewById(R.id.tvName);
            itemPlaceOfMonth = itemView.findViewById(R.id.itemPlaceOfMonth);
        }
    }
}
