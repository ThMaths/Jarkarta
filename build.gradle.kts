import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	war
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.squareup.okhttp3:okhttp:+")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation ("com.squareup.okhttp3:okhttp-urlconnection:+")
	//Permet à JAVA de se connecter à une base SQL
	runtimeOnly("com.mysql:mysql-connector-j")
	//JPA Framework Java qui génère du SQL
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//Pour utiliser avec Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.mindrot:jbcrypt:+")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
