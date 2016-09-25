package com.hctrom.romcontrol.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import com.hctrom.romcontrol.MainViewActivity;
import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.logcat.LogcatRecordingService;
import com.hctrom.romcontrol.logcat.RecordingWidgetProvider;
import com.hctrom.romcontrol.logcat.helper.PreferenceHelper;
import com.hctrom.romcontrol.logcat.helper.ServiceHelper;
import com.hctrom.romcontrol.logcat.util.UtilLogger;

public class WidgetHelper extends AppWidgetProvider {

	private static UtilLogger log = new UtilLogger(WidgetHelper.class);

	public static void updateWidgets(Context context) {

		int[] appWidgetIds = findAppWidgetIds(context);

		updateWidgets(context, appWidgetIds);

	}

	/**
	 * manually tell us if the service is running or not
	 * @param context
	 * @param serviceRunning
	 */
	public static void updateWidgets(Context context, boolean serviceRunning) {

		int[] appWidgetIds = findAppWidgetIds(context);

		updateWidgets(context, appWidgetIds, serviceRunning);

	}

	public static void updateWidgets(Context context, int[] appWidgetIds) {

		boolean serviceRunning = ServiceHelper.checkIfServiceIsRunning(context, LogcatRecordingService.class);

		updateWidgets(context, appWidgetIds, serviceRunning);

	}


	public static void updateWidgets(Context context, int[] appWidgetIds, boolean serviceRunning) {

		AppWidgetManager manager = AppWidgetManager.getInstance(context);

		for (int appWidgetId : appWidgetIds) {

			if (!PreferenceHelper.getWidgetExistsPreference(context, appWidgetId)) {
				// android has a bug that sometimes keeps stale app widget ids around
				log.d("Found stale app widget id %d; skipping...", appWidgetId);
				continue;
			}

			updateWidget(context, manager, appWidgetId, serviceRunning);

		}

	}

	private static void updateWidget(Context context, AppWidgetManager manager, int appWidgetId, boolean serviceRunning) {
		RemoteViews widgetViews = new RemoteViews(context.getPackageName(), R.layout.widget_catlog_hctcontrol);
		Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		Intent receiver = new Intent(context, WidgetFlashlightReceiver.class);
		receiver.setAction("COM_FLASHLIGHT");

		Intent receiver2 = new Intent(context, WidgetBatteryReceiver.class);
		receiver.setAction("COM_BATTERY");

		// change the subtext depending on whether the service is running or not
		CharSequence subtext = context.getText(
				serviceRunning ? R.string.widget_recording_in_progress : R.string.widget_start_recording);
		widgetViews.setTextViewText(R.id.widget_subtext, subtext);

		// if service not running, don't show the "recording" icon
		widgetViews.setViewVisibility(R.id.record_badge_image_view, serviceRunning ? View.VISIBLE : View.INVISIBLE);

		PendingIntent pendingIntent = getPendingIntent(context, appWidgetId);
		PendingIntent pendingIntent1 = getHCTControl(context, appWidgetId);
		PendingIntent pendingIntent2 = getPower(context, appWidgetId);
		PendingIntent pendingIntent3 = PendingIntent.getBroadcast(context, 0, receiver, 0);
		PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, 0, receiver2, 0);

		widgetViews.setOnClickPendingIntent(R.id.catlog_icon_widget, pendingIntent);
		widgetViews.setOnClickPendingIntent(R.id.catlog_icon_hctcontrol, pendingIntent1);
		widgetViews.setOnClickPendingIntent(R.id.widget_power, pendingIntent2);
		widgetViews.setOnClickPendingIntent(R.id.buttonTorch, pendingIntent3);
		widgetViews.setOnClickPendingIntent(R.id.battery, pendingIntent4);

		manager.updateAppWidget(new ComponentName(context, RecordingWidgetProvider.class), widgetViews);

	}

	private static PendingIntent getPower(Context context, int appWidgetId) {

		Intent intent = new Intent(context, WidgetPower.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(context,
				0 /* no requestCode */, intent, 0);

		return pendingIntent;
	}

	private static PendingIntent getHCTControl(Context context, int appWidgetId) {

		Intent intent = new Intent(context, MainViewActivity.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(context,
				0 /* no requestCode */, intent, 0);

		return pendingIntent;
	}

	private static PendingIntent getPendingIntent(Context context, int appWidgetId) {

		Intent intent = new Intent();
		intent.setAction(RecordingWidgetProvider.ACTION_RECORD_OR_STOP);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		// gotta make this unique for this appwidgetid - otherwise, the PendingIntents conflict
		// it seems to be a quasi-bug in Android
		Uri data = Uri.withAppendedPath(Uri.parse(RecordingWidgetProvider.URI_SCHEME + "://widget/id/#"), String.valueOf(appWidgetId));
        intent.setData(data);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0 /* no requestCode */, intent, PendingIntent.FLAG_ONE_SHOT);

		return pendingIntent;
	}

	private static int[] findAppWidgetIds(Context context) {
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName widget = new ComponentName(context, RecordingWidgetProvider.class);
		int[] appWidgetIds = manager.getAppWidgetIds(widget);
		return appWidgetIds;

	}

}
