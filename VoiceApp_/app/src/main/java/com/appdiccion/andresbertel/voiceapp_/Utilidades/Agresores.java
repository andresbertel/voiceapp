package com.appdiccion.andresbertel.voiceapp_.Utilidades;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.appdiccion.andresbertel.voiceapp_.MiBaseDatos;
import com.appdiccion.andresbertel.voiceapp_.MisDatos;
import com.appdiccion.andresbertel.voiceapp_.MisDatosPersonales;
import com.appdiccion.andresbertel.voiceapp_.Principal;
import com.appdiccion.andresbertel.voiceapp_.R;
import com.appdiccion.andresbertel.voiceapp_.intefaz_contacto;

public class Agresores extends AppCompatActivity {


    MiBaseDatos MBD;
    TextView NombreAgresor;
    TextView Descripc_agresor;
    TextView Descrip_agresion;
    ImageButton btGuardarAgre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_agresores);

        MBD=new MiBaseDatos(getApplicationContext());

        NombreAgresor=(EditText)findViewById(R.id.txtminombreagresor);
        Descripc_agresor=(EditText)findViewById(R.id.desAgresor);
        Descrip_agresion=(EditText)findViewById(R.id.txagresion);
        btGuardarAgre=(ImageButton)findViewById(R.id.btnguardaragre);





        String NombreAgresorT=NombreAgresor.getText().toString();
        String Descripc_agresorT=Descripc_agresor.getText().toString();
        String Descrip_agresiont=Descrip_agresion.getText().toString();

        cargardatos(1);

        btGuardarAgre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarMisDatos(NombreAgresor.getText().toString(),Descripc_agresor.getText().toString(),Descrip_agresion.getText().toString());
            }
        });

    }

    public void cargardatos(int id){
        MisDatosPersonales datos=  MBD.recuperarMISDATOS(id);

        if(datos==null){

        }else{
            NombreAgresor.setText(datos.getNombre());
            Descripc_agresor.setText(datos.getAlias());
            Descrip_agresion.setText(datos.getPolicia());


        }
    }

    public void GuardarMisDatos(String Nombre,String Descripc_agresor, String Descrip_agresion){



        if(verificar(Nombre,Descripc_agresor,Descrip_agresion)==false){

            Toast.makeText(getApplicationContext(),"Datos Vacios", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Datos Guardados", Toast.LENGTH_SHORT).show();
            MisDatosPersonales conta=  MBD.recuperarMISDATOS(1);

            if(conta==null){
                MBD.insertarMISDATOS(1,Nombre,Descripc_agresor,Descrip_agresion);
            }else{
                MBD.modificarMISDATOS(1,Nombre,Descripc_agresor,Descrip_agresion);
            }

        }

    }

    public boolean verificar(String Nombre,String Descripc_agresor, String Descrip_agresion){
        boolean r=false;

        if(Nombre.equals(null)||Descripc_agresor.equals(null)||Descrip_agresion.equals(null)||Nombre.equals("")||Descripc_agresor.equals("")||Descrip_agresion.equals("")){
            r=false;
        }else{
            r=true;
        }
        return r;
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
                // Toast.makeText(this, "contactos", Toast.LENGTH_LONG).show();
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
