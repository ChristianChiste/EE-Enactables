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
public abstract class EnactableAtomicDecorator extends EnactableAtomic {

	protected final EnactableAtomic enactableAtomic;

	protected EnactableAtomicDecorator(Set<EnactableStateListener> stateListeners, Set<String> inputKeys,
			Task functionNode, EnactableAtomic enactableAtomic) {
		super(stateListeners, inputKeys, functionNode);
		this.enactableAtomic = enactableAtomic;
	}

	/*
	 * 
	 * If we create an extra class for the decoration, it would make sense that the
	 * abstract class captures all things concerning the interaction with the
	 * decorated object, while the concrete implementations only specify the
	 * specific change in behavior.
	 * 
	 * 
	 */

	@Override
	protected void atomicPlay() throws StopException {
		prePlayDecoration();
		enactableAtomic.atomicPlay();
		postPlayDecoration();
	}
	
	@Override
	protected void myPause() {
		// Does nothing
	}

	/**
	 * Method where concrete instances describe what is to be done before the play
	 * of the decorated object.
	 */
	protected abstract void prePlayDecoration() throws StopException ;
	
	/**
	 * Method where concrete instances describe what is to be done before the play
	 * of the decorated object.
	 */
	protected abstract void postPlayDecoration() throws StopException ;

}