package com.github.hadilq.rxlifecyclehandler

import com.github.hadilq.androidlifecyclehandler.ELife
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.junit.Test
import org.reactivestreams.Subscription

class ExtendedEntryTest {

    @Test
    fun `in case of on born of ObserveEntry, must subscribe`() {
        val life: ELife = mock()
        val fn: (String) -> Unit = mock()
        val sub: (Consumer<String>) -> Disposable = mock()
        val entry = ExtendedEntry.ObserveEntry(fn, sub, life)

        entry.onBorn(null)

        verify(sub).invoke(any())
        verify(life).onBorn(null)
    }

    @Test
    fun `in case of on born of OnNextEntry, must subscribe`() {
        val life: ELife = mock()
        val onNext: Consumer<String> = mock()
        val sub: (Consumer<String>) -> Disposable = mock()
        val entry = ExtendedEntry.OnNextEntry(onNext, sub, life)

        entry.onBorn(null)

        verify(sub).invoke(any())
        verify(life).onBorn(null)
    }

    @Test
    fun `in case of on born of OnNextOnErrorEntry, must subscribe`() {
        val life: ELife = mock()
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val sub: (Consumer<String>, Consumer<Throwable>) -> Disposable = mock()
        val entry = ExtendedEntry.OnNextOnErrorEntry(onNext, onError, sub, life)

        entry.onBorn(null)

        verify(sub).invoke(any(), any())
        verify(life).onBorn(null)
    }

    @Test
    fun `in case of on born of OnNextOnErrorOnCompleteEntry, must subscribe`() {
        val life: ELife = mock()
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val onComplete: Action = mock()
        val sub: (Consumer<String>, Consumer<Throwable>, Action) -> Disposable = mock()
        val entry =
            ExtendedEntry.OnNextOnErrorOnCompleteEntry(onNext, onError, onComplete, sub, life)

        entry.onBorn(null)

        verify(sub).invoke(any(), any(), any())
        verify(life).onBorn(null)
    }

    @Test
    fun `in case of on born of OnNextOnErrorOnCompleteOnSubscribeEntry, must subscribe`() {
        val life: ELife = mock()
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val onComplete: Action = mock()
        val onSubscribe: Consumer<Subscription> = mock()
        val sub: (Consumer<String>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Disposable =
            mock()
        val entry = ExtendedEntry.OnNextOnErrorOnCompleteOnSubscribeEntry(
            onNext,
            onError,
            onComplete,
            onSubscribe,
            sub,
            life
        )

        entry.onBorn(null)

        verify(sub).invoke(any(), any(), any(), any())
        verify(life).onBorn(null)
    }

    @Test
    fun `in case of on born of OnNextOnErrorOnCompleteOnDisposableEntry, must subscribe`() {
        val life: ELife = mock()
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val onComplete: Action = mock()
        val onSubscribe: Consumer<Disposable> = mock()
        val sub: (Consumer<String>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Disposable =
            mock()
        val entry = ExtendedEntry.OnNextOnErrorOnCompleteOnDisposableEntry(
            onNext,
            onError,
            onComplete,
            onSubscribe,
            sub,
            life
        )

        entry.onBorn(null)

        verify(sub).invoke(any(), any(), any(), any())
        verify(life).onBorn(null)
    }
}
