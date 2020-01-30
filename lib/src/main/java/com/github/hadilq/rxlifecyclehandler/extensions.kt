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
import com.github.hadilq.androidlifecyclehandler.AndroidLifecycleHandlerImpl
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.FlowableProcessor
import io.reactivex.subjects.Subject
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
 * The [Flowable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observe(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observe(this::subscribe)

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
 * The [Flowable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNext(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>) -> Unit = handler.observeOnNext(this::subscribe)

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
 * The [Flowable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNextOnError(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    handler.observeOnNextOnError(this::subscribe)

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
 * The [Flowable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNextOnErrorOnComplete(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    handler.observeOnNextOnErrorOnComplete(this::subscribe)

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
 * The [Flowable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNextOnErrorOnCompleteOnSubscribe(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Unit =
    handler.observeOnNextOnErrorOnCompleteOnSubscribe(this::subscribe)

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
 * The [Maybe] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observe(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observe(this::subscribe)

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
 * The [Maybe] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observeOnNext(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>) -> Unit = handler.observeOnNext(this::subscribe)

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
 * The [Maybe] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observeOnNextOnError(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    handler.observeOnNextOnError(this::subscribe)

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
 * The [Maybe] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observeOnNextOnErrorOnComplete(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    handler.observeOnNextOnErrorOnComplete(this::subscribe)

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
 * The [Observable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observe(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observe(this::subscribe)

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
 * The [Observable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNext(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>) -> Unit = handler.observeOnNext(this::subscribe)

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
 * The [Observable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNextOnError(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    handler.observeOnNextOnError(this::subscribe)

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
 * The [Observable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNextOnErrorOnComplete(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit =
    handler.observeOnNextOnErrorOnComplete(this::subscribe)

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
 * The [Observable] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNextOnErrorOnCompleteOnSubscribe(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Unit =
    handler.observeOnNextOnErrorOnCompleteOnDisposable(this::subscribe)

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
 * The [Single] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Single<T>.observe(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observe(this::subscribe)

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
 * The [Single] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Single<T>.observeOnNext(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>) -> Unit = handler.observeOnNext(this::subscribe)

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
 * The [Single] is an upstream.
 * The [handler] is to help you with dependency inversion principle.
 * The [LifecycleOwner] is Activity or Fragment
 */
fun <T> Single<T>.observeOnNextOnError(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(AndroidLifecycleHandlerImpl())
): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit =
    handler.observeOnNextOnError(this::subscribe)

/***
 * To wrap up the [Subject] and hide it from the [LifecycleOwner], which is an
 * Activity or a Fragment.
 *
 * The [handler] to help you with dependency inversion principle.
 */
fun <T : Any> Subject<T>.toLifecycleAware(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(
        AndroidLifecycleHandlerImpl()
    )
): LifecycleAware<T> = SubjectLifecycleAwareImpl(this, handler)

/***
 * To wrap up the [FlowableProcessor] and hide it from the [LifecycleOwner], which is an
 * Activity or a Fragment.
 *
 * The [handler] to help you with dependency inversion principle.
 */
fun <T : Any> FlowableProcessor<T>.toLifecycleAware(
    handler: RxLifecycleHandler<T> = RxLifecycleHandlerImpl(
        AndroidLifecycleHandlerImpl()
    )
): LifecycleAware<T> = ProcessorLifecycleAwareImpl(this, handler)
