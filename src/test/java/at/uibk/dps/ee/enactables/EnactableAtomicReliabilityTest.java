package at.uibk.dps.ee.enactables;

import static org.mockito.Mockito.mock;

import java.util.HashSet;

import org.junit.Test;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import at.uibk.dps.ee.core.exception.StopException;
import net.sf.opendse.model.Task;

public class EnactableAtomicReliabilityTest {

	@Test(expected = Exception.class)
	public void testFault() throws StopException {
		Task task = new Task("functionTask");
		EnactableFactory tested = new EnactableFactory(new HashSet<>());
		EnactableAtomic enactableAtomic = tested.createEnactable(task);	
		EnactmentFunction funcMock = mock(EnactmentFunction.class);;
		EnactableAtomicReliability enactable = new EnactableAtomicReliability(new HashSet<>(), task, enactableAtomic, 1);
		enactable.init();
		enactable.schedule(funcMock);
		enactable.play();
	}
	
}
