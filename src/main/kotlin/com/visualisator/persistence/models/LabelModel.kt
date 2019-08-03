package com.visualisator.persistence.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class LabelModel (@Id
                       @GeneratedValue
                       val id: Long = 0,
                       val name: String = "") {
}