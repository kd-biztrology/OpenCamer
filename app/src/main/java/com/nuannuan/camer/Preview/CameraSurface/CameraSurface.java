package com.nuannuan.camer.preview.CameraSurface;

import android.graphics.Matrix;
import android.media.MediaRecorder;
import android.view.View;
import com.nuannuan.camer.cameracontroller.CameraController;

/**
 * Provides support for the surface used for the preview - this can either be
 * a SurfaceView or a TextureView.
 * @author Mark Harman 18 June 2016
 * @author kevin
 */
public interface CameraSurface {
  View getView();

  // n.b., uses double-dispatch similar to Visitor pattern - behaviour depends on type of CameraSurface and CameraController
  void setVideoRecorder(MediaRecorder video_recorder);

  void setPreviewDisplay(CameraController camera_controller);

  void setTransform(Matrix matrix);
}
