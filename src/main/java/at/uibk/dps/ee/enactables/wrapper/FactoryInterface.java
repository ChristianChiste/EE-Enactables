package at.uibk.dps.ee.enactables.wrapper;

import at.uibk.dps.ee.core.enactable.Enactable;
import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import net.sf.opendse.model.Task;

public interface FactoryInterface {

	/**
	 * Creates an enactable which can be used to perform the enactment modeled by
	 * the provided function node.
	 * 
	 * @param functionNode the provided function node
	 * @return an enactable which can be used to perform the enactment modeled by
	 *         the provided function node
	 */
	EnactableAtomic createEnactable(Task task);

	/**
	 * Creates the enactable of the given offspring task and adjusts its state so
	 * that it corresponds to the state of the parent enactable (if e.g., some
	 * inputs were already set before the reproduction).
	 * 
	 * @param offspring the offspring task
	 * @param parentEnactable the enactable of the parent
	 * @return the child enactable, adjusted to have the same state as the parent
	 *         enactable
	 */
	void reproduceEnactable(Task offspring, EnactableAtomic parentEnactable);

	/**
	 * Adds the given listener to the list of listeners provided to every
	 * constructed {@link Enactable}.
	 * 
	 * @param listener the listener to add
	 */
	void addEnactableStateListener(final EnactableStateListener listener);
}