plugins {
    id("java")
}

group = "com.github.brucemelo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.hibernate.orm:hibernate-core:6.5.0.Final")
    implementation("io.github.openfeign.querydsl:querydsl-jpa:6.3")
    annotationProcessor("io.github.openfeign.querydsl:querydsl-apt:6.3:jpa")
}

tasks.test {
    useJUnitPlatform()
}