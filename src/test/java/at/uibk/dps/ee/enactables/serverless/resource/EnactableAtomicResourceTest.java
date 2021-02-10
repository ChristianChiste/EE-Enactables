package at.uibk.dps.ee.enactables.serverless.resource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.core.exception.StopException;
import at.uibk.dps.ee.enactables.EnactableAtomic;
import at.uibk.dps.ee.enactables.EnactableAtomicResource;
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
	
	protected static class EnactableMock extends EnactableAtomic {
		protected EnactableMock(Set<EnactableStateListener> stateListeners, Set<String> inputKeys,
				Task functionNode) {
			super(stateListeners, inputKeys, functionNode);
		}

		@Override
		protected void atomicPlay() throws StopException {
		}

		@Override
		protected void myPause() {
		}
	}

	protected static EnactableAtomic getTested() {
		Set<String> inputKeys = new HashSet<>();
		inputKeys.add(key1);
		inputKeys.add(key2);
		return new EnactableMock(new HashSet<>(), inputKeys, function);
	}


	/**
	 * Tests the scheduling
	 * 
	 */
	@Test
	public void testScheduling() {
		
		ResourceRequest request = new ResourceRequest("function", RequestInformation.MISSING_LINK);
		when(scheduler.getResource(request)).thenReturn(new Resource("ibm-..."));
		
		EnactableAtomic enactableAtomic = getTested();
		enactableAtomic.init();
		
		EnactableAtomicResource enactable = new EnactableResourceMock(null, null, function, enactableAtomic, null);
		
		try {
			enactable.play();
		} catch (StopException e) {
			e.printStackTrace();
		}
		//assertEquals("ibm-...", enactable.resource.getResourceLink());
	}
	
}
