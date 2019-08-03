package com.visualisator.persistence.models

import org.springframework.data.domain.Persistable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserModel(
        @Id
        @GeneratedValue
        var id: Long = 0,
        var name: String,
        var email: String)