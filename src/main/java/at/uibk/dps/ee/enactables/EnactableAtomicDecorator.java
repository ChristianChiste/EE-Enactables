package at.uibk.dps.ee.enactables;

import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.enactable.EnactmentFunction;
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

	protected EnactableAtomicDecorator(Set<EnactableStateListener> stateListeners, Task functionNode,
			EnactableAtomic enactableAtomic) {
		super(stateListeners, functionNode);
		this.enactableAtomic = enactableAtomic;
	}

	public void play() throws StopException {
		prePlayDecoration();
		enactableAtomic.play();
		postPlayDecoration();
	}

	/**
	 * Method describes what is to be done before the play of the decorated object.
	 */
	protected abstract void prePlayDecoration() throws StopException;

	/**
	 * Method describes what is to be done before the play of the decorated object.
	 */
	protected void postPlayDecoration() throws StopException {
		this.jsonResult = enactableAtomic.getResult();
		this.setState(State.FINISHED);
	}

	@Override
	public JsonObject getResult() {
		return enactableAtomic.getResult();
	}

	@Override
	public State getState() {
		return enactableAtomic.getState();
	}

	@Override
	public void setInputValue(final String key, final JsonElement value) {
		// Removed line:
		//super.setInputValue(key, value); // is this necessary? Why not gelegate the get input to the original?
		enactableAtomic.setInputValue(key, value);
	}
	
	// Like this:
	@Override
	public JsonObject getInput() {
		return enactableAtomic.getInput();
	}

	@Override
	public void schedule(EnactmentFunction enactmentFunction) {
		enactableAtomic.setState(State.SCHEDULABLE);
		enactableAtomic.schedule(enactmentFunction);
	}

	@Override
	protected void myPause() {
		// Does nothing
	}
}