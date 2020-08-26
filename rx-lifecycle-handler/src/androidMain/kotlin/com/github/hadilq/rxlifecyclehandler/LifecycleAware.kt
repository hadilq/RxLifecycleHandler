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
package com.github.hadilq.rxlifecyclehandler

import androidx.lifecycle.LifecycleOwner
import io.reactivex.processors.FlowableProcessor
import io.reactivex.subjects.Subject

/***
 * Uses for hiding [Subject] or [FlowableProcessor] from [LifecycleOwner] and prepare it
 * for observation by [LifecycleOwner].
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (viewModel.stringEmitter.observe()) { testString ->
 *           assert(testString == "Test")
 *       }
 *   }
 * }
 * ```
 */
interface LifecycleAware<T> {

    /**
     * Observe the wrapped up [Subject] or [FlowableProcessor].
     *
     * The [LifecycleOwner] is the Activity or Fragment.
     */
    fun observe(): LifecycleOwner.((T) -> Unit) -> Unit
}
