Changelog
=========

0.3.3
-----

_2020-04-15_

Add `Flow.toLife` and `Flow.toELife` extension functions.
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
