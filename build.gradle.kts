import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.springframework.cloud:spring-cloud-starter-function-web:3.2.5")
	implementation("org.springframework.cloud:spring-cloud-function-kotlin:3.2.5")
	implementation("org.springframework.cloud:spring-cloud-function-adapter-aws:3.2.5")
	implementation("com.amazonaws:aws-lambda-java-events:3.11.0")
	implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
	runtimeOnly("com.amazonaws:aws-lambda-java-log4j2:1.5.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Jar> {
	// Spring Application 시작점
	manifest {
		attributes["Start-Class"] = "com.example.lambda.AwsLambdaApplication"
	}
}

tasks.assemble {
	dependsOn("shadowJar")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
	archiveFileName.set("batch.jar")
	dependencies {
		exclude("org.springframework.cloud:spring-cloud-function-web")
	}
	mergeServiceFiles()
	append("META-INF/spring.handlers")
	append("META-INF/spring.schemas")
	append("META-INF/spring.tooling")
	transform(com.github.jengelman.gradle.plugins.shadow.transformers.PropertiesFileTransformer::class.java) {
		paths.add("META-INF/spring.factories")
		mergeStrategy = "append"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}