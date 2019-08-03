package com.visualisator.services.impl

import com.visualisator.persistence.models.ItemStatusEnum
import com.visualisator.persistence.repository.TodoItemRepository
import com.visualisator.services.TodoItemService
import com.visualisator.services.converters.TodoItemConverter
import com.visualisator.services.converters.reverse.TodoItemReverseConverter
import com.visualisator.services.dto.TodoItemDTO
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class TodoItemServiceImpl (
        private val todoItemConverter: TodoItemConverter,
        private val todoItemReverseConverter: TodoItemReverseConverter,
        private val todoItemRepository: TodoItemRepository) : TodoItemService {

    override fun createTodoItem(itemDTO: TodoItemDTO): TodoItemDTO {

        val todoItemModel = todoItemReverseConverter.convert(itemDTO)
        todoItemModel.created = Date.from(Instant.now())
        todoItemModel.status = ItemStatusEnum.CREATED

        return todoItemConverter.convert(todoItemRepository.save(todoItemModel))
    }

    override fun findAllToDoItems(): List<TodoItemDTO> {
        return todoItemRepository.findAll().map { tdi -> todoItemConverter.convert(tdi) }
    }
}