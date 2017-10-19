package com.adiaz.data.api;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by toni on 19/10/2017.
 */
public class TodoBusinessImplMockitoRulesTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	TodoService todoService;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;


	@Test
	public void usingMockito() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}
}
