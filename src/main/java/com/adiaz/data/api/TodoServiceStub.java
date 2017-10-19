package com.adiaz.data.api;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.Arrays;
import java.util.List;

/**
 * Created by toni on 19/10/2017.
 */
public class TodoServiceStub implements TodoService {
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
	}

	public void deleteTodo(String todo) {

	}
}
