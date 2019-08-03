package com.visualisator.services.converters

import com.visualisator.persistence.models.TodoItemModel
import com.visualisator.services.dto.TodoItemDTO
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class TodoItemConverter : Converter<TodoItemModel, TodoItemDTO> {

    override fun convert(source: TodoItemModel): TodoItemDTO {
        return TodoItemDTO(
                id = source.id,
                description = source.description,
                created = source.created.toISOString(),
                priority = source.priority,
                status = source.status.name,
                labels = source.labels.map { x -> x.name }

        )
    }
}