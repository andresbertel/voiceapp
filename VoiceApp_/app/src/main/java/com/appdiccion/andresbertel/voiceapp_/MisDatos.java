package com.appdiccion.andresbertel.voiceapp_;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;

public class MisDatos extends AppCompatActivity {

    MiBaseDatos MBD;
    TextView MiNombre;
    TextView MiAlias;
    TextView Policia;
    ImageButton btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_mis_datos);

        MBD=new MiBaseDatos(getApplicationContext());

       MiNombre=(EditText)findViewById(R.id.txtminombre);
       MiAlias=(EditText)findViewById(R.id.txtalias);
       Policia=(EditText)findViewById(R.id.txpolicia);

       btGuardar=(ImageButton)findViewById(R.id.btnguardar);

        ImageButton btnGuardar=(ImageButton)findViewById(R.id.btnguardar);

         String Nombre=MiNombre.getText().toString();
         String alias1=MiAlias.getText().toString();
         String Poli=Policia.getText().toString();

        cargardatos(1);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarMisDatos(MiNombre.getText().toString(),MiAlias.getText().toString(),Policia.getText().toString());
            }
        });

    }

    public void cargardatos(int id){
        MisDatosPersonales datos=  MBD.recuperarMISDATOS(id);

       if(datos==null){

        }else{
            MiNombre.setText(datos.getNombre());
            MiAlias.setText(datos.getAlias());
            Policia.setText(datos.getPolicia());


        }
    }

    public void GuardarMisDatos(String Nombre,String Alias, String Policia){



      if(verificar(Nombre,Alias,Policia)==false){

          Toast.makeText(getApplicationContext(),"Datos Vacios", Toast.LENGTH_SHORT).show();

                }else{
                      Toast.makeText(getApplicationContext(),"Datos Guardados", Toast.LENGTH_SHORT).show();
                        MisDatosPersonales conta=  MBD.recuperarMISDATOS(1);

                      if(conta==null){
                          MBD.insertarMISDATOS(1,Nombre,Alias,Policia);
                               }else{
                                    MBD.modificarMISDATOS(1,Nombre,Alias,Policia);
                         }

      }

     }

     public boolean verificar(String Nombre,String Alias, String Policia){
        boolean r=false;

         if(Nombre.equals(null)||Alias.equals(null)||Policia.equals(null)||Nombre.equals("")||Alias.equals("")||Policia.equals("")){
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
                Intent ventanamisdatoss = new Intent(getApplicationContext(), MisDatos.class);
                startActivity(ventanamisdatoss);
                Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
