# Android Kotlin Project with Coroutines, MVVM, Repository Pattern, Jetpack Compose, DI Hilt, Unit test, Integration test and Instrumented Test 

This is a sample project written in Kotlin using MVVM, Use Case and the Repository Pattern. The project includes the following test: 
- Unit Test with JUnit, MockK and Kotest
- Integration Test with MockWebServer
- Instrumented Test  using the Page Object Pattern

The UI is built with Jetpack Compose, Glide, Animations, Material3 and it supports Dark Mode.
The ViewModel is using LiveData and Coroutines. 
The Data Layer is using Retrofit and Gson. 
The Dependency Injection is implemented with Hilt. 

## Project structure

The project follows a modular architecture, separating different concerns into their own modules. The modules are as follows:

- **app:** The main application module.
- **coreapp:** The core module responsible for containing the result model and custom exceptions.
- **coredata:** The core data module is responsible for injecting the REST module, the URL provider, and the exceptions mapper.
- **coreui:** The core UI module is responsible for providing common composable functions, an image loader, a resource provider loader, and ViewModel extensions.
- **designsystem:** The design system module is responsible for providing the application theme and base widget components.
- **features:** The features module is responsible for providing the different features in the app.

## Testing

The project has a comprehensive suite of tests:
- Unit test: ViewModel, Use Cases, Repository and Api datasource
- Integration test from ui: Check the flow in the main screen and the data in the second screen.

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
- [Mockwebserver](https://github.com/square/okhttp/tree/master/mockwebserver)

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
