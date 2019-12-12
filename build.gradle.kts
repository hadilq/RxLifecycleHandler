/**
 * Copyright 2019 Hadi Lashkari Ghouchani

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
plugins {
    kotlin("jvm") version Versions.kotlin
}
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}")
        classpath("com.dicedmelon.gradle:jacoco-android:${Versions.jacoco}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
