package com.github.hadilq.rxlifecyclehandler

import android.os.Bundle
import android.os.Parcelable
import com.nhaarman.mockito_kotlin.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.BehaviorProcessor
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProcessorExtendedLifecycleAwareImplTest {

    @Test
    fun `In case of born of Bundle LifecycleAware, load`() {
        `test loading`<Bundle> { putBundle(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Bundle LifecycleAware, save the cache`() {
        `test saving` { getBundle(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of Parcelable LifecycleAware, load`() {
        `test loading`<Parcelable> { putParcelable(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Parcelable LifecycleAware, save the cache`() {
        `test saving` { getParcelable<Parcelable>(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of String LifecycleAware, load`() {
        `test loading`("A") { putString(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of String LifecycleAware, save the cache`() {
        `test saving`("A") { getString(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of ArrayList String LifecycleAware, load`() {
        `test loading`<ArrayList<String>> { putStringArrayList(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of ArrayList String LifecycleAware, save the cache`() {
        `test saving` { getStringArrayList(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of CharSequence LifecycleAware, load`() {
        `test loading`<CharSequence> { putCharSequence(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of CharSequence LifecycleAware, save the cache`() {
        `test saving` { getCharSequence(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of Byte LifecycleAware, load`() {
        `test loading`<Byte>(2, true) { putByte(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Byte LifecycleAware, save the cache`() {
        `test saving`(2, true) { getByte(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Short LifecycleAware, load`() {
        `test loading`<Short>(1, true) { putShort(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Short LifecycleAware, save the cache`() {
        `test saving`(1, true) { getShort(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Char LifecycleAware, load`() {
        `test loading`('a', true) { putChar(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Char LifecycleAware, save the cache`() {
        `test saving`('a', true) { getChar(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Int LifecycleAware, load`() {
        `test loading`(1, true) { putInt(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Int LifecycleAware, save the cache`() {
        `test saving`(1, true) { getInt(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Long LifecycleAware, load`() {
        `test loading`(1L, true) { putLong(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Long LifecycleAware, save the cache`() {
        `test saving`(1L, true) { getLong(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Float LifecycleAware, load`() {
        `test loading`(0.1f, true) { putFloat(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Float LifecycleAware, save the cache`() {
        `test saving`(0.2f, true) { getFloat(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Double LifecycleAware, load`() {
        `test loading`(0.2, true) { putDouble(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of Double LifecycleAware, save the cache`() {
        `test saving`(0.1, true) { getDouble(AbsExtendedLifecycleAware.KEY) }
    }

    @Test
    fun `In case of born of Boolean LifecycleAware, load`() {
        `test loading`(value = true, supportAutoBoxing = true) {
            putBoolean(AbsExtendedLifecycleAware.KEY, it)
        }
    }

    @Test
    fun `In case of die of Boolean LifecycleAware, save the cache`() {
        `test saving`(value = true, supportAutoBoxing = true) {
            getBoolean(AbsExtendedLifecycleAware.KEY)
        }
    }

    @Test
    fun `In case of born of Array Parcelable LifecycleAware, load`() {
        `test loading`<Array<out Parcelable?>>(Array(0) { mock<Parcelable>() }) {
            putParcelableArray(AbsExtendedLifecycleAware.KEY, it)
        }
    }

    @Test
    fun `In case of die of Array Parcelable LifecycleAware, save the cache`() {
        `test saving`<Array<out Parcelable?>>(Array(0) { mock<Parcelable>() }) {
            getParcelableArray(AbsExtendedLifecycleAware.KEY)!!
        }
    }

    @Test
    fun `In case of born of Array String LifecycleAware, load`() {
        `test loading`<Array<out String?>>(Array(0) { "" }) {
            putStringArray(AbsExtendedLifecycleAware.KEY, it)
        }
    }

    @Test
    fun `In case of die of Array String LifecycleAware, save the cache`() {
        `test saving`<Array<out String?>>(Array(0) { "" }) {
            getStringArray(AbsExtendedLifecycleAware.KEY)!!
        }
    }

    @Test
    fun `In case of born of Array CharSequence LifecycleAware, load`() {
        `test loading`<Array<out CharSequence?>>(Array(0) { mock<CharSequence>() }) {
            putCharSequenceArray(AbsExtendedLifecycleAware.KEY, it)
        }
    }

    @Test
    fun `In case of die of Array CharSequence LifecycleAware, save the cache`() {
        `test saving`<Array<out CharSequence?>>(Array(0) { mock<CharSequence>() }) {
            getCharSequenceArray(AbsExtendedLifecycleAware.KEY)!!
        }
    }

    @Test
    fun `In case of born of ByteArray LifecycleAware, load`() {
        `test loading`(ByteArray(0)) { putByteArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of ByteArray LifecycleAware, save the cache`() {
        `test saving`(ByteArray(0)) { getByteArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of ShortArray LifecycleAware, load`() {
        `test loading`(ShortArray(0)) { putShortArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of ShortArray LifecycleAware, save the cache`() {
        `test saving`(ShortArray(0)) { getShortArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of CharArray LifecycleAware, load`() {
        `test loading`(CharArray(0)) { putCharArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of CharArray LifecycleAware, save the cache`() {
        `test saving`(CharArray(0)) { getCharArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of IntArray LifecycleAware, load`() {
        `test loading`(IntArray(0)) { putIntArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of IntArray LifecycleAware, save the cache`() {
        `test saving`(IntArray(0)) { getIntArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of LongArray LifecycleAware, load`() {
        `test loading`(LongArray(0)) { putLongArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of LongArray LifecycleAware, save the cache`() {
        `test saving`(LongArray(0)) { getLongArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of FloatArray LifecycleAware, load`() {
        `test loading`(FloatArray(0)) { putFloatArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of FloatArray LifecycleAware, save the cache`() {
        `test saving`(FloatArray(0)) { getFloatArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of DoubleArray LifecycleAware, load`() {
        `test loading`(DoubleArray(0)) { putDoubleArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of DoubleArray LifecycleAware, save the cache`() {
        `test saving`(DoubleArray(0)) { getDoubleArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of BooleanArray LifecycleAware, load`() {
        `test loading`(BooleanArray(0)) { putBooleanArray(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of BooleanArray LifecycleAware, save the cache`() {
        `test saving`(BooleanArray(0)) { getBooleanArray(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of ArrayList Parcelable LifecycleAware, load`() {
        `test loading`<ArrayList<Parcelable>> {
            putParcelableArrayList(AbsExtendedLifecycleAware.KEY, it)
        }
    }

    @Test
    fun `In case of die of ArrayList Parcelable LifecycleAware, save the cache`() {
        `test saving` { getParcelableArrayList<Parcelable>(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of ArrayList Int LifecycleAware, load`() {
        `test loading`<ArrayList<Int>> { putIntegerArrayList(AbsExtendedLifecycleAware.KEY, it) }
    }

    @Test
    fun `In case of die of ArrayList Int LifecycleAware, save the cache`() {
        `test saving` { getIntegerArrayList(AbsExtendedLifecycleAware.KEY)!! }
    }

    @Test
    fun `In case of born of UnsupportedData LifecycleAware, load`() {
        try {
            `test loading`(UnsupportedData(false)) { }
            throw AssertionError("An exception must be thrown")
        } catch (e: IllegalArgumentException) {
        }
    }

    @Test
    fun `In case of die of UnsupportedData LifecycleAware, save the cache`() {
        try {
            `test saving`(UnsupportedData(false)) { UnsupportedData(false) }
            throw AssertionError("An exception must be thrown")
        } catch (e: IllegalArgumentException) {
        }
    }

    private inline fun <reified T : Any> `test loading`(
        value: T = mock(),
        supportAutoBoxing: Boolean = false,
        crossinline putter: Bundle.(T) -> Unit
    ) {
        val publisher = BehaviorProcessor.create<T>()
        val handler = mock<RxExtendedLifecycleHandler<T>>()
        val lifecycleAware = publisher.toExtendedLifecycleAware(KEY, handler)
        lifecycleAware.observe()

        lifecycleAware.onBorn(Bundle().apply { putter(value) })

        // To cache a value
        val captor = argumentCaptor<(Consumer<T>) -> Disposable>()
        verify(handler).observe(captor.capture(), eq(lifecycleAware), eq(KEY))
        var result: T? = null
        captor.firstValue.invoke(Consumer { result = it })

        if (supportAutoBoxing) {
            assert(result == value)
        } else {
            assert(result === value)
        }
    }

    private inline fun <reified T : Any> `test saving`(
        value: T = mock(),
        supportAutoBoxing: Boolean = false,
        crossinline getter: Bundle.() -> T
    ) {
        val publisher = BehaviorProcessor.create<T>()
        val handler = mock<RxExtendedLifecycleHandler<T>>()
        val lifecycleAware = publisher.toExtendedLifecycleAware(KEY, handler)
        lifecycleAware.observe()

        publisher.onNext(value)

        // To cache a value
        val captor = argumentCaptor<(Consumer<T>) -> Disposable>()
        verify(handler).observe(captor.capture(), eq(lifecycleAware), eq(KEY))
        captor.firstValue.invoke(Consumer { })

        val result = lifecycleAware.onDie()

        if (supportAutoBoxing) {
            assert(result.run { getter() } == value)
        } else {
            assert(result.run { getter() } === value)
        }
    }

    @Test
    fun `in case of processor, calling observe would call handler observe`() {
        val publisher = BehaviorProcessor.create<String>()
        val handler = mock<RxExtendedLifecycleHandler<String>>()
        val lifecycleAware = publisher.toExtendedLifecycleAware(KEY, handler)

        lifecycleAware.observe()

        verify(handler).observe(any(), any(), eq(KEY))
    }

    data class UnsupportedData(val unknown: Boolean)
    companion object {
        private const val KEY = "EXTENDED_LIFECYCLE_AWARE_TEST_KEY"
    }
}
