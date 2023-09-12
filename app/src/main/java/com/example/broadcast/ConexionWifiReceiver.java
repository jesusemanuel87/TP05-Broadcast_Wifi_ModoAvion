package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ConexionWifiReceiver extends BroadcastReceiver {
    /*
    Otro método utilizando ConnectivityManager.CONNECTIVITY_ACTION
    @Override
     public void onReceive(Context context, Intent intent) {
         if (intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
             ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
             NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

             if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && networkInfo.isConnected()) {

                 realizarLlamada(context);
             }
         }
     }*/

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.net.wifi.supplicant.CONNECTION_CHANGE")) {
            // Verificar si hay una conexión WiFi

            boolean isConnected = intent.getBooleanExtra("connected", false);
            if (isConnected) {

                // Realizar acciones con la conexión WiFi
                Toast.makeText(context, "Conexión WiFi establecida", Toast.LENGTH_SHORT).show();
                realizarLlamada(context);
            } else {
                // Realizar acciones cuando se desconecta de la red WiFi
                Toast.makeText(context, "Desconectado de la red WiFi", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void realizarLlamada(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:2664553747"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Error al realizar la llamada", Toast.LENGTH_SHORT).show();
        }
    }
}
