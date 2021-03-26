package at.uibk.dps.ee.enactables.serverless;

import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import net.sf.opendse.model.Resource;

public class FunctionFactoryServerlessWrapper implements FunctionFactoryServerlessInterface{

  protected final double failRate;

  @Inject
  public FunctionFactoryServerlessWrapper(@Constant(namespace = FunctionFactoryServerlessWrapper.class, value = "failRate") double failRate) {
    this.failRate = failRate;
  }
  
  public EnactmentFunction createServerlessFunction(final Resource resource) {
    return new ServerlessFunctionWrapper(resource, failRate);
  }

}
