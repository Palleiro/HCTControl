package com.hctrom.romcontrol.alertas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.Button;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;
import com.hctrom.romcontrol.prefs.Shell;

/**
 * Created by Palleiro on 13/12/2015.
 */
public class DialogoAlertaReiniciar extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ThemeSelectorUtility theme1 = new ThemeSelectorUtility(getActivity());
        theme1.onActivityCreateSetTheme(getActivity());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("Es necesario reiniciar el teléfono para terminar de aplicar los cambios.\n\nDesea reiniciar ahora?")
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
                        getActivity().finish();
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
        Resources.Theme theme2 = getActivity().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
        int color2 = typedValue2.data;

        positive_button.setTextColor(color2);
        negative_button.setTextColor(color2);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, getActivity());

        return dialog;
    }
}
