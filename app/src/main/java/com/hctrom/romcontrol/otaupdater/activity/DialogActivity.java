package com.hctrom.romcontrol.otaupdater.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;
import com.hctrom.romcontrol.otaupdater.otalibary.RomUpdaterUtils;
import com.hctrom.romcontrol.otaupdater.otalibary.enums.RomUpdaterError;
import com.hctrom.romcontrol.otaupdater.otalibary.enums.UpdateFrom;
import com.hctrom.romcontrol.otaupdater.otalibary.objects.Update;

import static com.hctrom.romcontrol.otaupdater.util.Config.DownloadFileName;
import static com.hctrom.romcontrol.otaupdater.util.Config.Downloader;
import static com.hctrom.romcontrol.otaupdater.util.Config.Showlog;
import static com.hctrom.romcontrol.otaupdater.util.Config.UpdaterUri;
import static com.hctrom.romcontrol.otaupdater.util.Config.isOnline;
import static com.hctrom.romcontrol.otaupdater.util.Config.uri;
import static java.lang.String.valueOf;

public class DialogActivity extends Activity {
    private ProgressBar mProgressBar;
    private String Tag="RomUpdater";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        setContentView(R.layout.ota_activity_dialog);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme1 = getTheme();
        theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.windowBgTransparent));
        getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        final AlertDialog.Builder UpdaterDialog = new AlertDialog.Builder(this);
        final AlertDialog.Builder mNoUpdate = new AlertDialog.Builder(this);
        if(isOnline(this)) {
            RomUpdaterUtils romUpdaterUtils = new RomUpdaterUtils(this)
                    .setUpdateFrom(UpdateFrom.XML)
                    .setUpdateXML(UpdaterUri())
                    .withListener(new RomUpdaterUtils.UpdateListener() {
                        @Override
                        public void onSuccess(final Update update, Boolean isUpdateAvailable) {
                            if (Showlog().equals(true)) ;
                            {
                                Log.d(Tag, "Update Found");
                                Log.d(Tag, update.getLatestVersion() + ", " + update.getUrlToDownload() + ", " + Boolean.toString(isUpdateAvailable));
                            }
                            if (isUpdateAvailable == false) {
                                mProgressBar.setVisibility(View.GONE);
                                if (Showlog().equals(true)) ;
                                {
                                    Log.i(Tag, "No update found " + valueOf(isUpdateAvailable));
                                }

                                mNoUpdate.setTitle("No hay actualizaciones")
                                        .setMessage("Comprobar más tarde")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                    finish();
                                                }
                                        });

                                AlertDialog dialog = mNoUpdate.create();
                                dialog.show();

                                Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                                TypedValue typedValue = new TypedValue();
                                Resources.Theme theme1 = getTheme();
                                theme1.resolveAttribute(R.attr.colorAccent, typedValue, true);
                                int color = typedValue.data;

                                positive_button.setTextColor(color);
                                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);
                            }

                            if (isUpdateAvailable == true) {
                                mProgressBar.setVisibility(View.GONE);
                                UpdaterDialog.setIcon(R.drawable.ic_ota_hctrom)
                                        .setTitle("¡¡ Actualización disponible !!")
                                        .setMessage("Disponible una nueva versión de Rom : \n\n" + update.getReleaseNotes())
                                        .setPositiveButton("DESCARGAR", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                uri = Uri.parse(valueOf(update.getUrlToDownload()));
                                                DownloadFileName=uri.getLastPathSegment();
                                                Downloader(DialogActivity.this);
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                finish();
                                                dialog.dismiss();
                                            }
                                        });
                                if (Showlog().equals(true)) ;
                                {
                                    Log.d("Found", valueOf(update.getUrlToDownload()));
                                }

                                AlertDialog dialog = UpdaterDialog.create();
                                dialog.setCancelable(false);
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.show();

                                Button negative_button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                                TypedValue typedValue = new TypedValue();
                                Resources.Theme theme1 = getTheme();
                                theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                                int color = typedValue.data;

                                negative_button.setTextColor(color);

                                Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                                Resources.Theme theme2 = getTheme();
                                theme2.resolveAttribute(R.attr.colorAccent, typedValue, true);
                                int color1 = typedValue.data;

                                positive_button.setTextColor(color1);
                                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

                            }

                        }

                        @Override
                        public void onFailed(RomUpdaterError error) {
                            if (Showlog().equals(true)) ;
                            {
                                Log.d("RomUpdater", "Algo ha salido mal");
                            }
                        }

                    });
            romUpdaterUtils.start();
        } else
        {
            mProgressBar.setVisibility(View.GONE);
            mNoUpdate.setTitle("No hay conexión a Internet")
                    .setMessage("Por favor, comprueba la conexión e intentalo de nuevo")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            finish();
                        }
                    });

            AlertDialog dialog = mNoUpdate.create();
            dialog.show();

            Button positive_button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Resources.Theme theme2 = getTheme();
            theme2.resolveAttribute(R.attr.colorAccent, typedValue, true);
            int color = typedValue.data;

            positive_button.setTextColor(color);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
