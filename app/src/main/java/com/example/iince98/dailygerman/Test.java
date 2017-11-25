package com.example.iince98.dailygerman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
    Integer  top_ks, topb_ks, topnb_ks,sb=0, snb=0, cpos, kntr_mark=0;
    DatabaseHelper vt;
    Cursor cursor, cursor_renk;
    public String [] liste1;

    //herhangi bir butona tıklandıgında Id ile btn tespiti ve switch case ile uygulanacak metoda gitme
    public View.OnClickListener btnClickListener=new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            switch (v.getId()){
                case R.id.tv_info:
                    showall ();
                    break;
                case R.id.tv_answr:
                    showmean ();
                    break;
                case R.id.btn_go:
                    showgo ();
                    break;
                case R.id.btn_prev:
                    showprev ();
                    break;
                case R.id.btn_next:
                    shownext ();
                    break;
                case R.id.btn_st:
                    marknown ();
                    break;
                case R.id.btn_fvrt:
                    mrkfvrt ();
                    break;

            }
        }
    };

    private void showall() {
    }

    private void showmean() {
    }

    private void showprev() {
    }

    private void marknown() {
    }
    private void mrkfvrt() {
    }


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
        butn_go.setOnClickListener(btnClickListener);
        butn_prev = findViewById(R.id.btn_prev);
        butn_prev.setOnClickListener(btnClickListener);
        butn_next = findViewById(R.id.btn_next);
        butn_next.setOnClickListener(btnClickListener);
        butn_fvrt = findViewById(R.id.btn_fvrt);
        butn_fvrt.setOnClickListener(btnClickListener);
        butn_st = findViewById(R.id.btn_st);
        butn_st.setOnClickListener(btnClickListener);

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

    public void showgo() {
        //seçilen kelime bilgilerinin bulunması ve liste dizisine atanması

            DatabaseHelper db =new DatabaseHelper(this);
            try {

                db.createDataBase();
                db.openDataBase();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        SQLiteDatabase dbOku = vt.getReadableDatabase();

        if (ctgry.equals("All")) {
            switch (knwn_State) {
                case "Known":
                    cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE COLOR= 'Y' ORDER BY WORD ASC", null);
                    top_ks = cursor.getCount();
                    break;
                case "Unknown":
                    cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE COLOR= 'N' ORDER BY WORD ASC", null);
                    top_ks = cursor.getCount();
                    break;
                case "All":
                    cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD ORDER BY WORD ASC", null);
                    top_ks = cursor.getCount();
                    cursor_renk = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE COLOR= 'Y' ", null);
                    topb_ks = cursor_renk.getCount();
                    break;
            }

        } else {

            switch (knwn_State) {
                case "Known":
                    cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE TYPE='" + ctgry + "' AND COLOR= 'Y' ORDER BY WORD ASC", null);
                    top_ks = cursor.getCount();
                    break;
                case "Unknown":
                    cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE TYPE='" + ctgry + "' AND COLOR= 'N' ORDER BY WORD ASC", null);
                    top_ks = cursor.getCount();
                    break;
                case "All":
                    cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE TYPE='" + ctgry + "' ORDER BY WORD ASC ", null);
                    top_ks = cursor.getCount();
                    cursor_renk = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE TYPE='" + ctgry + "' AND COLOR= 'Y' ", null);
                    break;
            }
        }
        if (cursor.getCount()<1) Toast.makeText(this, "There is no record..", Toast.LENGTH_SHORT).show();
        else {cursor.moveToFirst();
            liste1 = new String[]{cursor.getString(0),  cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};


            txv_info.setText(liste1 [0]);
            txv_info.setSelected(true);
            txv_phrs.setText(liste1 [1]);
            txv_answr.setText(liste1 [2]);
            sb=sb+1;
            txv_pnmbr.setText(sb+"/"+top_ks);

            switch (liste1 [3]) {
                case "N":
                    butn_st.setBackground(Drawable.createFromPath("@android:color/holo_red_dark"));
                    txv_answr.setBackground(getResources().getDrawable(Integer.parseInt("@drawable/tv_phraseanswr")));
                    txv_phrs.setBackground(getResources().getDrawable(Integer.parseInt("tv_phrasek")));
                    break;
                case "Y":
                    butn_st.setBackground(Drawable.createFromPath("@android:color/holo_green_dark"));
                    txv_answr.setBackground(getResources().getDrawable(Integer.parseInt("@drawable/tv_phraseanswr")));
                    txv_phrs.setBackground(getResources().getDrawable(Integer.parseInt("tv_phrase")));
                    break;
            }
            if (cursor.getString(4)=="Y"){
                txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_on"));
            } else
                txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_off"));
        }
    }

    public void shownext(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txv_answr.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        if (cursor.getCount()<=0){
            Toast.makeText(this, "There is no record..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cursor.isLast()){
            cursor.moveToFirst();
            sb=0;
        }
        else
            cursor.moveToNext();
        txv_info.setText(cursor.getString(0)+"***"+cursor.getString(3));
        txv_info.setSelected(true);
        txv_phrs.setText(cursor.getString(1));
        txv_answr.setText(cursor.getString(2));
        sb=sb+1;
        txv_pnmbr.setText(sb+"/"+top_ks);
        switch (cursor.getString(3)) {
            case "N":
                butn_st.setBackground(Drawable.createFromPath("@android:color/holo_red_dark"));
                txv_answr.setBackground(getResources().getDrawable(Integer.parseInt("@drawable/tv_phraseanswr")));
                txv_phrs.setBackground(getResources().getDrawable(Integer.parseInt("tv_phrasek")));
                break;
            case "Y":
                butn_st.setBackground(Drawable.createFromPath("@android:color/holo_green_dark"));
                txv_answr.setBackground(getResources().getDrawable(Integer.parseInt("@drawable/tv_phraseanswr")));
                txv_phrs.setBackground(getResources().getDrawable(Integer.parseInt("tv_phrase")));
                break;
        }
        if (cursor.getString(4)=="Y"){
            txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_on"));
        } else
            txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_off"));

    }

}
