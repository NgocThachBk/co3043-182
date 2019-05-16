package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowSchedule extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Data2> dayList = new ArrayList<Data2>();
    public static Integer numDay;
    Button btnChiPhi;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_schedule);

        Intent intent = this.getIntent();

        dayList = (ArrayList<Data2>)intent.getSerializableExtra("dayList");
        numDay = intent.getIntExtra("numDays",1);
        String namePlace = intent.getStringExtra("namePlace");

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        toolbar.setTitle("Lịch Trình " + namePlace + " " + String.valueOf(numDay) + " ngày");

        btnChiPhi = findViewById(R.id.btnChiPhi);

        btnChiPhi.setOnClickListener(this);

        final RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts_main);

        ContactsAdapter adapter = new ContactsAdapter(dayList);

        rvContacts.setAdapter(adapter);

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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainChiPhi.class);
        Bundle bundle = new Bundle();

        intent.putExtra("ChiPhi",dayList);
        this.startActivity(intent);
    }

    public static class ContactsAdapter extends
            RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

        //private static List<Contact> mContacts;
        private static List<Data2> mContacts;
        //private static ArrayList<Contact> dataSend;
        private static ArrayList<Data2> dataSend;
        public static Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            //private List<Contact> mContacts;
            public TextView nameTextView;
            public Button messageButton;
            public RecyclerView recyclerView;
            public ListPlace listPlace;
            public LinearLayout linearLayout;
            public TextView numberPlace;

            public ViewHolder(View itemView) {

                super(itemView);
                //mContacts = Contact.createContactsList(20);
                recyclerView = itemView.findViewById(R.id.rvContacts);
                linearLayout = itemView.findViewById(R.id.linePlace);
                numberPlace = itemView.findViewById(R.id.numberDiaDiem);
                //listPlace = new ListPlace(mContacts);
                //recyclerView.setAdapter(listPlace);

                nameTextView = (TextView) itemView.findViewById(R.id.soNgay);
                //messageButton = (Button) itemView.findViewById(R.id.message_button);
            }
        }




        // Pass in the contact array into the constructor
        public ContactsAdapter(ArrayList<Data2> contacts) {
            mContacts = contacts;
            dataSend = contacts;
        }


        @Override
        public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.item_main_recyclerview, parent, false);

            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, final int position) {

            ListPlace listPlace;


            //Contact contact = mContacts.get(position);
            Data2 contact = mContacts.get(position);
            Integer numberPlace = contact.getPlaceItems().size();

            TextView textView = viewHolder.nameTextView;
            TextView numberDiaDiem = viewHolder.numberPlace;

            RecyclerView recyclerView = viewHolder.recyclerView;
            //listPlace = new ListPlace(contact.getListItems());
            //recyclerView.setAdapter(listPlace);

            //listPlace = new ListPlace(contact.getListItems());
            listPlace = new ListPlace(contact.getPlaceItems());
            recyclerView.setAdapter(listPlace);


            textView.setText("Ngày " + String.valueOf(position + 1));
            numberDiaDiem.setText(String.valueOf(numberPlace) +" địa điểm");


            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,MainFragment.class);
                    Bundle bundle = new Bundle();

                    intent.putExtra("listPlace",dataSend);
                    intent.putExtra("position",position);
                    intent.putExtra("numDay",numDay);
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
            return mContacts.size();
        }
    }

}
