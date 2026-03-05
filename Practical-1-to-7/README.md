# Android Programming Practicals 1–7 – Output App

A single, build-ready Android Studio project that demonstrates the output for
**Android Programming Practicals 1 through 7** in one app.

---

## How to Open in Android Studio

1. Clone or download this repository.
2. Open **Android Studio** (Electric Eel or newer recommended).
3. Choose **File → Open…** and navigate to:
   ```
   Practical-1-to-7/OutputApp/
   ```
4. Click **OK** / **Open**. Android Studio will import the project and sync Gradle automatically.
5. Wait for the Gradle sync and indexing to finish (first time may take a few minutes while dependencies download).

---

## How to Run

1. Connect an Android device (USB debugging enabled) **or** start an Android Virtual Device (AVD) from the **Device Manager**.
2. Click the green **▶ Run** button (or press `Shift + F10`).
3. The app will be installed and launched on the selected device/emulator.

---

## App Navigation

When the app launches you will see the **"Android Programming Practicals"** home screen with **7 buttons**.

Tap a button to open that practical's dedicated screen:

| Button | What it demonstrates |
|--------|----------------------|
| **Practical 1 – Resources** | String, Color, Drawable, and Dimension resources |
| **Practical 2 – Activities & Fragments** | Activity lifecycle log, two Fragments, Second Activity |
| **Practical 3 – Layouts** | LinearLayout, RelativeLayout, ConstraintLayout |
| **Practical 4 – UI Elements** | Toolbar/AppBar, TextView, Button, EditText, CheckBox, Switch |
| **Practical 5 – Menus & Dialogs** | Options menu (⋮), AlertDialog, DialogFragment |
| **Practical 6 – Intents & Adapters** | Implicit Intent (open website), RecyclerView with adapter |
| **Practical 7 – Services & Notifications** | Broadcast receiver, Notification, Start/Stop Service |

---

## What to Click for Each Practical

### Practical 1 – Resources
- The screen loads automatically and shows all four resource types.
- Observe: the **string**, **colored box**, **drawable icon**, and the **padded text block** at the bottom.

### Practical 2 – Activities & Fragments
- Watch the **lifecycle log** update as you navigate to/from the screen.
- Tap **Switch Fragment (A ↔ B)** to replace Fragment A with Fragment B and back.
- Tap **Open Second Activity** to navigate to a second Activity; press Back to return.

### Practical 3 – Layouts
- Scroll down to see all three layout examples: **LinearLayout**, **RelativeLayout**, **ConstraintLayout**.

### Practical 4 – UI Elements
- The **Toolbar/AppBar** is visible at the top.
- Tap **Click Me!** → Toast appears.
- Type in the **EditText** and tap **Read Input** → Toast shows your text.
- Toggle the **CheckBox** and **Switch** → Toasts show current state.

### Practical 5 – Menus & Dialogs
- Tap the **⋮ overflow icon** in the toolbar → Options menu appears with "Info".
- Tap **Info** → Toast confirms it was tapped.
- Tap **Show AlertDialog** → An AlertDialog appears with OK/Cancel.
- Tap **Show DialogFragment** → A DialogFragment (rotation-safe) appears.

### Practical 6 – Intents & Adapters
- Tap **Open developer.android.com** → Browser opens with the Android developer site (requires internet).
- Tap any item in the **RecyclerView** list → A Toast shows the tapped item's text.

### Practical 7 – Services & Notifications
- Tap **Send Custom Broadcast** → The local BroadcastReceiver receives it and logs to the screen.
- Tap **Send Notification** → A notification appears in the status bar.
- Tap **Start Service** / **Stop Service** → The background service starts/stops (check Logcat for `MyService` tag).

---

## Permissions & Android Version Notes

### Notifications (Practical 7)
- **Android 13+ (API 33+):** The app must request `POST_NOTIFICATIONS` permission at runtime.
  When you tap **Send Notification** for the first time on API 33+, a system dialog will ask you to allow notifications.
  If you deny it, the notification will not be sent (a Toast will inform you).
- **Android 12 and below:** No runtime permission is needed; the notification is sent immediately.

### Internet (Practical 6)
- Tapping **Open developer.android.com** requires an active internet connection on the device/emulator.
- The `INTERNET` permission is declared in `AndroidManifest.xml`.

### Foreground Service (Practical 7)
- `FOREGROUND_SERVICE` permission is declared in the manifest.
- The service in this demo is a simple background service (not a foreground/sticky service) to keep the demo minimal.

---

## Project Structure

```
OutputApp/
├── app/
│   └── src/main/
│       ├── kotlin/com/example/practicals/
│       │   ├── MainActivity.kt               ← Home screen (7 buttons)
│       │   ├── Practical1Activity.kt         ← Resources demo
│       │   ├── Practical2Activity.kt         ← Activities & Fragments demo
│       │   ├── Practical2SecondActivity.kt   ← Second Activity
│       │   ├── Practical3Activity.kt         ← Layouts demo
│       │   ├── Practical4Activity.kt         ← UI Elements demo
│       │   ├── Practical5Activity.kt         ← Menus & Dialogs demo
│       │   ├── Practical6Activity.kt         ← Intents & Adapters demo
│       │   ├── Practical7Activity.kt         ← Services & Notifications demo
│       │   ├── fragments/
│       │   │   ├── FragmentA.kt
│       │   │   ├── FragmentB.kt
│       │   │   └── MyDialogFragment.kt
│       │   ├── adapter/
│       │   │   └── ItemAdapter.kt            ← RecyclerView adapter
│       │   ├── service/
│       │   │   └── MyService.kt              ← Background service
│       │   └── receiver/                     ← Receiver registered dynamically
│       ├── res/
│       │   ├── layout/   ← All XML layouts
│       │   ├── values/   ← strings.xml, colors.xml, themes.xml, dimens.xml
│       │   ├── drawable/ ← ic_sample.xml (vector)
│       │   └── menu/     ← menu_practical5.xml
│       └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew  / gradlew.bat
└── .gitignore
```

---

## Tech Stack

- **Language:** Kotlin
- **UI:** XML Views (no Jetpack Compose)
- **Libraries:** AndroidX Core, AppCompat, Material Components, ConstraintLayout, RecyclerView
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Gradle:** 8.2 · AGP 8.2.2 · Kotlin 1.9.22
