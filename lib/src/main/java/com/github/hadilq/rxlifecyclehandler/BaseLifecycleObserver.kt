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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

/***
 * The base class of handlers, which glue both libraries. Notice here we assume the [LifecycleOwner]
 * needs the emitted values of upstream just between [onStart] and [onStop] events.
 */
internal abstract class BaseLifecycleObserver<T> : LifecycleObserver {
    private val lifecycle by lazy { owner.lifecycle }
    private var disposable: Disposable? = null

    private lateinit var owner: LifecycleOwner
    private lateinit var entry: Entry<T>

    fun observe(subscribe: (Consumer<T>) -> Disposable): LifecycleOwner.((T) -> Unit) -> Unit =
        { observer: (T) -> Unit ->
            observeEntry(Entry.ObserveEntry(observer, subscribe))
        }

    fun observeOnNext(subscribe: (Consumer<T>) -> Disposable): LifecycleOwner.(Consumer<T>) -> Unit =
        { onNext: Consumer<T> ->
            observeEntry(Entry.OnNextEntry(onNext, subscribe))
        }

    fun observeOnNextOnError(subscribe: (Consumer<T>, Consumer<Throwable>) -> Disposable): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
        { onNext: Consumer<T>, onError: Consumer<Throwable> ->
            observeEntry(Entry.OnNextOnErrorEntry(onNext, onError, subscribe))
        }

    fun observeOnNextOnErrorOnComplete(subscribe: (Consumer<T>, Consumer<Throwable>, Action) -> Disposable): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
        { onNext: Consumer<T>, onError: Consumer<Throwable>, onComplete: Action ->
            observeEntry(Entry.OnNextOnErrorOnCompleteEntry(onNext, onError, onComplete, subscribe))
        }

    fun observeOnNextOnErrorOnCompleteOnSubscribe(subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Disposable): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Unit =
        { onNext: Consumer<T>, onError: Consumer<Throwable>, onComplete: Action, onSubscribe: Consumer<Subscription> ->
            observeEntry(
                Entry.OnNextOnErrorOnCompleteOnSubscribeEntry(
                    onNext,
                    onError,
                    onComplete,
                    onSubscribe,
                    subscribe
                )
            )
        }

    fun observeOnNextOnErrorOnCompleteOnDisposable(subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Disposable): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Unit =
        { onNext: Consumer<T>, onError: Consumer<Throwable>, onComplete: Action, onSubscribe: Consumer<Disposable> ->
            observeEntry(
                Entry.OnNextOnErrorOnCompleteOnDisposableEntry(
                    onNext,
                    onError,
                    onComplete,
                    onSubscribe,
                    subscribe
                )
            )
        }

    private fun LifecycleOwner.observeEntry(entry: Entry<T>) {
        this@BaseLifecycleObserver.entry = entry
        this@BaseLifecycleObserver.owner = this
        registerIfPossible()
    }

    private fun registerIfPossible() {
        if (lifecycle.currentState != Lifecycle.State.DESTROYED) {
            lifecycle.addObserver(this)
            observeIfPossible()
        }
    }

    private fun observeIfPossible() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            disposable ?: let {
                disposable = entry.subscribe()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        observeIfPossible()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        disposable?.dispose()
        disposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        onStop()
        lifecycle.removeObserver(this)
    }

    private sealed class Entry<T> {
        abstract fun subscribe(): Disposable

        class ObserveEntry<T>(val fn: (T) -> Unit, val subscribe: (Consumer<T>) -> Disposable) :
            Entry<T>() {
            override fun subscribe(): Disposable = subscribe(Consumer { fn(it) })
        }

        class OnNextEntry<T>(val onNext: Consumer<T>, val subscribe: (Consumer<T>) -> Disposable) :
            Entry<T>() {
            override fun subscribe(): Disposable = subscribe(onNext)
        }

        class OnNextOnErrorEntry<T>(
            val onNext: Consumer<T>,
            val onError: Consumer<Throwable>,
            val subscribe: (Consumer<T>, Consumer<Throwable>) -> Disposable
        ) : Entry<T>() {
            override fun subscribe(): Disposable = subscribe(onNext, onError)
        }

        class OnNextOnErrorOnCompleteEntry<T>(
            val onNext: Consumer<T>,
            val onError: Consumer<Throwable>,
            val onComplete: Action,
            val subscribe: (Consumer<T>, Consumer<Throwable>, Action) -> Disposable
        ) : Entry<T>() {
            override fun subscribe(): Disposable = subscribe(onNext, onError, onComplete)
        }

        class OnNextOnErrorOnCompleteOnSubscribeEntry<T>(
            val onNext: Consumer<T>,
            val onError: Consumer<Throwable>,
            val onComplete: Action,
            val onSubscribe: Consumer<Subscription>,
            val subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Disposable
        ) : Entry<T>() {
            override fun subscribe(): Disposable =
                subscribe(onNext, onError, onComplete, onSubscribe)
        }

        class OnNextOnErrorOnCompleteOnDisposableEntry<T>(
            val onNext: Consumer<T>,
            val onError: Consumer<Throwable>,
            val onComplete: Action,
            val onSubscribe: Consumer<Disposable>,
            val subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Disposable
        ) : Entry<T>() {
            override fun subscribe(): Disposable =
                subscribe(onNext, onError, onComplete, onSubscribe)
        }
    }

}
