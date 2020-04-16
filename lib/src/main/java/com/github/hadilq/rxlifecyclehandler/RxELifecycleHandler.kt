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

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.savedstate.SavedStateRegistryOwner
import com.github.hadilq.androidlifecyclehandler.ELife
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

/***
 * A class to handle lifecycle of subscription and unsubscription of a [Observable],
 * [Flowable], [Maybe] or a [Single]. Notice here we assume the [SavedStateRegistryOwner], which
 * is an [Activity] or a [Fragment] needs the emitted values of upstream after [ON_START] and
 * before [ON_STOP] or [Activity.onSaveInstanceState] or [Fragment.onSaveInstanceState] events.
 */
interface RxELifecycleHandler<T> {

    fun observe(
        subscribe: (Consumer<T>) -> Disposable,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.((T) -> Unit) -> Unit

    fun observeOnNext(
        subscribe: (Consumer<T>) -> Disposable,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.(Consumer<T>) -> Unit

    fun observeOnNextOnError(
        subscribe: (Consumer<T>, Consumer<Throwable>) -> Disposable,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.(Consumer<T>, Consumer<Throwable>) -> Unit

    fun observeOnNextOnErrorOnComplete(
        subscribe: (Consumer<T>, Consumer<Throwable>, Action) -> Disposable,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit

    fun observeOnNextOnErrorOnCompleteOnSubscribe(
        subscribe: (
            Consumer<T>,
            Consumer<Throwable>,
            Action,
            Consumer<Subscription>
        ) -> Disposable,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.(
        Consumer<T>,
        Consumer<Throwable>,
        Action,
        Consumer<Subscription>
    ) -> Unit

    fun observeOnNextOnErrorOnCompleteOnDisposable(
        subscribe: (
            Consumer<T>,
            Consumer<Throwable>,
            Action,
            Consumer<Disposable>
        ) -> Disposable,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.(
        Consumer<T>,
        Consumer<Throwable>,
        Action,
        Consumer<Disposable>
    ) -> Unit
}
