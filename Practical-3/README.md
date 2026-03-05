# Practical 3 – Android Login Screen

A simple Android application demonstrating a **Login Screen** built with Kotlin and XML Views.

## Folder Structure

```
Practical-3/
└── LoginScreenApp/          ← Android Studio project root
    ├── app/
    │   └── src/main/
    │       ├── AndroidManifest.xml
    │       ├── kotlin/com/example/loginscreenapp/
    │       │   └── LoginActivity.kt     ← main activity with validation logic
    │       └── res/
    │           ├── layout/
    │           │   └── activity_login.xml   ← UI layout
    │           └── values/
    │               ├── strings.xml
    │               ├── colors.xml
    │               └── themes.xml
    ├── gradle/
    ├── build.gradle.kts
    ├── settings.gradle.kts
    └── gradle.properties
```

## Features

- **Username field** – plain text input
- **Password field** – masked input with show/hide toggle
- **Validation**
  - Shows an error on the username field if left empty
  - Shows an error on the password field if left empty
  - Shows a `"Login successful"` Toast when both fields are filled

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
   - Navigate to `BSC-IT-Practicals/Practical-3/LoginScreenApp/`
   - Click **OK** / **Open**

3. **Wait for Gradle sync**
   Android Studio will download all required dependencies automatically.
   Make sure you are connected to the internet for the first sync.

4. **Run the app**
   - Connect an Android device (USB debugging enabled) **or** start an emulator
   - Click the **Run ▶** button (or press `Shift+F10`)

5. **Expected behaviour**
   - Tapping **LOG IN** with empty fields shows inline error messages on each field
   - Tapping **LOG IN** with both fields filled shows a *"Login successful"* toast

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Kotlin |
| UI | XML Views (no Jetpack Compose) |
| Theme | Material Components (`Theme.MaterialComponents`) |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |
| Build tool | Gradle 8.7 + AGP 8.5.2 |
