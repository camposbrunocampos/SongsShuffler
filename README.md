# SongsShuffler

This is a sample project where the idea is to explore some of the Android jetpack components, like LiveData and ViewModel.
The idea here is also to explore how tests would fit with the jetpack components and the MVVM architecture.
One thing to keep in my mind is that the idea here is not to use well known frameworks/libraries for Android. My idea was to keep the app simple, so for example, for controling the dependency injection, this was done manually, using the application class to provide some of the dependencies.
Also for having the presentation layer architectured with MVVM paradigm, I used liveData and ViewModel, both components from Android Jetpack.

The base idea of the app is an app who lists the songs from an API.
The app also has a feature which shuffles the songs, this algorithm follows the following rules:
1) The algorithm needs to generate different list of songs in each execution.
2) Same artists songs should not appear together.
