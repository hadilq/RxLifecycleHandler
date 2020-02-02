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
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)
    defaultConfig {
        applicationId = "com.hadilq.rxlifecyclehandler.sample"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    kapt(Depends.lifecycleCompiler)

    implementation(project(":lib"))
//    implementation("${Versions.groupId}:${Versions.artifactId}:${Versions.libVersion}")

    implementation(Depends.kotlin)
    implementation(Depends.appCompat)
    implementation(Depends.lifecycle)
    implementation(Depends.viewModle)
    implementation(Depends.rxJava)
    implementation(Depends.rxAndroid)
}
