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
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;


/**
 * Created by user on 20/02/2018.
 */

public class MisAgresores extends AppCompatActivity {

    MiBaseDatos MBD;
    TextView NombreAgresor;
    TextView Descripc_agresor;
    TextView Descrip_agresion;
    ImageButton btGuardarAgre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.agresor);

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
                GuardarMiAgresor(NombreAgresor.getText().toString(),Descripc_agresor.getText().toString(),Descrip_agresion.getText().toString());
            }
        });

        MBD = new MiBaseDatos(getApplicationContext());
        int numContactos=MBD.recuperarCONTACTOS().size();

        if(numContactos<1){

            Intent ventanacontactos = new Intent(getApplicationContext(), intefaz_contacto.class);
            startActivity(ventanacontactos);
        }

    }

    public void cargardatos(int id){

        try{

            agresor datos=  MBD.recuperarAGRESOR(id);

            if(datos==null){

            }else{
                 NombreAgresor.setText(datos.getNOMBRE());
                 Descripc_agresor.setText(datos.getDesAgresor());
                Descrip_agresion.setText(datos.getdescripcion_agresion());


            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error "+e,Toast.LENGTH_SHORT).show();
        }

    }

    public void GuardarMiAgresor(String Nombre,String Descripc_agresor, String Descrip_agresion){



        if(verificar(Nombre,Descripc_agresor,Descrip_agresion)==false){

            Toast.makeText(getApplicationContext(),"Datos Vacios", Toast.LENGTH_SHORT).show();

        }else{

            try{


                agresor conta=  MBD.recuperarAGRESOR(1);

                if(conta==null){
                    MBD.insertarAGRESOR(1,Nombre,Descripc_agresor,Descrip_agresion);
                }else{
                    MBD.modificarAGRESOR(1,Nombre,Descripc_agresor,Descrip_agresion);
                }

                Toast.makeText(getApplicationContext(),"Datos Guardados", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error "+e, Toast.LENGTH_SHORT).show();
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
