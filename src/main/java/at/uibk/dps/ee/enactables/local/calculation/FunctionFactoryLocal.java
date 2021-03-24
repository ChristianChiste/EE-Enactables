package at.uibk.dps.ee.enactables.local.calculation;

import at.uibk.dps.ee.enactables.local.LocalFunctionAbstract;
import at.uibk.dps.ee.enactables.local.ConstantsLocal.LocalCalculations;

/**
 * The {@link FunctionFactoryLocal} provides the enactment functions modeling
 * local operations.
 * 
 * @author Fedor Smirnov
 *
 */
public class FunctionFactoryLocal implements FunctionFactoryLocalInterface {

  public LocalFunctionAbstract getLocalFunction(final LocalCalculations localFunction) {
    switch (localFunction) {
      case Addition:
        return new Addition();
      case Subtraction:
        return new Subtraction();
      case SumCollection:
        return new SumCollection();
      default:
        throw new IllegalArgumentException("Unknown local function " + localFunction.name());
    }
  }
}
