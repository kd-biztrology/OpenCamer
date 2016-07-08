package com.nuannuan.camer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.nuannuan.camer.log.Logger;
import com.nuannuan.camer.view.MainActivity;

/**
 * Entry Activity for the "take photo" widget (see MyWidgetProviderTakePhoto).
 * This redirects to MainActivity, but uses an intent extra/bundle to pass the
 * "take photo" request.
 * @author Mark Harman 18 June 2016
 * @author kevin
 */
public class TakePhoto extends Activity {
  private static final String TAG = "TakePhoto";
  public static final String TAKE_PHOTO = "com.nuannuan.camer.TAKE_PHOTO";

  @Override protected void onCreate(Bundle savedInstanceState) {

    Logger.d(TAG,"onCreate");
    super.onCreate(savedInstanceState);

    Intent intent = new Intent(this,MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtra(TAKE_PHOTO,true);
    this.startActivity(intent);

    Logger.d(TAG,"finish");
    this.finish();
  }

  protected void onResume() {

    Logger.d(TAG,"onResume");

    super.onResume();
  }
}
