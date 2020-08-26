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

import com.github.hadilq.androidlifecyclehandler.ELife
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class RxELifecycleHandlerSingleTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var observer: (String) -> Unit

    @Mock
    private lateinit var onNext: (String) -> Unit

    @Mock
    private lateinit var onError: (Throwable) -> Unit

    @Mock
    private lateinit var life: ELife

    private lateinit var publisher: PublishSubject<String>
    private lateinit var single: Single<String>
    private lateinit var owner: TestSavedStateRegistryOwner

    @Before
    fun setup() {
        owner = TestSavedStateRegistryOwner()
        publisher = PublishSubject.create<String>()
        single = publisher.single(ANY_STRING)
    }

    // region OBSERVE
    @Test
    fun `in case of just observe, single should not has observer`() {
        owner.(single.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start, single should has observer`() {
        owner.(single.observe(life))(observer)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then create then start then stop, single should not has observer`() {
        owner.(single.observe(life))(observer)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start then stop then start again, single should has observer`() {
        owner.(single.observe(life))(observer)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then create then start then destroy, single should not has observer`() {
        owner.(single.observe(life))(observer)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then create then start then destroy then start, which is impossible, single should not has observer`() {
        owner.(single.observe(life))(observer)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observe, single should not has observer`() {
        owner.destroy()

        owner.(single.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observe, single should has observer`() {
        owner.create()
        owner.start()

        owner.(single.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observe, single should not has observer`() {
        owner.start()
        owner.stop()

        owner.(single.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observe, single should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(single.observe(life))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE

    // region OBSERVE ON NEXT
    @Test
    fun `in case of just observeOnNext, single should not has observer`() {
        owner.(single.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start, single should has observer`() {
        owner.(single.observeOnNext(life))(onNext)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then create then start then stop, single should not has observer`() {
        owner.(single.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start then stop then start again, single should has observer`() {
        owner.(single.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNext then create then start then destroy, single should not has observer`() {
        owner.(single.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNext then create then start then destroy then start, which is impossible, single should not has observer`() {
        owner.(single.observeOnNext(life))(onNext)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNext, single should not has observer`() {
        owner.destroy()

        owner.(single.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNext, single should has observer`() {
        owner.create()
        owner.start()

        owner.(single.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNext, single should not has observer`() {
        owner.start()
        owner.stop()

        owner.(single.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNext, single should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(single.observeOnNext(life))(onNext)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT

    // region OBSERVE ON NEXT ON ERROR
    @Test
    fun `in case of just observeOnNextOnError, single should not has observer`() {
        owner.(single.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError the  create then start, single should has observer`() {
        owner.(single.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then stop, single should not has observer`() {
        owner.(single.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then stop then start again, single should has observer`() {
        owner.(single.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observeOnNextOnError then create then start then destroy, single should not has observer`() {
        owner.(single.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observeOnNextOnError the create then start then destroy then start, which is impossible, single should not has observer`() {
        owner.(single.observeOnNextOnError(life))(onNext, onError)

        owner.create()
        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observeOnNextOnError, single should not has observer`() {
        owner.destroy()

        owner.(single.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of create then start then observeOnNextOnError, single should has observer`() {
        owner.create()
        owner.start()

        owner.(single.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observeOnNextOnError, single should not has observer`() {
        owner.start()
        owner.stop()

        owner.(single.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observeOnNextOnError, single should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(single.observeOnNextOnError(life))(onNext, onError)

        assertThat(publisher.hasObservers(), `is`(false))
    }
    // end of region OBSERVE ON NEXT ON ERROR

    companion object {
        private const val ANY_STRING = "anyString"
    }
}
