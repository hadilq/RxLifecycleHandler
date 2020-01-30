package com.github.hadilq.rxlifecyclehandler

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.processors.BehaviorProcessor
import org.junit.Test

class ProcessorLifecycleAwareImplTest {

    @Test
    fun `in case of processor, calling observe would call handler observe`() {
        val publisher = BehaviorProcessor.create<String>()
        val handler = mock<RxLifecycleHandler<String>>()
        val lifecycleAware = publisher.toLifecycleAware(handler)

        lifecycleAware.observe()

        verify(handler).observe(any())
    }
}
