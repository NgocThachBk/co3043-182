package com.example.findschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    //private ArrayList<Contact> dataReceive;
    private ArrayList<Data2> dataReceive;
    private int position;

    public static Integer numDay;

    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = this.getIntent();

        dataReceive = new ArrayList<Data2>();
        dataReceive = (ArrayList<Data2>)intent.getSerializableExtra("listPlace");
        position = intent.getIntExtra("position",0);
        numDay = intent.getIntExtra("numDay",1);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),dataReceive,numDay);

        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(position);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



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

    public static class PlaceholderFragment extends Fragment {

        private static final String KEY_COLOR = "key_color";
        private static List<Data2> contacts;

        public PlaceholderFragment() {
        }

        // Method static dạng singleton, cho phép tạo fragment mới, lấy tham số đầu vào để cài đặt màu sắc.
        public static PlaceholderFragment newInstance(int color,ArrayList<Data2> mcontacts) {
            contacts = mcontacts;
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt("aaa", color);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            //List<ContactTest> mContacts;
            TextView nameTextView;
            Button messageButton;
            //RecyclerView recyclerView;
            //ListFragment listFragment;
            ListPlace listPlace;
            FragmentList fragmentList;

            //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rlfragment);

            View rootView = inflater.inflate(R.layout.fragment_recycle, container, false);
            RecyclerView recyclerView = rootView.findViewById(R.id.rvContacts_main_fragment);

            /**
             * Số 1: Màu xanh.
             * Số 2: Màu đỏ.
             * Số 3: Màu vàng.
             */

            //mContacts = ContactTest.createContactsList(20);
            //recyclerView = itemView.findViewById(R.id.rvContacts);
            //listFragment = new ListFragment(contacts);
            //recyclerView.setAdapter(listFragment);

//            switch (getArguments().getInt("aaa")) {
//                case 1:
//                    relativeLayout.setBackgroundColor(Color.BLUE);
//                    break;
//                case 2:
//                    relativeLayout.setBackgroundColor(Color.RED);
//                    break;
//                case 3:
//                    relativeLayout.setBackgroundColor(Color.YELLOW);
//                    break;
//                default:
//                    relativeLayout.setBackgroundColor(Color.GREEN);
//                    break;
//            }
//
//            TextView textView = (TextView) rootView.findViewById(R.id.sectionlabel);
//            textView.setText("Kteam");

            switch (getArguments().getInt("aaa")) {
                case 1:
                    fragmentList = new FragmentList(contacts.get(0).getPlaceItems());
                    recyclerView.setAdapter(fragmentList);

                    break;
                case 2:
                    fragmentList = new FragmentList(contacts.get(1).getPlaceItems());
                    recyclerView.setAdapter(fragmentList);

                    break;
                case 3:
                    fragmentList = new FragmentList(contacts.get(2).getPlaceItems());
                    recyclerView.setAdapter(fragmentList);

                    break;
                case 4:
                    fragmentList = new FragmentList(contacts.get(3).getPlaceItems());
                    recyclerView.setAdapter(fragmentList);

                    break;
                default:
                    //recyclerView.setAdapter(listFragment);
                    break;
            }

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Data2> mcontacts;
        Integer numberTitle;

        public SectionsPagerAdapter(FragmentManager fm,ArrayList<Data2> contacts,Integer numTitle) {
            super(fm);
            mcontacts = contacts;
            numberTitle = numTitle;
        }

        @Override
        public Fragment getItem(int position) {
            // position + 1 vì position bắt đầu từ số 0.
            return PlaceholderFragment.newInstance(position + 1,mcontacts);
        }

        @Override
        public int getCount() {
            return numberTitle;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "Ngày 1";
                case 1:

                    return "Ngày 2";
                case 2:

                    return "Ngày 3";
                case 3:
                    return "Ngày 4";
            }
            return null;
        }
    }
}
