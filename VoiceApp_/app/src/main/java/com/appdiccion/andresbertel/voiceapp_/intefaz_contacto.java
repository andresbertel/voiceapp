package com.appdiccion.andresbertel.voiceapp_;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;

public class intefaz_contacto extends AppCompatActivity {

    MiBaseDatos MDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.content_intefaz_contacto);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.hideOverflowMenu();
                setSupportActionBar(toolbar);*/


        MDB = new MiBaseDatos(getApplicationContext());

        ImageButton boton = (ImageButton) findViewById(R.id.bntagragarcont);
        final EditText nombre = (EditText) findViewById(R.id.ETnombre);
        final EditText celulr = (EditText) findViewById(R.id.ETcelular);
        final EditText email = (EditText) findViewById(R.id.ETemail);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(nombre.getText().toString().equals(null)||celulr.getText().toString().equals(null)||email.getText().toString().equals(null)||nombre.getText().toString().equals("")||celulr.getText().toString().equals("")||email.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Campos Vacios", Toast.LENGTH_LONG).show();
            }else {
                MDB.insertarCONTACTO(nombre.getText().toString(), celulr.getText().toString(), email.getText().toString());

                celulr.setText("");
                email.setText("");
                nombre.setText("");
                nombre.requestFocus();
                Toast.makeText(getApplicationContext(), "Contacto Agregado", Toast.LENGTH_LONG).show();
            }


            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iaconctacto:
                // Toast.makeText(this, "Agregra", Toast.LENGTH_LONG).show();
                Intent ventanacontactos = new Intent(getApplicationContext(), intefaz_contacto.class);
                startActivity(ventanacontactos);
                Log.d("analisis", "naaa");
                return true;
            case R.id.iinicio:
                // Toast.makeText(this, "Inicio", Toast.LENGTH_LONG).show();
                Intent ventanainicio = new Intent(getApplicationContext(), Principal.class);
                startActivity(ventanainicio);
                return true;
            case R.id.icontacto:
               // Toast.makeText(this, "Ver contactos", Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "contactos", Toast.LENGTH_LONG).show();
                Intent ventanalistadocontactos = new Intent(getApplicationContext(), ListaContactosPrincipal.class);
                startActivity(ventanalistadocontactos);
                return true;
            case R.id.imisdatos:
                Intent ventanamisdatoss = new Intent(getApplicationContext(), MisDatos.class);
                startActivity(ventanamisdatoss);
                //Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
