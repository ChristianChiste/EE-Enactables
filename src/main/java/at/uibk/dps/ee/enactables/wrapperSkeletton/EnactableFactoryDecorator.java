package at.uibk.dps.ee.enactables.wrapperSkeletton;

import java.util.Set;

import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import at.uibk.dps.ee.enactables.EnactableAtomicReliability;
import at.uibk.dps.ee.enactables.EnactableFactory;
import net.sf.opendse.model.Task;

public class EnactableFactoryDecorator extends EnactableFactory {

	protected final EnactableFactory decoratedFactory;
	protected final double faultRate;

	@Inject
	public EnactableFactoryDecorator(Set<EnactableStateListener> stateListeners, EnactableFactory wrappedFactory,
			@Constant(namespace = EnactableFactoryDecorator.class, value = "failRate") double faultRate) {
		super(stateListeners);
		this.decoratedFactory = wrappedFactory;
		this.faultRate = faultRate;
	}

	@Override
	public EnactableAtomic createEnactable(Task task) {
		EnactableAtomic rawEnactable = decoratedFactory.createEnactable(task);
		return new EnactableAtomicReliability(stateListeners, task, rawEnactable, faultRate);
	}

}
