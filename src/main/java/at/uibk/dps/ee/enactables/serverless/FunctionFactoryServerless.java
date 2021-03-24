package at.uibk.dps.ee.enactables.serverless;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import net.sf.opendse.model.Resource;

/**
 * The {@link FunctionFactoryServerless} creates the {@link EnactmentFunction}s
 * modeling the enactment of a serverless function.
 * 
 * @author Fedor Smirnov
 */
public class FunctionFactoryServerless implements FunctionFactoryServerlessInterface{

  public EnactmentFunction createServerlessFunction(final Resource resource) {
    return new ServerlessFunction(resource);
  }
}
