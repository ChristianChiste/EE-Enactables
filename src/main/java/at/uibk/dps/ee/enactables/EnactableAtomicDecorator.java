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

	protected EnactableAtomicDecorator(Set<EnactableStateListener> stateListeners,
			Task functionNode, EnactableAtomic enactableAtomic) {
		super(stateListeners, functionNode);
		this.enactableAtomic = enactableAtomic;
	}

	public void play() throws StopException {
		prePlayDecoration();
		enactableAtomic.play();
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
	protected abstract void prePlayDecoration() throws StopException;
	
	/**
	 * Method where concrete instances describe what is to be done before the play
	 * of the decorated object.
	 */
	protected abstract void postPlayDecoration() throws StopException;

}