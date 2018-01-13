package com.appdiccion.andresbertel.voiceapp_;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;




public class inicio extends AppCompatActivity {

    public EditText messageTextView2;
    public EditText messageTextView;
    public String Direct = "nada";
    EditText celular;
    AlertDialog alert = null;
    int sw = 1;
    LocationManager mlocManager;
    String direccionapp = "nada";
    double lati;
    double longi;
    public ProgressBar spinner;
    public ImageButton boton;
    public ImageButton violentometro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        boton = (ImageButton) findViewById(R.id.button);
        violentometro = (ImageButton) findViewById(R.id.button3);


        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);

        messageTextView2 = (EditText) findViewById(R.id.messageTextView2);
        messageTextView = (EditText) findViewById(R.id.messageTextView);

        // messageTextView.setVisibility(View.INVISIBLE);
        // messageTextView2.setVisibility(View.INVISIBLE);


        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertNoGps();
        }else{
            ubicacion();
        }





        violentometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent irviolentometro = new Intent(getApplicationContext(), RegistroUsuario.class);
                startActivity(irviolentometro);


            }
        });


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            callPhone("1234");
                        } catch (Exception e) {
                            Log.e("llamar", e.getMessage(), e);
                        }
                    }

                }).start();

                sw = 0;

                if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    AlertNoGps();
                }

                if (direccionapp.equals("nada")) {
                    ubicacion();
                } else {

                    String mensajeauxilio = "AUXILIO ESTOY EN PELIGRO, MI UBICACIÓN ES: " + direccionapp + " LLAMA A LA POLICIA, BUSCAME EN EL MAPA: https://www.google.com.co/maps/place/" + lati + "," + longi;
                    mail("Jennifer.garrido@cecar.edu.co,viancy.tapia@cecar.edu.co ", mensajeauxilio, "Viancy");
                    EnviarMensaje("3015557298", mensajeauxilio, "Viancy");

                }


            }
        });


        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "contactos", Toast.LENGTH_LONG).show();
                return true;
            case R.id.imisdatos:
                Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //  Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertNoGps();
        }
        // Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        // Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        // Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public void wsp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Este es el mensaje");
        sendIntent.setType("text/plain");
        //sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);

    }

    public void enviaMensajeWhatsApp(String msj) {
        PackageManager pm = getPackageManager();
        try {
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");
            waIntent.putExtra(Intent.EXTRA_TEXT, msj);
            startActivity(Intent.createChooser(waIntent, "Compartir con:"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    private void EnviarMensaje(String Numero, String Mensaje, String Nombre) {
        ArrayList Mensajep;
        try {
            SmsManager sms = SmsManager.getDefault();

            Mensaje = "Soy " + Nombre + " " + Mensaje;
            if (Mensaje.length() > 100) {
                Mensajep = sms.divideMessage(Mensaje);

                sms.sendMultipartTextMessage(Numero, null, Mensajep, null, null);

            } else {
                sms.sendTextMessage(Numero, null, Mensaje, null, null);

            }


            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


    public void callPhone(String number) {


        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+"+number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);





    }

    public void ubicacion(){


        MyLocationListener mlocListener = new MyLocationListener(this,messageTextView);

        mlocListener.setMainActivity(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                 return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mlocListener);

    }



    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }





    public void setLocation(Location loc) {
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        String direccion="nada";
        Address address = null;
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);



                if (!list.isEmpty() ) {
                    address = list.get(0);

                    String mensajeauxilio="AUXILIO ESTOY EN PELIGRO, MI UBICACIÓN ES: "+address.getAddressLine(0)+" LLAMA A LA POLICIA, BUSCAME EN EL MAPA: https://www.google.com.co/maps/place/"+loc.getLatitude()+","+loc.getLongitude();

                        if(sw==0){
                            mail("extreme-g17@hotmail.com,andres.bertel@hotmail.com",mensajeauxilio,"Viancy");
                            EnviarMensaje("3015557298",mensajeauxilio,"Viancy");
                            sw=1;
                        }


                    direccionapp=address.getAddressLine(0);
                    lati=loc.getLatitude();
                    longi=loc.getLongitude();
                   // callPhone(celular.getText().toString());//Aquí ejecutamos nuestras tareas costosas

                    //Direct=address.getAddressLine(0);

                    messageTextView2.setText(address.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return direccion=address.getAddressLine(0);
    }


    private void enviarWhatsApp(){
        //Instanciamos un Intent del tipo ACTION_SEND
        Intent wappIntent = new Intent(android.content.Intent.ACTION_SEND);
        //Definimos la tipologia de datos del contenido del mensaje de WhatsApp en este caso text/plain
        wappIntent.setType("text/plain");
        // Obtenemos la referencia al texto y lo pasamos al WhatsApp Intent
        wappIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.app_name));
        //Definimos whatsapp com proveeddor para el envío
        wappIntent.setPackage("com.whatsapp");
        try {
            //Enviamos el mensaje iniciando una nueva Activity con el wappIntent.
            startActivity(wappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(inicio.this, "No hay ninguna cuenta de WhatsApp configurada.", Toast.LENGTH_SHORT).show();
        }
    }




public void mail(final String destinatarios, final String Mensaje,String Nombre){

    final String Mensaje1="SOY "+Nombre+", "+Mensaje;

    new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                GMailSender sender = new GMailSender("voiceapp0@gmail.com","Cecarvirtual1");
                sender.sendMail("AUXILIOO",
                        Mensaje1,
                        "voiceapp0@gmail.com",
                        destinatarios);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
        }

    }).start();


}












}

