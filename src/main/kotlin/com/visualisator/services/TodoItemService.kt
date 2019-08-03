package com.visualisator.services

import com.visualisator.services.dto.TodoItemDTO

interface TodoItemService {

    fun createTodoItem(itemDTO: TodoItemDTO): TodoItemDTO

    fun findAllToDoItems(): List<TodoItemDTO>
}