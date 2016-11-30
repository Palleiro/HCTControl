package com.hctrom.romcontrol.alertas;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;
import com.hctrom.romcontrol.prefs.Shell;

/**
 * Created by Palleiro on 13/12/2015.
 */
public class DialogosMenuReiniciar {

    public static Dialog reiniciar (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reiniciar Sistema\".\n\n¿Desea reiniciar ahora?")
                .setTitle("REINICIAR SISTEMA")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("reboot");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public static Dialog reiniciarSystemUI (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reiniciar SystemUI\".\n\n¿Desea reiniciarlo ahora?")
                .setTitle("REINICIAR SYSTEMUI")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("pkill com.android.systemui");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public static Dialog reiniciarRapido (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reinicio Rápido\".\n\n¿Desea reiniciar ahora?")
                .setTitle("REINICIO RÁPIDO")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("busybox killall system_server");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public static Dialog reiniciarRecovery (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reiniciar Recovery\".\n\n¿Desea reiniciar ahora?")
                .setTitle("REINICIAR RECOVERY")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("reboot recovery");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public static Dialog reiniciarDownload (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reiniciar Download\".\n\n¿Desea reiniciar ahora?")
                .setTitle("REINICIAR DOWNLOAD")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("reboot download");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public static Dialog reiniciarLauncher (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reiniciar Launcher\".\n\n¿Desea reiniciarlo ahora?")
                .setTitle("REINICIAR LAUNCHER")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("pkill com.sec.android.app.launcher");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }

    public static Dialog reiniciarInCallUI (final View v){
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(v.getContext());
        theme1.onActivityCreateSetTheme((Activity) v.getContext());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());

        builder.setMessage("Ha seleccionado la opción de \"Reiniciar InCallUI\".\n\n¿Desea reiniciarlo ahora?")
                .setTitle("REINICIAR INCALLUI")
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
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity) v.getContext()).finish();
                        Shell.getRebootAction("pkill com.android.incallui");
                    }
                })
                .setNegativeButton("AHORA NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = v.getContext().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, v.getContext());

        return dialog;
    }
}
