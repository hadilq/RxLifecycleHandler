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

import android.os.Bundle
import android.os.Parcelable
import java.util.*
import kotlin.reflect.KClass

/***
 * An abstract class of [ExtendedLifecycleAware] to handle the work with [Bundle].
 */
abstract class AbsExtendedLifecycleAware<T : Any>(private val clazz: KClass<T>) :
    ExtendedLifecycleAware<T> {

    private var cache: T? = null

    protected fun cache(cache: T) {
        this.cache = cache
    }

    override fun onBorn(bundle: Bundle?) {
        bundle?.apply {
            when {
                isAssignable(Bundle::class.java) -> getBundle(KEY)?.apply { emit(this) }
                isAssignable(Parcelable::class.java) -> getParcelable<Parcelable>(KEY)?.apply {
                    emit(this)
                }
                isAssignable(String::class.java) -> getString(KEY)?.apply { emit(this) }
                isAssignable(CharSequence::class.java) -> getCharSequence(KEY)?.apply { emit(this) }
                clazz == Byte::class -> getByte(KEY).apply { emit(this) }
                clazz == Short::class -> getShort(KEY).apply { emit(this) }
                clazz == Char::class -> getChar(KEY).apply { emit(this) }
                clazz == Int::class -> getInt(KEY).apply { emit(this) }
                clazz == Long::class -> getLong(KEY).apply { emit(this) }
                clazz == Float::class -> getFloat(KEY).apply { emit(this) }
                clazz == Double::class -> getDouble(KEY).apply { emit(this) }
                clazz == Boolean::class -> getBoolean(KEY).apply { emit(this) }
                isAssignable(Array<out Parcelable?>::class.java) ->
                    getParcelableArray(KEY)?.apply { emit(this) }
                isAssignable(Array<out String?>::class.java) -> getStringArray(KEY)?.apply {
                    emit(this)
                }
                isAssignable(Array<out CharSequence?>::class.java) ->
                    getCharSequenceArray(KEY)?.apply { emit(this) }
                isAssignable(ByteArray::class.java) -> getByteArray(KEY)?.apply { emit(this) }
                isAssignable(ShortArray::class.java) -> getShortArray(KEY)?.apply { emit(this) }
                isAssignable(CharArray::class.java) -> getCharArray(KEY)?.apply { emit(this) }
                isAssignable(IntArray::class.java) -> getIntArray(KEY)?.apply { emit(this) }
                isAssignable(LongArray::class.java) -> getLongArray(KEY)?.apply { emit(this) }
                isAssignable(FloatArray::class.java) -> getFloatArray(KEY)?.apply { emit(this) }
                isAssignable(DoubleArray::class.java) -> getDoubleArray(KEY)?.apply { emit(this) }
                isAssignable(BooleanArray::class.java) -> getBooleanArray(KEY)?.apply { emit(this) }
                isAssignable(ArrayList::class.java) -> {
                    getParcelableArrayList<Parcelable>(KEY)?.apply { emit(this) }
                }
                else -> throw IllegalArgumentException("Cannot get $clazz from a bundle!")
            }
            Unit
        }
    }

    override fun onDie(): Bundle = Bundle().takeIf { cache != null }?.apply {
        @Suppress("UNCHECKED_CAST")
        when {
            isAssignable(Bundle::class.java) -> putBundle(KEY, cache as Bundle)
            isAssignable(Parcelable::class.java) -> putParcelable(KEY, cache as Parcelable)
            isAssignable(String::class.java) -> putString(KEY, cache as String)
            isAssignable(CharSequence::class.java) -> putCharSequence(KEY, cache as CharSequence)
            clazz == Byte::class -> putByte(KEY, cache as Byte)
            clazz == Short::class -> putShort(KEY, cache as Short)
            clazz == Char::class -> putChar(KEY, cache as Char)
            clazz == Int::class -> putInt(KEY, cache as Int)
            clazz == Long::class -> putLong(KEY, cache as Long)
            clazz == Float::class -> putFloat(KEY, cache as Float)
            clazz == Double::class -> putDouble(KEY, cache as Double)
            clazz == Boolean::class -> putBoolean(KEY, cache as Boolean)
            isAssignable(Array<out Parcelable?>::class.java) -> putParcelableArray(
                KEY,
                cache as Array<out Parcelable?>
            )
            isAssignable(Array<out String?>::class.java) -> putStringArray(
                KEY,
                cache as Array<out String?>
            )
            isAssignable(Array<out CharSequence?>::class.java) ->
                putCharSequenceArray(KEY, cache as Array<out CharSequence?>)
            isAssignable(ByteArray::class.java) -> putByteArray(KEY, cache as ByteArray)
            isAssignable(ShortArray::class.java) -> putShortArray(KEY, cache as ShortArray)
            isAssignable(CharArray::class.java) -> putCharArray(KEY, cache as CharArray)
            isAssignable(IntArray::class.java) -> putIntArray(KEY, cache as IntArray)
            isAssignable(LongArray::class.java) -> putLongArray(KEY, cache as LongArray)
            isAssignable(FloatArray::class.java) -> putFloatArray(KEY, cache as FloatArray)
            isAssignable(DoubleArray::class.java) -> putDoubleArray(KEY, cache as DoubleArray)
            isAssignable(BooleanArray::class.java) -> putBooleanArray(KEY, cache as BooleanArray)
            isAssignable(ArrayList::class.java) ->
                // "parcelable" arraylists - lol
                putParcelableArrayList(KEY, cache as ArrayList<Parcelable?>)
            else -> throw IllegalArgumentException("Cannot put $clazz into a bundle!")
        }
    } ?: Bundle()

    protected abstract fun <R> emit(value: R)

    private fun <T> isAssignable(cls: Class<T>) = cls.isAssignableFrom(clazz.java)

    companion object {
        internal const val KEY = "EXTENDED_LIFECYCLE_AWARE_KEY"
    }
}
