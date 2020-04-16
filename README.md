[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.hadilq/rxlifecyclehandler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.hadilq/rxlifecyclehandler)
[![CircleCI](https://circleci.com/gh/hadilq/RxLifecycleHandler.svg?style=svg)](https://circleci.com/gh/hadilq/RxLifecycleHandler)
[![codecov](https://codecov.io/gh/hadilq/RxLifecycleHandler/branch/master/graph/badge.svg)](https://codecov.io/gh/hadilq/RxLifecycleHandler)

Rx Lifecycle Handler
---
This library is a glue between the lifecycle of `androidx.lifecycle:lifecycle-extensions` and 
`io.reactivex.rxjava2:rxjava` libraries. The assumption is that we want the emitted values between 
`start` and `stop` of `LifecycleOwner`, so on other moments this library would unsubscribe 
from the upstream of `RxJava`, so called `Observable`, `Flowable`, `Maybe`, and `Single`. Also,
we support a more general assumption that we want the emitted values after `start` and before
 `stop` or `saveState` of `SavedStateRegistryOwner`, depends which one gets called sooner.

Also you can find its twin library for Kotlin Coroutines in https://github.com/hadilq/CoroutineLifecycleHandler/.

Usage
---
This source has a sample app, which doesn't do anything, where you can find the usage in `MainActivity`
and `MainViewModelActivity`. However, in `MVVM` or `MVI` architectural patterns, you can use it
based on the data you want to save in `onSaveInstanceState` or not. For now let's restrict
ourselves to `MVVM`. If the data you want to propagate is an action, then you probably don't want
to save it so in `ViewModel` you have.
```kotlin
class MainViewModel : ViewModel() {

    private val publisher = PublishProcessor.create<String>()
    val stringEmitter = publisher.toLifecycleAware()
}

```
And in the `Activity` or `Fragment` you have.
```kotlin
class MyViewModelActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        (viewModel.stringEmitter.observe()) { testString ->
            /* use it here */
        }
    }
}
```
But if you want to save the propagated data in `onSaveInstanceState` method, then you may want to
use it as follows.
```kotlin
class MainViewModel : ViewModel() {

    private val extendedPublisher = BehaviorProcessor.create<String>()

    val extendedStringEmitter = extendedPublisher.toExtendedLifecycleAware(KEY)

    companion object {
        private const val KEY = "key_to_save_string_emitter"
    }
}

```
And in the `Activity` or `Fragment` you have.
```kotlin
class MyViewModelActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        (viewModel.extendedStringEmitter.observe()) { testString ->
            /* use it here */
        }
    }
}
```
Here, you may noticed the extra parentheses above, or you may noticed that we didn't passe the
`Activity` or `Fragment` to the `observe` method. In both cases, read the last sentence again!
Because they're related to each other, which means in this library we preferred to avoid writing
`this` to pass the `Activity` or `Fragment` directly in trade off of having odd position of parentheses.

Any way, in case you want to use this library out of `MVVM` architectural pattern, you can use it
 as follows.
```kotlin
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ...

        val flowable: Flowable<String> = ...
        val maybe: Maybe<String> = ...
        val observable: Observable<String> = ...
        val single: Single<String> = ...

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

    private fun handleString(s: String) { }

    companion object {
        private const val KEY = "key_to_save_the_data"
    }
}
```

Enjoy!

Download
---
Download via gradle
```groovy
implementation "com.github.hadilq:rxlifecyclehandler:$libVersion"
```
where the `libVersion` is [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.hadilq/rxlifecyclehandler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.hadilq/rxlifecyclehandler).

Contribution
---
Just create your branch from the master branch, change it, write additional tests, satisfy all 
tests, create your pull request, thank you, you're awesome.
