package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    private CambioAvion ca;
    private ConexionWifiReceiver cw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrarBroadcast();
        permisoCall();
    }

    public void registrarBroadcast() {

        ca = new CambioAvion();
        IntentFilter intentFilterAirplane = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(ca, intentFilterAirplane);

        /*cw = new ConexionWifiReceiver();
        IntentFilter intentFilterWifi = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(cw, intentFilterWifi);*/

        cw = new ConexionWifiReceiver();
        IntentFilter intentFilterWifi = new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE");
        registerReceiver(cw, intentFilterWifi);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(ca);
        unregisterReceiver(cw);
    }

    private void permisoCall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 2500);
        }
    }
}
