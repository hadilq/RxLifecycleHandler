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
import com.github.hadilq.androidlifecyclehandler.AndroidExtendedLifecycleHandler
import com.github.hadilq.androidlifecyclehandler.ExtendedLife
import com.github.hadilq.androidlifecyclehandler.LifeSpan
import com.github.hadilq.rxlifecyclehandler.ExtendedEntry.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

/***
 * An implementation of [RxExtendedLifecycleHandler].
 */
class RxExtendedLifecycleHandlerImpl<T>(private val handler: AndroidExtendedLifecycleHandler) :
    RxExtendedLifecycleHandler<T> {

    override fun observe(
        subscribe: (Consumer<T>) -> Disposable,
        life: ExtendedLife,
        key: String
    ): SavedStateRegistryOwner.((T) -> Unit) -> Unit = { observer: (T) -> Unit ->
        observeEntry(ObserveEntry(observer, subscribe, life), key)
    }

    override fun observeOnNext(
        subscribe: (Consumer<T>) -> Disposable,
        life: ExtendedLife,
        key: String
    ): SavedStateRegistryOwner.(Consumer<T>) -> Unit = { onNext: Consumer<T> ->
        observeEntry(OnNextEntry(onNext, subscribe, life), key)
    }

    override fun observeOnNextOnError(
        subscribe: (Consumer<T>, Consumer<Throwable>) -> Disposable,
        life: ExtendedLife,
        key: String
    ): SavedStateRegistryOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
        { onNext: Consumer<T>, onError: Consumer<Throwable> ->
            observeEntry(OnNextOnErrorEntry(onNext, onError, subscribe, life), key)
        }

    override fun observeOnNextOnErrorOnComplete(
        subscribe: (Consumer<T>, Consumer<Throwable>, Action) -> Disposable,
        life: ExtendedLife,
        key: String
    ): SavedStateRegistryOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
        { onNext: Consumer<T>,
          onError: Consumer<Throwable>,
          onComplete: Action ->
            observeEntry(
                OnNextOnErrorOnCompleteEntry(onNext, onError, onComplete, subscribe, life),
                key
            )
        }

    override fun observeOnNextOnErrorOnCompleteOnSubscribe(
        subscribe: (
            Consumer<T>,
            Consumer<Throwable>,
            Action,
            Consumer<Subscription>
        ) -> Disposable,
        life: ExtendedLife,
        key: String
    ): SavedStateRegistryOwner.(
        Consumer<T>,
        Consumer<Throwable>,
        Action,
        Consumer<Subscription>
    ) -> Unit =
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
                    subscribe,
                    life
                ),
                key
            )
        }

    override fun observeOnNextOnErrorOnCompleteOnDisposable(
        subscribe: (
            Consumer<T>,
            Consumer<Throwable>,
            Action,
            Consumer<Disposable>
        ) -> Disposable,
        life: ExtendedLife,
        key: String
    ): SavedStateRegistryOwner.(
        Consumer<T>,
        Consumer<Throwable>,
        Action,
        Consumer<Disposable>
    ) -> Unit =
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
                    subscribe,
                    life
                ),
                key
            )
        }

    private fun SavedStateRegistryOwner.observeEntry(entry: ExtendedEntry<T>, key: String) {
        handler.register(this, entry, LifeSpan.STARTED, key)
    }
}
