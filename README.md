# ClipsMyanmar Android App

This is the initial project setup for the ClipsMyanmar Android application, built with modern Android development practices.

## Project Setup

The project is configured with the following key technologies:

- **Kotlin**: The primary programming language.
- **Jetpack Compose**: For building the UI declaratively.
- **Hilt**: For dependency injection, simplifying dependency management and improving testability.
- **Gradle Kotlin DSL**: For managing build scripts (`build.gradle.kts`).

### How to Run

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```

2.  **Open in Android Studio:**
    - Open Android Studio (Hedgehog or newer is recommended).
    - Select "Open an Existing Project" and navigate to the cloned directory.

3.  **Sync Gradle:**
    - Android Studio will automatically prompt you to sync the Gradle files. This will download all the necessary dependencies defined in `app/build.gradle.kts`.

4.  **Build and Run:**
    - Select a target device (emulator or a physical device).
    - Click the "Run 'app'" button (the green play icon) in the toolbar.

The application is now set up with Hilt. The entry point is `ClipsMyanmarApp.kt`, which is annotated with `@HiltAndroidApp`. The `MainActivity` will need to be annotated with `@AndroidEntryPoint` to enable field injection. Dependency modules, like `AppModule.kt`, are located in the `di` package.
