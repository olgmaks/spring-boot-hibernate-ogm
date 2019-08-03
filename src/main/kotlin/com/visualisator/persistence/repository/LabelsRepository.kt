package com.visualisator.persistence.repository

import com.visualisator.persistence.models.LabelModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LabelsRepository : CrudRepository<LabelModel, Long> {

    fun findByName(name: String): LabelModel

    fun existsByName(name: String): Boolean
}