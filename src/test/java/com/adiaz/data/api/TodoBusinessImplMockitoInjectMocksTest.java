package com.adiaz.data.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by toni on 19/10/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest  {
	@Mock
	TodoService todoService;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void usingMockito() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}

	@Test
	public void letsTestDeleteNow() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		verify(todoService).deleteTodo("Learn to Dance");
		verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");
		verify(todoService, Mockito.never()).deleteTodo("Learn Spring");
		verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
	}

	@Test
	public void captureArgument() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",  "Learn Spring", "Learn to Dance");
		Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		Mockito.verify(todoService).deleteTodo(stringArgumentCaptor.capture());
		assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
	}
}
