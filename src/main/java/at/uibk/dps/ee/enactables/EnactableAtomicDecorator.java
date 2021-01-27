package at.uibk.dps.ee.enactables;

import java.util.Set;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.exception.StopException;
import net.sf.opendse.model.Task;


/**
 * Decorator for an EnactableAtomic.
 * 
 * @author Christian Chisté
 *
 */
public abstract class EnactableAtomicDecorator extends EnactableAtomic{
	
	protected EnactableAtomic enactableAtomic;

	protected EnactableAtomicDecorator(Set<EnactableStateListener> stateListeners, Set<String> inputKeys,
			Task functionNode, EnactableAtomic enactableAtomic) {
		super(stateListeners, inputKeys, functionNode);
		this.enactableAtomic = enactableAtomic;
	}
	
}