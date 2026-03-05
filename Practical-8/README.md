# Practical 8 – Airplane Mode Notifier

## Aim

To demonstrate how to use a **BroadcastReceiver** to detect system-level events—specifically, Airplane Mode being toggled ON or OFF—and notify the user through a **Notification** and a **Toast**.

---

## Project Structure

```
Practical-8/
├── AirplaneModeNotifier/          ← Standalone Android Studio project
│   ├── app/
│   │   └── src/main/
│   │       ├── AndroidManifest.xml
│   │       ├── kotlin/com/example/airplanemodenotifier/
│   │       │   ├── AirplaneModeReceiver.kt   ← Manifest-declared BroadcastReceiver
│   │       │   └── MainActivity.kt           ← UI + permission request
│   │       └── res/
│   │           ├── layout/activity_main.xml
│   │           └── values/{strings,colors,themes}.xml
│   ├── build.gradle.kts
│   ├── settings.gradle.kts
│   ├── gradle.properties
│   └── gradle/libs.versions.toml
└── README.md                      ← This file
```

---

## Key Concepts

| Concept | Where used |
|---|---|
| Manifest-declared `BroadcastReceiver` | `AndroidManifest.xml` + `AirplaneModeReceiver.kt` |
| Reading system Airplane Mode state | `Settings.Global.AIRPLANE_MODE_ON` |
| `NotificationCompat` + channel (API 26+) | `AirplaneModeReceiver.kt` |
| `POST_NOTIFICATIONS` permission (API 33+) | `AndroidManifest.xml` + `MainActivity.kt` |
| Live UI update via local broadcast | `AirplaneModeReceiver` → `MainActivity` |
| Toast for immediate feedback | `AirplaneModeReceiver.onReceive()` |

---

## How to Run

1. Open **Android Studio** (Hedgehog or newer).
2. Choose **File → Open** and navigate to `Practical-8/AirplaneModeNotifier/`.
3. Let Gradle sync complete.
4. Connect an Android device or start an emulator (API 24+).
5. Click **Run ▶**.

> **Note:** On Android 13+ (API 33+) the app will prompt for the `POST_NOTIFICATIONS` permission on first launch.  Tap **Allow** to see notifications.

---

## How to Test

### On a physical device
1. Launch the app and note the current status shown in the UI.
2. Swipe down from the top of the screen to open the **Quick Settings** panel.
3. Tap the **Airplane Mode** tile to toggle it ON.
   - A **Toast** appears immediately: *"Airplane Mode: ON ✈"*
   - A **Notification** is posted: *"Airplane Mode Changed"*
   - The in-app status text updates to **"✈ Airplane Mode: ON"**.
4. Tap Airplane Mode again to toggle it OFF.
   - A **Toast** appears: *"Airplane Mode: OFF 📶"*
   - A **Notification** is posted.
   - The in-app status updates to **"📶 Airplane Mode: OFF"**.

### On an emulator
1. Open **Extended Controls** (the `…` button in the emulator sidebar).
2. Go to **Cellular → Network type** or use the Quick Settings panel (same as above).
   - Alternatively, run `adb shell settings put global airplane_mode_on 1 && adb shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true` in a terminal.

---

## Expected Output

| Trigger | Toast | Notification | UI |
|---|---|---|---|
| Airplane Mode turned **ON** | *"Airplane Mode: ON ✈"* | Title: *"Airplane Mode Changed"*, Body: *"✈ Airplane Mode: ON"* | Status shows **"✈ Airplane Mode: ON"** |
| Airplane Mode turned **OFF** | *"Airplane Mode: OFF 📶"* | Title: *"Airplane Mode Changed"*, Body: *"📶 Airplane Mode: OFF"* | Status shows **"📶 Airplane Mode: OFF"** |

---

## Build Configuration

| Property | Value |
|---|---|
| `minSdk` | 24 |
| `targetSdk` | 34 |
| `compileSdk` | 34 |
| Language | Kotlin |
| AGP | 8.2.2 |
| Gradle | 8.2 |
