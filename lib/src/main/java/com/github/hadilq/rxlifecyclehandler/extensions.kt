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

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observe())(::handleString)
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Flowable<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerFlowable<T>().observe(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNext())(Consumer(::handleString))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Flowable<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNext(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNextOnError())(Consumer(::handleString), Consumer(::handleError))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Flowable<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNextOnError(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNextOnErrorOnComplete())(
 *           Consumer(::handleString),
 *           Consumer(::handleError),
 *           Action(::handleComplete)
 *       )
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Flowable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNextOnErrorOnComplete(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNextOnErrorOnCompleteOnSubscribe())(
 *           Consumer(::handleString),
 *           Consumer(::handleError),
 *           Action(::handleComplete),
 *           Consumer(::handleSubscription)
 *       )
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Flowable<T>.observeOnNextOnErrorOnCompleteOnSubscribe(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Unit =
    RxLifecycleHandlerFlowable<T>().observeOnNextOnErrorOnCompleteOnSubscribe(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observe())(::handleString)
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Maybe<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerMaybe<T>().observe(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNext())(Consumer(::handleString))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Maybe<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerMaybe<T>().observeOnNext(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNextOnError())(Consumer(::handleString), Consumer(::handleError))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Maybe<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerMaybe<T>().observeOnNextOnError(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNextOnErrorOnComplete())(
 *           Consumer(::handleString),
 *           Consumer(::handleError),
 *           Action(::handleComplete)
 *       )
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Maybe<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    RxLifecycleHandlerMaybe<T>().observeOnNextOnErrorOnComplete(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observe())(::handleString)
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Observable<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerObservable<T>().observe(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNext())(Consumer(::handleString))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Observable<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNext(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNextOnError())(Consumer(::handleString), Consumer(::handleError))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Observable<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNextOnError(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNextOnErrorOnComplete())(
 *           Consumer(::handleString),
 *           Consumer(::handleError),
 *           Action(::handleComplete)
 *       )
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Observable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNextOnErrorOnComplete(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNextOnErrorOnCompleteOnSubscribe())(
 *           Consumer(::handleString),
 *           Consumer(::handleError),
 *           Action(::handleComplete),
 *           Consumer(::handleSubscription)
 *       )
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Observable<T>.observeOnNextOnErrorOnCompleteOnSubscribe(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Unit =
    RxLifecycleHandlerObservable<T>().observeOnNextOnErrorOnCompleteOnDisposable(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observe())(::handleString)
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Single<T>.observe(): LifecycleOwner.((T) -> Unit) -> Unit =
    RxLifecycleHandlerSingle<T>().observe(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observeOnNext())(Consumer(::handleString))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Single<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit =
    RxLifecycleHandlerSingle<T>().observeOnNext(this::subscribe)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observeOnNextOnError())(Consumer(::handleString), Consumer(::handleError))
 *   }
 * }
 *
 * ```
 *
 */
fun <T> Single<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    RxLifecycleHandlerSingle<T>().observeOnNextOnError(this::subscribe)
