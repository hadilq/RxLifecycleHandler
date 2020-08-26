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
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

class Entry(val subscribe: () -> Disposable) : Life {

    private var disposable: Disposable? = null

    override fun onBorn() {
        disposable = subscribe()
    }

    override fun onDie() {
        disposable?.dispose()
        disposable = null
    }
}

fun <T> Flowable<T>.toLife() = Entry() { subscribe() }
