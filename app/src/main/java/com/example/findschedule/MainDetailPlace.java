package com.example.findschedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainDetailPlace extends AppCompatActivity
        implements SummaryTab.OnFragmentInteractionListener,DescriptionTab.OnFragmentInteractionListener {
    ImageView titltImg;

    String placeName;
    String imageTitle;
    TextView textView;
    ArrayList<String> listImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        textView = findViewById(R.id.tvTitle);

        Intent intent = this.getIntent();

        placeName = intent.getStringExtra("namePlace");
        imageTitle = intent.getStringExtra("imageTitle");
        listImage = (ArrayList<String>)intent.getSerializableExtra("listImage");



        textView.setText(placeName);

        titltImg = (ImageView) findViewById(R.id.titltImg);

        Glide.with(this).load(imageTitle).into(titltImg);

        titltImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDetailPlace.this,SlideActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("listImage",listImage);
                startActivity(intent);
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tổng quan"));
        tabLayout.addTab(tabLayout.newTab().setText("Mô tả"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
