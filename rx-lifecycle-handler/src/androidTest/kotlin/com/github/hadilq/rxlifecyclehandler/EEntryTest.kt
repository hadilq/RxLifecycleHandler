package com.github.hadilq.rxlifecyclehandler

import com.github.hadilq.androidlifecyclehandler.ELife
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.Disposable
import org.junit.Test

class EEntryTest {

    @Test
    fun `in case of on born of EEntry, must subscribe`() {
        val life: ELife = mock()
        val sub: () -> Disposable = mock()
        val entry = EEntry(life, sub)

        entry.onBorn(null)

        verify(sub).invoke()
        verify(life).onBorn(null)
    }

    @Test
    fun `in case of on die of EEntry, must call on die`() {
        val life: ELife = mock()
        val sub: () -> Disposable = mock()
        val entry = EEntry(life, sub)

        entry.onDie()

        verify(life).onDie()
    }

    @Test
    fun `in case of on die of EEntry, must dispose`() {
        val life: ELife = mock()
        val sub: () -> Disposable = mock()
        val disposable: Disposable = mock()
        given(sub).willReturn(disposable)
        val entry = EEntry(life, sub)

        entry.onBorn(null)
        entry.onDie()

        verify(disposable).dispose()
    }
}
