package com.hctrom.romcontrol.alertas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.Button;

import com.hctrom.romcontrol.hosts.HostWhiteListView;
import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;

/**
 * Created by Palleiro on 13/12/2015.
 */
public class DialogoAlertaDominioDelete extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ThemeSelectorUtility theme = new ThemeSelectorUtility(getActivity());
        theme.onActivityCreateSetTheme(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Desea eliminar el contenido completo de la lista blanca?")
                .setTitle("ELIMINAR LISTA BLANCA")
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
                .setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // ListView Clicked item index
                        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = data.edit();
                        int i = data.getInt("Dominio_value", 0);

                        int x = 0;
                        while (x <= i) {
                            editor.remove("Dominio_item_" + x);
                            editor.apply();
                            x++;
                        }
                        editor.remove("Dominio_value");
                        editor.apply();
                        HostWhiteListView.reiniciarActivity(getActivity());
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme1 = getActivity().getTheme();
        theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;

        positive_button.setTextColor(color);
        negative_button.setTextColor(color);

        ThemeSelectorUtility.ThemeDrawableBG(dialog, getActivity());

        return dialog;
    }
}
