plugins {
    kotlin("jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.3.2"
    id("com.gradleup.shadow") version "9.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "com.aurora"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // Ktor server
    implementation("io.ktor:ktor-server-core:3.3.2")
    implementation("io.ktor:ktor-server-netty:3.3.2")
    implementation("io.ktor:ktor-server-auth:3.3.2")
    implementation("io.ktor:ktor-server-auth-jwt:3.3.2")
    implementation("io.ktor:ktor-server-call-logging:3.3.2")
    implementation("io.ktor:ktor-server-content-negotiation:3.3.2")
    implementation("io.ktor:ktor-server-status-pages:3.3.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.2")

    // Exposed
    implementation("org.jetbrains.exposed:exposed-core:0.55.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.55.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.55.0")

    // HikariCP
    implementation("com.zaxxer:HikariCP:6.0.0")

    // Postgres
    implementation("org.postgresql:postgresql:42.7.4")

    // Koin
    implementation("io.insert-koin:koin-ktor:4.0.0")
    implementation("io.insert-koin:koin-logger-slf4j:4.0.0")

    // Logback
    implementation("ch.qos.logback:logback-classic:1.5.12")

    // Testing
    testImplementation("io.ktor:ktor-server-test-host:3.3.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:2.1.0")
}

// Ensure application.conf is included
/*
tasks.processResources {
    from("src/main/resources") {
        include("application.conf")
    }
}
*/

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


// Configure the run task
tasks.named<JavaExec>("run") {
    mainClass.set("com.aurora.notes.ApplicationKt")
    classpath = sourceSets["main"].runtimeClasspath
}

// Configure Shadow JAR task
tasks.shadowJar {
    manifest {
        attributes(
            "Main-Class" to "com.aurora.notes.ApplicationKt"
        )
    }
    archiveBaseName.set("aurora-notes")
    archiveClassifier.set("")
    archiveVersion.set("")
}


application {
    mainClass.set("com.aurora.notes.ApplicationKt")
    applicationDefaultJvmArgs = listOf()

   /* applicationDefaultJvmArgs = listOf(
        "-Dio.ktor.development=true",
        "-config=src/main/resources/application.conf"
    )*/
}

