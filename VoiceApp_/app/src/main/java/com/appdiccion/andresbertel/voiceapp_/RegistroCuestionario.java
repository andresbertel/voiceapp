package com.appdiccion.andresbertel.voiceapp_;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.appdiccion.andresbertel.voiceapp_.Modelo.Datos ;
import com.appdiccion.andresbertel.voiceapp_.Utilidades.Utilidades;

import java.util.ArrayList;

public class RegistroCuestionario extends AppCompatActivity {

    boolean encontrado=false;

    Spinner tipo_cuestionario;
    EditText identifiacion;
    //nombre
    EditText nombre;
    //edad
    EditText edad;
    Button comenzar;
    ArrayList<com.appdiccion.andresbertel.voiceapp_.Modelo.Cuestionario> cuestionariosList;
    ArrayList<String> cuestiona;

    ConexionSqLiteHelper cnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuestionario);

        //identifiacion
        identifiacion=(EditText)findViewById(R.id.editText7);
        //nombre
        nombre=(EditText)findViewById(R.id.editText6);
        //edad
        edad=(EditText)findViewById(R.id.editText3);
        tipo_cuestionario=(Spinner)findViewById(R.id.spinner);

        Button cestionario_crear=(Button)findViewById(R.id.button5);
        cestionario_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento=new Intent(RegistroCuestionario.this,Cuestionario.class);
                startActivity(intento);
            }
        });

        Button buscar=(Button)findViewById(R.id.button2);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!identifiacion.getText().toString().equals("") ){
                    cnn=new  ConexionSqLiteHelper(getApplicationContext(),"db_test",null,Utilidades.DATA_VERSION);
                    buscarDatos();
                }else{
                    Toast.makeText(getApplicationContext(),"Debe Rellenar el campo identificacion. ",Toast.LENGTH_SHORT).show();
                }
            }
        });


        comenzar=(Button)findViewById(R.id.button4);
        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(true){
                    ConexionSqLiteHelper conn=new ConexionSqLiteHelper(getApplicationContext(),"db_test",null,Utilidades.DATA_VERSION);
                    SQLiteDatabase db=conn.getWritableDatabase();

                    if(encontrado==false){
                        ContentValues Convalues=new ContentValues();
                        Convalues.put(Utilidades.CAMPO_ID_PERSONA, Integer.parseInt(""+identifiacion.getText().toString()));
                        Convalues.put(Utilidades.CAMPO_NOMBRE_PERSONA, nombre.getText().toString());
                        Convalues.put(Utilidades.CAMPO_EDAD_PERSONA, Integer.parseInt(""+edad.getText().toString()));
                        Long IdResultante=db.insert(Utilidades.TABLA_PERSONA,Utilidades.CAMPO_ID_PERSONA,Convalues);
                    }

                    Datos datos_Objt=new Datos( nombre.getText().toString(),tipo_cuestionario.getSelectedItem().toString(),identifiacion.getText().toString());
                    Intent intento=new Intent(RegistroCuestionario.this,RegistroUsuario.class);
                    intento.putExtra("DATOS",datos_Objt);


                    startActivity(intento);
                }else{
                    Toast.makeText(getApplicationContext(),"Debe rellenar todos los campos \n", Toast.LENGTH_SHORT).show();
                }


            }
        });

        cnn=new  ConexionSqLiteHelper(getApplicationContext(),"db_test",null,Utilidades.DATA_VERSION);
        consultarListaTest();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,cuestiona);
        tipo_cuestionario.setAdapter(adaptador);
    }

    private void buscarDatos() {
        SQLiteDatabase db=cnn.getReadableDatabase();
        Cursor curso=db.rawQuery("select * from "+ Utilidades.TABLA_PERSONA+" WHERE "+Utilidades.CAMPO_ID_PERSONA+"="+identifiacion.getText(),null);
        while (curso.moveToNext()){
            nombre.setText(curso.getString(1));
            edad.setText(curso.getString(2));
            identifiacion.setText(curso.getString(0));
            encontrado=true;
        }
    }

    private void consultarListaTest() {
        SQLiteDatabase db=cnn.getReadableDatabase();
        cuestionariosList=new ArrayList<com.appdiccion.andresbertel.voiceapp_.Modelo.Cuestionario >();
        com.appdiccion.andresbertel.voiceapp_.Modelo.Cuestionario  cuestionario;

        Cursor curso=db.rawQuery("select * from "+ Utilidades.TABLA_CUESTIONARIO,null);
        while (curso.moveToNext()){
            cuestionario=new com.appdiccion.andresbertel.voiceapp_.Modelo.Cuestionario();
            cuestionario.setID(curso.getInt(3));
            cuestionario.setNOMBRE(curso.getString(1));

            cuestionariosList.add(cuestionario);
        }
        
        obtenerCuestionario();
    }

    private void obtenerCuestionario() {
        cuestiona=new ArrayList<String> ();
        cuestiona.add("Seleccione un cuestionario");
        for (int i=0; i<cuestionariosList.size(); i++){
            cuestiona.add(cuestionariosList.get(i).getID()+" - "+cuestionariosList.get(i).getNOMBRE());
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
