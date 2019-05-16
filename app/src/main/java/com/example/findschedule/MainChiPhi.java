package com.example.findschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainChiPhi extends AppCompatActivity {

    Spinner spinnerDS_xe;
    ImageButton back_btn;
    ImageButton menu_btn;
    ImageButton swap_btn;
    Button search_btn;
    EditText ddi;
    EditText dden;
    LinearLayout bottomsheet;
    BottomSheetBehavior bottomSheetBehavior;
    Context context;
    ListView lv;
    ListView ad;
    final ArrayList<Data1> placeList = new ArrayList<Data1>();
    private ArrayList<Data2> dataReceive;





    //final ArrayList<diadiem> placeList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_phi);

        Intent intent = this.getIntent();

        dataReceive = new ArrayList<Data2>();
        dataReceive = (ArrayList<Data2>)intent.getSerializableExtra("ChiPhi");

        Log.d("testne","xxx");

        Log.d("mangsand",dataReceive.get(0).getPlaceItems().get(0).getAteMoney());

        placeAdapter padapter = new placeAdapter(this,R.layout.row_item_lo,placeList);
        for (int i = 0; i < dataReceive.size(); i++){
            for (int j = 0; j < dataReceive.get(i).getPlaceItems().size(); j ++){
                Data1 getMoney = new Data1();
                if (j == 0)
                    getMoney.setPeriodOfTime("Ngày " + (i + 1));
                getMoney.setname(dataReceive.get(i).getPlaceItems().get(j).getname());
                getMoney.setAteMoney(dataReceive.get(i).getPlaceItems().get(j).getAteMoney());
                getMoney.setGateMoney(dataReceive.get(i).getPlaceItems().get(j).getGateMoney());
                getMoney.setRestMoney(dataReceive.get(i).getPlaceItems().get(j).getRestMoney());
                placeList.add(getMoney);
            }
        }
        lv = findViewById(R.id.date_lt);
        lv.setAdapter(padapter);

        back_btn  = (ImageButton) findViewById(R.id.backbtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public class placeAdapter extends ArrayAdapter<Data1> {
        private Context context;
        private int resource;
        private List<Data1> placelist;

        public placeAdapter( Context context, int resource, List<Data1> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.placelist = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView =  LayoutInflater.from(context).inflate(R.layout.row_item_lo, parent, false);
            TextView dd_t = (TextView) convertView.findViewById(R.id.diadiem_t);
            TextView day = convertView.findViewById(R.id.date_ht);
            TextView cp_an = convertView.findViewById(R.id.chi_phi_an_uong);
            TextView cp_cong = convertView.findViewById(R.id.chi_phi_vao_cong);
            TextView cp_o = convertView.findViewById(R.id.chi_phi_noi_o);
            Data1 diadiem_name = placelist.get(position);
            dd_t.setText(diadiem_name.getname());
            day.setText(diadiem_name.getPeriodOfTime());
            cp_an.setText(diadiem_name.getAteMoney());
            cp_cong.setText(diadiem_name.getGateMoney());
            cp_o.setText(diadiem_name.getRestMoney());
            return convertView;
        }
    }

}
