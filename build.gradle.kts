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
    api("com.prowidesoftware:pw-iso20022:SRU2025-10.4.0")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
