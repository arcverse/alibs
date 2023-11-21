import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("maven-publish")
}

group = "se.arcver"
version = "1.0.1"

val kotlinXCoroutinesVersion = "1.7.3"
val kotlinXSerializationVersion = "1.6.0"
val kotlinXCollectionsImmutableVersion = "0.3.6"

val ktorVersion = "2.2.3"

val reflectionsVersion = "0.11.7"
val lombokVersion = "1.18.30"

val exposedVersion = "0.44.1"
val jedisVersion = "5.1.0"
val eclipseCollectionVersion = "11.1.0"
val flyawayVersion = "9.8.1"

val gsonVersion = "2.10.1"

val ascendVersion = "2023.4"

val dotenvVersion = "6.4.1"

repositories {
    mavenCentral()
    maven {
        name = "arcverseRepository"
        url = uri("https://repo.arcver.se/private")
        credentials(PasswordCredentials::class)
        authentication {
            create<BasicAuthentication>("basic")
        }
    }
}

val shadowDependencies = listOf(
    "com.github.TheFruxz:Ascend:$ascendVersion",
    "net.oneandone.reflections8:reflections8:$reflectionsVersion",
    "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinXCoroutinesVersion",
    "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinXSerializationVersion",
    "org.jetbrains.exposed:exposed-core:$exposedVersion",
    "org.jetbrains.exposed:exposed-dao:$exposedVersion",
    "org.jetbrains.exposed:exposed-jdbc:$exposedVersion",
    "org.jetbrains.exposed:exposed-java-time:$exposedVersion",
    "io.ktor:ktor-client-core:$ktorVersion",
    "io.ktor:ktor-client-okhttp:$ktorVersion",
    "io.ktor:ktor-client-websockets:$ktorVersion",
    "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion",
    "io.ktor:ktor-client-content-negotiation:$ktorVersion",
    "org.jetbrains.kotlinx:kotlinx-collections-immutable:$kotlinXCollectionsImmutableVersion",
    "com.google.code.gson:gson:$gsonVersion",
    "org.projectlombok:lombok:$lombokVersion",
    "io.github.cdimascio:dotenv-kotlin:$dotenvVersion",
    "redis.clients:jedis:$jedisVersion",
)

dependencies {
    // Shadow dependencies
    shadowDependencies.forEach { dependency ->
        implementation(dependency)
        shadow(dependency)
    }

    // Eclipse collections
    listOf("-api", "").forEach { module ->
        ("org.eclipse.collections:eclipse-collections$module:$eclipseCollectionVersion").let { dependency ->
            compileOnly(dependency)
            shadow(dependency)
        }
    }

    // Database Migration Flyway
    listOf("core", "mysql").forEach { module ->
        ("org.flywaydb:flyway-$module:$flyawayVersion").let { dependency ->
            compileOnly(dependency)
            shadow(dependency)
        }
    }
}

publishing {
    repositories {
        maven {
            name = "arcverseRepository"
            url = uri("https://repo.arcver.se/releases")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "se.arcver"
            artifactId = "alibs"
            this.version = version
            from(components["java"])
        }
    }
}

tasks {

    build {
        dependsOn("shadowJar")
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "19"
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }

    withType<ShadowJar> {
        mergeServiceFiles()
        configurations = listOf(project.configurations.shadow.get())
        archiveFileName.set("alibs.jar")
    }

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(19))
}