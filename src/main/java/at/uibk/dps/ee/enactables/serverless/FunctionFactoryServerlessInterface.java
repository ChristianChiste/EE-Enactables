package at.uibk.dps.ee.enactables.serverless;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import net.sf.opendse.model.Resource;

public interface FunctionFactoryServerlessInterface {

  /**
   * Creates the {@link ServerlessFunction} which is modeled by the provided
   * resource node.
   * 
   * @param resource the provided resource node
   * @return the {@link ServerlessFunction} which is modeled by the provided
   *         resource node
   */
  EnactmentFunction createServerlessFunction(final Resource resource);
}
