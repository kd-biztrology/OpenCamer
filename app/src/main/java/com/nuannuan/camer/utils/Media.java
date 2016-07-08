package com.nuannuan.camer.utils;

import android.net.Uri;

/**
 * @author kevin.
 */

public class Media {

  public long id;
  public boolean video;
  public Uri uri;
  public long date;
  public int orientation;

  public Media(long id,boolean video,Uri uri,long date,int orientation) {
    this.id = id;
    this.video = video;
    this.uri = uri;
    this.date = date;
    this.orientation = orientation;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public Uri getUri() {
    return uri;
  }

  public void setUri(Uri uri) {
    this.uri = uri;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public int getOrientation() {
    return orientation;
  }

  public void setOrientation(int orientation) {
    this.orientation = orientation;
  }
}
