package com.hctrom.romcontrol.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.logcat.RecordingWidgetProvider;

/**
 * Created by Palleiro on 18/09/2016.
 */

public class WidgetFlashlightReceiver extends BroadcastReceiver {
    static boolean isLightOn = false;
    private static Camera camera;

    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_catlog_hctcontrol);

        if (PreferenceManager.getDefaultSharedPreferences(context).getInt("aviso_temp_flash", 0) == 0) {
            Intent i = new Intent(context.getApplicationContext(), WidgetFlashlightDialogAlert.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            Toast.makeText(context, "¡¡ Linterna Activada !!", Toast.LENGTH_LONG).show();
            views.setImageViewResource(R.id.buttonTorch, R.drawable.widget_torch_off);
            isLightOn = false;
        }else{
            if(isLightOn) {
                views.setImageViewResource(R.id.buttonTorch, R.drawable.widget_torch_off);
                Toast.makeText(context, "¡¡ Linterna Desactivada !!", Toast.LENGTH_SHORT).show();
            }else {
                views.setImageViewResource(R.id.buttonTorch, R.drawable.widget_torch_on);
                Toast.makeText(context, "¡¡ Linterna Activada !!", Toast.LENGTH_SHORT).show();
            }
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, RecordingWidgetProvider.class), views);

        if (isLightOn) {
            if (camera != null) {
                camera.stopPreview();
                camera.release();
                camera = null;
                isLightOn = false;
            }

        } else {
            // Open the default i.e. the first rear facing camera.
            camera = Camera.open();

            if(camera == null) {
                Toast.makeText(context, "CAMARA NO DETECTADA", Toast.LENGTH_SHORT).show();
            } else {
                // Set the torch flash mode
                Camera.Parameters param = camera.getParameters();
                param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                try {
                    camera.setParameters(param);
                    camera.startPreview();
                    isLightOn = true;
                } catch (Exception e) {
                    Toast.makeText(context, "FLASH NO DETECTADO", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
