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

import androidx.savedstate.SavedStateRegistryOwner
import com.github.hadilq.androidlifecyclehandler.AndroidELifeHandlerImpl
import com.github.hadilq.androidlifecyclehandler.ELife
import io.reactivex.*
import io.reactivex.processors.FlowableProcessor
import io.reactivex.subjects.Subject

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observe(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Flowable] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observe(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit = handler.observeOnNext(this, life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNext(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Flowable] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNext(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit = handler.observeOnNext(this, life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNextOnError(extendedLife, KEY))(::handleString, ::handleError)
 *   }
 * }
 *
 * ```
 *
 * The [Flowable] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNextOnError(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(this, life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (flowable.observeOnNextOnErrorOnComplete(extendedLife, KEY))(
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
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Flowable<T>.observeOnNextOnErrorOnComplete(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
    handler.observeOnNextOnErrorOnComplete(this, life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observe(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Maybe] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observe(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable(), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNext(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Maybe] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observeOnNext(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable(), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNextOnError(extendedLife, KEY))(::handleString, ::handleError)
 *   }
 * }
 *
 * ```
 *
 * The [Maybe] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observeOnNextOnError(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(toFlowable(), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (maybe.observeOnNextOnErrorOnComplete(extendedLife, KEY))(
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
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Maybe<T>.observeOnNextOnErrorOnComplete(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
    handler.observeOnNextOnErrorOnComplete(toFlowable(), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observe(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Observable] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observe(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit =
    handler.observeOnNext(toFlowable(BackpressureStrategy.DROP), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNext(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Observable] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNext(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit =
    handler.observeOnNext(toFlowable(BackpressureStrategy.DROP), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNextOnError(extendedLife, KEY))(::handleString, ::handleError)
 *   }
 * }
 *
 * ```
 *
 * The [Observable] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNextOnError(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(toFlowable(BackpressureStrategy.DROP), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (observable.observeOnNextOnErrorOnComplete(extendedLife, KEY))(
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
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Observable<T>.observeOnNextOnErrorOnComplete(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit =
    handler.observeOnNextOnErrorOnComplete(toFlowable(BackpressureStrategy.DROP), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observe(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Single] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Single<T>.observe(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable(), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observeOnNext(extendedLife, KEY))(::handleString)
 *   }
 * }
 *
 * ```
 *
 * The [Single] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Single<T>.observeOnNext(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit) -> Unit = handler.observeOnNext(toFlowable(), life, key)

/***
 * Creates a handler to sync the subscription.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity : ComponentActivity {
 *
 *   override fun onCreate(savedInstanceState: Bundle?) {
 *
 *       (single.observeOnNextOnError(extendedLife, KEY))(::handleString, ::handleError)
 *   }
 * }
 *
 * ```
 *
 * The [Single] is an upstream.
 * The [life] is for handling the bundle in [ELife].
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] is to help you with dependency inversion principle.
 * The [SavedStateRegistryOwner] is Activity or Fragment
 */
fun <T> Single<T>.observeOnNextOnError(
    life: ELife,
    key: String = "",
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): SavedStateRegistryOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit =
    handler.observeOnNextOnError(toFlowable(), life, key)

/***
 * To wrap up the [Subject] and hide it from the [SavedStateRegistryOwner], which is an
 * Activity or a Fragment.
 *
 * Example of use:
 * ```
 * class MyViewModel : ViewModel() {
 *
 *   private val extendedPublisher = BehaviorSubject.create<String>().apply { onNext("Test") }
 *   val extendedStringEmitter = extendedPublisher.toExtendedLifecycleAware(KEY)
 *
 *   companion object {
 *       private const val KEY = "key_to_save_string_emitter"
 *   }
 * }
 * ```
 *
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] to help you with dependency inversion principle.
 */
inline fun <reified T : Any> Subject<T>.toExtendedLifecycleAware(
    key: String,
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): ExtendedLifecycleAware<T> = SubjectExtendedLifecycleAwareImpl(this, handler, key, T::class)

/***
 * To wrap up the [FlowableProcessor] and hide it from the [SavedStateRegistryOwner], which is an
 * Activity or a Fragment.
 *
 * Example of use:
 * ```
 * class MyViewModel : ViewModel() {
 *
 *   private val extendedPublisher = BehaviorProcessor.create<String>().apply { onNext("Test") }
 *   val extendedStringEmitter = extendedPublisher.toExtendedLifecycleAware(KEY)
 *
 *   companion object {
 *       private const val KEY = "key_to_save_string_emitter"
 *   }
 * }
 * ```
 *
 * The [key] is the key which returned saved state will be associated with.
 * The [handler] to help you with dependency inversion principle.
 */
inline fun <reified T : Any> FlowableProcessor<T>.toELifeAware(
    key: String,
    handler: RxELifecycleHandler<T> = RxELifeHandlerImpl(
        AndroidELifeHandlerImpl()
    )
): ExtendedLifecycleAware<T> = ProcessorExtendedLifecycleAwareImpl(this, handler, key, T::class)
