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

import androidx.savedstate.SavedStateRegistryOwner
import io.reactivex.processors.FlowableProcessor
import kotlin.reflect.KClass

/**
 * An implementation of [ExtendedLifecycleAware] for a [FlowableProcessor].
 */
class ProcessorExtendedLifecycleAwareImpl<T : Any>(
    private val processor: FlowableProcessor<T>,
    private val handler: RxExtendedLifecycleHandler<T>,
    private val key: String,
    clazz: KClass<T>
) : AbsExtendedLifecycleAware<T>(clazz) {

    override fun observe(): SavedStateRegistryOwner.((T) -> Unit) -> Unit =
        handler.observe({ processor.doOnNext { value -> cache(value) }.subscribe(it) }, this, key)

    override fun <R> emit(value: R) {
        @Suppress("UNCHECKED_CAST")
        processor.onNext(value as T)
    }
}
