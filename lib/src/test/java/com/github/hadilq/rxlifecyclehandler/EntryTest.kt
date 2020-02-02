package com.github.hadilq.rxlifecyclehandler

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.junit.Test
import org.reactivestreams.Subscription

class EntryTest {

    @Test
    fun `in case of on born of ObserveEntry, must subscribe`() {
        val fn: (String) -> Unit = mock()
        val sub: (Consumer<String>) -> Disposable = mock()
        val entry = Entry.ObserveEntry(fn, sub)

        entry.onBorn()

        verify(sub).invoke(any())
    }

    @Test
    fun `in case of on born of OnNextEntry, must subscribe`() {
        val onNext: Consumer<String> = mock()
        val sub: (Consumer<String>) -> Disposable = mock()
        val entry = Entry.OnNextEntry(onNext, sub)

        entry.onBorn()

        verify(sub).invoke(any())
    }

    @Test
    fun `in case of on born of OnNextOnErrorEntry, must subscribe`() {
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val sub: (Consumer<String>, Consumer<Throwable>) -> Disposable = mock()
        val entry = Entry.OnNextOnErrorEntry(onNext, onError, sub)

        entry.onBorn()

        verify(sub).invoke(any(), any())
    }

    @Test
    fun `in case of on born of OnNextOnErrorOnCompleteEntry, must subscribe`() {
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val onComplete: Action = mock()
        val sub: (Consumer<String>, Consumer<Throwable>, Action) -> Disposable = mock()
        val entry = Entry.OnNextOnErrorOnCompleteEntry(onNext, onError, onComplete, sub)

        entry.onBorn()

        verify(sub).invoke(any(), any(), any())
    }

    @Test
    fun `in case of on born of OnNextOnErrorOnCompleteOnSubscribeEntry, must subscribe`() {
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val onComplete: Action = mock()
        val onSubscribe: Consumer<Subscription> = mock()
        val sub: (Consumer<String>, Consumer<Throwable>, Action, Consumer<Subscription>) -> Disposable =
            mock()
        val entry = Entry.OnNextOnErrorOnCompleteOnSubscribeEntry(
            onNext,
            onError,
            onComplete,
            onSubscribe,
            sub
        )

        entry.onBorn()

        verify(sub).invoke(any(), any(), any(), any())
    }

    @Test
    fun `in case of on born of OnNextOnErrorOnCompleteOnDisposableEntry, must subscribe`() {
        val onNext: Consumer<String> = mock()
        val onError: Consumer<Throwable> = mock()
        val onComplete: Action = mock()
        val onSubscribe: Consumer<Disposable> = mock()
        val sub: (Consumer<String>, Consumer<Throwable>, Action, Consumer<Disposable>) -> Disposable =
            mock()
        val entry = Entry.OnNextOnErrorOnCompleteOnDisposableEntry(
            onNext,
            onError,
            onComplete,
            onSubscribe,
            sub
        )

        entry.onBorn()

        verify(sub).invoke(any(), any(), any(), any())
    }
}
