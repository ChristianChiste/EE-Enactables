package at.uibk.dps.ee.enactables.local.calculation;

import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.start.Constant;

import at.uibk.dps.ee.enactables.serverless.FunctionFactoryServerless;
import at.uibk.dps.ee.enactables.serverless.FunctionFactoryServerlessInterface;
import at.uibk.dps.ee.enactables.serverless.FunctionFactoryServerlessWrapper;
import at.uibk.dps.ee.guice.modules.EeModule;

/**
 * The module configuring the Function Factory.
 * 
 * @author Christian Chisté
 *
 */
public class FunctionFactoryModule extends EeModule {

  @Order(1)
  @Info("If checked, the enactables fail with a certain probability")
  protected static boolean useReliabilityWrapper = false;
  
  @Order(2)
  @Info("The fail rate of the serverless functions")
  @Constant(namespace = FunctionFactoryServerlessWrapper.class, value = "failRate")
  protected static double failRateServerless = .5;

  @Order(3)
  @Info("The fail rate of the local functions")
  @Constant(namespace = FunctionFactoryLocalWrapper.class, value = "failRate")
  protected static double failRateLocal = 0;
  
  @Override
  protected void config() {
    if (useReliabilityWrapper) {
      bind(FunctionFactoryLocalInterface.class).to(FunctionFactoryLocalWrapper.class);
      bind(FunctionFactoryServerlessInterface.class).to(FunctionFactoryServerlessWrapper.class);
    } else {
      bind(FunctionFactoryLocalInterface.class).to(FunctionFactoryLocal.class);
      bind(FunctionFactoryServerlessInterface.class).to(FunctionFactoryServerless.class);
    }
  }

  public boolean isUseReliabilityWrapper() {
    return useReliabilityWrapper;
  }

  public void setUseReliabilityWrapper(boolean useReliabilityWrapper) {
    this.useReliabilityWrapper = useReliabilityWrapper;
  }

  public double getFailRateLocal() {
    return failRateLocal;
  }

  public void setFailRateLocal(double failRateLocal) {
    this.failRateLocal = failRateLocal;
  }
  
  public double getFailRateServerless() {
    return failRateServerless;
  }

  public void setFailRateServerless(double failRateServerless) {
    this.failRateServerless = failRateServerless;
  }
}
