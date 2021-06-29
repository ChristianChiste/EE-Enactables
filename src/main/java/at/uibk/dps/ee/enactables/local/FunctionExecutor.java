package at.uibk.dps.ee.enactables.local;

import java.util.concurrent.Callable;

import com.google.gson.JsonObject;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;

/**
 * Thread for executing a function.
 * 
 * @author Christian Chisté
 *
 */
public class FunctionExecutor implements Callable<FunctionResult>{

  private final EnactmentFunction function;
  private final JsonObject input;

  public FunctionExecutor(EnactmentFunction function, JsonObject input) {
    this.function = function;
    this.input = input;
  }

  @Override
  public FunctionResult call() throws Exception {
    JsonObject output = function.processInput(input);
    FunctionResult result = new FunctionResult(function, output);
    return result;
  }
}
