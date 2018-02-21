package com.appdiccion.andresbertel.voiceapp_;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres.bertel on 25/10/2017.
 */

public class MiBaseDatos extends SQLiteOpenHelper {

            private static final int VERSION_BASEDATOS = 1;

            // Nombre de nuestro archivo de base de datos
            private static final String NOMBRE_BASEDATOS = "contactos.db";

           // Nombre de la tabla
            private static final String TABLA="contactos";
            private static final String TABLA1="misdatos";
             private static final String TABLA2="agresor";

            // Sentencia SQL para la creación de una tabla
            private static final String CONSULTA_TABLA_CONTACTOS = "CREATE TABLE "+TABLA+
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, email TEXT)";

            private static final String CONSULTA_TABLA_MISDATOS = "CREATE TABLE "+TABLA1+
             "(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, alias TEXT, policia TEXT)";

    private static final String CONSULTA_TABLA_AGRESOR = "CREATE TABLE "+TABLA2+
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion_agresor TEXT, descripcion_agresion TEXT)";



            // CONSTRUCTOR de la clase
            public MiBaseDatos(Context context) {
                super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CONSULTA_TABLA_CONTACTOS);
                db.execSQL(CONSULTA_TABLA_MISDATOS);
                db.execSQL(CONSULTA_TABLA_AGRESOR);


                Log.d("revisar","Oncreate");
               // Toast.makeText(null, "Oncreate", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLA);
                db.execSQL("DROP TABLE IF EXISTS " + TABLA1);
                db.execSQL("DROP TABLE IF EXISTS " + TABLA2);
                Log.d("revisar","OnUpgrade");
               onCreate(db);

             //   Toast.makeText(null, "OnUpgrade", Toast.LENGTH_SHORT).show();
            }



    public void insertarMISDATOS( int id,String nom, String alias, String policia) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();

            valores.put("_id", id);
            valores.put("nombre", nom);
            valores.put("alias", alias);
            valores.put("policia", policia);
            db.insert("misdatos", null, valores);
            db.close();
        }
    }

    public MisDatosPersonales recuperarMISDATOS(int id) {
        MisDatosPersonales DatosPersonales=null;
        try {

            SQLiteDatabase db1 = getReadableDatabase();
            String[] valores_recuperar = {"_id", "nombre", "alias", "policia"};
            Cursor c = db1.query("misdatos", valores_recuperar, "_id=" + id,
                    null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
                DatosPersonales = new MisDatosPersonales(1, c.getString(1),
                        c.getString(2), c.getString(3));

            }



            db1.close();
            c.close();
        }catch (Exception e){
           Log.e("Bassee ",""+e);
        }

        return DatosPersonales;
    }


    public void modificarMISDATOS(int id,String nom, String alias, String policia){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put("nombre", nom);
        valores.put("alias", alias);
        valores.put("policia", policia);
        db.update("misdatos", valores, "_id=" + id, null);
        db.close();
    }



    public void insertarCONTACTO( String nom, String tlf, String email) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();

           // valores.put("_id", id);
            valores.put("nombre", nom);
            valores.put("telefono", tlf);
            valores.put("email", email);
            db.insert("contactos", null, valores);
            db.close();
        }
    }

    public void modificarCONTACTO(int id, String nom, String tlf, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put("nombre", nom);
        valores.put("telefono", tlf);
        valores.put("email", email);
        db.update("contactos", valores, "_id=" + id, null);
        db.close();
    }

    public void borrarCONTACTO(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("contactos", "_id="+id, null);
        db.close();
    }

    public Contactos recuperarCONTACTO(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", "nombre", "telefono", "email"};
        Cursor c = db.query("contactos", valores_recuperar, "_id=" + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Contactos contactos = new Contactos(c.getInt(0), c.getString(1),
                c.getString(2), c.getString(3));
        db.close();
        c.close();
        return contactos;
    }

    public List<Contactos> recuperarCONTACTOS() {
        SQLiteDatabase db = getReadableDatabase();
        List<Contactos> lista_contactos = new ArrayList<Contactos>();
        String[] valores_recuperar = {"_id", "nombre", "telefono", "email"};
        Cursor c = db.query("contactos", valores_recuperar,
                null, null, null, null, null, null);
       // c.moveToFirst();


if(c.moveToFirst()){

        do {
             Contactos contactos = new Contactos(c.getInt(0), c.getString(1),
                c.getString(2), c.getString(3));
                 lista_contactos.add(contactos);
                   } while (c.moveToNext());


}

        db.close();
        c.close();
        return lista_contactos;
    }




    /**********************************************************************/

    public void insertarAGRESOR(int id, String nom, String desA, String DesAgresion) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
           // nombre TEXT, descripcion_agresor TEXT, descripcion_agresion TEXT
             valores.put("_id", id);
            valores.put("nombre", nom);
            valores.put("descripcion_agresor", desA);
            valores.put("descripcion_agresion", DesAgresion);
            db.insert("agresor", null, valores);
            db.close();
        }
    }

    public void modificarAGRESOR(int id, String nom, String desA, String DesAgresion){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put("nombre", nom);
        valores.put("descripcion_agresor", desA);
        valores.put("descripcion_agresion", DesAgresion);
        db.update("agresor", valores, "_id=" + id, null);
        db.close();
    }

    public void borrarAGRESOR(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("agresor", "_id="+id, null);
        db.close();
    }

    public agresor recuperarAGRESOR(int id) {

        agresor miagresor=null;
        try {

            SQLiteDatabase db2 = getReadableDatabase();
            String[] valores_recuperar = {"_id", "nombre", "descripcion_agresor", "descripcion_agresion"};
            Cursor c = db2.query("agresor", valores_recuperar, "_id=" + id,
                    null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
                miagresor = new agresor(1, c.getString(1),
                        c.getString(2), c.getString(3));

            }



            db2.close();
            c.close();
        }catch (Exception e){
            Log.e("Bassee ",""+e);
        }

        return miagresor;
    }

    public List<agresor> recuperarAGRESOR() {
        SQLiteDatabase db = getReadableDatabase();
        List<agresor> lista_agresor = new ArrayList<agresor>();
        String[] valores_recuperar = {"_id", "nombre", "descripcion_agresor", "descripcion_agresion"};
        Cursor c = db.query("agresor", valores_recuperar,
                null, null, null, null, null, null);
        // c.moveToFirst();


        if(c.moveToFirst()){

            do {
                agresor agres = new agresor(c.getInt(0), c.getString(1),
                        c.getString(2), c.getString(3));
                lista_agresor.add(agres);
            } while (c.moveToNext());


        }

        db.close();
        c.close();
        return lista_agresor;
    }


}
