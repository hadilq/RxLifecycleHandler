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

import com.github.hadilq.androidlifecyclehandler.ExtendedLife
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class RxExtendedLifecycleHandlerObservableTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var observer: (String) -> Unit

    @Mock
    private lateinit var onNext: Consumer<String>

    @Mock
    private lateinit var onError: Consumer<Throwable>

    @Mock
    private lateinit var onComplete: Action

    @Mock
    private lateinit var onSubscribe: Consumer<Disposable>

    @Mock
    private lateinit var life: ExtendedLife

    private lateinit var publisher: PublishSubject<String>
    private lateinit var owner: TestSavedStateRegistryOwner

    @Before
    fun setup() {
        owner = TestSavedStateRegistryOwner()
        publisher = PublishSubject.create<String>()
    }

    // region OBSERVE
    @Test
    fun `in case of just observe, observable should not has observer`() {
        owner.(publisher.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start, observable should has observer`() {
        owner.(publisher.observe(life))(observer)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then create then start then stop, observable should not has observer`() {
        owner.(publisher.observe(life))(observer)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start then stop then start again, observable should has observer`() {
        owner.(publisher.observe(life))(observer)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then create then start then destroy, observable should not has observer`() {
        owner.(publisher.observe(life))(observer)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start then destroy then start, which is impossible, observable should not has observer`() {
        owner.(publisher.observe(life))(observer)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observe, observable should not has observer`() {
        owner.destroy()

        owner.(publisher.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observe, observable should has observer`() {
        owner.create()
        owner.start()

        owner.(publisher.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observe, observable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observe, observable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // region end of OBSERVE

    // region OBSERVE ON NEXT
    @Test
    fun `in case of just observeOnNext, observable should not has observer`() {
        owner.(publisher.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start, observable should has observer`() {
        owner.(publisher.observeOnNext(life))(onNext)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then create then start then stop, observable should not has observer`() {
        owner.(publisher.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start then stop then start again, observable should has observer`() {
        owner.(publisher.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then create then start then destroy, observable should not has observer`() {
        owner.(publisher.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start then destroy then start, which is impossible, observable should not has observer`() {
        owner.(publisher.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNext, observable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNext, observable should has observer`() {
        owner.create()
        owner.start()

        owner.(publisher.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNext, observable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNext, observable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // region end of OBSERVE ON NEXT

    // region OBSERVE ON NEXT ON ERROR
    @Test
    fun `in case of just observeOnNextOnError, observable should not has observer`() {
        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start, observable should has observer`() {
        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then stop, observable should not has observer`() {
        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then stop then start again, observable should has observer`() {
        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then destroy, observable should not has observer`() {
        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then destroy then start, which is impossible, observable should not has observer`() {
        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnError, observable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNextOnError, observable should has observer`() {
        owner.create()
        owner.start()

        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnError, observable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnError, observable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // region end of OBSERVE ON NEXT ON ERROR

    // region OBSERVE ON NEXT ON ERROR ON COMPLETE
    @Test
    fun `in case of just observeOnNextOnErrorOnComplete, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start, observable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then stop, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then stop then start again, observable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then destroy, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then destroy then start, which is impossible, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnErrorOnComplete, observable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNextOnErrorOnComplete, observable should has observer`() {
        owner.create()
        owner.start()

        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnErrorOnComplete, observable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnErrorOnComplete, observable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // region end of OBSERVE ON NEXT ON ERROR ON COMPLETE

    // region OBSERVE ON NEXT ON ERROR ON COMPLETE ON SUBSCRIBE
    @Test
    fun `in case of just observeOnNextOnErrorOnCompleteOnSubscribe, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then create then start, observable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then create then start then stop, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then create then start then stop then start again, observable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then create then start then destroy, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then create then start then destroy then start, which is impossible, observable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnErrorOnCompleteOnSubscribe, observable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNextOnErrorOnCompleteOnSubscribe, observable should has observer`() {
        owner.create()
        owner.start()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnErrorOnCompleteOnSubscribe, observable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnErrorOnCompleteOnSubscribe, observable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe(life))(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // region end of OBSERVE ON NEXT ON ERROR ON COMPLETE ON SUBSCRIBE
}
