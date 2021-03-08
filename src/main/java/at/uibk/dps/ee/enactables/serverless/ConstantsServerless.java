package at.uibk.dps.ee.enactables.serverless;

import okhttp3.MediaType;

/**
 * Static container containing the constants used during the enactment of
 * serverless functions.
 * 
 * @author Fedor Smirnov
 */
public final class ConstantsServerless {

  public static final int readWriteTimeoutSeconds = 50;
  public static final MediaType MediaTypeJson = MediaType.get("application/json; charset=utf-8");
  

  /**
   * No constructor.
   */
  private ConstantsServerless() {}
}
