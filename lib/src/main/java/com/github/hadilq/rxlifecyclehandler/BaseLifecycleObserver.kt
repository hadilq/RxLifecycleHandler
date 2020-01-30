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

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.hadilq.androidlifecyclehandler.AndroidLifecycleHandler
import com.github.hadilq.androidlifecyclehandler.LifeSpan
import com.github.hadilq.rxlifecyclehandler.Entry.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

/***
 * The base class of handlers, which glue both libraries. Notice here we assume the [LifecycleOwner]
 * needs the emitted values of upstream just between [onStart] and [onStop] events.
 */
internal abstract class BaseLifecycleObserver<T>(private val handler: AndroidLifecycleHandler) :
    LifecycleObserver {

    private lateinit var entry: Entry<T>

    fun observe(
        subscribe: (Consumer<T>) -> Disposable
    ): LifecycleOwner.((T) -> Unit) -> Unit = { observer: (T) -> Unit ->
        observeEntry(ObserveEntry(observer, subscribe))
    }

    fun observeOnNext(
        subscribe: (Consumer<T>) -> Disposable
    ): LifecycleOwner.(Consumer<T>) -> Unit = { onNext: Consumer<T> ->
        observeEntry(OnNextEntry(onNext, subscribe))
    }

    fun observeOnNextOnError(
        subscribe: (Consumer<T>, Consumer<Throwable>) -> Disposable
    ): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
        { onNext: Consumer<T>, onError: Consumer<Throwable> ->
            observeEntry(OnNextOnErrorEntry(onNext, onError, subscribe))
        }

    fun observeOnNextOnErrorOnComplete(
        subscribe: (Consumer<T>, Consumer<Throwable>, Action) -> Disposable
    ): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
        { onNext: Consumer<T>,
          onError: Consumer<Throwable>,
          onComplete: Action ->
            observeEntry(OnNextOnErrorOnCompleteEntry(onNext, onError, onComplete, subscribe))
        }

    fun observeOnNextOnErrorOnCompleteOnSubscribe(
        subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Disposable
    ): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Unit =
        { onNext: Consumer<T>,
          onError: Consumer<Throwable>,
          onComplete: Action,
          onSubscribe: Consumer<Subscription> ->
            observeEntry(
                OnNextOnErrorOnCompleteOnSubscribeEntry(
                    onNext,
                    onError,
                    onComplete,
                    onSubscribe,
                    subscribe
                )
            )
        }

    fun observeOnNextOnErrorOnCompleteOnDisposable(
        subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Disposable
    ): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Unit =
        { onNext: Consumer<T>,
          onError: Consumer<Throwable>,
          onComplete: Action,
          onSubscribe: Consumer<Disposable> ->
            observeEntry(
                OnNextOnErrorOnCompleteOnDisposableEntry(
                    onNext,
                    onError,
                    onComplete,
                    onSubscribe,
                    subscribe
                )
            )
        }

    private fun LifecycleOwner.observeEntry(entry: Entry<T>) {
        handler.register(this, entry, LifeSpan.STARTED)
    }
}
