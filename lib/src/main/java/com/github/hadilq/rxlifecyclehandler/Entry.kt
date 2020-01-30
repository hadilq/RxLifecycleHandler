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

import com.github.hadilq.androidlifecyclehandler.Life
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

sealed class Entry<T> : Life {

    protected var disposable: Disposable? = null

    override fun onDie() {
        disposable?.dispose()
        disposable = null
    }

    class ObserveEntry<T>(val fn: (T) -> Unit, val subscribe: (Consumer<T>) -> Disposable) :
        Entry<T>() {

        override fun onBorn() {
            disposable = subscribe(Consumer { fn(it) })
        }
    }

    class OnNextEntry<T>(
        private val onNext: Consumer<T>,
        val subscribe: (Consumer<T>) -> Disposable
    ) :
        Entry<T>() {

        override fun onBorn() {
            disposable = subscribe(onNext)
        }
    }

    class OnNextOnErrorEntry<T>(
        private val onNext: Consumer<T>,
        private val onError: Consumer<Throwable>,
        val subscribe: (Consumer<T>, Consumer<Throwable>) -> Disposable
    ) : Entry<T>() {

        override fun onBorn() {
            disposable = subscribe(onNext, onError)
        }
    }

    class OnNextOnErrorOnCompleteEntry<T>(
        private val onNext: Consumer<T>,
        private val onError: Consumer<Throwable>,
        private val onComplete: Action,
        val subscribe: (Consumer<T>, Consumer<Throwable>, Action) -> Disposable
    ) : Entry<T>() {

        override fun onBorn() {
            disposable = subscribe(onNext, onError, onComplete)
        }
    }

    class OnNextOnErrorOnCompleteOnSubscribeEntry<T>(
        private val onNext: Consumer<T>,
        private val onError: Consumer<Throwable>,
        private val onComplete: Action,
        private val onSubscribe: Consumer<Subscription>,
        val subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Disposable
    ) : Entry<T>() {

        override fun onBorn() {
            disposable = subscribe(onNext, onError, onComplete, onSubscribe)
        }
    }

    class OnNextOnErrorOnCompleteOnDisposableEntry<T>(
        private val onNext: Consumer<T>,
        private val onError: Consumer<Throwable>,
        private val onComplete: Action,
        private val onSubscribe: Consumer<Disposable>,
        val subscribe: (Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Disposable
    ) : Entry<T>() {

        override fun onBorn() {
            disposable = subscribe(onNext, onError, onComplete, onSubscribe)
        }
    }
}
