package com.visualisator.persistence.repository

import com.visualisator.persistence.models.TodoItemModel
import org.springframework.data.repository.CrudRepository

interface TodoItemRepository : CrudRepository<TodoItemModel, Long>