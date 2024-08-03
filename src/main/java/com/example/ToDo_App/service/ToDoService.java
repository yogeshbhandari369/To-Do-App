package com.example.ToDo_App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDo_App.IToDoRepo;
import com.example.ToDo_App.model.ToDo;

@Service
public class ToDoService {
	@Autowired
	IToDoRepo repo;

	public List<ToDo> getAllToDoItems() {
		ArrayList<ToDo> todoList = new ArrayList<>();
		repo.findAll().forEach(todo -> todoList.add(todo));
		return todoList;
	}

	public ToDo getToDoItemsById(Long id) {
		return repo.findById(id).get();
	}

	public boolean updateStatus(Long id) {
		ToDo todo = getToDoItemsById(id);
		todo.setStatus("completed");
		return saveOrUpdateTodoItem(todo);
	}

	public boolean saveOrUpdateTodoItem(ToDo todo) {
		ToDo updatedObj = repo.save(todo);
		if (getToDoItemsById(updatedObj.getId()) != null) {
			return true;
		}
		return false;
	}

	public boolean deleteToDoItem(Long id) {
		repo.deleteById(id);
		if (repo.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}
}
