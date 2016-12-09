package com.nuannuan.camer;

import com.nuannuan.camer.log.Logger;
import com.nuannuan.camer.view.MainActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Handles the Open Camera lock screen widget. Lock screen widgets are no
 * longer supported in Android 5 onwards (instead Open Camera can be launched
 * from the lock screen using the standard camera icon), but this is kept here
 * for older Android versions.
 *
 * @author Mark Harman 18 June 2016
 * @author kevin
 */
public class MyWidgetProvider extends AppWidgetProvider {
  private static final String TAG = MyWidgetProvider.class.getSimpleName();


  // from http://developer.android.com/guide/topics/appwidgets/index.html
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    Logger.d(TAG, "onUpdate");

    final int N = appWidgetIds.length;

    Logger.d(TAG, "N = " + N);

    // Perform this loop procedure for each App Widget that belongs to this provider
    for (int i = 0; i < N; i++) {
      int appWidgetId = appWidgetIds[i];

      Logger.d(TAG, "appWidgetId: " + appWidgetId);

      PendingIntent pendingIntent;
      {

        Intent intent = new Intent(context, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
      }

      // Get the layout for the App Widget and attach an on-click listener
      // to the button
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
      views.setOnClickPendingIntent(R.id.widget_launch_open_camera, pendingIntent);

      appWidgetManager.updateAppWidget(appWidgetId, views);
    }
  }
}
