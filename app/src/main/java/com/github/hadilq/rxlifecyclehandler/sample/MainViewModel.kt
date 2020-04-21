package com.github.hadilq.rxlifecyclehandler.sample

import androidx.lifecycle.ViewModel
import com.github.hadilq.rxlifecyclehandler.toELifeAware
import com.github.hadilq.rxlifecyclehandler.toLifeAware
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

class MainViewModel : ViewModel() {

    private val publisher = PublishProcessor.create<String>()
    private val extendedPublisher = BehaviorProcessor.create<String>().apply { onNext("Test") }

    /**
     * This emitter would NOT be saved on onSaveInstanceState
     */
    val stringEmitter = publisher.toLifeAware()

    /**
     * This emitter would be saved on onSaveInstanceState
     */
    val extendedStringEmitter = extendedPublisher.toELifeAware(KEY)

    companion object {
        private const val KEY = "key_to_save_string_emitter"
    }
}
