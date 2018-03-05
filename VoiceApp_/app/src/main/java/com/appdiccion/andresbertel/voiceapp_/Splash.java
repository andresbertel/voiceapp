package com.appdiccion.andresbertel.voiceapp_;

import java.util.Timer;
import java.util.TimerTask;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;



public class Splash extends Activity {

    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int REQUEST_CALL_PHONE = 103;
    private static final int REQUEST_SEND_SMS = 104;

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.content_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        Splash.this, Principal.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    //@Override
    protected void onPause() {
        super.onPause();
        //apiClient.connect();

/**********************SOLICITO PERMISOS PARA ENVIAR MENSAJES DE TEXTO***************************/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "SI NECESITO PERMISOS MESAJE TEXTO", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        } else {
            // Toast.makeText(this, "NO NECESITO PERMISOS MESAJE TEXTO", Toast.LENGTH_SHORT).show();
        }

/**********************SOLICITO PERMISOS PARA REALIZAR LLAMADAS***************************/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(this, "SI NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        } else {
            //Toast.makeText(this, "NO NECESITO PERMISOS LLAMADA", Toast.LENGTH_SHORT).show();

        }


    }



}
