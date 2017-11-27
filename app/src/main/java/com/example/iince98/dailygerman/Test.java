package com.example.iince98.dailygerman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Calendar;

public class Test extends AppCompatActivity {

    TextView txv_info, txv_st, txv_ctgry, txv_fvrt, txv_phrs, txv_answr, txv_pnmbr;
    Button butn_go, butn_prev, butn_next, butn_fvrt, butn_st;
    Spinner spinner_ctgry, spinner_st, spinner_fvrt;
    String knwn_State, ctgry, fvrt;
    Integer  top_ks, topb_ks, topnb_ks,sb=0, snb=0, cpos, kntr_mark=0;
    DatabaseHelper db;
    Cursor cursor, cursor_renk;
    public String [] liste1;

    //herhangi bir butona tıklandıgında Id ile btn tespiti ve switch case ile uygulanacak metoda gitme
    public View.OnClickListener btnClickListener=new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            switch (v.getId()){
                case R.id.tv_info:
                    //showall ();
                    break;
                case R.id.tv_answr:
                   // showmean ();
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
                    //mrkfvrt ();
                    break;

            }
        }
    };



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
        //info bar
        String currentDate = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        //txv_info.setText(liste1 [0]);
        txv_info.setText("The Phrase of the Day ***  "+currentDate+"  ***  Hallo Leute.. Herzlich Willkommen. Wir hoffen euch eines schönes Wochenende..");
        txv_info.setSelected(true);
        float speed1=10;
        setMarqueeSpeed(txv_info, speed1, true);

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

            db =new DatabaseHelper(this);
            try {

                db.createDataBase();
                db.openDataBase();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        Toast.makeText(getApplicationContext(),"heey..",Toast.LENGTH_SHORT).show();
        SQLiteDatabase dbOku = db.getReadableDatabase();

        if (ctgry.equals("All")) {
            switch (knwn_State) {
                case "Known":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE STATE= 'KNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE STATE= 'KNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE STATE= 'KNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;

                case "Unknown":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE STATE= 'UNKNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE STATE= 'UNKNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE STATE= 'UNKNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
                case "All":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
            }

        } else {

            switch (knwn_State) {
                case "Known":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "' AND STATE= 'KNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "' AND STATE= 'KNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "' AND STATE= 'KNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
                case "Unknown":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "' AND STATE= 'UNKNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "' AND STATE= 'UNKNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "' AND STATE= 'UNKNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
                case "All":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "'  AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "'  AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM Terimler WHERE CATEGORY='" + ctgry + "'  ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
            }
        }
        if (cursor.getCount()<1) Toast.makeText(this, "There is no record..", Toast.LENGTH_SHORT).show();
        else {cursor.moveToFirst();
            liste1 = new String[]{cursor.getString(0),  cursor.getString(1)/*, cursor.getString(2), cursor.getString(3), cursor.getString(4)*/};



            txv_phrs.setText(liste1 [0]);
            txv_answr.setText(liste1 [1]);
            sb=sb+1;
            txv_pnmbr.setText(sb+"/"+top_ks);

            /*switch (liste1 [3]) {
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
            if (cursor.getString(4).equals("Y")){
                txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_on"));
            } else
                txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_off"));*/
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
        txv_info.setText(cursor.getString(0)+"***"+cursor.getString(1));
        txv_info.setSelected(true);
        txv_phrs.setText(cursor.getString(0));
        txv_answr.setText(cursor.getString(1));
        sb=sb+1;
        txv_pnmbr.setText(sb+"/"+top_ks);
       /* switch (cursor.getString(3)) {
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
            txv_fvrt.setBackground (Drawable.createFromPath("@android:drawable/star_off"));*/

    }
    public void showprev(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txv_answr.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        if (cursor.getCount()<=0){
            Toast.makeText(this, "There is no record..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cursor.isFirst()){
            if (kntr_mark.equals(1)) {kntr_mark=0; sb=2;}
            else {
                sb=top_ks+1; cursor.moveToLast();}
        }
        else {
            if (kntr_mark.equals(1)) kntr_mark=0;
            else cursor.moveToPrevious();}

        txv_info.setText(cursor.getString(0)+"***"+cursor.getString(3));
        txv_info.setSelected(true);
        txv_phrs.setText(cursor.getString(1));
        txv_answr.setText(cursor.getString(2));
        sb=sb-1;
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

    public void marknown () {
        if (cursor.getCount()<=0){
            Toast.makeText(this, "There is no record..", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (cursor.getString(3)){
            case "N":
                cpos=cursor.getPosition();
                boolean isUpdate = db.markData(cursor.getString(0),  "Y");
                if(isUpdate){
                    Toast.makeText(this,"This phrase is marked as known..",Toast.LENGTH_LONG).show();
                    butn_st.setBackgroundColor(Color.parseColor("#8BC34A"));
                    txv_phrs.setBackground(getResources().getDrawable(R.drawable.tv_phrase));
                    refresh_data();
                    if (knwn_State.equals("Known")|| knwn_State.equals("Unknown")){
                        cursor.moveToPosition(cpos-1); kntr_mark=1;}
                    else cursor.moveToPosition(cpos);
                    txv_pnmbr.setText(sb+"/"+top_ks);
                }
                else
                    Toast.makeText(this,"The phrase is not marked as known..",Toast.LENGTH_LONG).show();break;
            case "Y":
                cpos=cursor.getPosition();
                isUpdate = db.markData(cursor.getString(0),  "N");
                if(isUpdate){
                    Toast.makeText(this,"This phrase is marked as unknown..",Toast.LENGTH_LONG).show();
                    butn_st.setBackgroundColor(Color.RED);
                    txv_phrs.setBackground(getResources().getDrawable(R.drawable.tv_phrasek));
                    refresh_data();
                    if (knwn_State.equals("Known")|| knwn_State.equals("Unknown")){
                        cursor.moveToPosition(cpos-1); kntr_mark=1;}
                    else cursor.moveToPosition(cpos);

                    txv_pnmbr.setText(sb+"/"+top_ks);
                }
                else
                    Toast.makeText(this,"This phrase is not marked as unknown..",Toast.LENGTH_LONG).show();break;

        }}

    public void refresh_data() {
        db =new DatabaseHelper(this);
        SQLiteDatabase dbOku = db.getReadableDatabase();

        if (ctgry.equals("All")) {
            switch (knwn_State) {
                case "Known":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE STATE= 'KNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE STATE= 'KNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE STATE= 'KNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;

                case "Unknown":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE STATE= 'UNKNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE STATE= 'UNKNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE STATE= 'UNKNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
                case "All":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
            }

        } else {

            switch (knwn_State) {
                case "Known":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "' AND STATE= 'KNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "' AND STATE= 'KNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "' AND STATE= 'KNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
                case "Unknown":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "' AND STATE= 'UNKNOWN' AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "' AND STATE= 'UNKNOWN' AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "' AND STATE= 'UNKNOWN' ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
                case "All":
                    switch (fvrt){
                        case "Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "'  AND FAVORITE= 'FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "Not Favorites":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "'  AND FAVORITE= 'NOT FAVORITES' ", null);
                            top_ks = cursor.getCount();
                            break;
                        case "All":
                            cursor = dbOku.rawQuery("SELECT * FROM TABLO_WORD WHERE CATEGORY='" + ctgry + "'  ", null);
                            top_ks = cursor.getCount();
                            break;
                    }
                    break;
            }
        }
    }

    protected void setMarqueeSpeed(TextView tv, float speed, boolean speedIsMultiplier) {

        try {
            Field f;
            if (tv instanceof AppCompatTextView) {
                f = tv.getClass().getSuperclass().getDeclaredField("mMarquee");
            } else {
                f = tv.getClass().getDeclaredField("mMarquee");
            }
            f.setAccessible(true);

            Object marquee = f.get(tv);
            if (marquee != null) {

                String scrollSpeedFieldName = "mScrollUnit";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    scrollSpeedFieldName = "mPixelsPerSecond";

                Field mf = marquee.getClass().getDeclaredField(scrollSpeedFieldName);
                mf.setAccessible(true);

                float newSpeed = speed;
                if (speedIsMultiplier)
                    newSpeed = mf.getFloat(marquee) * speed;

                mf.setFloat(marquee, newSpeed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
