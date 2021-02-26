package at.uibk.dps.ee.enactables.wrapperSkeletton;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import net.sf.opendse.model.Task;

public interface FactoryInterface {

	EnactableAtomic createEnactable(Task task);

	void reproduceEnactable(Task offspring, EnactableAtomic parentEnactable);

	void addEnactableStateListener(final EnactableStateListener listener);
}