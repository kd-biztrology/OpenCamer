package com.nuannuan.camer.cameracontroller;

/**
 * Provides additional support related to the Android camera APIs. This is to
 * support functionality that doesn't require a camera to have been opened.
 *
 * @author Mark Harman 18 June 2016
 * @author kevin
 */
public abstract class CameraControllerManager {
  public abstract int getNumberOfCameras();

  public abstract boolean isFrontFacing(int cameraId);
}
