buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.jreleaser:org.jreleaser.gradle.plugin:1.27.0")
    }
}

plugins {
    `java-library`
}

group = "io.github.mjd507"
version = "0.1.1-SNAPSHOT"
description = "a model adapter for iso20022 messages"

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.prowidesoftware:pw-iso20022:SRU2025-10.4.1")
    implementation("org.mapstruct:mapstruct:1.6.3")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    testImplementation("org.junit.jupiter:junit-jupiter:6.1.3")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.1.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

apply(from = "publish-maven.gradle")
