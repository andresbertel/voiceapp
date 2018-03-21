package com.appdiccion.andresbertel.voiceapp_;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.appdiccion.andresbertel.voiceapp_.Modelo.Datos ;

import java.util.ArrayList;

public class RegistroUsuario extends AppCompatActivity {

    ArrayList<PreguntasVo> listaPregunta;
    RecyclerView recicle;

    TextView NombreCuestionario,NombrePersona;

    Button boton;

    Integer nivel=0;
    String retorno;

    Datos objDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        listaPregunta=new ArrayList<PreguntasVo>();
        recicle=(RecyclerView)findViewById(R.id.RecyclerViewP);
        recicle.setLayoutManager(new LinearLayoutManager(this));

        recicle.setItemViewCacheSize(20);

        NombreCuestionario=(TextView)findViewById(R.id.textView6);
        //NombrePersona=(TextView)findViewById(R.id.textView12);

       // objDatos=(Datos)getIntent().getExtras().getSerializable("DATOS");
        NombreCuestionario.setText("NOMBRE DEL TEST: VIOLENTOMETRO");
      //  NombrePersona.setText("PARTICIPANTE: USUARIO");


        llenarPreguntas();

        final AdaptadorPreguntas adapter=new AdaptadorPreguntas(listaPregunta);
        recicle.setAdapter(adapter);

        boton=(Button)findViewById(R.id.button3);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                responder();

                String data = "";
                ArrayList<PreguntasVo> stList = ((AdaptadorPreguntas)adapter).getSelectedUnitsist();
                for (int i = 0; i < stList.size(); i++) {
                    PreguntasVo singleUnits = stList.get(i);
                        data = data + "\n" + singleUnits.getPregunta()+ " , " + singleUnits.getItem();
                       // Toast.makeText(getApplicationContext(),"Selected Units with ranges: \n" +data , Toast.LENGTH_LONG).show();
                   // Log.i("INFORMACION ",""+data );
                    nivel+=singleUnits.getItem();

                }

                if(nivel<=5){
                    retorno="Esta Fuera del Rango";
                }

                if(nivel>=6 && nivel<=15){
                    retorno="Relación con primeras señales de violencia: Existen problemas, pero se resuelven sin violencia física. Los actos violentos son minimizados y justificados por LA VIOLENCIA PUEDE AUMENTAR, ¡TOMA UNA DECISIÓN!";
                }

                if(nivel>=16 && nivel<=25){
                    retorno="Relación de abuso: Tu pareja está usando cada vez más la violencia para resolver los conflictos y la tensión se empieza a acumular. Debes controlar la situación con ¡CUIDADO! REACCIONA NO TE DEJES DESTRUIR";
                }

                if(nivel>=26 && nivel<=40){
                    retorno="Relación de abuso severo: Definitivamente tu relación es violenta. Los actos violentos se dan bajo cualquier pretexto y cada vez más frecuentes e intensos. Después de la agresión, intenta remediar el daño te pide perdón y te promete que no volverá a ocurrir. Esta etapa es difícil porque sientes miedo y vergüenza por lo que pasa. Tienes esperanza de que cambiara. ¡BUSCA AYUDA YA!";
                }

                if(nivel>=41 ){
                    retorno="Relación Violenta: Es URGENTE que tomes medidas de seguridad y que recibas inmediatamente ayuda especializada, tu vida está en peligro, tu salud física y/o mental puede quedar severamente dañada. !DENUNCIA INMEDIATAMENTE!";
                }

                new AlertDialog.Builder(RegistroUsuario.this)
                        .setTitle("RESULTADO")
                        .setMessage("Indice de violencia según el puntaje obtenido \n Resultado "+  nivel+" \n   "+retorno)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        }).show();

                nivel=0;
            }
        });



    }

    private void responder() {
       /* ConexionSqLiteHelper conn=new ConexionSqLiteHelper(getApplicationContext(),"db_test",null, Utilidades.DATA_VERSION);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues Convalues=new ContentValues();
        Convalues.put(Utilidades.CAMPO_ID_PERSONA_RESPONDER, Integer.parseInt(""+objDatos.getId_persona()));
        Long IdResultante=db.insert(Utilidades.TABLA_PERSONA,Utilidades.CAMPO_ID_PERSONA,Convalues);
         public static final String TABLA_RESPONDER="responder";
       public static final String CAMPO_ID_CUESTIONARIO_RESPONDER = "idCuestinario";
        public static final String CAMPO_ID_PREGUNTA_RESPONDER = "idPregunta";
        public static final String CAMPO_ID_RESPONDER="id";
        public static final String CAMPO_ID_RESPUESTA="idRespuesta";
        */

    }

    private void llenarPreguntas() {
        listaPregunta.add(new PreguntasVo("Cuando se dirige a ti ¿te llama por un apodo que te desagrade y/o con groserías? ", 1, 112,0));
        listaPregunta.add(new PreguntasVo("Te ha dicho que estás con alguien más, o que tus amigos quieren estar contigo? ", 2, 113,0));
        listaPregunta.add(new PreguntasVo("¿Te dice que tiene otras mujeres y te compara con tus ex parejas? ", 3, 114,0));
        listaPregunta.add(new PreguntasVo("¿Todo el tiempo quiere saber qué haces y con quién estas?", 4, 115,0));
        listaPregunta.add(new PreguntasVo("¿Te critica, se burla de tu cuerpo y exagera de tus defectos en público o en privado? ", 5, 116,0));
        listaPregunta.add(new PreguntasVo("Cuando estás con tu pareja ¿sientes tensión y piensas que hagas lo que hagas se molestará?", 6, 119,0));
        listaPregunta.add(new PreguntasVo("Para decidir lo que harán cuando salen ¿Ignora tu opinión?", 6, 119,0));
        listaPregunta.add(new PreguntasVo("Cuando hablan ¿te sientes mal porque sólo te habla de sexo y te pregunta si tuviste relaciones sexuales con otras personas?", 8, 119,0));
        listaPregunta.add(new PreguntasVo("¿Sientes que tu pareja constantemente te está controlando por amor?", 9, 119,0));
        listaPregunta.add(new PreguntasVo("¿Controla tus redes sociales y el uso de tu movil?", 10, 119,0));
        listaPregunta.add(new PreguntasVo("¿Te riculiza, te descalifica y te anula tu autoestima haciendote sentir que no eres buena para nada?", 11, 119,0));
        listaPregunta.add(new PreguntasVo("Si has cedido a sus deseos sexuales ¿sientes que ha sido por amor por temor, por complacer o presión?", 12, 119,0));
        listaPregunta.add(new PreguntasVo("Si tienen relaciones sexuales ¿Te impide o condiciona el uso de métodos anticonceptivos?", 13, 119,0));
        listaPregunta.add(new PreguntasVo("¿Te ha obligado a ver pornografía y/o a tener prácticas sexuales que te desagraden?", 14, 119,0));
        listaPregunta.add(new PreguntasVo("¿Te ha presionado u obligado a consumir droga?", 15, 119,0));
        listaPregunta.add(new PreguntasVo("Si tu pareja toma alcohol o se droga ¿se comporta violento contigo o con otras personas?", 16, 119,0));
        listaPregunta.add(new PreguntasVo("A causa de los problemas con tu pareja ¿has tenido unas o más de las siguientes alteraciones: pérdida del apetito, sueño, malos resultados, abandono de tus actividades, alejamiento de tus amigos o amigas y familiares?", 17, 119,0));
        listaPregunta.add(new PreguntasVo("Cuando se enojan o discuten ¿has sentido que tu vida está en peligro?", 18, 119,0));
        listaPregunta.add(new PreguntasVo("¿Te ha golpeado con alguna parte de su cuerpo o con algún objeto?", 19, 119,0));
        listaPregunta.add(new PreguntasVo("¿Alguna vez te ha causado lesiones que ameriten recibir atención médica, psicológica, jurídica y/o auxilio policial?", 20, 119,0));
        listaPregunta.add(new PreguntasVo("¿Te ha amenazado con matarse  cuando le dices que quieres terminar con él, o cuando tienen algún problema?", 21, 119,0));
        listaPregunta.add(new PreguntasVo("Después de una discusión fuerte en donde te humilla, e incluso golpea ¿tu pareja se muestra cariñosa y atenta; te regala cosas y te promete que nunca más volverá a suceder, que todo cambiará?", 22, 119,0));


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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
