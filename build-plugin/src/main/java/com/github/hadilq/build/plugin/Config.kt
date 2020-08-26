/**
 * Copyright 2020 Hadi Lashkari Ghouchani

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("UnstableApiUsage")

package com.github.hadilq.build.plugin

import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.signing.SigningPlugin
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File

fun Project.setup() {
}

fun Project.setupJacoco() {
  plugins.apply(JacocoPlugin::class.java)

  extensions.getByType<JacocoPluginExtension>().run {
    toolVersion = VERSION_JACOCO

    tasks.register<JacocoReport>("jacocoTestReport") {
      val coverageSourceDirs = arrayOf(
        "src/commonMain/kotlin",
        "src/jvmMain/kotlin",
        "src/androidMain/kotlin"
      )

      val classFiles = File("${buildDir}/tmp/kotlin-classes/debug")
        .walkBottomUp()
        .toSet()

      classDirectories.setFrom(classFiles)
      sourceDirectories.setFrom(files(coverageSourceDirs))

      executionData
        .setFrom(files("${buildDir}/jacoco/testDebugUnitTest.exec"))

      reports {
        xml.isEnabled = true
        html.isEnabled = true
      }
    }
  }

}

fun Project.setupPublication() {
  plugins.apply("org.jetbrains.dokka")

  if (!hasProperty("signing.keyId")) {
    return
  }
  plugins.apply("maven-publish")
  plugins.apply(SigningPlugin::class.java)

  group = GROUP_ID
  version = LIB_VERSION

  val userId = "hadilq"
  val userName = "Hadi Lashkari Ghouchani"
  val userEmail = "hadilq.dev@gmail.com"
  val githubUrl = "https://github.com/hadilq/RxLifecycleHandler"
  val githubScmUrl = "scm:git@github.com:hadilq/RxLifecycleHandler.git"

  val javadocJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    archiveClassifier.value("javadoc")
    from(tasks.getByName("dokkaJavadoc"))
  }

  val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.value("sources")
  }

  extensions.getByType<PublishingExtension>().run {
    publications {
      withType<MavenPublication>()["kotlinMultiplatform"].artifact(sourcesJar)
    }
    publications.withType<MavenPublication>().all {
      signing.sign(this)
      artifact(javadocJar)
      pom {
        withXml {
          asNode().apply {
            appendNode("name", "RxLifecycleHandler")
            appendNode(
              "description",
              "This library is a glue between the lifecycle of `androidx.lifecycle:lifecycle-extensions` and \n" +
                      " `io.reactivex.rxjava2:rxjava` libraries."
            )
            appendNode("url", githubUrl)
          }
        }
        licenses {
          license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
          developer {
            id.set(userId)
            name.set(userName)
            email.set(userEmail)
          }
        }
        scm {
          url.set(githubUrl)
          connection.set(githubScmUrl)
          developerConnection.set(githubScmUrl)
        }
      }

    }

    repositories {
      maven {
        url = if ("$version".endsWith("-SNAPSHOT"))
          uri("https://oss.sonatype.org/content/repositories/snapshots/")
        else
          uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
        credentials {
          username = findProperty("ossrhUsername")?.toString()
          password = findProperty("ossrhPassword")?.toString()
        }
      }
    }

  }
}

/**
 * Retrieves the [signing][org.gradle.plugins.signing.SigningExtension] extension.
 */
val org.gradle.api.Project.signing: org.gradle.plugins.signing.SigningExtension
  get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("signing") as org.gradle.plugins.signing.SigningExtension
