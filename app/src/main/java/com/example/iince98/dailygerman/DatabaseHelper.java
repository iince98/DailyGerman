package com.example.iince98.dailygerman;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH = "data/datatext.com.example.iince98.dailygerman/databases/";
    public static String DB_NAME = "terimler.db";
    public SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void openDataBase() {
        String myPath = myContext.getDatabasePath(DB_PATH).getPath();
        if (myDataBase != null && myDataBase.isOpen()){
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDataBase () {
        if (myDataBase !=null)
            myDataBase.close();
    }

    public List<Terim_class> terimal () {
        Terim_class terim=null;
        List<Terim_class> terimlistesi= new ArrayList<>();
        openDataBase();
        Cursor cursor= myDataBase.rawQuery("SELECT * FROM Terimler", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            terim= new Terim_class(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
         terimlistesi.add(terim);
         cursor.moveToNext();
    }
    cursor.close();
        closeDataBase();
        return terimlistesi;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

