package com.example.pf_agenda_morales_cp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelp extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelp(@Nullable Context context, @Nullable String nombre, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table agenda (idcontacto integer primary key, nombre text, apellidos text,  telefono text , apodo text )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        db.execSQL("drop table if exists agenda");
        db.execSQL("create table agenda (idcontacto integer primary key, nombre text, apellidos text,  telefono text, apodo text )");
    }

}
