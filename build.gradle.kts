plugins {
    application
    java
    jacoco
    pmd
}

application {
    mainClass.set("wheel/UI/Driver")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mockito:mockito-core:4.0.0")
    implementation("org.mockito:mockito-junit-jupiter:4.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.junit.platform:junit-platform-console:1.8.1")
}

tasks {
    val flags = listOf("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")

    getByName<JavaCompile>("compileJava") {
        options.compilerArgs = flags
    }

    getByName<JavaCompile>("compileTestJava") {
        options.compilerArgs = flags
    }
}

sourceSets {
    main {
        java.srcDirs("src")
    }
    test {
        java.srcDirs("test")
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform {}
    //jvmArgs("--enable-preview")
}

pmd {
    toolVersion = "6.27.0"
    ruleSetFiles = files("../conf/pmd/ruleset.xml")
    ruleSets = listOf()
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("*/preview/*")
                exclude("wheel/UI/*.class")
            }
        }))
    }
}

defaultTasks("clean", "test", "jacocoTestReport", "pmdMain")
