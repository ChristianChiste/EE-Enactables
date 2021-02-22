package at.uibk.dps.ee.enactables.serverless;

import java.util.Set;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.exception.StopException;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import at.uibk.dps.ee.enactables.EnactableAtomicDecorator;
import at.uibk.dps.ee.enactables.serverless.resource.RequestInformation;
import at.uibk.dps.ee.enactables.serverless.resource.Resource;
import at.uibk.dps.ee.enactables.serverless.resource.ResourceRequest;
import at.uibk.dps.ee.enactables.serverless.resource.SchedulerProxy;
import net.sf.opendse.model.Task;

public class EnactableAtomicResource extends EnactableAtomicDecorator{

	protected SchedulerProxy schedulerProxy;
	
	protected EnactableAtomicResource(Set<EnactableStateListener> stateListeners, Set<String> inputKeys,
			Task functionNode, EnactableAtomic enactableAtomic, SchedulerProxy schedulerProxy) {
		super(stateListeners, inputKeys, functionNode, enactableAtomic);
		this.schedulerProxy = schedulerProxy;
	}

	@Override
	protected void prePlayDecoration() throws StopException {
		ResourceRequest resourceRequest = new ResourceRequest(functionNode.getId(),
				RequestInformation.MISSING_LINK);
		((EnactableServerless)enactableAtomic).resourceLink = schedulerProxy.getResource(resourceRequest).getResourceLink();
	}

	@Override
	protected void postPlayDecoration() throws StopException {
		// Nothing
		
	}

}
