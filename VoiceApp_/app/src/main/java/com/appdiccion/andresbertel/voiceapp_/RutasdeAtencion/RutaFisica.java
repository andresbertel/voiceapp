package com.appdiccion.andresbertel.voiceapp_.RutasdeAtencion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.appdiccion.andresbertel.voiceapp_.ListadodeContactos;
import com.appdiccion.andresbertel.voiceapp_.MisAgresores;
import com.appdiccion.andresbertel.voiceapp_.MisDatos;
import com.appdiccion.andresbertel.voiceapp_.Principal;
import com.appdiccion.andresbertel.voiceapp_.R;
import com.appdiccion.andresbertel.voiceapp_.intefaz_contacto;

public class RutaFisica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ruta_fisica);
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
          /*  case R.id.imisagresor:
                Intent VentanaAgresores = new Intent(getApplicationContext(), MisAgresores.class);
                startActivity(VentanaAgresores);
                //Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
