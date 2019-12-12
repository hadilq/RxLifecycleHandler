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

import io.reactivex.Maybe
import io.reactivex.subjects.PublishSubject
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class RxLifecycleHandlerMaybeTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var observer: (String) -> Unit

    private lateinit var publisher: PublishSubject<String>
    private lateinit var maybe: Maybe<String>
    private lateinit var owner: TestLifecycleOwner
    private lateinit var handler: RxLifecycleHandlerMaybe<String>

    @Before
    fun setup() {
        owner = TestLifecycleOwner()
        publisher = PublishSubject.create<String>()
        handler = RxLifecycleHandlerMaybe()
        maybe = publisher.firstElement()
    }

    @Test
    fun `in case of just observe, maybe should not has observer`() {
        owner.(handler.observe(maybe::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then start, maybe should has observer`() {
        owner.(handler.observe(maybe::subscribe))(observer)

        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then start then stop, maybe should not has observer`() {
        owner.(handler.observe(maybe::subscribe))(observer)

        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then start then stop then start again, maybe should has observer`() {
        owner.(handler.observe(maybe::subscribe))(observer)

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then start then destroy, maybe should not has observer`() {
        owner.(handler.observe(maybe::subscribe))(observer)

        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then start then destroy then start, which is impossible, maybe should not has observer`() {
        owner.(handler.observe(maybe::subscribe))(observer)

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observe, maybe should not has observer`() {
        owner.destroy()

        owner.(handler.observe(maybe::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then observe, maybe should has observer`() {
        owner.start()

        owner.(handler.observe(maybe::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observe, maybe should not has observer`() {
        owner.start()
        owner.stop()

        owner.(handler.observe(maybe::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observe, maybe should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(handler.observe(maybe::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }
}
