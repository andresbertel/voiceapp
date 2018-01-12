package com.appdiccion.andresbertel.voiceapp_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appdiccion.andresbertel.voiceapp_.Utilidades.Utilidades  ;

/**
 * Created by Administrador on 27/10/2017.
 */

public class ConexionSqLiteHelper extends SQLiteOpenHelper{

    public ConexionSqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_CUESTIONARIO);
        db.execSQL(Utilidades.CREAR_TABLA_PERSONA);
     //   db.execSQL(Utilidades.CREAR_TABLA_RESPONDE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CUESTIONARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PERSONA);
       // db.execSQL("DROP TABLE IF EXISTS "+Utilidades.CREAR_TABLA_RESPONDE);
        onCreate(db);
    }
}
