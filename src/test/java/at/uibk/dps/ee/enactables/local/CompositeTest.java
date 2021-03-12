package at.uibk.dps.ee.enactables.local;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.JsonObject;

import at.uibk.dps.ee.core.enactable.EnactmentFunction;
import at.uibk.dps.ee.core.exception.StopException;
import at.uibk.dps.ee.enactables.local.calculation.Addition;

public class CompositeTest {
	@Test
	public void testComposite() {
		Composite c = new Composite();
		EnactmentFunction e1 = new Addition();
		EnactmentFunction e2 = new Addition();
		c.addFunction(e1);
		c.addFunction(e2);
		JsonObject input = new JsonObject();
		input.addProperty(ConstantsLocal.inputAdditionFirst, 6);
		input.addProperty(ConstantsLocal.inputAdditionSecond, 7);
		input.addProperty(ConstantsLocal.inputWaitTime, 150);
		try {
			JsonObject result = c.processInput(input);
			assertEquals(13, result.get(ConstantsLocal.outputAdditionResult).getAsLong());
		} catch (StopException e) {
			e.printStackTrace();
		}
		

	}
}

