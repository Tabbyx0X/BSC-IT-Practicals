# Practical 7 – Registration Form UI

A single-screen Android registration form built with **Kotlin + XML Views** and **Material Components**, featuring a custom pastel purple color scheme.

---

## Features

- **Full Name**, **Email**, **Password**, and **Confirm Password** input fields
- **Submit / Register** button
- Client-side validation:
  - No empty fields allowed
  - Email must contain `@`
  - Password and Confirm Password must match
- Toast notification **"Registration successful"** on valid submission
- Custom color scheme:
  - Light lavender/pastel background (`#FFF3E0FF`)
  - Deep purple submit button (`#FF6A1B9A`) with white text
  - Readable purple-toned text and input strokes

---

## How to Open in Android Studio

1. Open **Android Studio** (Electric Eel or newer recommended).
2. Choose **File → Open** and navigate to this folder (`Practical-7/RegistrationFormApp/`).
3. Android Studio will detect the Gradle project automatically.
4. Wait for Gradle sync to finish.

---

## How to Build

From the project root (`Practical-7/RegistrationFormApp/`), run:

```bash
# Linux / macOS
./gradlew assembleDebug

# Windows
gradlew.bat assembleDebug
```

The generated APK will be located at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## How to Run

1. Connect an Android device (API 24+) or start an Android Emulator.
2. In Android Studio, press the **▶ Run** button, or run from the terminal:

```bash
./gradlew installDebug
```

---

## Expected Output

On launch you will see a scrollable registration form with:

| Field            | Input Type    |
|------------------|---------------|
| Full Name        | Text          |
| Email Address    | Email         |
| Password         | Password (hidden) |
| Confirm Password | Password (hidden) |

**Validation behaviour:**
- Leaving any field blank → shows an inline error on that field.
- Entering an email without `@` → shows "Enter a valid email address".
- Passwords that don't match → shows "Passwords do not match" on Confirm Password.
- All fields valid → shows a **Toast** message: _"Registration successful"_.

---

## Project Structure

```
RegistrationFormApp/
├── app/
│   └── src/main/
│       ├── kotlin/com/example/registrationformapp/
│       │   └── MainActivity.kt          # Validation logic
│       ├── res/
│       │   ├── layout/activity_main.xml # Registration form UI
│       │   └── values/
│       │       ├── colors.xml           # Custom color scheme
│       │       ├── strings.xml          # String resources
│       │       └── themes.xml           # App theme
│       └── AndroidManifest.xml
├── gradle/
│   ├── libs.versions.toml               # Version catalog
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

---

## Dependencies

| Library                  | Version |
|--------------------------|---------|
| Android Gradle Plugin    | 8.5.2   |
| Kotlin                   | 1.9.24  |
| AndroidX Core KTX        | 1.13.1  |
| AndroidX AppCompat       | 1.7.0   |
| Material Components      | 1.12.0  |
| ConstraintLayout         | 2.1.4   |
| Gradle                   | 8.7     |
