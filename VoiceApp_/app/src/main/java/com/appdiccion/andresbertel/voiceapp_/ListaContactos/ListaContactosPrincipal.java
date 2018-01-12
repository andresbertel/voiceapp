package com.appdiccion.andresbertel.voiceapp_.ListaContactos;

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
import android.view.View;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.Contactos;
import com.appdiccion.andresbertel.voiceapp_.MiBaseDatos;
import com.appdiccion.andresbertel.voiceapp_.Principal;
import com.appdiccion.andresbertel.voiceapp_.R;
import com.appdiccion.andresbertel.voiceapp_.intefaz_contacto;

import java.util.ArrayList;
import java.util.List;

public class ListaContactosPrincipal extends AppCompatActivity {


    ArrayList<Contactos> listaContactos;


    MiBaseDatos MDB;
    int con=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_lista_contactos_principal);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        MDB = new MiBaseDatos(getApplicationContext());

        listaContactos=new ArrayList<Contactos>();

        llenarContactos();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MaterialPaletteAdapter(listaContactos, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast toast = Toast.makeText(ListaContactosPrincipal.this, String.valueOf(position)+" "+listaContactos.get(position).getNOMBRE(), Toast.LENGTH_SHORT);


            }
        }));
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //HORIZONTAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

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
                Toast.makeText(this, "Agregra", Toast.LENGTH_LONG).show();
                Intent ventanacontactos = new Intent(getApplicationContext(), intefaz_contacto.class);
                startActivity(ventanacontactos);
                Log.d("analisis", "naaa");
                return true;
            case R.id.iinicio:
                Toast.makeText(this, "Inicio", Toast.LENGTH_LONG).show();
                Intent ventanainicio = new Intent(getApplicationContext(), Principal.class);
                startActivity(ventanainicio);
                return true;
            case R.id.icontacto:
                Toast.makeText(this, "Ver contactos", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "contactos", Toast.LENGTH_LONG).show();
                Intent ventanalistadocontactos = new Intent(getApplicationContext(), ListaContactosPrincipal.class);
                startActivity(ventanalistadocontactos);
                return true;
            case R.id.imisdatos:
                Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
