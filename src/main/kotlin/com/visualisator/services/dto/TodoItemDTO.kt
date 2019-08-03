package com.visualisator.services.dto

import com.visualisator.persistence.models.ItemStatusEnum

class TodoItemDTO(
        val id: Long = 0,
        val description: String = "",
        val created: String = "",
        val priority: Int = -1,
        val status: String = ItemStatusEnum.CREATED.name,
        val labels: List<String> = emptyList())