//package com.visualisator.persistence.repository
//
//import com.visualisator.persistence.models.UserModel
//import org.springframework.data.mongodb.repository.MongoRepository
//import org.springframework.data.mongodb.repository.Query
//import org.springframework.stereotype.Repository
//
//@Repository
//interface UserRepository : MongoRepository<UserModel, Long> {
//
//    @Query("select u from UserModel u where u.name like %:q%")
//    fun search(q: String): List<UserModel>
//}