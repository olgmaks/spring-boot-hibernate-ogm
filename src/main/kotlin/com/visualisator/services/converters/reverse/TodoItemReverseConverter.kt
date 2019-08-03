package com.visualisator.services.converters.reverse

import com.visualisator.persistence.models.ItemStatusEnum
import com.visualisator.persistence.models.LabelModel
import com.visualisator.persistence.models.TodoItemModel
import com.visualisator.persistence.repository.LabelsRepository
import com.visualisator.services.converters.fromISOString
import com.visualisator.services.dto.TodoItemDTO
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class TodoItemReverseConverter(val labelsRepository: LabelsRepository) : Converter<TodoItemDTO, TodoItemModel> {


    override fun convert(source: TodoItemDTO): TodoItemModel {
        return TodoItemModel(
                description = source.description,
                created = source.created.fromISOString(),
                priority = source.priority,
                status = ItemStatusEnum.valueOf(source.status),
                labels = convertLabels(source.labels)
        )
    }

    private fun convertLabels(labelsNames: List<String>): List<LabelModel> =
            labelsNames
                    .map { name ->
                        if (labelsRepository.existsByName(name)) labelsRepository.findByName(name)
                        else labelsRepository.save(LabelModel(name = name)) }
}