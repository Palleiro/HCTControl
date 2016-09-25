package com.hctrom.romcontrol.logcat.helper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.logcat.RecordingWidgetProvider;

/**
 * Created by Palleiro on 18/09/2016.
 */
public class WidgetBatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final int BATTERY_STEPS = 10;
        RemoteViews widgetViews = new RemoteViews(context.getPackageName(), R.layout.widget_catlog_hctcontrol);
        intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        Intent receiver = new Intent(context, WidgetBatteryReceiver.class);
        receiver.setAction("COM_BATTERY");
        Toast.makeText(context, "¡¡ Actualizado estado Batería Widget !!", Toast.LENGTH_SHORT).show();

        float  temp   = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0)) / 10;
        String st = String.valueOf(temp);
        widgetViews.setTextViewText(R.id.text_temp, st + "°C");

        boolean charger = isPlugged(context, intent);
        widgetViews.setImageViewResource(R.id.charger, R.drawable.charger);
        if (charger){
            widgetViews.setViewVisibility(R.id.charger, View.VISIBLE);
        }else{
            widgetViews.setViewVisibility(R.id.charger, View.GONE);
        }

        int level = intent.getIntExtra("level", 0);
        widgetViews.setTextViewText(R.id.text_level, level + "%");
        int currLevel = level;
        if(currLevel >= 10) {
            widgetViews.setImageViewResource(R.id.bar1, R.drawable.battery_level_shape_low);
            widgetViews.setViewVisibility(R.id.bar1, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar1, View.INVISIBLE);
        if(currLevel >=20 ) {
            widgetViews.setImageViewResource(R.id.bar2, R.drawable.battery_level_shape_low);
            widgetViews.setViewVisibility(R.id.bar2, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar2, View.INVISIBLE);
        if(currLevel >= 30)
        {
            widgetViews.setImageViewResource(R.id.bar3, R.drawable.battery_level_shape_low);
            widgetViews.setViewVisibility(R.id.bar3, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar3, View.INVISIBLE);
        if(currLevel>=40)
        {
            widgetViews.setImageViewResource(R.id.bar4, R.drawable.battery_level_shape_mid);
            widgetViews.setViewVisibility(R.id.bar4, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar4, View.INVISIBLE);
        if(currLevel>=50)
        {
            widgetViews.setImageViewResource(R.id.bar5, R.drawable.battery_level_shape_mid);
            widgetViews.setViewVisibility(R.id.bar5, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar5, View.INVISIBLE);
        if(currLevel>=60)
        {
            widgetViews.setImageViewResource(R.id.bar6, R.drawable.battery_level_shape_mid);
            widgetViews.setViewVisibility(R.id.bar6, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar6, View.INVISIBLE);
        if(currLevel>=70)
        {
            widgetViews.setImageViewResource(R.id.bar7, R.drawable.battery_level_shape_high);
            widgetViews.setViewVisibility(R.id.bar7, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar7, View.INVISIBLE);
        if(currLevel>=80)
        {
            widgetViews.setImageViewResource(R.id.bar8, R.drawable.battery_level_shape_high);
            widgetViews.setViewVisibility(R.id.bar8, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar8, View.INVISIBLE);
        if(currLevel>=90)
        {
            widgetViews.setImageViewResource(R.id.bar9, R.drawable.battery_level_shape_high);
            widgetViews.setViewVisibility(R.id.bar9, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar9, View.INVISIBLE);
        if(currLevel>=100)
        {
            widgetViews.setImageViewResource(R.id.bar10, R.drawable.battery_level_shape_high);
            widgetViews.setViewVisibility(R.id.bar10, View.VISIBLE);
        }
        else
            widgetViews.setViewVisibility(R.id.bar10, View.INVISIBLE);
	    	/*
			 * We also want to show a partially visible level to represent the
			 * diagnostic_battery level more accurately.
			 * E.G. for 34% there should be three fully visible bars and one partially visible
			 * We need to work out which level should be set partially visible
			 */
        //deal with the remainder
        int partLevel = currLevel%BATTERY_STEPS;
        //find out how many full levels we have
        int fullSteps = currLevel-partLevel;
        //find out which is the next level up and call helper method to set partially visible
        int partStep = (fullSteps/BATTERY_STEPS)+1;
        if(partLevel>0) {

            if (partStep > BATTERY_STEPS) return;
            else {
                //the process should only be setting one level
                //I.E. for 45% the fifth bar up from the bottom should be set partially visible
                //- find out which level and set the image drawable, then visibility
                switch (partStep) {
                    case 1:
                        widgetViews.setImageViewResource(R.id.bar1, R.drawable.battery_level_shape_low_alpha);
                        widgetViews.setViewVisibility(R.id.bar1, View.VISIBLE);
                        break;
                    case 2:
                        widgetViews.setImageViewResource(R.id.bar2, R.drawable.battery_level_shape_low_alpha);
                        widgetViews.setViewVisibility(R.id.bar2, View.VISIBLE);
                        break;
                    case 3:
                        widgetViews.setImageViewResource(R.id.bar3, R.drawable.battery_level_shape_low_alpha);
                        widgetViews.setViewVisibility(R.id.bar3, View.VISIBLE);
                        break;
                    case 4:
                        widgetViews.setImageViewResource(R.id.bar4, R.drawable.battery_level_shape_mid_alpha);
                        widgetViews.setViewVisibility(R.id.bar4, View.VISIBLE);
                        break;
                    case 5:
                        widgetViews.setImageViewResource(R.id.bar5, R.drawable.battery_level_shape_mid_alpha);
                        widgetViews.setViewVisibility(R.id.bar5, View.VISIBLE);
                        break;
                    case 6:
                        widgetViews.setImageViewResource(R.id.bar6, R.drawable.battery_level_shape_mid_alpha);
                        widgetViews.setViewVisibility(R.id.bar6, View.VISIBLE);
                        break;
                    case 7:
                        widgetViews.setImageViewResource(R.id.bar7, R.drawable.battery_level_shape_high_alpha);
                        widgetViews.setViewVisibility(R.id.bar7, View.VISIBLE);
                        break;
                    case 8:
                        widgetViews.setImageViewResource(R.id.bar8, R.drawable.battery_level_shape_high_alpha);
                        widgetViews.setViewVisibility(R.id.bar8, View.VISIBLE);
                        break;
                    case 9:
                        widgetViews.setImageViewResource(R.id.bar9, R.drawable.battery_level_shape_high_alpha);
                        widgetViews.setViewVisibility(R.id.bar9, View.VISIBLE);
                        break;
                    case 10:
                        widgetViews.setImageViewResource(R.id.bar10, R.drawable.battery_level_shape_high_alpha);
                        widgetViews.setViewVisibility(R.id.bar10, View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, receiver, 0);
        widgetViews.setOnClickPendingIntent(R.id.battery, pendingIntent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, RecordingWidgetProvider.class), widgetViews);
    }

    public static boolean isPlugged(Context context, Intent intent) {
        boolean isPlugged= false;
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        isPlugged = plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
        isPlugged = isPlugged || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS;
        return isPlugged;
    }
}
