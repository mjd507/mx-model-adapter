# mx-model-adapter
a model adapter for iso20022 messages, based on prowide-iso20022 and MapStruct.

## usage

```kotlin
implementation("io.github.mjd507:mx-model-adapter:${version}")
```

For a `-SNAPSHOT` version, add the Central Portal Snapshots repository:

```kotlin
repositories {
    maven {
        name = "CentralPortalSnapshots"
        url = uri("https://central.sonatype.com/repository/maven-snapshots/")
        content {
            includeModule("io.github.mjd507", "mx-model-adapter")
        }
    }
    mavenCentral()
}
```
