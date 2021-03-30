package at.uibk.dps.ee.enactables.serverless;

import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.exception.StopException;
import net.sf.opendse.model.Resource;

/**
 * The {@link ServerlessFunction} models the enactment of an atomic serverless
 * function.
 * 
 * @author Christian Chisté
 */
public class ServerlessFunctionWrapper extends ServerlessFunction {
  
  private double failRate;

  public ServerlessFunctionWrapper(Resource serverlessFunction, double failRate) {
    super(serverlessFunction);
    this.failRate = failRate;
  }

  @Override
  public JsonObject processInput(final JsonObject input) throws StopException {
    JsonObject result = enactServerlessFunction(url, input);
    if(faultDecision(failRate))
      throw new StopException("Fail");
    return result;
  }

  /**
   * Decides if we let the function fail.
   * 
   * @param faultRate probability of failing
   * @return boolean which indicates decision to fail
   */
  private boolean faultDecision(double failRate){
    return Math.random() <= failRate;
  }

  public double getFailRate() {
    return failRate;
  }
}
