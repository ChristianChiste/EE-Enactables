package at.uibk.dps.ee.enactables.local.calculation;

import at.uibk.dps.ee.enactables.local.LocalFunctionAbstract;
import at.uibk.dps.ee.enactables.local.LocalFunctionWrapper;

import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

import at.uibk.dps.ee.enactables.local.ConstantsLocal.LocalCalculations;

/**
 * The {@link FunctionFactoryLocalWrapper} provides the enactment functions modeling
 * local operations, while giving the opportunity to let the function fail.
 * 
 * @author Christian Chisté
 *
 */
public class FunctionFactoryLocalWrapper implements FunctionFactoryLocalInterface {

  protected final double failRate;

  @Inject
  public FunctionFactoryLocalWrapper(@Constant(namespace = FunctionFactoryLocalWrapper.class, value = "failRate") double failRate) {
    this.failRate = failRate;
  }
  
  public LocalFunctionAbstract getLocalFunction(final LocalCalculations localFunction) {
    switch (localFunction) {
      case Addition:
        return new LocalFunctionWrapper(new Addition(), failRate);
      case Subtraction:
        return new LocalFunctionWrapper(new Subtraction(), failRate);
      case SumCollection:
        return new LocalFunctionWrapper(new SumCollection(), failRate);
      default:
        throw new IllegalArgumentException("Unknown local function " + localFunction.name());
    }
  }
}
