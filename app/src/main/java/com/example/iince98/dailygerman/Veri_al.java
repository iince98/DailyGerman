package com.example.iince98.dailygerman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Veri_al extends AppCompatActivity {
    private ListView lvterim;
    private Adapter adapter;
    private List<Terim_class> mterimclass;
    private DatabaseHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvterim= (ListView)findViewById(R.id.lvterim);
        mDBHelper= new DatabaseHelper(this);

        File veritabanı= getApplicationContext().getDatabasePath(DatabaseHelper.DB_NAME);
        if (false== veritabanı.exists()) {
            mDBHelper.getReadableDatabase();

            if (copyDatabase(this)) {
                Toast.makeText(this, "Veri alma işlerim başarılı", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Veri alma işlerim başarısızdır", Toast.LENGTH_LONG).show();
                return;
            }
        }
        mterimclass= mDBHelper.terimal();
        adapter= new Adapter(this, mterimclass);
        lvterim.setAdapter(adapter);
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream= context.getAssets().open(DatabaseHelper.DB_NAME);
            String outfilename= DatabaseHelper.DB_PATH + DatabaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outfilename);
            byte [] buffer= new byte[inputStream.available()];
            int dosyauzunlugu=0;
            while ((dosyauzunlugu = inputStream.read(buffer))>0) {
                outputStream.write(buffer, 0, dosyauzunlugu);
                }
                outputStream.flush();
                outputStream.close();
            Log.v("Veri_al", "DB kopyalandı");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
