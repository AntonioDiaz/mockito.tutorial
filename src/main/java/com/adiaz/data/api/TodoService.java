package com.adiaz.data.api;

import java.util.List;

/**
 * Created by toni on 19/10/2017.
 */
public interface TodoService {
	List<String> retrieveTodos(String user);
	void deleteTodo(String todo);

}
