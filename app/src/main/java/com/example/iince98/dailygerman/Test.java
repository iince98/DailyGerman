package com.example.iince98.dailygerman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends AppCompatActivity {

    TextView txv_info, txv_st, txv_ctgry, txv_fvrt, txv_phrs, txv_answr, txv_pnmbr;
    Button butn_go, butn_prev, butn_next, butn_fvrt, butn_st;
    Spinner spinner_ctgry, spinner_st, spinner_fvrt;
    String knwn_State, ctgry, fvrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //texview tanımlama
        txv_st= findViewById(R.id.tv_st);
        txv_ctgry= findViewById(R.id.tv_ctgry);
        txv_fvrt= findViewById(R.id.tv_fvrt);
        txv_info= findViewById(R.id.tv_info);
        txv_phrs= findViewById(R.id.tv_phrs);
        txv_answr= findViewById(R.id.tv_answr);
        txv_pnmbr= findViewById(R.id.tv_pnmbr);

        //Buton tanımlama
        butn_go = findViewById(R.id.btn_go);
        butn_prev = findViewById(R.id.btn_prev);
        butn_next = findViewById(R.id.btn_next);
        butn_fvrt = findViewById(R.id.btn_fvrt);
        butn_st = findViewById(R.id.btn_st);

        // spinner tanımlama
        spinner_ctgry= findViewById(R.id.sp_ctgry);
        spinner_st= findViewById(R.id.sp_st);
        spinner_fvrt= findViewById(R.id.sp_fvrt);

        spinner_ctgry_refresh();
        spinner_st_refresh();
        spinner_fvrt_refresh();

    }

    public void spinner_ctgry_refresh(){
        String ctgries []=getResources().getStringArray(R.array.ctgries);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_1, ctgries);
        spinner_ctgry.setAdapter(adapter);
        spinner_ctgry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        ctgry = "All";
                        break;
                    case 1:
                        ctgry = "Kitchen";
                        break;
                    case 2:
                        ctgry = "Health";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Select a category..",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void spinner_st_refresh(){
        String states []=getResources().getStringArray(R.array.states);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_1, states);
        spinner_st.setAdapter(adapter);
        spinner_st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        knwn_State = "All";
                        break;
                    case 1:
                        knwn_State = "Known";
                        break;
                    case 2:
                        knwn_State = "Unknown";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Select a state..",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void spinner_fvrt_refresh(){
        String fvrts []=getResources().getStringArray(R.array.fvrts);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_1, fvrts);
        spinner_fvrt.setAdapter(adapter);
        spinner_fvrt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        fvrt = "All";
                        break;
                    case 1:
                        fvrt = "Favorites";
                        break;
                    case 2:
                        fvrt = "Not Favorites";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Select a favorite state..",Toast.LENGTH_SHORT).show();

            }
        });
    }


}
