package com.github.hadilq.rxlifecyclehandler

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.subjects.BehaviorSubject
import org.junit.Test

class SubjectLifecycleAwareImplTest {

    @Test
    fun `in case of subject, calling observe would call handler observe`() {
        val publisher = BehaviorSubject.create<String>()
        val handler = mock<RxLifeHandler<String>>()
        val lifecycleAware = publisher.toLifecycleAware(handler)

        lifecycleAware.observe()

        verify(handler).observe(any())
    }
}
