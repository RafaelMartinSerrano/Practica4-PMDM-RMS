package com.example.practica4_pmdm_rms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelp extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "AGENDARMS";
     static final String  TABLE_CONTACTOS = "T_contactos";




    public dbhelp(@Nullable Context context) {
        super(context, DATABASE_NOMBRE,null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT NOT NULL," +
                "TELEFONO TEXT NOT NULL," +
                "CORREO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(db);
    }
}
