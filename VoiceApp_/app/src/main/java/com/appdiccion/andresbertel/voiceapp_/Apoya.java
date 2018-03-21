package com.appdiccion.andresbertel.voiceapp_;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.ListaContactos.ListaContactosPrincipal;
import com.google.android.gms.location.LocationServices;

public class Apoya extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE = 103;
    private static final int REQUEST_CALL_PHONE_MUJER = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_apoya);

        ImageButton btnPolicia=(ImageButton)findViewById(R.id.imagePolicia);
        ImageButton btnMujer=(ImageButton)findViewById(R.id.imageMujer);


        btnMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Mujer",Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Toast.makeText(this, "SI NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_MUJER);
                } else {
                    //Toast.makeText(this, "NO NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();
                    call("155");
                }
            }
        });


        btnPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(getApplicationContext(),"Policia",Toast.LENGTH_SHORT).show();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Toast.makeText(this, "SI NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                } else {
                    //Toast.makeText(this, "NO NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();
                    //call("123");
                    callEmergencia();
                }


            }
        });
    }



    public void callEmergencia(){
        try {
            Intent callIntent = new Intent("com.android.phone.EmergencyDialer.DIAL");

            callIntent.setData(Uri.parse("tel:123")); // < Optional, will prompt number for user

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            startActivity(callIntent);
        } catch (Exception e) {
            // in case something went wrong ...
        }

    }
    private void call(String Numero) {

        String number = Numero;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
            /*case R.id.imisagresor:
                Intent VentanaAgresores = new Intent(getApplicationContext(), MisAgresores.class);
                startActivity(VentanaAgresores);
                //Toast.makeText(this, "mis datos", Toast.LENGTH_LONG).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                call("123");
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_CALL_PHONE_MUJER) {
            if (grantResults.length == 1 &&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call("155");
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
