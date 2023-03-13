package com.example.practica4_pmdm_rms.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.practica4_pmdm_rms.entidades.Contactos;

import java.util.ArrayList;


public class dbContactos extends dbhelp{

    Context context;
    public dbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String telefono, String correo_electronico) {

        long id = 0;

        try {
            dbhelp dbhelp = new dbhelp(context);
            SQLiteDatabase db = dbhelp.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("NOMBRE", nombre);
            values.put("TELEFONO", telefono);
            values.put("CORREO", correo_electronico);


            id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Contactos> mostrarContactos() {

        dbhelp dbhelp = new dbhelp(context);
        SQLiteDatabase db = dbhelp.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " ORDER BY NOMBRE ASC", null);

        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electornico(cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }

    public Contactos verContacto(int id) {

        dbhelp dbhelp = new dbhelp(context);
        SQLiteDatabase db = dbhelp.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE ID = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electornico(cursorContactos.getString(3));
        }

        cursorContactos.close();

        return contacto;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        dbhelp dbhelp = new dbhelp(context);
        SQLiteDatabase db = dbhelp.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET NOMBRE = '" + nombre + "', TELEFONO = '" + telefono + "', CORREO = '" + correo_electronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        dbhelp dbhelp = new dbhelp(context);
        SQLiteDatabase db = dbhelp.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE ID = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}