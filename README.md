# Android Clean Architecture

A sample Android app that showcases the use of Clean Architecture principles and the Model-View-ViewModel (MVVM) pattern. The app is built using Kotlin and follows a modular approach for better maintainability and testability.


# Project Structure
The project is divided into several modules:

* `domain` contains the domain layer with use cases and models.
* `data` contains the data layer with network and database components.
* `presentation` contains the presentation layer with activities, fragments, view models and adapters.
* `navigation` contains the navigation components for handling app navigation.
* `jetpack` contains the Android Jetpack components used in the project.
* `services` contains the services used in the project.


# Features
* Uses Clean Architecture principles to separate the concerns of the app.
* Uses MVVM pattern to separate the business logic from the UI.
* Uses Dagger for Dependency Injection to make the code more maintainable and testable.
* Uses Retrofit and Room for network and local persistence.
* Uses Android Jetpack components such as Lifecycle and Room.


# How to run the app
* Clone the repository and import it into Android Studio. You can then run the app on an emulator or an Android device.

# Contributing
* If you would like to contribute to the project, feel free to create a pull request. All contributions are welcome!


# License

```
Copyright (C) 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```