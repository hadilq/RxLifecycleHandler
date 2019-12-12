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

import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.reactivestreams.Subscription

class RxLifecycleHandlerFlowableTest {

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
    private lateinit var onSubscribe: Consumer<Subscription>

    private lateinit var publisher: PublishProcessor<String>
    private lateinit var owner: TestLifecycleOwner

    @Before
    fun setup() {
        owner = TestLifecycleOwner()
        publisher = PublishProcessor.create<String>()
    }

    // region OBSERVE
    @Test
    fun `in case of just observe, flowable should not has observer`() {
        owner.(publisher.observe())(observer)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observe then start, flowable should has observer`() {
        owner.(publisher.observe())(observer)

        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observe then start then stop, flowable should not has observer`() {
        owner.(publisher.observe())(observer)

        owner.start()
        owner.stop()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observe then start then stop then start again, flowable should has observer`() {
        owner.(publisher.observe())(observer)

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observe then start then destroy, flowable should not has observer`() {
        owner.(publisher.observe())(observer)

        owner.start()
        owner.destroy()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observe then start then destroy then start, which is impossible, flowable should not has observer`() {
        owner.(publisher.observe())(observer)

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observe, flowable should not has observer`() {
        owner.destroy()

        owner.(publisher.observe())(observer)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then observe, flowable should has observer`() {
        owner.start()

        owner.(publisher.observe())(observer)

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observe, flowable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observe())(observer)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observe, flowable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observe())(observer)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }
    // end of region OBSERVE

    // region OBSERVE ON NEXT
    @Test
    fun `in case of just observeOnNext, flowable should not has observer`() {
        owner.(publisher.observeOnNext())(onNext)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then start, flowable should has observer`() {
        owner.(publisher.observeOnNext())(onNext)

        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then start then stop, flowable should not has observer`() {
        owner.(publisher.observeOnNext())(onNext)

        owner.start()
        owner.stop()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then start then stop then start again, flowable should has observer`() {
        owner.(publisher.observeOnNext())(onNext)

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then start then destroy, flowable should not has observer`() {
        owner.(publisher.observeOnNext())(onNext)

        owner.start()
        owner.destroy()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then start then destroy then start, which is impossible, flowable should not has observer`() {
        owner.(publisher.observeOnNext())(onNext)

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNext, flowable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNext())(onNext)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then observeOnNext, flowable should has observer`() {
        owner.start()

        owner.(publisher.observeOnNext())(onNext)

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNext, flowable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNext())(onNext)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNext, flowable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNext())(onNext)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT

    // region OBSERVE ON NEXT ON ERROR
    @Test
    fun `in case of just observeOnNextOnError, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnError())(onNext, onError)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then start, flowable should has observer`() {
        owner.(publisher.observeOnNextOnError())(onNext, onError)

        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then start then stop, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnError())(onNext, onError)

        owner.start()
        owner.stop()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then start then stop then start again, flowable should has observer`() {
        owner.(publisher.observeOnNextOnError())(onNext, onError)

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then start then destroy, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnError())(onNext, onError)

        owner.start()
        owner.destroy()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then start then destroy then start, which is impossible, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnError())(onNext, onError)

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnError, flowable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNextOnError())(onNext, onError)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then observeOnNextOnError, flowable should has observer`() {
        owner.start()

        owner.(publisher.observeOnNextOnError())(onNext, onError)

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnError, flowable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNextOnError())(onNext, onError)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnError, flowable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNextOnError())(onNext, onError)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT ON ERROR

    // region OBSERVE ON NEXT ON ERROR ON COMPLETE
    @Test
    fun `in case of just observeOnNextOnErrorOnComplete, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then start, flowable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then start then stop, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()
        owner.stop()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then start then stop then start again, flowable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then start then destroy, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()
        owner.destroy()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnComplete then start then destroy then start, which is impossible, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnErrorOnComplete, flowable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then observeOnNextOnErrorOnComplete, flowable should has observer`() {
        owner.start()

        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnErrorOnComplete, flowable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnErrorOnComplete, flowable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        assertThat(publisher.hasSubscribers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT ON ERROR ON COMPLETE

    // region OBSERVE ON NEXT ON ERROR ON COMPLETE ON SUBSCRIBE
    @Test
    fun `in case of just observeOnNextOnErrorOnCompleteOnSubscribe, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then start, flowable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then start then stop, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnComplete())(onNext, onError, onComplete)

        owner.start()
        owner.stop()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then start then stop then start again, flowable should has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then start then destroy, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.start()
        owner.destroy()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnErrorOnCompleteOnSubscribe then start then destroy then start, which is impossible, flowable should not has observer`() {
        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnErrorOnCompleteOnSubscribe, flowable should not has observer`() {
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then observeOnNextOnErrorOnCompleteOnSubscribe, flowable should has observer`() {
        owner.start()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasSubscribers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnErrorOnCompleteOnSubscribe, flowable should not has observer`() {
        owner.start()
        owner.stop()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasSubscribers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnErrorOnCompleteOnSubscribe, flowable should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(publisher.observeOnNextOnErrorOnCompleteOnSubscribe())(
            onNext,
            onError,
            onComplete,
            onSubscribe
        )

        assertThat(publisher.hasSubscribers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT ON ERROR ON COMPLETE ON SUBSCRIBE
}
