package at.uibk.dps.ee.enactables.wrapperSkeletton;

import at.uibk.dps.ee.enactables.EnactableAtomic;
import net.sf.opendse.model.Task;

/**
 * Did not think of this, but we will actually need an interface for the binding
 * scheme to work. In order for the whole binding to work, you have to replace
 * the parts where the enactable factory is used by the factory interface.
 * 
 * @author fedor
 *
 */
public interface FactoryInterface {

	EnactableAtomic createEnactable(Task task);

	void reproduceEnactable(Task offspring, EnactableAtomic parentEnactable);

}
