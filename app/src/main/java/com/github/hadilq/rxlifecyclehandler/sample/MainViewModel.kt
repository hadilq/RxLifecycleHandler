package com.github.hadilq.rxlifecyclehandler.sample

import androidx.lifecycle.ViewModel
import com.github.hadilq.rxlifecyclehandler.toExtendedLifecycleAware
import com.github.hadilq.rxlifecyclehandler.toLifecycleAware
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

class MainViewModel : ViewModel() {

    private val publisher = PublishProcessor.create<String>()
    private val extendedPublisher = BehaviorProcessor.create<String>().apply { onNext("Test") }

    /**
     * This emitter would NOT be saved on onSaveInstanceState
     */
    val stringEmitter = publisher.toLifecycleAware()

    /**
     * This emitter would be saved on onSaveInstanceState
     */
    val extendedStringEmitter = extendedPublisher.toExtendedLifecycleAware(KEY)

    companion object {
        private const val KEY = "key_to_save_string_emitter"
    }
}
