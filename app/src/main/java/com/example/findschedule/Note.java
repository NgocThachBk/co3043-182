package com.example.findschedule;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Note#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Note extends Fragment implements ItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;

    ArrayList<NoteItem> arrayList;
    NoteAdapter noteAdapter;

    DatabaseHelper databaseHelper;

    int currentIndex = -1;

    int maxId;

    private OnFragmentInteractionListener mListener;

    public Note() {
        // Required empty public constructor
    }

    public void setContext(Context context) {
        this.context = context;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Note.
     */
    // TODO: Rename and change types and number of parameters
    public static Note newInstance(String param1, String param2) {
        Note fragment = new Note();
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
        setHasOptionsMenu(true);


        //setSupportActionBar(toolbar);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.recycle_view_notes);


        databaseHelper = new DatabaseHelper(getContext());
        Log.i("db", "create db success");
        arrayList = new ArrayList<>();
        initView(recyclerView);

        FloatingActionButton add_note_bnt = view.findViewById(R.id.add_note_btn);
        add_note_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddNote();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clearall) {
            int items = arrayList.size();
            arrayList.clear();
            if (noteAdapter != null) {
                noteAdapter.notifyItemRangeChanged(0, items);
            }
            databaseHelper.deleteData("note", "1 = 1");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getDataDbs() {
        try {
            Cursor c = databaseHelper.getData("SELECT * FROM note");

            int idIndex = c.getColumnIndex("ID");
            int placeIndex = c.getColumnIndex("place");
            int start_dayIndex = c.getColumnIndex("start_day");
            int end_dayIndex = c.getColumnIndex("end_day");
            int costIndex = c.getColumnIndex("cost");
            int memberIndex = c.getColumnIndex("member");

            c.moveToFirst();

            while (c != null) {
                Integer id = Integer.parseInt(c.getString(idIndex));
                int member = Integer.parseInt(c.getString(memberIndex));

                arrayList.add(new NoteItem(c.getString(placeIndex), c.getString(start_dayIndex), c.getString(end_dayIndex), member, c.getInt(costIndex), c.getInt(idIndex)));
                Log.i("aaa", arrayList.toString());
                maxId = id;

                c.moveToNext();
            }
        } catch (Exception e) {
            Log.i("in getDataDbs", "can't get data");
            e.printStackTrace();
        }
    }

    public void initView(RecyclerView recyclerView) {

        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        arrayList.add(new NoteItem("Vũng Tàu", "12/12/1998", "14/12/1998", 4));
//        arrayList.add(new NoteItem("Đà Nẵng", "1/1/2015", "5/1/2015", 5));
//        arrayList.add(new NoteItem("Vũng Tàu", "12/12/1998", "14/12/1998", 4));
//        arrayList.add(new NoteItem("Vũng Tàu", "12/12/1998", "14/12/1998", 4));
//        arrayList.add(new NoteItem("Vũng Tàu", "12/12/1998", "14/12/1998", 4));

        getDataDbs();

        noteAdapter = new NoteAdapter(arrayList, context);
        noteAdapter.setClickListener(this);
        recyclerView.setAdapter(noteAdapter);


    }

    public void addNote(String place_str, String start_day_str, String end_day_str, int member_int, int cost) {

        Object []oj = new Object[5];
        oj[0] = place_str;
        oj[1] = start_day_str;
        oj[2] = end_day_str;
        oj[3] = cost;
        oj[4] = member_int;
        boolean insertDB =  databaseHelper.addData("note", oj);
        if(insertDB) {
            Log.i("insert", "insert successful");
            maxId++;
        }

        NoteItem note = new NoteItem(place_str, start_day_str, end_day_str, member_int, cost, maxId);
        arrayList.add(note);
        noteAdapter.notifyItemInserted(arrayList.size() - 1);
    }

    public void DialogAddNote(){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_note);

        final EditText place = dialog.findViewById(R.id.place_dialog);
        final EditText member = dialog.findViewById(R.id.member_dialog);
        final EditText start_day = dialog.findViewById(R.id.start_day_dialog);
        final EditText end_day = dialog.findViewById(R.id.end_day_dialog);

        Button them_note_btn = dialog.findViewById(R.id.them_note_btn);
        Button huy_btn = dialog.findViewById(R.id.huy_bnt);

        them_note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place_str = place.getText().toString();
                String start_day_str = start_day.getText().toString();
                String end_day_str = end_day.getText().toString();
                Integer member_int = 0;
                if (!member.getText().toString().equals("")) {
                    member_int = Integer.parseInt(member.getText().toString());
                }
                if (place_str.equals("") || start_day_str.equals("") || end_day_str.equals("") || member_int <= 0 )
                {
                    Toast.makeText(getContext(), "Bạn chưa điền đủ dữ liệu kìa :v", Toast.LENGTH_SHORT).show();
                }
                else {
                    addNote(place_str, start_day_str, end_day_str, member_int, 0);
                    dialog.dismiss();
                }
            }
        });

        huy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(currentIndex != -1) {
            try {
                Cursor c = databaseHelper.getData("SELECT * FROM note WHERE ID = " + Integer.toString(arrayList.get(currentIndex).getId()));
                int costIndex = c.getColumnIndex("cost");
                c.moveToFirst();

                if (c != null) {
                    arrayList.get(currentIndex).setCost(Integer.parseInt(c.getString(costIndex)));
                    noteAdapter.notifyItemChanged(currentIndex);
                }

            } catch (Exception e) {
                Log.i("in get cost onResum", "can't get data");
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onClickMember(View view, int position) {

    }

    @Override
    public void onClickMoney(View view, int position) {

    }

    @Override
    public void onClickItem(View view, int position) {
        currentIndex = position;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


//    public Toolbar getSupportActionBar() {
//        return supportActionBar;
//    }

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
}
