package at.uibk.dps.ee.enactables;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.enactable.Enactable.State;
import at.uibk.dps.ee.core.exception.StopException;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import at.uibk.dps.ee.enactables.serverless.EnactableAtomicResource;
import at.uibk.dps.ee.enactables.serverless.EnactableServerless;
import at.uibk.dps.ee.enactables.serverless.resource.RequestInformation;
import at.uibk.dps.ee.enactables.serverless.resource.Resource;
import at.uibk.dps.ee.enactables.serverless.resource.ResourceRequest;
import at.uibk.dps.ee.enactables.serverless.resource.SchedulerProxy;
import net.sf.opendse.model.Task;

public class EnactableAtomicResourceTest {

	protected static final String key1 = "input1";
	protected static final String key2 = "input2";
	protected static final Task function = new Task("function");
	protected static final SchedulerProxy scheduler= mock(SchedulerProxy.class);

	protected static class EnactableResourceMock extends EnactableAtomicResource {
		protected EnactableResourceMock(Set<EnactableStateListener> stateListeners, Set<String> inputKeys,
				Task functionNode, EnactableAtomic enactableAtomic, SchedulerProxy schedulerProxy) {
			super(stateListeners, inputKeys, functionNode, enactableAtomic, schedulerProxy);
		}

		@Override
		protected void atomicPlay() throws StopException {
		}

		@Override
		protected void myPause() {
		}
	}
	
	protected static class EnactableMock extends EnactableServerless {
		protected EnactableMock(Set<EnactableStateListener> stateListeners, Set<String> inputKeys,
				Task functionNode, String resourceLink) {
			super(stateListeners, inputKeys, resourceLink, functionNode);
		}

		@Override
		protected void atomicPlay() throws StopException {
		}

		@Override
		protected void myPause() {
		}
	}

	protected static EnactableServerless getTested() {
		Set<String> inputKeys = new HashSet<>();
		inputKeys.add(key1);
		inputKeys.add(key2);
		return new EnactableMock(new HashSet<>(), inputKeys, function, "-");
	}


	/**
	 * Tests the scheduling
	 * 
	 */
	@Test
	public void testScheduling() {
		
		ResourceRequest request = new ResourceRequest("function", RequestInformation.MISSING_LINK);
		when(scheduler.getResource(request)).thenReturn(new Resource("ibm-..."));
		
		EnactableServerless enactableServerless = getTested();
		enactableServerless.setState(State.READY);
		enactableServerless.init = true;
		
		Set<String> inputKeys = new HashSet<>();
		inputKeys.add(key1);
		inputKeys.add(key2);
		EnactableAtomicResource enactable = new EnactableResourceMock(new HashSet<>(), inputKeys, function, enactableServerless, scheduler);
		enactable.setState(State.READY);
		enactable.init = true;
		
		try {
			enactable.play();
		} catch (StopException e) {
			e.printStackTrace();
		}
		verify(scheduler, times(1)).getResource(request);
		assertEquals("ibm-...", ((EnactableServerless)enactable.enactableAtomic).getResourceLink());
	}
	
}
