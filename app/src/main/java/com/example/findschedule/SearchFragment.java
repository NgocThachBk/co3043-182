package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements View.OnClickListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageButton imageButton;
    RecyclerView recyclerView;
    RecyclerView rcvPOM;
    DatabaseReference ref;
    DatabaseReference placeOfMonth;
    public ArrayList<DataItemPopular> listPopular ;
    public ArrayList<DataItemPopular> listPlaceOfMonth;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        imageButton = view.findViewById(R.id.search_frag);
        imageButton.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.rclPopular);
        rcvPOM = view.findViewById(R.id.rclPlaceMonth);
        final DataItemPopular dataItemPopular = new DataItemPopular();
        //dataItemPopular.addItem("https://firebasestorage.googleapis.com/v0/b/test2shadow.appspot.com/o/Image%2Fbai-bien-my-khe.jpg?alt=media&token=3d506d9a-6051-463b-9e38-71260a41c565",listPopular);
       // dataItemPopular.addItem("https://firebasestorage.googleapis.com/v0/b/test2shadow.appspot.com/o/Image%2Fbai-bien-my-khe.jpg?alt=media&token=3d506d9a-6051-463b-9e38-71260a41c565",listPopular);
        //dataItemPopular.addItem("https://firebasestorage.googleapis.com/v0/b/test2shadow.appspot.com/o/Image%2Fbai-bien-my-khe.jpg?alt=media&token=3d506d9a-6051-463b-9e38-71260a41c565",listPopular);
        //dataItemPopular.addItem("https://firebasestorage.googleapis.com/v0/b/test2shadow.appspot.com/o/Image%2Fbai-bien-my-khe.jpg?alt=media&token=3d506d9a-6051-463b-9e38-71260a41c565",listPopular);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        ref =  firebaseDatabase.getReference("PlacePopular");
        placeOfMonth = firebaseDatabase.getReference("PlaceOfMonth");

        ValueEventListener eventListener = new ValueEventListener( ) {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPopular = new ArrayList<DataItemPopular>();
                DataItemPopular dataValue;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                   dataValue = ds.getValue(DataItemPopular.class);
                   listPopular.add(dataValue);
                }
                SearchFragment.ContactsAdapter adapter = new SearchFragment.ContactsAdapter(listPopular);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        ValueEventListener monthPlace = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPlaceOfMonth = new ArrayList<DataItemPopular>();
                DataItemPopular dataValue;
                DataSnapshot getPlace = dataSnapshot.child("ListPlace");
                for(DataSnapshot ds: getPlace.getChildren()){
                    dataValue = ds.getValue(DataItemPopular.class);
                    listPlaceOfMonth.add(dataValue);
                }

                ListPlaceOfMonth listPlace;
                listPlace = new ListPlaceOfMonth(listPlaceOfMonth);

                rcvPOM.setAdapter(listPlace);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        ref.addListenerForSingleValueEvent(eventListener);
        placeOfMonth.addListenerForSingleValueEvent(monthPlace);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(),ShowPlace.class);

        this.startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public static class ContactsAdapter extends
            RecyclerView.Adapter<SearchFragment.ContactsAdapter.ViewHolder> {


        private static List<DataItemPopular> listPopular;
        private static ArrayList<Data2> dataSend;
        public static Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {


            public ImageView viewPopular;
            public TextView textPopular;
            public LinearLayout itemPopular;

            public ViewHolder(View itemView) {

                super(itemView);

                viewPopular = itemView.findViewById(R.id.imgPopular);
                textPopular = itemView.findViewById(R.id.tvPopular);
                itemPopular = itemView.findViewById(R.id.itemPopular);
            }
        }




        // Pass in the contact array into the constructor
        public ContactsAdapter(ArrayList<DataItemPopular> contacts) {
            listPopular = contacts;
            //dataSend = contacts;
        }


        @Override
        public SearchFragment.ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.item_popular, parent, false);

            SearchFragment.ContactsAdapter.ViewHolder viewHolder = new SearchFragment.ContactsAdapter.ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,final int i) {

            ImageView imageView;
            TextView textView;
            imageView = viewHolder.viewPopular;
            textView = viewHolder.textPopular;
            Glide.with(context).load(listPopular.get(i).getLinkImage()).into(imageView);
            textView.setText(listPopular.get(i).getName());

            viewHolder.itemPopular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,NumberSchedule.class);
                    Bundle bundle = new Bundle();

                    intent.putExtra("namePlace",listPopular.get(i).getName());
                    context.startActivity(intent);
                    //Toast.makeText(context,"xxx",Toast.LENGTH_SHORT).show();
                }
            });
        }


        // Return the total count of items
        @Override
        public int getItemCount() {
            return listPopular.size();
        }
    }

}
