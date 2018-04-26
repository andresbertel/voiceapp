package com.appdiccion.andresbertel.voiceapp_;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;

import java.util.ArrayList;
import java.util.List;

public class ListadodeContactos extends AppCompatActivity {

    ArrayList<Contactos> listaContactos;
    RecyclerView recicle;
    MiBaseDatos MDB;
    int con=0;

    TextView NombreCuestionario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_listadode_contactos);
        MDB = new MiBaseDatos(getApplicationContext());

        listaContactos=new ArrayList<Contactos>();
        recicle=(RecyclerView)findViewById(R.id.RecyclerViewUsuarios);
        recicle.setLayoutManager(new LinearLayoutManager(this));

        recicle.setItemViewCacheSize(20);

       // NombreCuestionario=(TextView)findViewById(R.id.textView61);
        int numContactos=MDB.recuperarCONTACTOS().size();

        if(numContactos<1){

            Intent ventanacontactos = new Intent(getApplicationContext(), intefaz_contacto.class);
            startActivity(ventanacontactos);
        }


        llenarContactos();

        final AdaptadorContactos adapter=new AdaptadorContactos(listaContactos);
        recicle.setAdapter(adapter);






    }

    private void llenarContactos() {

            List a=  MDB.recuperarCONTACTOS();
            int longitLis=a.size();




        while (con<longitLis){
            Contactos contactos=(Contactos)a.get(con);
            listaContactos.add(contactos);
            con=con+1;

               }



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
            case R.id.termiCondi:
                Intent ventanaCondiciones = new Intent(getApplicationContext(), Condiciones.class);
                startActivity(ventanaCondiciones);
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
