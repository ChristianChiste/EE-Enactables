package at.uibk.dps.ee.enactables.local.calculation;

import at.uibk.dps.ee.enactables.local.LocalFunctionAbstract;
import at.uibk.dps.ee.enactables.local.ConstantsLocal.LocalCalculations;

public interface FunctionFactoryLocalInterface {
  
  /**
   * Returns the local function for the given enum.
   * 
   * @param localFunction the local function enum
   * @return the local function for the enum
   */
  LocalFunctionAbstract getLocalFunction(final LocalCalculations localFunction);
}
