package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class FragmentList extends
        RecyclerView.Adapter<FragmentList.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public ImageView imageView;
        public TextView timeTextView;
        public TextView timeTQ;
        public CardView cardView;



        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.textName);
            imageView = (ImageView) itemView.findViewById(R.id.imgPlaceFragment);
            timeTextView = itemView.findViewById(R.id.tvTime);
            timeTQ = itemView.findViewById(R.id.timeThamQuan);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    private List<DataExtra> mContacts;
    public static Context context;

    // Pass in the contact array into the constructor
    public FragmentList(List<DataExtra> contacts) {
        mContacts = contacts;
    }


    @Override
    public FragmentList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.fragment_recycle_item, parent, false);

        FragmentList.ViewHolder viewHolder = new FragmentList.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FragmentList.ViewHolder viewHolder, int position) {

        final DataExtra contact = mContacts.get(position);
        ImageView imageView = viewHolder.imageView;
        TextView textView = viewHolder.nameTextView;
        TextView tvStart = viewHolder.timeTextView;
        TextView timeTQ = viewHolder.timeTQ;
        textView.setText(contact.getname());
        tvStart.setText(contact.getTimeStart());
        timeTQ.setText("T/g Tham Quan : " + contact.getPeriodOfTime());



        Glide.with(context).load(contact.getImage()).into(imageView);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainDetailPlace.class);
                Bundle bundle = new Bundle();

                intent.putExtra("namePlace",contact.getname());
                intent.putExtra("listImage",contact.getListImage());
                intent.putExtra("imageTitle",contact.getImage());
                //intent.putExtra("position",position);
                //intent.putExtra("numDay",numDay);
                context.startActivity(intent);
                //Toast.makeText(context,"xxx",Toast.LENGTH_SHORT).show();
            }
        });



    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}