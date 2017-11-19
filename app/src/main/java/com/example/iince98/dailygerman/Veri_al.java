package com.example.iince98.dailygerman;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.example.iince98.dailygerman.Terim.Terim;
import com.example.iince98.dailygerman.TerimAdaptor.Listeadaptor;
import com.example.iince98.dailygerman.Veritabanı.DatabaseHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Veri_al extends AppCompatActivity {
    private ListView lvTerim;
    private Listeadaptor adapter;
    private List<Terim> terimList;
    private DatabaseHelper mDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veri_al);
        lvTerim=(ListView)findViewById(R.id.lv_terim);
        mDBhelper=new DatabaseHelper(this);

        File database= getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false==database.exists()){

            mDBhelper.getReadableDatabase();
            if (copyDatabase(this)) {
                Toast.makeText(this, "copy database success", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
            terimList=mDBhelper.Terim_al();
            adapter= new Listeadaptor (this, terimList);
            lvTerim.setAdapter(adapter);

        }
    private boolean copyDatabase (Context context) {

        try {
            InputStream inputStream= context.getAssets().open(DatabaseHelper.DBNAME);
            String outfilename=  DatabaseHelper.DBNAME;
            OutputStream outputStream= new FileOutputStream(outfilename);
            byte [] buff=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buff))>0){
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("Mainactivity", "DB copied");
            return true;
            }
            catch (Exception e) {
            e.printStackTrace();
            return false;
            }
    }

}
