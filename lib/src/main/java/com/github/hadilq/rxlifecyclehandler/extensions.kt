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
import com.github.hadilq.androidlifecyclehandler.AndroidLifeHandlerImpl
import io.reactivex.*
import io.reactivex.disposables.Disposable
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observeOnNext(this)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNext())(::handleString)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observeOnNext(this)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNextOnError())(::handleString, ::handleError)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(this)

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
 *           ::handleString,
 *           ::handleError,
 *           ::handleComplete
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
    handler.observeOnNextOnErrorOnComplete(this)

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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable())

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNext())(::handleString)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable())

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNextOnError())(::handleString, ::handleError)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(toFlowable())

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
 *           ::handleString,
 *           ::handleError,
 *           ::handleComplete
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
    handler.observeOnNextOnErrorOnComplete(toFlowable())

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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit =
    handler.observeOnNext(toFlowable(BackpressureStrategy.DROP))

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNext())(::handleString)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit =
    handler.observeOnNext(toFlowable(BackpressureStrategy.DROP))

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNextOnError())(::handleString, ::handleError)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(toFlowable(BackpressureStrategy.DROP))

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
 *           ::handleString,
 *           ::handleError,
 *           ::handleComplete
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
    handler.observeOnNextOnErrorOnComplete(toFlowable(BackpressureStrategy.DROP))

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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable())

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observeOnNext())(::handleString)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable())

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observeOnNextOnError())(::handleString, ::handleError)
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
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(AndroidLifeHandlerImpl())
): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(toFlowable())

/***
 * To wrap up the [Subject] and hide it from the [LifecycleOwner], which is an
 * Activity or a Fragment.
 *
 * Example of use:
 * ```
 * class MyViewModel : ViewModel() {
 *
 *   private val publisher = PublishSubject.create<String>()
 *   val stringEmitter = publisher.toLifecycleAware()
 * }
 * ```
 *
 * The [handler] to help you with dependency inversion principle.
 */
fun <T : Any> Subject<T>.toLifeAware(
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(
        AndroidLifeHandlerImpl()
    )
): LifecycleAware<T> = SubjectLifecycleAwareImpl(this, handler)

/***
 * To wrap up the [FlowableProcessor] and hide it from the [LifecycleOwner], which is an
 * Activity or a Fragment.
 *
 * Example of use:
 * ```
 * class MyViewModel : ViewModel() {
 *
 *   private val publisher = PublishProcessor.create<String>()
 *   val stringEmitter = publisher.toLifecycleAware()
 * }
 * ```
 *
 * The [handler] to help you with dependency inversion principle.
 */
fun <T : Any> FlowableProcessor<T>.toLifeAware(
    handler: RxLifeHandler<T> = RxLifeHandlerImpl(
        AndroidLifeHandlerImpl()
    )
): LifecycleAware<T> = ProcessorLifecycleAwareImpl(this, handler)
