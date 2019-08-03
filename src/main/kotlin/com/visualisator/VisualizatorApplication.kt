package com.visualisator

import org.hibernate.ogm.OgmSessionFactory
import org.hibernate.ogm.jpa.HibernateOgmPersistence
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.persistence.spi.PersistenceProvider
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter






@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableJpaRepositories
@EnableWebMvc
class VisualizatorApplication {



    @Bean
    fun transactionManager(): PlatformTransactionManager = JpaTransactionManager()

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
//        mongodump --host mongo-test-instance-shard-0/mongo-test-instance-shard-00-00-musz7.mongodb.net:27017,mongo-test-instance-shard-00-01-musz7.mongodb.net:27017,mongo-test-instance-shard-00-02-musz7.mongodb.net:27017 --ssl --username mongouser --password <PASSWORD> --authenticationDatabase admin --db <DATABASE>
        val properties = HashMap<String, Any>()
        properties["javax.persistence.transactionType"] = "resource_local"
        properties["hibernate.ogm.datastore.provider"] = "com.visualisator.services.provider.XMongoDBDatastoreProvider"
//        properties["hibernate.ogm.datastore.host"] = "mongo-test-instance-shard-00-01-musz7.mongodb.net"
//        properties["hibernate.ogm.datastore.port"] = "27017"
        properties["hibernate.ogm.datastore.database"] = "test"
//        properties["hibernate.ogm.datastore.username"] = "mongouser"
//        properties["hibernate.ogm.datastore.password"] = "mongouser123"
//        properties["hibernate.ogm.mongodb.database"]= "test"
//        properties["hibernate.ogm.mongodb.authentication_database"]= "test"

        val entityManager = LocalContainerEntityManagerFactoryBean()
        entityManager.setPackagesToScan("com.visualisator.persistence.models")
        entityManager.persistenceUnitName = "mongoPersistenceUnit"
        entityManager.jpaVendorAdapter = MyJpaVendorAdapter()
        entityManager.setJpaPropertyMap(properties)
        entityManager.setPersistenceProviderClass(HibernateOgmPersistence::class.java)
        return entityManager
    }

    @Configuration
    @EnableWebMvc
    inner class WebConfig : WebMvcConfigurer {

        override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
            registry.addResourceHandler(
                    "/webjars/**",
                    "/img/**",
                    "/css/**",
                    "/js/**")
                    .addResourceLocations(
                            "classpath:/META-INF/resources/webjars/",
                            "classpath:/static/img/",
                            "classpath:/static/css/",
                            "classpath:/static/js/")
        }

    }
}

fun main(args: Array<String>) {
    runApplication<VisualizatorApplication>(*args)
}

class MyJpaVendorAdapter : HibernateJpaVendorAdapter() {

    override fun getPersistenceProvider(): PersistenceProvider = HibernateOgmPersistence()
}

