plugins {
    application
    kotlin("jvm") version "1.3.21"
    // https://github.com/ben-manes/gradle-versions-plugin
    id("com.github.ben-manes.versions") version "0.20.0"
    // https://github.com/johnrengelman/shadow
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

application {
    mainClassName = "com.networknt.server.Server"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compile(kotlin("stdlib"))
    val light4jVersion: String by project

    // light-4j
    compile("com.networknt", "server", light4jVersion)
    compile("com.networknt", "handler", light4jVersion)
    compile("com.networknt", "info", light4jVersion)
    compile("com.networknt", "health", light4jVersion)
    compile("com.networknt", "metrics", light4jVersion)
    compile("com.networknt", "traceability", light4jVersion)
    compile("com.networknt", "correlation", light4jVersion)
    compile("com.networknt", "encode-decode", light4jVersion)
    compile("com.networknt", "body", light4jVersion)
    compile("com.networknt", "audit", light4jVersion)
    compile("com.networknt", "sanitizer", light4jVersion)

    compile("com.networknt", "openapi-parser", light4jVersion)
    compile("com.networknt", "openapi-meta", light4jVersion)
    compile("com.networknt", "openapi-security", light4jVersion)
    compile("com.networknt", "openapi-validator", light4jVersion)
    compile("com.networknt", "specification", light4jVersion)

    // json-schema-validator
    val jsonSchemaValidatorVersion : String by project
    compile("com.networknt", "json-schema-validator", jsonSchemaValidatorVersion)


    // jackson json/xml/yaml serialisation
    val jacksonVersion: String by project
    compile("com.fasterxml.jackson.core", "jackson-core", jacksonVersion)
    compile("com.fasterxml.jackson.core", "jackson-databind", jacksonVersion)
    compile("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    compile("com.fasterxml.jackson.datatype", "jackson-datatype-jsr310", jacksonVersion)

    // undertow version for the http core
    val undertowVersion: String by project
    compile("io.undertow", "undertow-core", undertowVersion)

    val logbackVersion: String by project
    compile("ch.qos.logback", "logback-classic", logbackVersion)

    val kotlinLoggingVersion: String by project
    compile("io.github.microutils", "kotlin-logging", kotlinLoggingVersion)

    // standard testing libraries
    val junitVersion: String by project
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    // assertk
    val assertkVersion: String by project
    testCompile("com.willowtreeapps.assertk", "assertk-jvm", assertkVersion)

}

repositories {
    mavenLocal() // mavenLocal must be added first.
    mavenCentral()
    jcenter()
}

