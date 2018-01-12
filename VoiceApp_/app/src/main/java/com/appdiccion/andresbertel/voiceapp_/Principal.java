package com.appdiccion.andresbertel.voiceapp_;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.appdiccion.andresbertel.voiceapp_.R.id.messageTextView2;

public class Principal extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    MiBaseDatos MDB;

    int sw = 0;
    String direccionapp = "nada";
    double lati;
    double longi;
    AlertDialog alert = null;
    LocationManager mlocManager;
    Location lastLocation;
    String direccion = "nada";
    Address address = null;
    Geocoder geocoder;
    List<Address> list;
    public ImageButton boton;
    String Latitud = "0";
    String Longitud = "0";


    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int REQUEST_CALL_PHONE = 103;
    private static final int REQUEST_SEND_SMS = 104;
    // private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=102;

    private GoogleApiClient apiClient;
    private TextView lblLatitudLongitud;
    private TextView lblDireccion;
    public ImageButton violentometro;
    private ImageButton btninformate;
    private ImageButton btnapoya;
    // private ToggleButton btnActualizar;

    /*********VARIABLES DE CONTACTOS**********/
    String nombre = "";
    String telefono = "12345";
    String email = "";

    /***********MIS DATOS PERSONALES****************/
    String NombrePropio = "";
    String Alias = "";
    String Policia = "12345";

    /*********VARIABLE DE MENSAJE***********/
    String mensajeauxilio;


    /*************VARIABLE LISTA DE CONTACTOS**************/
    List a;
    int longitLis = 0;

    /************VARIABLE SW PARA PERMISO DE MENSAJES**********/

    @Override
    protected void onStart() {
        super.onStart();
        apiClient.connect();

/**********************SOLICITO PERMISOS PARA ENVIAR MENSAJES DE TEXTO***************************/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "SI NECESITO PERMISOS MESAJE TEXTO", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        } else {
           // Toast.makeText(this, "NO NECESITO PERMISOS MESAJE TEXTO", Toast.LENGTH_SHORT).show();
        }

/**********************SOLICITO PERMISOS PARA REALIZAR LLAMADAS***************************/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
           // Toast.makeText(this, "SI NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        } else {
            //Toast.makeText(this, "NO NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.content_principal);
        MDB = new MiBaseDatos(getApplicationContext());

        a = MDB.recuperarCONTACTOS();

        lblLatitudLongitud = (TextView) findViewById(messageTextView2);
        lblDireccion = (TextView) findViewById(R.id.messageTextView);
        boton = (ImageButton) findViewById(R.id.button);
        violentometro = (ImageButton) findViewById(R.id.btnviolentometro);
        btninformate = (ImageButton) findViewById(R.id.bntinformate);
        btnapoya = (ImageButton) findViewById(R.id.btnapoya);


        apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        btnapoya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent irapoyo = new Intent(getApplicationContext(), Apoya.class);
                startActivity(irapoyo);
                // msn("3015735780","Holaa","Andres");
            }
        });


        btninformate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent irinformate = new Intent(getApplicationContext(), TiposdeViolencia.class);
                startActivity(irinformate);
                //call("3015735780");


            }
        });

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


                mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if ((!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) || (isNetDisponible() == false)) {
                    AlertNoRed();
                } else {
                    //Toast.makeText(getApplicationContext(),"Else boton ..........",Toast.LENGTH_SHORT).show();

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        //Toast.makeText(getApplicationContext(),"Entre a los permisos ..........",Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getApplicationContext(),"Entre a los permisos Else ..............",Toast.LENGTH_SHORT).show();
                    }

                    Location lastLocation = null;

                    // Check if we're running on Android 5.0 or higher
                    if (Build.VERSION.SDK_INT >= 21) {
                        // Call some material design APIs here
                        // Toast.makeText(getApplicationContext(),"Condicional de Versiones "+lastLocation,Toast.LENGTH_SHORT).show();
                        lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                    } else {
                        // Implement this feature without material design
                        lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                    }

                    updateUI(lastLocation);

                    try {
                        // Toast.makeText(getApplicationContext(),"Entre al try",Toast.LENGTH_SHORT).show();
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        //   Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                        Double la, lon;
                        //Toast.makeText(getApplicationContext(),"dos",Toast.LENGTH_SHORT).show();
                        la = Double.valueOf(Latitud);
                        lon = Double.valueOf(Longitud);
                        //  Toast.makeText(getApplicationContext(),"tres",Toast.LENGTH_SHORT).show();
                        List<Address> list = geocoder.getFromLocation(la, lon, 1);


                        if (!list.isEmpty()) {
                            // msn("3015735780","Mensajee", "Andres");
                            EnviarMesajeAuxilio();
                        }


                        Toast.makeText(getApplicationContext(), "llamada", Toast.LENGTH_SHORT).show();
                        call(Policia);//REALIZA LLAMADAAAAA

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error " + e, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            }
        });


    }

    public void buscarMisDatos() {
        MisDatosPersonales dp = MDB.recuperarMISDATOS(1);

        if (dp != null) {
            NombrePropio = dp.getNombre();
            Alias = dp.getAlias();
            Policia = dp.getPolicia();

        }
    }


    private void EnviarMesajeAuxilio() {
        // Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();

        buscarMisDatos();
        if (list != null) {
            address = list.get(0);
            direccionapp = address.getAddressLine(0);
            lblDireccion.setText(direccionapp);
            lblLatitudLongitud.setText("Latitud: " + Latitud + " Longitud: " + Longitud);
        }


        longitLis = a.size();
        if (longitLis == 0) {
            Toast.makeText(getApplicationContext(), "NO EXISTEN CONTACTOS", Toast.LENGTH_SHORT).show();
        } else {
            int con = 0;
            int id = 0;

            while (con < longitLis) {
                Contactos contactos = (Contactos) a.get(con);
                id = contactos.getID();
                nombre = contactos.getNOMBRE();
                telefono = contactos.getTELEFONO();
                email = contactos.getEMAIL();
                con = con + 1;
                mensajeauxilio = "AUXILIO ESTOY EN PELIGRO, MI UBICACIÓN ES: " + direccionapp + " LLAMA A LA POLICIA, BUSCAME EN EL MAPA: https://www.google.com.co/maps/place/" + Latitud + "," + Longitud;
                mail(email, mensajeauxilio, NombrePropio + " (" + Alias + ")");
                //Open call function

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    //requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                } else {
                    msn(telefono, mensajeauxilio, NombrePropio + " (" + Alias + ")");
                    Toast.makeText(this, "NO NECESITO PERMISOS MESAJE TEXTO", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getApplicationContext(), "Contacto Agregado " + "id: " + id + " Nombres: " + nombre + " Celular: " + telefono + " Email: " + email, Toast.LENGTH_SHORT).show();
            }

        }//fin si
    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.
        //   Toast.makeText(this,"Entre a onConnectionFailed",Toast.LENGTH_SHORT).show();
        // Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {

            Location lastLocation =LocationServices.FusedLocationApi.getLastLocation(apiClient);
            updateUI(lastLocation);
        }
    }


    private void call(String Numero) {

        String number = Numero;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
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

    private void msn(String Numero, String Mensaje, String Nombre) {

        Toast.makeText(this,"metodo msn",Toast.LENGTH_SHORT).show();
        ArrayList Mensajep;
        // Check the SDK version and whether the permission is already granted or not.


        SmsManager sms = SmsManager.getDefault();
        Mensaje = "Soy " + Nombre + " " + Mensaje;
        if (Mensaje.length() > 100) {
            Mensajep = sms.divideMessage(Mensaje);
            sms.sendMultipartTextMessage(Numero, null, Mensajep, null, null);

              } else {
                 sms.sendTextMessage(Numero, null, Mensaje, null, null);

          }

           Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
    }




    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services
        Toast.makeText(this, "Se ha interrumpido la conexión con Google Play Services", Toast.LENGTH_SHORT).show();
    }

    private void updateUI(Location loc) {
     //   Toast.makeText(this,"Entre a updateUI",Toast.LENGTH_SHORT).show();
                try{
                //    Toast.makeText(this,"Entre al TRY",Toast.LENGTH_SHORT).show();
                    if (loc!= null) {
                        try {
                           // Toast.makeText(this,"Entre a if",Toast.LENGTH_SHORT).show();
                            geocoder = new Geocoder(this, Locale.getDefault());
                            list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                            Latitud=String.valueOf(loc.getLatitude());
                            Longitud=String.valueOf(loc.getLongitude());
                           } catch (IOException e) {
                            e.printStackTrace();
                            }
                            } else {
                        //Toast.makeText(this,"Entre a else",Toast.LENGTH_SHORT).show();
                        lblLatitudLongitud.setText("Desconocido");
                        lblDireccion.setText("Desconocido");
                       }

                   } catch (Exception E){

                    Toast.makeText(this,"catch updateUI: "+E,Toast.LENGTH_SHORT).show();
             }

         }

    public void mail(final String destinatarios, final String Mensaje,String Nombre){
        final String Mensaje1="SOY "+Nombre+", "+Mensaje;
          new Thread(new Runnable() {
            @Override
             public void run() {
                try {
                    GMailSender sender = new GMailSender("voiceapp0@gmail.com","Cecarvirtual2");
                    sender.sendMail("AUXILIO, NECESITO AYUDA URGENTE!!",
                            Mensaje1,
                            "voiceapp0@gmail.com",
                            destinatarios);
                   } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage("El sistema GPS estan desactivados")
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }


    private void AlertNoRed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setMessage("ACTIVAR RED Y/O GPS")
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();

        lblLatitudLongitud.setText("");
        lblDireccion.setText("");
    }

    private boolean isNetDisponible() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
                  NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
                         return (actNetInfo != null && actNetInfo.isConnected());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);

            }

        }

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                //call(Policia);
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

       if (requestCode == REQUEST_SEND_SMS) {
            if (grantResults.length == 1 &&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "PERMISO MENSAJE TEXTO", Toast.LENGTH_SHORT).show();
              //  sw=1;
                //msn(telefono, mensajeauxilio, NombrePropio + " (" + Alias + ")");
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }



    }






}
