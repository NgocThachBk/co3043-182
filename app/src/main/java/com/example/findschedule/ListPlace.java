package com.example.findschedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class ListPlace extends
        RecyclerView.Adapter<ListPlace.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public ImageView imageView;
        public Context contextx;



        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.itemName);
            imageView = (ImageView) itemView.findViewById(R.id.profile_image);
        }
    }

    //private List<Placeitem> mContacts;

    private List<DataExtra> mContacts;

    public static Context context;

    // Pass in the contact array into the constructor
//        public ListPlace(List<Placeitem> contacts) {
//            mContacts = contacts;
//        }

    public ListPlace(List<DataExtra> contacts) {
        mContacts = contacts;
    }


    @Override
    public ListPlace.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_recyclerview, parent, false);

        ListPlace.ViewHolder viewHolder = new ListPlace.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListPlace.ViewHolder viewHolder, int position) {

        //Placeitem contact = mContacts.get(position);
        DataExtra contact = mContacts.get(position);
        ImageView imageView = viewHolder.imageView;
        TextView textView = viewHolder.nameTextView;
        //textView.setText(contact.getName());
        textView.setText(contact.getname());

        //StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("langculan.jpeg");
        Glide.with(context).load(contact.getImage()).into(imageView);

        //imageView.setImageResource(contact.getLink());

        //Button button = viewHolder.messageButton;

//            if (contact.isOnline()) {
//                button.setText("Message");
//                button.setEnabled(true);
//            }
//            else {
//                button.setText("Offline");
//                button.setEnabled(false);
//            }

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}