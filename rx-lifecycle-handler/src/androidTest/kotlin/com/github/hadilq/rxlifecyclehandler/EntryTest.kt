package com.github.hadilq.rxlifecyclehandler

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.Disposable
import org.junit.Test

class EntryTest {

    @Test
    fun `in case of on born of Entry, must subscribe`() {
        val sub: () -> Disposable = mock()
        val entry = Entry(sub)

        entry.onBorn()

        verify(sub).invoke()
    }

    @Test
    fun `in case of on die of Entry, must dispose`() {
        val sub: () -> Disposable = mock()
        val disposable: Disposable = mock()
        given(sub).willReturn(disposable)
        val entry = Entry(sub)

        entry.onBorn()
        entry.onDie()

        verify(disposable).dispose()
    }

}
