# Practical 2 – Hello World

A simple Android application that displays a **Hello World** message, built with Kotlin and XML Views.

## Folder Structure

```
Practical-2/
└── HelloWorldApp/          ← Android Studio project root
    ├── app/
    │   └── src/main/
    │       ├── AndroidManifest.xml
    │       ├── kotlin/com/example/helloworldapp/
    │       │   └── MainActivity.kt     ← single activity
    │       └── res/
    │           ├── layout/
    │           │   └── activity_main.xml   ← centered Hello World UI
    │           └── values/
    │               ├── strings.xml
    │               ├── colors.xml
    │               └── themes.xml
    ├── gradle/
    ├── build.gradle.kts
    ├── settings.gradle.kts
    └── gradle.properties
```

## Aim

To create a basic Android application with a single activity that displays "Hello World!" centered on the screen, demonstrating the minimal project structure required for an Android app.

## How to Open & Build in Android Studio

### Prerequisites

| Tool | Minimum Version |
|------|-----------------|
| Android Studio | Hedgehog (2023.1.1) or later |
| Android SDK | API 34 |
| JDK | 8 or later (bundled with Android Studio) |

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Tabbyx0X/BSC-IT-Practicals.git
   ```

2. **Open the project**
   - Launch **Android Studio**
   - Choose **File → Open…**
   - Navigate to `BSC-IT-Practicals/Practical-2/HelloWorldApp/`
   - Click **OK** / **Open**

3. **Wait for Gradle sync**
   Android Studio will download all required dependencies automatically.
   Make sure you are connected to the internet for the first sync.

4. **Run the app**
   - Connect an Android device (USB debugging enabled) **or** start an emulator
   - Click the **Run ▶** button (or press `Shift+F10`)

## Expected Output

The app opens to a single screen showing:

- **"Hello World!"** in large bold text, centered on the screen
- **"Practical 2 – Hello World"** subtitle below it

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Kotlin |
| UI | XML Views (no Jetpack Compose) |
| Theme | Material Components (`Theme.MaterialComponents`) |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |
| Build tool | Gradle 8.7 + AGP 8.5.2 |
