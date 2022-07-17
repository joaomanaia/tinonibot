import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "com.joaomanaia"
version = "1.0.0"

repositories {
    mavenCentral()

    // Kord Snapshots Repository (Optional):
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.kotlindiscord.com/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.mockk:mockk:1.12.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")

    implementation("dev.kord:kord-core:0.8.0-M15")

    implementation("org.slf4j:slf4j-log4j12:1.7.36")

    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.5-SNAPSHOT")

    implementation("org.apache.logging.log4j:log4j-api:2.18.0")
    implementation("org.apache.logging.log4j:log4j-core:2.18.0")

    val koinVersion = "3.2.0"

    // Koin Core features
    implementation("io.insert-koin:koin-core:$koinVersion")

    implementation("io.supabase:postgrest-kt:0.2.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("MainKt")
}