package com.nuannuan.camer.preview;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.camera2.DngCreator;
import android.location.Location;
import android.media.CamcorderProfile;
import android.media.Image;
import android.net.Uri;
import android.util.Pair;
import android.view.MotionEvent;

import java.io.File;
import java.io.IOException;

/**
 * Provides communication between the Preview and the rest of the application
 * - so in theory one can drop the Preview/ (and CameraController/) classes
 * into a new application, by providing an appropriate implementation of this
 * ApplicationInterface.
 *
 * @author Mark Harman 18 June 2016
 * @author kevin
 */
public interface ApplicationInterface {

  // video will be saved to a file
  int VIDEOMETHOD_FILE = 0;

  // video will be saved using Android 5's Storage Access Framework
  int VIDEOMETHOD_SAF = 1;

  // video will be written to the supplied Uri
  int VIDEOMETHOD_URI = 2;

  // get the application context

  // methods that request information
  Context getContext();

  // should Android 5's Camera 2 API be used?
  boolean useCamera2();

  // get current location - null if not available (or you don't care about geotagging)
  Location getLocation();

  // return a VIDEOMETHOD_* value to specify how to create a video file
  int createOutputVideoMethod();

  // will be called if createOutputVideoUsingSAF() returns VIDEOMETHOD_FILE
  File createOutputVideoFile() throws IOException;

  // will be called if createOutputVideoUsingSAF() returns VIDEOMETHOD_SAF
  Uri createOutputVideoSAF() throws IOException;

  // will be called if createOutputVideoUsingSAF() returns VIDEOMETHOD_URI
  Uri createOutputVideoUri() throws IOException;

  /**
   * for all of the get*Pref() methods, you can use Preview methods to get the supported values
   * (e.g., getSupportedSceneModes())
   * if you just want a default or don't really care, see the comments for each method for a
   * default
   * or possible options
   * if Preview doesn't support the requested setting, it will check this, and choose its own
   */

  // camera to use, from 0 to getCameraControllerManager().getNumberOfCameras()
  int getCameraIdPref();

  // flash_off, flash_auto, flash_on, flash_torch, flash_red_eye
  String getFlashPref();

  // focus_mode_auto, focus_mode_infinity, focus_mode_macro, focus_mode_locked, focus_mode_fixed, focus_mode_manual2, focus_mode_edof, focus_mode_continuous_video
  String getFocusPref(boolean is_video);

  // start up in video mode?
  boolean isVideoPref();

  // "auto" for default (strings correspond to Android's scene mode constants in android.hardware.Camera.Parameters)
  String getSceneModePref();

  // "node" for default (strings correspond to Android's color effect constants in android.hardware.Camera.Parameters)
  String getColorEffectPref();

  // "auto" for default (strings correspond to Android's white balance constants in android.hardware.Camera.Parameters)
  String getWhiteBalancePref();

  // "default" for default
  String getISOPref();

  // 0 for default
  int getExposureCompensationPref();

  // return null to let Preview choose size
  Pair<Integer, Integer> getCameraResolutionPref();

  // jpeg quality for taking photos; "90" is a recommended default
  int getImageQualityPref();

  // whether to use face detection mode
  boolean getFaceDetectionPref();

  // should be one of Preview.getSupportedVideoQuality() (use Preview.getCamcorderProfile() or
  // Preview.getCamcorderProfileDescription() for details); or return "" to let Preview choose quality
  String getVideoQualityPref();

  // whether to use video stabilization for video
  boolean getVideoStabilizationPref();

  // whether to force 4K mode - experimental,
  // only really available for some devices that allow 4K recording
  // but don't return it as an available resolution - not recommended for most uses
  boolean getForce4KPref();

  // return "default" to let Preview choose
  String getVideoBitratePref();

  // return "default" to let Preview choose
  String getVideoFPSPref();

  // time in ms after which to automatically stop video recording (return 0 for off)
  long getVideoMaxDurationPref();

  // number of times to restart video recording after hitting max duration (return 0 for never auto-restarting)
  int getVideoRestartTimesPref();

  // maximum file size in bytes for video (return 0 for device default)
  long getVideoMaxFileSizePref();

  boolean getVideoRestartMaxFileSizePref();
  // whether to restart on hitting max file size

  // option to switch flash on/off while recording video (should be false in most cases!)
  boolean getVideoFlashPref();

  // "preference_preview_size_wysiwyg" is recommended (preview matches aspect ratio
  // of photo resolution as close as possible), but can also be "preference_preview_size_display" to maximise the preview size
  String getPreviewSizePref();

  // return "0" for default; use "180" to rotate the preview 180 degrees
  String getPreviewRotationPref();

  // return "none" for default; use "portrait" or "landscape" to lock photos/videos to that orientation
  String getLockOrientationPref();

  // whether to enable touch to capture
  boolean getTouchCapturePref();

  // whether to enable double-tap to capture
  boolean getDoubleTapCapturePref();

  // whether to pause the preview after taking a photo
  boolean getPausePreviewPref();

  boolean getShowToastsPref();

  // whether to play sound when taking photo
  boolean getShutterSoundPref();

  // whether to do autofocus on startup
  boolean getStartupFocusPref();

  // time in ms for timer (so 0 for off)
  long getTimerPref();

  // return number of times to repeat photo in a row (as a string), so "1" for default; return "unlimited" for unlimited
  String getRepeatPref();

  // time in ms between repeat
  long getRepeatIntervalPref();

  // whether to geotag photos
  boolean getGeotaggingPref();

  // if getGeotaggingPref() returns true, and this method returns true, then phot/video will only be taken if location data is available
  boolean getRequireLocationPref();

  // whether to record audio when recording video
  boolean getRecordAudioPref();

  // either "audio_default", "audio_mono" or "audio_stereo"
  String getRecordAudioChannelsPref();

  // "audio_src_camcorder" is recommended, but other options
  // are: "audio_src_mic", "audio_src_default", "audio_src_voice_communication";
  // see corresponding values in android.media.MediaRecorder.AudioSource
  String getRecordAudioSourcePref();

  // index into Preview.getSupportedZoomRatios() array (each entry is the zoom factor, scaled by 100; array is sorted from min to max zoom)
  int getZoomPref();

  // Camera2 only modes:

  // only called if getISOPref() is not "default"
  long getExposureTimePref();

  float getFocusDistancePref();

  // whether to enable RAW photos
  boolean isRawPref();

  // for testing purposes:
  // if true, pretend autofocus always successful
  boolean isTestAlwaysFocus();

  // methods that transmit information/events (up to the Application whether to do anything or not)

  // called when the camera is (re-)set up - should update UI elements/parameters that depend on camera settings
  void cameraSetup();

  void touchEvent(MotionEvent event);

  // called just before video recording starts
  void startingVideo();

  // called just before video recording stops
  void stoppingVideo();

  // called after video recording stopped (uri/filename will be null if video is corrupt or not created)
  void stoppedVideo(final int video_method, final Uri uri, final String filename);

  // called if failed to start camera preview
  void onFailedStartPreview();

  // callback for failing to take a photo
  void onPhotoError();

  // callback for info when recording video (see MediaRecorder.OnInfoListener)
  void onVideoInfo(int what, int extra);

  // callback for errors when recording video (see MediaRecorder.OnErrorListener)
  void onVideoError(int what, int extra);

  // callback for video recording failing to start
  void onVideoRecordStartError(CamcorderProfile profile);

  // callback for video recording being corrupted
  void onVideoRecordStopError(CamcorderProfile profile);

  // failed to reconnect camera after stopping video recording
  void onFailedReconnectError();

  // callback if unable to create file for recording video
  void onFailedCreateVideoFileError();

  // called when the preview is paused or unpaused (due to getPausePreviewPref())
  void hasPausedPreview(boolean paused);

  // called when the camera starts/stops being operation (taking photos or recording video),
  // use to disable GUI elements during camera operation
  void cameraInOperation(boolean in_operation);

  void cameraClosed();

  // n.b., called once per second on timer countdown - so application can beep, or do whatever it likes
  void timerBeep(long remaining_time);

  // methods that request actions

  // application should layout UI that's on top of the preview
  void layoutUI();

  // zoom has changed due to multitouch gesture on preview
  void multitouchZoom(int new_zoom);

  /**
   * the set/clear*Pref() methods are called if Preview decides to override the requested pref
   * (because Camera device doesn't support requested pref) (clear*Pref() is called if the feature
   * isn't supported at all)
   * the application can use this information to update its preferences
   */
  void setCameraIdPref(int cameraId);

  void setFlashPref(String flash_value);

  void setFocusPref(String focus_value, boolean is_video);

  void setVideoPref(boolean is_video);

  void setSceneModePref(String scene_mode);

  void clearSceneModePref();

  void setColorEffectPref(String color_effect);

  void clearColorEffectPref();

  void setWhiteBalancePref(String white_balance);

  void clearWhiteBalancePref();

  void setISOPref(String iso);

  void clearISOPref();

  void setExposureCompensationPref(int exposure);

  void clearExposureCompensationPref();

  void setCameraResolutionPref(int width, int height);

  void setVideoQualityPref(String video_quality);

  void setZoomPref(int zoom);

  // Camera2 only modes:
  void setExposureTimePref(long exposure_time);

  void clearExposureTimePref();

  void setFocusDistancePref(float focus_distance);

  // callbacks
  void onDrawPreview(Canvas canvas);

  boolean onPictureTaken(byte[] data);

  boolean onRawPictureTaken(DngCreator dngCreator, Image image);

  // called when focusing starts/stop in continuous picture mode (in photo mode only)
  void onContinuousFocusMove(boolean start);
}
