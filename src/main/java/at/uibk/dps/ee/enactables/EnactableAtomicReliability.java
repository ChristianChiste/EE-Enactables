package at.uibk.dps.ee.enactables;

import java.util.Set;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.exception.StopException;
import at.uibk.dps.ee.enactables.local.Composite;
import net.sf.opendse.model.Task;

/**
 * EnactableAtomic which fails with a given probability.
 * 
 * @author Christian Chisté
 *
 */
public class EnactableAtomicReliability extends EnactableAtomicDecorator{

  protected final double failRate;

  public EnactableAtomicReliability(Set<EnactableStateListener> stateListeners, Task functionNode, 
      EnactableAtomic enactableAtomic, double failRate) {
    super(stateListeners, functionNode, enactableAtomic);
    this.failRate = failRate;
  }

  /**
   * Decides if we let the EnactableAtomic fail.
   * 
   * @param faultRate probability of fault
   * @return boolean which indicates decision to fail
   */
  private boolean faultDecision(double failRate){
    return Math.random() <= failRate;
  }

  @Override
  protected void prePlayDecoration() {
    // Nothing here
  }

  @Override
  protected void postPlayDecoration() throws StopException  {
    int numberComponents = 1;
    if (enactmentFunction instanceof Composite) {
      numberComponents = ((Composite)enactmentFunction).getFunctions().size();
    }
    if (faultDecision(Math.pow(failRate, numberComponents))) {
      throw new StopException("Fault occurred");
    }
    else {
      super.postPlayDecoration();
    }
  }
}
