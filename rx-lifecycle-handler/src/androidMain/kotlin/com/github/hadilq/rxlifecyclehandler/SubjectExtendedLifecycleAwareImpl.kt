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
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.Subject
import kotlin.reflect.KClass

/**
 * An implementation of [ExtendedLifecycleAware] for a [Subject].
 */
class SubjectExtendedLifecycleAwareImpl<T : Any>(
    private val subject: Subject<T>,
    private val handler: RxELifecycleHandler<T>,
    private val key: String,
    clazz: KClass<T>
) : AbsExtendedLifecycleAware<T>(clazz) {

    override fun observe(): SavedStateRegistryOwner.((T) -> Unit) -> Unit =
        handler.observeOnNext(subject.doOnNext { value -> cache(value) }
            .toFlowable(BackpressureStrategy.DROP), this, key)

    override fun <R> emit(value: R) {
        @Suppress("UNCHECKED_CAST")
        subject.onNext(value as T)
    }
}
