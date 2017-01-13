package com.hctrom.romcontrol.otaupdater.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.otaupdater.activity.DialogActivity;
import com.hctrom.romcontrol.otaupdater.otalibary.RomUpdaterUtils;
import com.hctrom.romcontrol.otaupdater.otalibary.enums.RomUpdaterError;
import com.hctrom.romcontrol.otaupdater.otalibary.enums.UpdateFrom;
import com.hctrom.romcontrol.otaupdater.otalibary.objects.Update;

import static com.hctrom.romcontrol.otaupdater.util.Config.ShowToast;
import static com.hctrom.romcontrol.otaupdater.util.Config.Showlog;
import static com.hctrom.romcontrol.otaupdater.util.Config.UpdaterUri;

/**
 * Created by sumit on 20/12/16.
 */

public class UpdateChecker extends Service {
    private String Tag="UpdateChecker";
    private NotificationCompat.Builder mBuilder;

    public IBinder onBind(Intent intent) {
        return null;
    }
    @Nullable
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Tag,"Started");
        RomUpdaterUtils romUpdaterUtils = new RomUpdaterUtils(this)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML(UpdaterUri())
                .withListener(new RomUpdaterUtils.UpdateListener() {
                    @Override
                    public void onSuccess(final Update update, Boolean isUpdateAvailable) {
                        if(Showlog().equals(true));
                        {
                            Log.d("Found", "Update Found");
                            Log.d("RomUpdater", update.getLatestVersion() + ", " + update.getUrlToDownload() + ", " + Boolean.toString(isUpdateAvailable));
                        }
                        if(isUpdateAvailable==true)
                        {
                            mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(UpdateChecker.this)
                                    .setSmallIcon(R.drawable.ota_ic_stat_name)
                                    .setContentTitle("OTA Rom Update")
                                    .setContentText("Actualización de Rom disponible")
                                    .setContentInfo("Descargar")
                                    .setAutoCancel(true);
                            Intent intent = new Intent(UpdateChecker.this, DialogActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(UpdateChecker.this,0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
                            mBuilder.setContentIntent(pi);
                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            mNotificationManager.notify(0, mBuilder.build());
                            if(Showlog().equals(true));
                            {
                                Log.d("Found", String.valueOf(update.getUrlToDownload()));
                            }
                        }

                    }
                    @Override
                    public void onFailed(RomUpdaterError error) {
                        if(Showlog().equals(true));
                        {
                            Log.d("RomUpdater", "Something went wrong");
                        }
                    }

                });
        romUpdaterUtils.start();

        Boolean checkAuto = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("ota_autocheck", true);
        if (checkAuto == true) {
            if(ShowToast().equals(true));
            {
                Toast.makeText(this, "OTA Service", Toast.LENGTH_LONG).show();
            }
        }
        return START_STICKY;
    }

}
