package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CambioAvion extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            if (isAirplaneModeOn) {
                // Modo avión activado
                Toast.makeText(context, "Broadcast ACTIVADO - Modo Avión", Toast.LENGTH_SHORT).show();
            } else {
                // Modo avión desactivado
                Toast.makeText(context, "Broadcast DESACTIVADO - Modo Avión", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
