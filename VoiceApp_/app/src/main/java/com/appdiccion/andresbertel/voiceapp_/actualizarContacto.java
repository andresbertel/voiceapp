package com.appdiccion.andresbertel.voiceapp_;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;

import static com.appdiccion.andresbertel.voiceapp_.R.id.btnactualizar;

public class actualizarContacto extends AppCompatActivity {

    String nombres,email,celular;
    Bundle datos;
    int idc;
    MiBaseDatos MBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contacto);

      MBD=new MiBaseDatos(getApplicationContext());

        datos = this.getIntent().getExtras();
        idc=datos.getInt("id");
        nombres=datos.getString("nombre");
        email=datos.getString("email");
        celular=datos.getString("celular");

        final TextView TNombre=(TextView)findViewById(R.id.ETnombre);
        final TextView Tcelular=(TextView)findViewById(R.id.ETcelular);
       final TextView TEmail=(TextView)findViewById(R.id.ETemail);
        ImageButton BtnActualizar=(ImageButton)findViewById(R.id.bntactualizar1);

        TNombre.setText(nombres);
        Tcelular.setText(celular);
        TEmail.setText(email);

       BtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MBD.modificarCONTACTO(idc,TNombre.getText().toString(),Tcelular.getText().toString(),TEmail.getText().toString());
                Toast.makeText(getApplicationContext(),"Contacto Actualizado",Toast.LENGTH_SHORT).show();

                Intent Irlistacontactos=new Intent(getApplicationContext(), ListaContactosPrincipal.class);
                startActivity(Irlistacontactos);

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
                Toast.makeText(this, "Ver contactos", Toast.LENGTH_LONG).show();
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
