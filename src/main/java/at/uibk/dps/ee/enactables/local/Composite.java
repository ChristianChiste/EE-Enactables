package at.uibk.dps.ee.enactables.local;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.JsonObject;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import at.uibk.dps.ee.core.exception.StopException;

/**
 * For executing a function on different resources 
 * and returning the first available result.
 * 
 * @author Christian Chisté
 *
 */
public class Composite implements EnactmentFunction{

  private List<EnactmentFunction> functions = new ArrayList<EnactmentFunction>();
  private EnactmentFunction succeededFunction;

  @Override
  public JsonObject processInput(final JsonObject input) throws StopException {
    ExecutorService executor = Executors.newFixedThreadPool(functions.size());
    List<FunctionExecutor> f = new ArrayList<FunctionExecutor>();
    for(EnactmentFunction function : functions)
      f.add(new FunctionExecutor(function, input));
    FunctionResult result = null;
    try {
      result = executor.invokeAny(f);
      succeededFunction = result.getFunction();
    } catch (Exception e) {
      throw new StopException(e.getMessage());
    }
    return result.getOutput();
  }

  public void addFunction(EnactmentFunction function) {
    functions.add(function);
  }

  public List<EnactmentFunction> getFunctions(){
    return functions;
  }

  public EnactmentFunction getSucceededFunction() {
    return succeededFunction;
  }
}
