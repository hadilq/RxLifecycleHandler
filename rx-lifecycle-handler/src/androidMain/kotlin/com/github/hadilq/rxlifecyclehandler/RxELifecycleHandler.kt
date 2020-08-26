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

/***
 * A class to handle lifecycle of subscription and unsubscription of a [Observable],
 * [Flowable], [Maybe] or a [Single]. Notice here we assume the [SavedStateRegistryOwner], which
 * is an [Activity] or a [Fragment] needs the emitted values of upstream after [ON_START] and
 * before [ON_STOP] or [Activity.onSaveInstanceState] or [Fragment.onSaveInstanceState] events.
 */
interface RxELifecycleHandler<T> {

    fun observeOnNext(
        flowable: Flowable<T>,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.((T) -> Unit) -> Unit

    fun observeOnNextOnError(
        flowable: Flowable<T>,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit

    fun observeOnNextOnErrorOnComplete(
        flowable: Flowable<T>,
        life: ELife,
        key: String = ""
    ): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit
}
