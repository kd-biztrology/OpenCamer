package com.nuannuan.camer.log;

import com.nuannuan.camer.BuildConfig;

/**
 * logger utils
 * @author kevin.
 */
public class Logger {
  static {
    com.orhanobut.logger.Logger.init();
  }

  /**
   * i  method.
   */
  public static void i(String tag,String msg) {
    if (BuildConfig.DEBUG) {
      com.orhanobut.logger.Logger.t(tag).i(msg);
    }
  }

  /**
   * d  method.
   */
  public static void d(String tag,String msg) {
    if (BuildConfig.DEBUG) {
      com.orhanobut.logger.Logger.t(tag).d(msg);
    }
  }

  /**
   * e  method,get throwable.
   */
  public static void e(String tag,String msg,Throwable tr) {
    if (BuildConfig.DEBUG) {
      com.orhanobut.logger.Logger.t(tag).e(tr,msg);
    }
  }

  /**
   * w  method.
   */
  public static void w(String tag,String msg) {
    if (BuildConfig.DEBUG) {
      com.orhanobut.logger.Logger.t(tag).w(msg);
    }
  }

  /**
   * e  method.
   */
  public static void e(String tag,String msg) {
    if (BuildConfig.DEBUG) {
      com.orhanobut.logger.Logger.t(tag).e(msg);
    }
  }

  /**
   * json  method.
   */
  public static void json(String tag,String msg) {
    com.orhanobut.logger.Logger.t(tag).json(msg);
  }

  /**
   * xml  method.
   */
  public static void xml(String tag,String msg) {
    com.orhanobut.logger.Logger.t(tag).xml(msg);
  }
}
