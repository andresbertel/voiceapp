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
import android.widget.ImageButton;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.appdiccion.andresbertel.voiceapp_.TiposViolencia.IViolenciaFisica;
import com.appdiccion.andresbertel.voiceapp_.TiposViolencia.IViolenciaPatrimonial;
import com.appdiccion.andresbertel.voiceapp_.TiposViolencia.IViolenciaPsicologica;
import com.appdiccion.andresbertel.voiceapp_.TiposViolencia.IViolenciaSexual;

public class TiposdeViolencia extends AppCompatActivity implements View.OnClickListener {

    ImageButton vpatri;
    ImageButton vfisi;
    ImageButton vpsi;
    ImageButton vsex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_tiposde_violencia);


        vpatri=(ImageButton)findViewById(R.id.btnvpatrimonial);
        vfisi=(ImageButton)findViewById(R.id.btnvfisica);
        vpsi=(ImageButton)findViewById(R.id.btnvpsicologica);
        vsex=(ImageButton)findViewById(R.id.btnvsexual);


        vpatri.setOnClickListener(this);
         vfisi.setOnClickListener(this);
          vpsi.setOnClickListener(this);
           vsex.setOnClickListener(this);





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
            case R.id.imisagresor:
                Intent VentanaAgresores = new Intent(getApplicationContext(), MisAgresores.class);
                startActivity(VentanaAgresores);
                //Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View control_pulsado) {

      if(control_pulsado.getId() == R.id.btnvfisica){ //Si se ha pulsado btPrincipal

             Intent ventanavfisica = new Intent(TiposdeViolencia.this, IViolenciaFisica.class);
             startActivity(ventanavfisica);
             //Escribimos Hola Mundo

                       }else if(control_pulsado.getId() == R.id.btnvpatrimonial){

                                    Intent ventanavpatrimonial = new Intent(TiposdeViolencia.this, IViolenciaPatrimonial.class);
                                    startActivity(ventanavpatrimonial);

                                           }else if(control_pulsado.getId() == R.id.btnvsexual){

                                                Intent ventanavsexual = new Intent(TiposdeViolencia.this, IViolenciaSexual.class);
                                                startActivity(ventanavsexual);

                                                 }else if(control_pulsado.getId() == R.id.btnvpsicologica){

                                                        Intent ventanavpsicologica = new Intent(TiposdeViolencia.this, IViolenciaPsicologica.class);
                                                        startActivity(ventanavpsicologica);
                                                }

                                            }



                                        }
