
Rx Lifecycle Handler
---
This library is a glue between the lifecycle of `androidx.lifecycle:lifecycle-extensions` and 
`io.reactivex.rxjava2:rxjava` libraries. The assumption is that we want the emitted values between 
`start` and `stop` of `LifecycleOwner`, so on other moments this library would unsubscribe 
from the upstream of `RxJava`, so called `Observable`, `Flowable`, `Maybe`, and `Single`.

Usage
---
This source has a sample app, which doesn't do anything, where you can find the usage in `MainActivity`.

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
    }

    private fun handleString(s: String) { }
}
```

Enjoy!

Contribution
---
Just create your branch from the master branch, change it, write additional tests, satisfy all 
tests, create your pull request, thank you, you're awesome.
