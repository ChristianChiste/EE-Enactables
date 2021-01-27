package at.uibk.dps.ee.enactables;

import java.util.Set;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.exception.StopException;
import at.uibk.dps.ee.core.exception.StopException.StoppingReason;
import net.sf.opendse.model.Task;

/**
 * EnactableAtomic which fails with a given probability.
 * 
 * @author Christian Chisté
 *
 */
public class EnactableAtomicReliability extends EnactableAtomicDecorator{

	protected double faultRate;

	protected EnactableAtomicReliability(Set<EnactableStateListener> stateListeners, Set<String> inputKeys, Task functionNode, 
			EnactableAtomic enactableAtomic, double faultRate) {
		super(stateListeners, inputKeys, functionNode, enactableAtomic);
		this.faultRate=faultRate;
	}

	@Override
	protected void atomicPlay() throws StopException {
		if(faultDecision(faultRate))
				throw new StopException(StoppingReason.ERROR);
		enactableAtomic.atomicPlay();
	}

	/**
	 * Decides if we let the EnactableAtomic fail.
	 * 
	 * @param faultRate probability of fault
	 * @return boolean which indicates decision to fail
	 */
	private boolean faultDecision(double faultRate){
		return Math.random() <= faultRate;
	}
	

	@Override
	protected void myPause() {
		// Nothing here
	}

}
