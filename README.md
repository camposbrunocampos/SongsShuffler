# Shuffling songs
This is a sample project where the idea is to explore some of the Android jetpack components, like LiveData and ViewModel.
In order to do that, I used the Clean Architecture approach for the project architecture. The main idea is to structure the project in 5 layers:
1. Presentation: This is the layer that interacts with the UI. This layer consists in classes of android UI (Views, Activities and Fragments) and a viewModel. The idea here is to separate business logic from the UI. This architecture is optimized for unit testing and makes it easy to do integration tests with it.

2. Domain: It contains the business model of the project. In this project it would be the Song class.

3. Data: This layer that consists in accessing the data, it uses the repository pattern. The benefit of it is decoupling parts of the software from the data access logic, for example, when the system requests for a song list, the system will get it independently if the list of songs come from a database, webserver or something else.

4. Framework: The focus of this layer is to encapsulate the interactions with the framework. so that the rest of the code can be agnostic and reusable in case you want to implement the same App in another platform.
This layer doesn't focus in encapsulate the android framework, but it tries to encapsulate logic from third party libraries.

5. Use cases: This is where the actions of the application are.

The idea here is also to explore how tests would fit with the jetpack components and the MVVM architecture.
One thing to keep in my mind is that the idea here is not to use well known frameworks/libraries for Android. My idea was to keep the app simple, so for example, for controling the dependency injection, this was done manually, using the application class to provide some of the dependencies.
Also for having the presentation layer architectured with MVVM paradigm, I used liveData and ViewModel, both components from Android Jetpack.

The base idea of the app is an app who lists the songs from an API.

The app also has a feature which shuffles the songs, this algorithm follows the following rules:
1) The algorithm needs to generate different list of songs in each execution.
2) Same artists songs should not appear together.

