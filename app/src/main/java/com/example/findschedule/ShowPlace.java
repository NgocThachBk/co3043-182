package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowPlace extends AppCompatActivity implements View.OnClickListener {

    ImageButton button;
    EditText editText;
    RecyclerView rvContacts;
    ArrayList<Data3> listplace;
    DatabaseReference ref;
    String textSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_place);

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts_main);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        ref =  firebaseDatabase.getReference("Place");
        button = findViewById(R.id.btnSearch);
        editText = findViewById(R.id.edInput);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                textSearch = editText.getText().toString();
                Log.d("testChuoi",textSearch);

                if(!textSearch.isEmpty()){

                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            listplace = new ArrayList<Data3>();
                            Log.d("ggg",dataSnapshot.getKey());
                            String key;
                            for(DataSnapshot ds: dataSnapshot.getChildren()){
                                key = ds.getKey();
                                Log.d("key",key);
                                if(key.toLowerCase().contains(textSearch.toLowerCase())){
                                    long number = ds.getChildrenCount();
                                    listplace.add(new Data3(key, number));
                                }
                            }

                            Log.d("aaa",listplace.toString());
                            ContactsAdapter adapter = new ContactsAdapter(listplace);
                            rvContacts.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };

                    ref.addListenerForSingleValueEvent(eventListener);
                }
                else{
                    Log.d("nill","yyyy");
                    listplace = new ArrayList<Data3>();
                    ContactsAdapter adapter = new ContactsAdapter(listplace);
                    rvContacts.setAdapter(adapter);
                }
            }
        });

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        textSearch = editText.getText().toString();
//        if(textSearch != ""){
//            listplace = new ArrayList<Data3>();
//            ValueEventListener eventListener = new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Log.d("ggg",dataSnapshot.getKey());
//                    String key;
//                    for(DataSnapshot ds: dataSnapshot.getChildren()){
//                        key = ds.getKey();
//                        Log.d("key",key);
//                        if(key.toLowerCase().contains(textSearch.toLowerCase())){
//                            long number = ds.getChildrenCount();
//                            listplace.add(new Data3(key, number));
//                        }
//                    }
//
//                    Log.d("aaa",listplace.toString());
//                    ContactsAdapter adapter = new ContactsAdapter(listplace);
//                    rvContacts.setAdapter(adapter);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            };
//
//            ref.addListenerForSingleValueEvent(eventListener);
//        }
    }


    public static class ContactsAdapter extends
            RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

        //private static List<Contact> mContacts;
        private static List<Data3> listPlace;
        //private static ArrayList<Contact> dataSend;
        //private static ArrayList<Data2> dataSend;
        public static Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            //private List<Contact> mContacts;
            public TextView namePlace;
            public TextView numberSchedule;
            //public Button messageButton;
            //public RecyclerView recyclerView;
            //public ListPlace listPlace;
            public LinearLayout linearLayout;
            public ImageView xxx;

            public ViewHolder(View itemView) {

                super(itemView);
                //mContacts = Contact.createContactsList(20);
                //recyclerView = itemView.findViewById(R.id.rvContacts);
                linearLayout = itemView.findViewById(R.id.linePlace);
                //listPlace = new ListPlace(mContacts);
                //recyclerView.setAdapter(listPlace);
                namePlace = itemView.findViewById(R.id.namePlace);
                numberSchedule = itemView.findViewById(R.id.numberSchedule);
                //nameTextView = (TextView) itemView.findViewById(R.id.soNgay);
                //messageButton = (Button) itemView.findViewById(R.id.message_button);
                xxx = itemView.findViewById(R.id.imgTitle);
            }
        }




        // Pass in the contact array into the constructor
        public ContactsAdapter(ArrayList<Data3> contacts) {
            listPlace = contacts;
            //dataSend = contacts;
        }


        @Override
        public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.item_show_place, parent, false);

            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, final int position) {

            //ListPlace listPlace;


            //Contact contact = mContacts.get(position);
            //Data2 contact = mContacts.get(position);


            final Data3 value = listPlace.get(position);
            TextView name = viewHolder.namePlace;
            TextView number = viewHolder.numberSchedule;

            name.setText(value.getName());
            number.setText("Có " + String.valueOf(value.getCount()) + " Lịch Trình");

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
                    Intent intent = new Intent(context,NumberSchedule.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("namePlace",value.getName());
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
