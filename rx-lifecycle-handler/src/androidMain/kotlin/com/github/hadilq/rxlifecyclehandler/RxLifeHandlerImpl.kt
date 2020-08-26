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
import com.github.hadilq.androidlifecyclehandler.AndroidLifeHandler
import com.github.hadilq.androidlifecyclehandler.LifeSpan
import io.reactivex.Flowable

/***
 * An implementation of [RxLifeHandler].
 */
class RxLifeHandlerImpl<T>(private val handler: AndroidLifeHandler) :
    RxLifeHandler<T> {

    override fun observeOnNext(
        flowable: Flowable<T>
    ): LifecycleOwner.((T) -> Unit) -> Unit =
        { onNext: (T) -> Unit ->
            observeEntry(flowable.doOnNext(onNext).toLife())
        }

    override fun observeOnNextOnError(
        flowable: Flowable<T>
    ): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
        { onNext: (T) -> Unit, onError: (Throwable) -> Unit ->
            observeEntry(
                flowable
                    .doOnNext(onNext)
                    .doOnError(onError)
                    .toLife()
            )
        }

    override fun observeOnNextOnErrorOnComplete(
        flowable: Flowable<T>
    ): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
        { onNext: (T) -> Unit, onError: (Throwable) -> Unit, onComplete: () -> Unit ->
            observeEntry(
                flowable
                    .doOnNext(onNext)
                    .doOnError(onError)
                    .doOnComplete(onComplete)
                    .toLife()
            )
        }

    private fun LifecycleOwner.observeEntry(entry: Entry) {
        handler.register(this, entry, LifeSpan.STARTED)
    }
}
