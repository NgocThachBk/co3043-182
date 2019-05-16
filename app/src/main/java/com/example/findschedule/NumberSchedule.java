package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NumberSchedule extends AppCompatActivity {
    public static String namePlace;
    EditText editText;
    RecyclerView rvContacts;
    DatabaseReference ref;
    Data1 data1;
    DataExtra dataExtra;
    ArrayList<Data3> listSchedule ;
     ArrayList<DataExtra> placeList;
    public static ArrayList<Data2> dayList ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_schedule);
        Intent intent = this.getIntent();

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts_main);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        ref =  firebaseDatabase.getReference("Place");

        namePlace = intent.getStringExtra("namePlace");

        Log.d("namexxx",namePlace);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        toolbar.setTitle("Lịch Trình " + namePlace);


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String linkImage;
                //ArrayList<String> listImage = new ArrayList<String>();
                DataSnapshot place = dataSnapshot.child(namePlace);
                listSchedule = new ArrayList<Data3>();
                dayList = new ArrayList<Data2>();

                for(DataSnapshot ds: place.getChildren()){
                    placeList = new ArrayList<DataExtra>();
                    listSchedule.add(new Data3(namePlace,0));
                    for(DataSnapshot dh:ds.getChildren()){
                        ArrayList<String> listImage = new ArrayList<String>();
                        dataExtra = new DataExtra();
                        String key = dh.getKey();
                        Log.d("key",key);


                        data1 = dh.getValue(Data1.class);
                        //Log.d("hhh",data1.getname());


                        DataSnapshot dataListImage = dh.child("ListImage");
                        for(DataSnapshot dx : dataListImage.getChildren()){
                            linkImage = (String) dx.getValue();
                            listImage.add(linkImage);
                        }

                        Log.d("image",listImage.toString());
                        dataExtra.setObject(data1);
                        dataExtra.setListImage(listImage);
                        placeList.add(dataExtra);
                    }
                    Data2.addItem(placeList,dayList);
                }
                //Log.d("list",placeList.toString());
                //Log.d("aaa",listplace.toString());
                if(listSchedule.size() > 0){
                    ContactsAdapter adapter = new ContactsAdapter(listSchedule);
                    rvContacts.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        ref.addListenerForSingleValueEvent(eventListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_back_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            Intent intent = new Intent(this,MainActivity.class);

            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public static class ContactsAdapter extends
            RecyclerView.Adapter<ShowPlace.ContactsAdapter.ViewHolder> {

        //private static List<Contact> mContacts;
        private static List<Data3> listPlace;
        //private static ArrayList<Contact> dataSend;
        //private static ArrayList<Data2> dataSend;
        public static Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {


            public TextView numberSchedule;
            public ImageView xxx;
            public LinearLayout linearLayout;



            public ViewHolder(View itemView) {

                super(itemView);

                linearLayout = itemView.findViewById(R.id.linePlace);

                numberSchedule = itemView.findViewById(R.id.numberSchedule);
                xxx = itemView.findViewById(R.id.imgTitle);

            }
        }




        // Pass in the contact array into the constructor
        public ContactsAdapter(ArrayList<Data3> contacts) {
            listPlace = contacts;
            //dataSend = contacts;
        }


        @Override
        public ShowPlace.ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.item_number_schedule, parent, false);

            ShowPlace.ContactsAdapter.ViewHolder viewHolder = new ShowPlace.ContactsAdapter.ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ShowPlace.ContactsAdapter.ViewHolder viewHolder, final int position) {

            //ListPlace listPlace;


            //Contact contact = mContacts.get(position);
            //Data2 contact = mContacts.get(position);


            final Data3 value = listPlace.get(position);
            //TextView name = viewHolder.namePlace;
            TextView number = viewHolder.numberSchedule;
            ImageView imageView = viewHolder.xxx;
            Glide.with(context).load(dayList.get(position).getPlaceItems().get(0).getImage()).into(imageView);
            //imageView.setBackgroundResource(R.drawable.iamgeday5);
            //name.setText(value.getName());
            number.setText( String.valueOf(position + 1) + " ngày");
            if(position == 0){
                //viewHolder.linearLayout.setBackgroundResource(R.drawable.imageday1);
                }
            else if(position == 1){
                //viewHolder.linearLayout.setBackgroundResource(R.drawable.image12);
            }
            else if(position ==2){
                //viewHolder.linearLayout.setBackgroundResource(R.drawable.imageday13);
            }


            //RecyclerView recyclerView = viewHolder.recyclerView;
            //listPlace = new ListPlace(contact.getListItems());
            //recyclerView.setAdapter(listPlace);

            //listPlace = new ListPlace(contact.getListItems());
            //listPlace = new ListPlace(contact.getPlaceItems());
            //recyclerView.setAdapter(listPlace);


            //.setText("Ngày " + String.valueOf(position + 1));


            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ShowSchedule.class);
                    Bundle bundle = new Bundle();
                    ArrayList<Data2> dayListSplit = new ArrayList<Data2>();
                    for(Integer i =0; i <= position;i++ ){
                        dayListSplit.add(dayList.get(i));
                    }
                    intent.putExtra("dayList",dayListSplit);
                    intent.putExtra("numDays",position+1);
                    intent.putExtra("namePlace",namePlace);
                    //intent.putExtra("position",position);
                    context.startActivity(intent);
                    //Toast.makeText(context,"xxx",Toast.LENGTH_SHORT).show();
                }
            });



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
            return listPlace.size();
        }
    }
}
