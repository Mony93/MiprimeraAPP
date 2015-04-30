package miprimerapp.proyecto.android.miprimeraapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moka on 30/04/2015.
 */
public class almacen extends SQLiteOpenHelper {
    public almacen(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
    super(context, name, factory, version);}

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table almacen(codi integer primary key unique, login text unique, nombre text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table if exists almacen");
        bd.execSQL("create table almacen(codi integer primary key unique, login text unique, nombre text)");
    }
}