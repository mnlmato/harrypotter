# Android Kotlin Project with MVVM

This is a sample project written in Kotlin using MVVM, Use Case and the Repository Pattern.

![architecture_uml](https://github.com/mnlmato/harrypotter/blob/main/mvvm_demo_app.png)

- The UI is built with Jetpack Compose, Glide, Animations, Material3 and it supports Dark Mode
- The ViewModel is using LiveData and Coroutines
- The Data Layer is using Retrofit and Gson
- The Dependency Injection is implemented with Hilt
- The errors are caught and displayed on a screen with a retry button
- Additionally, the detail screen uses an ID to fetch data from a cache data source
- The build gradle files were migrated to Kotlin
- The dependencies were migrated to version catalogs
- Setup Gradle Convention Plugins
- An easy CI was built using GitHub actions to do the build and run unit tests and screenshot tests


The project includes the following test:

-  Unit Test with JUnit, MockK and Kotest
-  Integration Test with MockWebServer
-  Instrumented Test  using the Page Object Pattern
-  Screenshot tests with Paparazzi

## Video demo

![Alt Text](https://github.com/mnlmato/harrypotter/blob/main/app_demo.gif)

## Project structure

The project follows a modular architecture approach, with a goal of separating different concerns into their own packages. Although the current implementation is not a true modularization, the code has been organized in a way that facilitates future modularization if desired. By separating concerns into modules, the codebase can be maintained more easily and improved independently. This leads to a more scalable and maintainable app, which can evolve over time as new features are added and requirements change.

The packages are as follows:

- **app:** The main application module.
- **core-app:** The core module responsible for containing the result model and custom exceptions
- **core-data:** The core data module is responsible for injecting the REST module, the URL provider, and the exceptions mapper
- **core-ui:** The core UI module is responsible for providing common composable functions, an image loader, a resource provider loader and ViewModel extensions
- **design-system:** The design system module is responsible for providing the application theme and base widget components
- **features:** The features module is responsible for providing the different features in the app

## Testing

The project has a comprehensive suite of tests:

- Unit test: ViewModel, Use Cases, Repository and Api datasource

- Integration test from ui: Check the flow in the main screen and the data in the second screen

## Libraries used

The following libraries are used in this project:

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Glide](https://bumptech.github.io/glide/int/compose.html)
- [Retrofit](https://square.github.io/retrofit/)
- [Gson](https://github.com/google/gson)
- [Hilt](https://dagger.dev/hilt/)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [JUnit](https://junit.org/junit5/)
- [MockK](https://mockk.io/)
- [Kotest](https://kotest.io/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)

## License

MIT License

Copyright (c) [2023] [Manuel Mato Marinño]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.