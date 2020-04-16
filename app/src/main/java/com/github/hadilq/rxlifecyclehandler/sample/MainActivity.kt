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
package com.github.hadilq.rxlifecyclehandler.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.github.hadilq.androidlifecyclehandler.ELife
import com.github.hadilq.rxlifecyclehandler.observe
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor

class MainActivity : ComponentActivity() {

    private val life = object : ELife {
        override fun onBorn(bundle: Bundle?) {
        }

        override fun onDie(): Bundle = Bundle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val flowable: Flowable<String> = PublishProcessor.create<String>().hide()
        val maybe: Maybe<String> = flowable.firstElement()
        val observable: Observable<String> = flowable.toObservable()
        val single: Single<String> = flowable.single("single")

        // Flowable usage
        (flowable.observe())(::handleString)

        // Maybe usage
        (maybe.observe())(::handleString)

        // Observable usage
        (observable.observe())(::handleString)

        // Single usage
        (single.observe())(::handleString)

        // Flowable usage
        (flowable.observe(life, KEY))(::handleString)

        // Maybe usage
        (maybe.observe(life, KEY))(::handleString)

        // Observable usage
        (observable.observe(life, KEY))(::handleString)

        // Single usage
        (single.observe(life, KEY))(::handleString)
    }

    private fun handleString(@Suppress("UNUSED_PARAMETER") s: String) {
    }

    companion object {
        private const val KEY = "key_to_save_the_data"
    }
}
