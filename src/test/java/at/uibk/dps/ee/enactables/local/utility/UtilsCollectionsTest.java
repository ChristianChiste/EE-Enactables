package at.uibk.dps.ee.enactables.local.utility;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class UtilsCollectionsTest {

	@Test
	public void testGetSubcollectionsForString() {
		String string1 = "3";
		String string2 = "1::2";
		String string3 = "1, 1:2, :3:1";
		String string4 = "1, 1:2, producer/output:3:1";
		
		JsonObject object = new JsonObject();
		String key = "producer/output";
		JsonElement jsonInt = new JsonPrimitive(2);
		object.add(key, jsonInt);
		
		SubCollections result1 = UtilsCollections.readSubCollections(string1, object);
		SubCollections result2 = UtilsCollections.readSubCollections(string2, object);
		SubCollections result3 = UtilsCollections.readSubCollections(string3, object);
		SubCollections result4 = UtilsCollections.readSubCollections(string4, object);
		
		assertEquals(1, result1.size());
		SubCollection sub1 = result1.get(0);
		assertTrue(sub1 instanceof SubCollectionElement);
		assertEquals("3", sub1.toString());
		
		assertEquals(1, result2.size());
		SubCollection sub2 = result2.get(0);
		assertTrue(sub2 instanceof SubCollectionStartEndStride);
		assertEquals("1::2", sub2.toString());
		
		assertEquals(3, result3.size());
		SubCollection sub31 = result3.get(0);
		assertTrue(sub31 instanceof SubCollectionElement);
		assertEquals("1", sub31.toString());
		SubCollection sub32 = result3.get(1);
		assertTrue(sub32 instanceof SubCollectionStartEndStride);
		assertEquals("1:2:", sub32.toString());
		SubCollection sub33 = result3.get(2);
		assertTrue(sub33 instanceof SubCollectionStartEndStride);
		assertEquals(":3:1", sub33.toString());
		
		assertEquals(3, result4.size());
		SubCollection sub41 = result4.get(0);
		assertTrue(sub41 instanceof SubCollectionElement);
		assertEquals("1", sub41.toString());
		SubCollection sub42 = result4.get(1);
		assertTrue(sub42 instanceof SubCollectionStartEndStride);
		assertEquals("1:2:", sub42.toString());
		SubCollection sub43 = result4.get(2);
		assertTrue(sub43 instanceof SubCollectionStartEndStride);
		assertEquals("2:3:1", sub43.toString());
	}
}
