Changelog
=========

0.4.0
-----

_2020-08-26_

 - Move to Kotlin Multiplatform
  + The previous artifactId was `rxlifecyclehandler` which is now as following:
     * For Android, it's `rx-lifecycle-handler-android`
     * For Jvm, it's `rx-lifecycle-handler-jvm`
     * For Common, it's `rx-lifecycle-handler-metadata`
 - Remove `buildSrc`, instead use composite build with `build-plugin`
 - Configure JaCoCo
 - Configure Maven Publication
 - Configure Dokka

0.3.0
-----

_2020-04-22_

Renaming:
 - `AndroidExtendedLifecycleHandlerImpl` to `AndroidELifecHandlerImpl`
 - `AndroidExtendedLifecycleHandler` to `AndroidELifecHandler`
 - `AndroidLifecycleHandlerImpl` to `AndroidLifeHandlerImpl`
 - `AndroidLifecycleHandler` to `AndroidLifeHandler`
 - `BaseLifecycleHandler` to `BaseLifeHandler`
 - `ExtendedLife` to `ELife`
 - `RxExtendedLifecycleHandlerImpl` to `RxELifeHandlerImpl`
 - `RxExtendedLifecycleHandler` to `RxELifeHandler`
 - `RxLifeHandlerImpl` to `RxLifeHandlerImpl`
 - `RxLifeHandler` to `RxLifeHandler`
 - `ExtendedEntry` to `EEntry`
 - `ExtendedLifecycleAwareImpl` to `ELifeAwareImpl`
 - `ExtendedLifecycleAware` to `ELifeAware`
 - `LifecycleAwareImpl` to `LifeAwareImpl`
 - `LifecycleAware` to `LifeAware`
 - `toExtendedLifecycleAware` to `toELifeAware`
 - `toLifecycleAware` to `toLifeAware`

Adding:
 - `LifeSpan#CONFIGURATION_CHANGED`
 - `LifeSpan#USER_FLOW`
 - `LifeStore`
 - `SLife`
 - `Flow.toLife` and `Flow.toELife` extension functions

Modified:
 - `Flowable<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit` to `Flowable<T>.observeOnNext(): LifecycleOwner.((T) -> Unit) -> Unit`
 - `Observable<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit` to `Observable<T>.observeOnNext(): LifecycleOwner.((T) -> Unit) -> Unit`
 - `Maybe<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit` to `Maybe<T>.observeOnNext(): LifecycleOwner.((T) -> Unit) -> Unit`
 - `Single<T>.observeOnNext(): LifecycleOwner.(Consumer<T>) -> Unit` to `Single<T>.observeOnNext(): LifecycleOwner.((T) -> Unit) -> Unit`
 - `Flowable<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit` to `Flowable<T>.observeOnNextOnError(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit`
 - `Observable<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit` to `Observable<T>.observeOnNextOnError(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit`
 - `Maybe<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit` to `Maybe<T>.observeOnNextOnError(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit`
 - `Single<T>.observeOnNextOnError(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>) -> Unit` to `Single<T>.observeOnNextOnError(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit) -> Unit`
 - `Flowable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit` to `Flowable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit`
 - `Observable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit` to `Observable<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit`
 - `Maybe<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit` to `Maybe<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit) -> Unit`
 - `Single<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.(Consumer<T>, Consumer<Throwable>, Action) -> Unit` to `Single<T>.observeOnNextOnErrorOnComplete(): LifecycleOwner.((T) -> Unit, (Throwable) -> Unit, () -> Unit -> Unit`

Removing:
 - `bservable<T>.observeOnNextOnErrorOnCompleteOnSubscribe()`
 - `Flowable<T>.observeOnNextOnErrorOnCompleteOnSubscribe()`
`

0.2.0
-----

_2020-02-02_

Implement `AndroidLifecycleHandlerImpl` and `AndroidExtendedLifecycleHandlerImpl`. Also make the
`ExtendedLife#onBorn` method accepts null bundle.

Also implement `RxExtendedLifecycleHandlerImpl`, `toLifecycleAware` and `toExtendedLifecycleAware`.

0.1.0
-----

_2020-01-15_

Initial release
