package com.hctrom.romcontrol.alertas;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;

/**
 * Created by Palleiro on 13/12/2015.
 */
public class DialogoAppNoInstalada {

    public static Dialog dialogoAppNoInstalada (final View v, String paquete){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());

        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Esta aplicación NO se encuentra instalada en el dispositivo.")
                .setTitle("APP NO INSTALADA")
                .setOnKeyListener(new Dialog.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                        // TODO Auto-generated method stub
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            // No hacer nada al pulsar el Botón Atrás
                        }
                        return true;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public void checkAppInstalada (final View v, String paquete){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());

        boolean tw_launcher = appInstalledOrNot(paquete, v);
        if (tw_launcher){
            dialogoAppNoInstalada(v, paquete);
            //Toast.makeText(v.getContext(), "App NO instalada ...", Toast.LENGTH_LONG).show();
        }
    }

    private static boolean appInstalledOrNot(String uri, View v) {
        PackageManager pm = v.getContext().getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
