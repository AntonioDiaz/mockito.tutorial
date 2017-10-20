package com.adiaz.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;

/**
 * Created by toni on 20/10/2017.
 */
@RunWith(PowerMockRunner.class)
/*To be able to mock the Constructor, we need to add in the Class that creates the new object*/
@PrepareForTest({ SystemUnderTest.class})
public class PowerMockitoMockingConstructorTest {

	private static final int SOME_DUMMY_SIZE = 100;

	@Mock
	ArrayList mockList;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void powerMockito_MockingAConstructor() throws Exception {
		//ArrayList<String> mockList = mock(ArrayList.class);
		stub(mockList.size()).toReturn(SOME_DUMMY_SIZE);
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		assertEquals(SOME_DUMMY_SIZE, size);
	}
}
