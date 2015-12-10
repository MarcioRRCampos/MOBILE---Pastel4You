package com.br.marcio.campos.pastel4you.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcio on 08/11/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "foryou.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "client";
    private SQLiteDatabase db;

    private String stringCreate = "( id INTEGER PRIMARY KEY, " +
                                    "nome TEXT, " +
                                    "email TEXT, " +
                                    "passoword TEXT," +
                                    "telefone TEXT," +
                                    "bairro TEXT, " +
                                    "rua TEXT, " +
                                    "cep TEXT, " +
                                    "numero INT );";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE +
                stringCreate;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);
    }


    public SQLiteDatabase openDatabase()
    {
        return this.getWritableDatabase();
    }



}