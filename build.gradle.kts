import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("plugin.jpa") version "1.3.31"
	id("org.springframework.boot") version "2.2.0.M4"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
}

group = "com.visualisator"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

//dependencyManagement {
//	imports {
//		mavenBom("org.hibernate.ogm:hibernate-ogm-bom:5.4.1.Final") {
//		}
//	}
//}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
		exclude("org.hibernate")
	}
	implementation("org.hibernate.ogm:hibernate-ogm-mongodb:5.3.1.Final")
	implementation("org.springframework.boot:spring-boot-devtools:2.1.6.RELEASE")

	implementation("org.hibernate:hibernate-core:5.2.16.Final")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	implementation("org.hibernate.ogm:hibernate-ogm-mongodb")
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.11.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
