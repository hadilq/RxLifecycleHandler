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

class RxLifecycleHandlerSingleTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var observer: (String) -> Unit

    private lateinit var publisher: PublishSubject<String>
    private lateinit var single: Single<String>
    private lateinit var owner: TestLifecycleOwner
    private lateinit var handler: RxLifecycleHandlerSingle<String>

    @Before
    fun setup() {
        owner = TestLifecycleOwner()
        publisher = PublishSubject.create<String>()
        handler = RxLifecycleHandlerSingle()
        single = publisher.single(ANY_STRING)
    }

    @Test
    fun `in case of just observe, single should not has observer`() {
        owner.(handler.observe(single::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then start, single should has observer`() {
        owner.(handler.observe(single::subscribe))(observer)

        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then start then stop, single should not has observer`() {
        owner.(handler.observe(single::subscribe))(observer)

        owner.start()
        owner.stop()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then start then stop then start again, single should has observer`() {
        owner.(handler.observe(single::subscribe))(observer)

        owner.start()
        owner.stop()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of observe then start then destroy, single should not has observer`() {
        owner.(handler.observe(single::subscribe))(observer)

        owner.start()
        owner.destroy()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of observe then start then destroy then start, which is impossible, single should not has observer`() {
        owner.(handler.observe(single::subscribe))(observer)

        owner.start()
        owner.destroy()
        owner.start()

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of destroy then observe, single should not has observer`() {
        owner.destroy()

        owner.(handler.observe(single::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then observe, single should has observer`() {
        owner.start()

        owner.(handler.observe(single::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(true))
    }

    @Test
    fun `in case of start then stop then observe, single should not has observer`() {
        owner.start()
        owner.stop()

        owner.(handler.observe(single::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    @Test
    fun `in case of start then destroy then observe, single should not has observer`() {
        owner.start()
        owner.destroy()

        owner.(handler.observe(single::subscribe))(observer)

        assertThat(publisher.hasObservers(), `is`(false))
    }

    companion object {
        private const val ANY_STRING = "anyString"
    }
}
