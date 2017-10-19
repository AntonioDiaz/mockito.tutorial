package com.adiaz.data.api;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by toni on 19/10/2017.
 */
public class ListTest {
	@Test
	public void letsMockListSize() {
		List list = mock(List.class);
		Mockito.when(list.size()).thenReturn(10);
		assertEquals(10, list.size());
	}

	@Test
	public void letsMockListSizeWithMultipleReturnValues() {
		List list = mock(List.class);
		Mockito.when(list.size()).thenReturn(10).thenReturn(20);
		assertEquals(10, list.size()); // First Call
		assertEquals(20, list.size()); // Second Call
	}

	@Test
	public void letsMockListGet() {
		List<String> list = mock(List.class);
		Mockito.when(list.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", list.get(0));
		assertNull(list.get(1));
	}

	@Test
	public void letsMockListGetWithAny() {
		List<String> list = mock(List.class);
		Mockito.when(list.get(Mockito.anyInt())).thenReturn("in28Minutes");
		// If you are using argument matchers, all arguments have to be provided by matchers.
		assertEquals("in28Minutes", list.get(0));
		assertEquals("in28Minutes", list.get(1));
	}
}
