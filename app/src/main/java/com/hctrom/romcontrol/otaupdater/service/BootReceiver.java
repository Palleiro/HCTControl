package com.hctrom.romcontrol.otaupdater.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hctrom.romcontrol.otaupdater.misc.Constants;


/**
 * Created by sumit on 18/12/16.
 */

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "UpdateCheckReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean checkAuto = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ota_autocheck", true);
        if (checkAuto == true) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            boolean bootCheckCompleted = prefs.getBoolean(Constants.BOOT_CHECK_COMPLETED, false);

            Log.i(TAG, "Starting");
            Intent i = new Intent(context, UpdateChecker.class);
            context.startService(i);
        }else{
            //Toast.makeText(context, "OTA Check AUTO: OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
