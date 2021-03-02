package at.uibk.dps.ee.enactables;

import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.enactable.Enactable.State;
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
	 * Method describes what is to be done before the play
	 * of the decorated object.
	 */
	protected void prePlayDecoration() throws StopException {
		for(Entry<String, JsonElement> entry:this.getInput().entrySet())
			enactableAtomic.setInputValue(entry.getKey(), entry.getValue());
		enactableAtomic.setState(State.SCHEDULABLE);
		enactableAtomic.schedule(this.enactmentFunction);
	}

	/**
	 * Method describes what is to be done before the play
	 * of the decorated object.
	 */
	protected void postPlayDecoration() throws StopException {
		try {
			this.jsonResult=enactableAtomic.getResult();
			enactableAtomic.setState(State.FINISHED);
			this.setState(State.FINISHED);
		}
		catch(IllegalStateException e) {
			enactableAtomic.setState(State.WAITING);
			this.setState(State.SCHEDULABLE);

		}
	}

}