package at.uibk.dps.ee.enactables.local;

import com.google.gson.JsonObject;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import at.uibk.dps.ee.core.exception.StopException;

public class LocalFunctionWrapper extends LocalFunctionAbstract {

  private EnactmentFunction function;
  private double failRate;

  public LocalFunctionWrapper(EnactmentFunction function, double failRate) {
    this.function = function;
    this.failRate = failRate;
  }

  @Override
  public JsonObject processInput(final JsonObject input) throws StopException {
    final JsonObject result = function.processInput(input);
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
}
