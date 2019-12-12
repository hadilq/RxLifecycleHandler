/***
 * Copyright 2019 Hadi Lashkari Ghouchani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * */

package com.github.hadilq.rxlifecyclehandler

import androidx.lifecycle.LifecycleOwner
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

fun <T> Flowable<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerFlowable<T>().observe(this::subscribe)

fun <T> Flowable<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNext(this::subscribe)

fun <T> Flowable<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNextOnError(this::subscribe)

fun <T> Flowable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNextOnErrorOnComplete(this::subscribe)

fun <T> Flowable<T>.observeOnNextOnErrorOnCompleteOnSubscribe(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNextOnErrorOnCompleteOnSubscribe(this::subscribe)

fun <T> Maybe<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerMaybe<T>().observe(this::subscribe)

fun <T> Maybe<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerMaybe<T>().observeOnNext(this::subscribe)

fun <T> Maybe<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerMaybe<T>().observeOnNextOnError(this::subscribe)

fun <T> Maybe<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    RxLifecycleHandlerMaybe<T>().observeOnNextOnErrorOnComplete(this::subscribe)

fun <T> Observable<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerObservable<T>().observe(this::subscribe)

fun <T> Observable<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNext(this::subscribe)

fun <T> Observable<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNextOnError(this::subscribe)

fun <T> Observable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNextOnErrorOnComplete(this::subscribe)

fun <T> Observable<T>.observeOnNextOnErrorOnCompleteOnSubscribe(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNextOnErrorOnCompleteOnDisposable(this::subscribe)

fun <T> Single<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerSingle<T>().observe(this::subscribe)

fun <T> Single<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerSingle<T>().observeOnNext(this::subscribe)

fun <T> Single<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerSingle<T>().observeOnNextOnError(this::subscribe)
