plugins {
    `java-library`
}

group = "com.jiandong"
version = "0.0.1-SNAPSHOT"
description = "mx-model-adapter"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.prowidesoftware:pw-iso20022:SRU2025-10.4.0")
    implementation("org.mapstruct:mapstruct:1.6.3")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.12.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
