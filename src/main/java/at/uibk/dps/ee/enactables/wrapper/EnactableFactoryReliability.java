package at.uibk.dps.ee.enactables.wrapper;

import java.util.Set;

import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

import at.uibk.dps.ee.core.enactable.Enactable;
import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import at.uibk.dps.ee.enactables.EnactableAtomicReliability;
import at.uibk.dps.ee.enactables.EnactableFactory;
import net.sf.opendse.model.Task;

/**
 * Factory for creating Enactables of type EnactableAtomicReliabilty
 * 
 * @author Christian Chisté
 *
 */
public class EnactableFactoryReliability extends EnactableFactory {

	protected final double failRate;

	@Inject
	public EnactableFactoryReliability(Set<EnactableStateListener> stateListeners,
			@Constant(namespace = EnactableFactoryReliability.class, value = "failRate") double failRate) {
		super(stateListeners);
		this.failRate = failRate;
	}

	@Override
	public EnactableAtomic createEnactable(Task task) {
		EnactableAtomic rawEnactable = super.createEnactable(task);
		return new EnactableAtomicReliability(stateListeners, task, rawEnactable, failRate);
	}

}
