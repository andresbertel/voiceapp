package com.appdiccion.andresbertel.voiceapp_;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class ver_contactos extends AppCompatActivity {

    MiBaseDatos MDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_ver_contactos);
        MDB = new MiBaseDatos(getApplicationContext());
    }


    public void vertodoscontactos(){
        List a=  MDB.recuperarCONTACTOS();
        int lon=a.size();
        int con=0;

        int id=0;
        String nombre="";
        String telefono="";
        String email="";
        while (con<lon){
            Contactos contactos=(Contactos)a.get(con);

            id=contactos.getID();
            nombre=contactos.getNOMBRE();
            telefono=contactos.getTELEFONO();
            email=contactos.getEMAIL();

            Log.d("contactor", "Holaaa");
            con=con+1;

            Toast.makeText(getApplicationContext(), "Contacto Agregado "+"id: "+ id+" Nombres: "+nombre+" Celular: "+telefono+" Email: "+email, Toast.LENGTH_SHORT).show();
        }
    }
}
