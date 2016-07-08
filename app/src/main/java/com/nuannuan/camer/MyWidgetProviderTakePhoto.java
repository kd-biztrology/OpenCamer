package com.nuannuan.camer;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.nuannuan.camer.log.Logger;

/**
 * Handles the Open Camera "take photo" widget. This widget launches Open
 * Camera, and immediately takes a photo.
 */
public class MyWidgetProviderTakePhoto extends AppWidgetProvider {
  private static final String TAG = MyWidgetProviderTakePhoto.class.getSimpleName();

  // from http://developer.android.com/guide/topics/appwidgets/index.html
  public void onUpdate(Context context,AppWidgetManager appWidgetManager,int[] appWidgetIds) {

    Logger.d(TAG,"onUpdate");
    final int N = appWidgetIds.length;

    Logger.d(TAG,"N = " + N);

    // Perform this loop procedure for each App Widget that belongs to this provider
    for (int i = 0; i < N; i++) {
      int appWidgetId = appWidgetIds[i];

      Logger.d(TAG,"appWidgetId: " + appWidgetId);

      Intent intent = new Intent(context,TakePhoto.class);
      PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

      // Get the layout for the App Widget and attach an on-click listener
      // to the button
      RemoteViews views =
          new RemoteViews(context.getPackageName(),R.layout.widget_layout_take_photo);
      views.setOnClickPendingIntent(R.id.widget_take_photo,pendingIntent);

      // Tell the AppWidgetManager to perform an update on the current app widget
      appWidgetManager.updateAppWidget(appWidgetId,views);
    }
  }
}
