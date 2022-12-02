import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
}

group = "org.cadadr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-graphql")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    implementation("org.jetbrains:annotations:23.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjsr305=strict"
        )
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
