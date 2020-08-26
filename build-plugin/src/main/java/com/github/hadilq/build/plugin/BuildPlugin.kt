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
package com.github.hadilq.build.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildPlugin : Plugin<Project> {

  override fun apply(target: Project) = target.setup()
}

private const val VERSION_LIFECYCLE = "2.2.0"
private const val VERSION_ANDROIDX_APPCOMPAT = "1.2.0"
private const val VERSION_ANDROID_LIFECYCLE = "0.4.3"
private const val VERSION_RX_JAVA = "2.2.15"
private const val VERSION_RX_JAVA_ANDROID = "2.1.1"

private const val VERSION_JUNIT = "4.12"
private const val VERSION_MOCKITO = "1.4.0"
private const val VERSION_ROBOLECTRIC = "4.3"

const val VERSION_JACOCO = "0.8.5"

const val GROUP_ID = "com.github.hadilq"
const val LIB_VERSION = "0.4.0"

const val VERSION_COMPILE_SDK = 29
const val VERSION_MIN_SDK = 15
const val VERSION_TARGET_SDK = 29

const val KOTLIN_STDLIB = "stdlib"
const val KOTLIN_STDLIB_COMMON = "stdlib-common"
const val KOTLIN_TEST_COMMON = "test-common"
const val KOTLIN_TEST_ANNOTATIONS_COMMON = "test-annotations-common"
const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:$VERSION_ANDROIDX_APPCOMPAT"

const val LIFECYCLE = "androidx.lifecycle:lifecycle-extensions:$VERSION_LIFECYCLE"
const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:$VERSION_LIFECYCLE"
const val ANDROIDX_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION_LIFECYCLE"
const val ANDROID_LIFECYCLE = "com.github.hadilq:android-lifecycle-handler-metadata:$VERSION_ANDROID_LIFECYCLE"
const val ANDROID_LIFECYCLE_ANDROID = "com.github.hadilq:android-lifecycle-handler-android:$VERSION_ANDROID_LIFECYCLE"
const val ANDROID_LIFECYCLE_JVM = "com.github.hadilq:android-lifecycle-handler-jvm:$VERSION_ANDROID_LIFECYCLE"
const val RX_JAVA = "io.reactivex.rxjava2:rxjava:$VERSION_RX_JAVA"
const val RX_JAVA_ANDROID = "io.reactivex.rxjava2:rxandroid:$VERSION_RX_JAVA_ANDROID"

const val JUNIT = "junit:junit:$VERSION_JUNIT"
const val MOCKITO = "com.nhaarman:mockito-kotlin:$VERSION_MOCKITO"
const val ROBOLECTRIC = "org.robolectric:robolectric:$VERSION_ROBOLECTRIC"
const val MOCKK_COMMON = "io.mockk:mockk-common:1.10.0"
