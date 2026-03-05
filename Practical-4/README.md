# Practical 4 – Back Press AlertDialog (Android)

This practical demonstrates how to intercept the Android system **back button** press and show an `AlertDialog` giving the user a choice to exit or stay in the app.

## Project location

```
Practical-4/
└── BackPressAlertDialog/   ← Android Studio project root
```

## How to open in Android Studio

1. Open **Android Studio** (Hedgehog 2023.1 or newer recommended).
2. Choose **File → Open…** and navigate to `Practical-4/BackPressAlertDialog/`.
3. Let Gradle sync complete (requires an active internet connection on first sync to download the Gradle distribution and dependencies).

## How to build from the command line

```bash
cd Practical-4/BackPressAlertDialog
./gradlew assembleDebug        # build a debug APK
# APK will be at: app/build/outputs/apk/debug/app-debug.apk
```

On Windows:
```cmd
cd Practical-4\BackPressAlertDialog
gradlew.bat assembleDebug
```

## How to run

- Connect an Android device (USB debugging enabled) **or** start an AVD emulator.
- In Android Studio press the **Run** button (▶), or via CLI:

```bash
./gradlew installDebug   # builds and installs on the connected device/emulator
```

## What the app does

| User action | App behaviour |
|---|---|
| Launch app | Shows a single screen with a hint label |
| Press system back button | An **AlertDialog** appears: *"Do you want to exit?"* |
| Tap **Yes** | The activity is closed (`finish()`) |
| Tap **No** | The dialog is dismissed; the user stays on screen |

## Key implementation details

- **`onBackPressedDispatcher.addCallback()`** is used instead of the deprecated `onBackPressed()` override.
- `AlertDialog.Builder` (AndroidX AppCompat) is used for the dialog.
- All UI strings are stored in `res/values/strings.xml`.

## Requirements

| Tool | Version |
|---|---|
| Android Studio | Hedgehog 2023.1+ (or any with AGP 8 support) |
| Gradle | 8.6 (downloaded automatically by the wrapper) |
| Android Gradle Plugin | 8.3.2 |
| Kotlin | 1.9.23 |
| Min SDK | 21 (Android 5.0) |
| Target/Compile SDK | 34 (Android 14) |
