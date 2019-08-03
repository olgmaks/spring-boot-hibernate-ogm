package com.visualisator.services.provider

import com.mongodb.MongoClient
import org.hibernate.ogm.datastore.mongodb.configuration.impl.MongoDBConfiguration
import org.hibernate.ogm.datastore.mongodb.impl.MongoDBDatastoreProvider
import com.mongodb.MongoClientURI



class XMongoDBDatastoreProvider : MongoDBDatastoreProvider() {

    override fun createMongoClient(config: MongoDBConfiguration?): com.mongodb.MongoClient {
        val uri = MongoClientURI(
                "mongodb+srv://mongouser:mongouser123@mongo-test-instance-musz7.mongodb.net/test?retryWrites=true&w=majority")

        return MongoClient(uri)
    }
}