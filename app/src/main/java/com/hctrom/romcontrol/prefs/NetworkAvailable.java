package com.hctrom.romcontrol.prefs;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Iván on 29-05-2016.
 */
public class NetworkAvailable {

    /**
     * Comprueba si hay conexión a internet.
     * @return boolean
     */
    public static boolean existeConexionInternet(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }else {
            return false;
        }
    }

}
