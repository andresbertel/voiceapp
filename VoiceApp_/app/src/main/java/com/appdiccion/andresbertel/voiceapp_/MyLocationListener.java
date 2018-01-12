package com.appdiccion.andresbertel.voiceapp_;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by andres.bertel on 25/10/2017.
 */

public class MyLocationListener extends AppCompatActivity  implements LocationListener {



    inicio mainActivity;
    EditText messageTextView;

    public MyLocationListener(inicio mainActivity, EditText ET){
        this.mainActivity=mainActivity;
        messageTextView= (EditText) ET;

    }






    public inicio getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(inicio mainActivity) {
        this.mainActivity = mainActivity;
    }




    public void onCreate(Location loc){
        Toast.makeText(mainActivity, "Oncreate de listener", Toast.LENGTH_SHORT).show();
        loc.getLatitude();
        loc.getLongitude();
        String Text = " Lat = " + loc.getLatitude() + "\n Long = " + loc.getLongitude();


        messageTextView.setText(Text);
        this.mainActivity.setLocation(loc);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(mainActivity, "oncreate de listener", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location loc) {

        loc.getLatitude();
        loc.getLongitude();
        String Text = " Lat = "+ loc.getLatitude() + "\n Long = " + loc.getLongitude();


        messageTextView.setText(Text);
        this.mainActivity.setLocation(loc);

        this.mainActivity.spinner.setVisibility(View.INVISIBLE);



    }


    @Override
    public void onProviderDisabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es desactivado
        messageTextView.setText("GPS Desactivado");
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es activado
        messageTextView.setText("GPS Activado");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Este mŽtodo se ejecuta cada vez que se detecta un cambio en el
        // status del proveedor de localizaci—n (GPS)
        // Los diferentes Status son:
        // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
        // TEMPORARILY_UNAVAILABLE -> Temp˜ralmente no disponible pero se
        // espera que este disponible en breve
        // AVAILABLE -> Disponible
    }




}