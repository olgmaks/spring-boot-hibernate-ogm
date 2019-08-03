package com.visualisator.persistence.models

import java.util.*
import javax.persistence.*

@Entity
data class TodoItemModel (
        @Id
        @GeneratedValue
        val id: Long = 0,
        val description: String,
        var created: Date,
        val priority: Int,
        var status: ItemStatusEnum,
        @OneToMany(fetch = FetchType.EAGER)
        var labels: List<LabelModel> = emptyList())