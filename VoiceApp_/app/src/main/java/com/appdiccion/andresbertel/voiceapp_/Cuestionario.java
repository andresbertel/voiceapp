package com.appdiccion.andresbertel.voiceapp_;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.Utilidades.Utilidades;

public class Cuestionario extends AppCompatActivity {

    EditText NombreCuestionario,ObjetoCuestionario,NumeroPagina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        NombreCuestionario=(EditText)findViewById(R.id.editText2);
        ObjetoCuestionario=(EditText)findViewById(R.id.editText);
        NumeroPagina=(EditText)findViewById(R.id.editText4);


        Button button_inicio=(Button)findViewById(R.id.button6);
        button_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento=new Intent(Cuestionario.this,RegistroCuestionario.class);
                startActivity(intento);
            }
        });

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!NombreCuestionario.getText().equals("")  && !ObjetoCuestionario.getText().equals("")  && !NumeroPagina.getText().equals("")){
                    ConexionSqLiteHelper conn=new ConexionSqLiteHelper(getApplicationContext(),"db_test",null,Utilidades.DATA_VERSION);
                    SQLiteDatabase db=conn.getWritableDatabase();

                    ContentValues Convalues=new ContentValues();
                    Convalues.put(Utilidades.CAMPO_NOMBRE,NombreCuestionario.getText().toString());
                    Convalues.put(Utilidades.CAMPO_OBJETO,ObjetoCuestionario.getText().toString());
                    Convalues.put(Utilidades.CAMPO_PREGUNTA,Integer.parseInt(NumeroPagina.getText().toString()));

                    Long IdResultante=db.insert(Utilidades.TABLA_CUESTIONARIO,Utilidades.CAMPO_ID,Convalues);

                    if(IdResultante>=0 ){
                        //Intent intento=new Intent(Cuestionario.this,RegistroUsuario.class);
                        Intent intento=new Intent(Cuestionario.this,RegistroCuestionario.class);
                        startActivity(intento);
                    }
                    // Toast.makeText(getApplicationContext(),"Id REGISTRO "+IdResultante,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Rellene todos los campos",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
