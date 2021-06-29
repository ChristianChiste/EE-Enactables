package at.uibk.dps.ee.enactables.local;

import com.google.gson.JsonObject;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;

public class FunctionResult {
  
  private final EnactmentFunction function;
  private final JsonObject output;
  
  public FunctionResult(EnactmentFunction function, JsonObject output) {
    this.function = function;
    this.output = output;
  }

  public EnactmentFunction getFunction() {
    return function;
  }

  public JsonObject getOutput() {
    return output;
  }
}
