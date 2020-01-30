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
import io.reactivex.Maybe
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

class RxExtendedLifecycleHandlerMaybeTest {

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
    private lateinit var life: ExtendedLife

    private lateinit var publisher: PublishSubject<String>
    private lateinit var maybe: Maybe<String>
    private lateinit var owner: TestSavedStateRegistryOwner

    @Before
    fun setup() {
        owner = TestSavedStateRegistryOwner()
        publisher = PublishSubject.create<String>()
        maybe = publisher.firstElement()
    }

    // region OBSERVE
    @Test
    fun `in case of just observe, maybe should not has observer`() {
        owner.(maybe.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start, maybe should has observer`() {
        owner.(maybe.observe(life))(observer)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then create then start then stop, maybe should not has observer`() {
        owner.(maybe.observe(life))(observer)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start then stop then start again, maybe should has observer`() {
        owner.(maybe.observe(life))(observer)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then create then start then destroy, maybe should not has observer`() {
        owner.(maybe.observe(life))(observer)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start then destroy then start, which is impossible, maybe should not has observer`() {
        owner.(maybe.observe(life))(observer)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observe, maybe should not has observer`() {
        owner.destroy()

        owner.(maybe.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observe, maybe should has observer`() {
        owner.create()
        owner.start()

        owner.(maybe.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observe, maybe should not has observer`() {
        owner.start()
        owner.stop()

        owner.(maybe.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observe, maybe should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(maybe.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE

    // region OBSERVE ON NEXT
    @Test
    fun `in case of just observeOnNext, maybe should not has observer`() {
        owner.(maybe.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start, maybe should has observer`() {
        owner.(maybe.observeOnNext(life))(onNext)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then create then start then stop, maybe should not has observer`() {
        owner.(maybe.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start then stop then start again, maybe should has observer`() {
        owner.(maybe.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then create then start then destroy, maybe should not has observer`() {
        owner.(maybe.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start then destroy then start, which is impossible, maybe should not has observer`() {
        owner.(maybe.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNext, maybe should not has observer`() {
        owner.destroy()

        owner.(maybe.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNext, maybe should has observer`() {
        owner.create()
        owner.start()

        owner.(maybe.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNext, maybe should not has observer`() {
        owner.start()
        owner.stop()

        owner.(maybe.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNext, maybe should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(maybe.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT

    // region OBSERVE ON NEXT ON ERROR
    @Test
    fun `in case of just observeOnNextOnError, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start, maybe should has observer`() {
        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then stop, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then creat then start then stop then start again, maybe should has observer`() {
        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then creat then start then destroy, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then destroy then start, which is impossible, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnError, maybe should not has observer`() {
        owner.destroy()

        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNextOnError, maybe should has observer`() {
        owner.create()
        owner.start()

        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnError, maybe should not has observer`() {
        owner.start()
        owner.stop()

        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnError, maybe should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(maybe.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT ON ERROR

    // region OBSERVE ON NEXT ON COMPLETE
    @Test
    fun `in case of just observeOnNextOnErrorOnComplete, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start, maybe should has observer`() {
        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then stop, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then stop then start again, maybe should has observer`() {
        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then destroy, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then create then start then destroy then start, which is impossible, maybe should not has observer`() {
        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnErrorOnComplete, maybe should not has observer`() {
        owner.destroy()

        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNextOnErrorOnComplete, maybe should has observer`() {
        owner.create()
        owner.start()

        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnErrorOnComplete, maybe should not has observer`() {
        owner.start()
        owner.stop()

        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnErrorOnComplete, maybe should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(maybe.observeOnNextOnErrorOnComplete(life))(onNext, onError, onComplete)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT ON COMPLETE

}
