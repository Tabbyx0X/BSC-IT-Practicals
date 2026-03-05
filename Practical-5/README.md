# Practical 5 – Android Activity Lifecycle Demo

## What This App Demonstrates

The **Android Activity Lifecycle** is the set of states an Activity goes through from creation to destruction. Android calls specific methods as the activity transitions between states. This app overrides all of them and shows every event on the screen so you can see the lifecycle in real time—no Logcat needed.

### Lifecycle Callback Order

```
onCreate  →  onStart  →  onResume
                               ↕  (user interacting)
                          onPause
                          onStop
                          onRestart → onStart → onResume
                             or
                          onDestroy
```

| Callback | When it is called |
|---|---|
| `onCreate` | Activity is created for the first time (or re-created after rotation) |
| `onStart` | Activity becomes visible to the user |
| `onResume` | Activity is in the foreground and the user can interact |
| `onPause` | Another activity comes on top (activity partially hidden) |
| `onStop` | Activity is completely hidden (e.g. Home pressed, or new activity opened) |
| `onRestart` | Called before `onStart` when returning from the stopped state |
| `onDestroy` | Activity is being finished or the system is destroying it |

### State Saving with `onSaveInstanceState`

When Android needs to destroy and recreate an Activity (e.g. on screen rotation), `onSaveInstanceState` is called so you can save small bits of data. On recreation, the saved `Bundle` is passed back to `onCreate`. The text field in this app demonstrates this.

---

## Project Structure

```
Practical-5/
├── app/
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/com/example/lifecycledemo/
│       │   ├── LifecycleDemoActivity.kt   ← main activity (lifecycle callbacks)
│       │   └── SecondActivity.kt          ← second screen to trigger pause/stop/restart
│       └── res/
│           ├── layout/
│           │   ├── activity_lifecycle_demo.xml
│           │   └── activity_second.xml
│           └── values/
│               ├── strings.xml
│               ├── colors.xml
│               └── themes.xml
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/
```

---

## How to Run in Android Studio

1. Open **Android Studio** → **Open** → select the `Practical-5/` folder.
2. Wait for Gradle sync to finish.
3. Connect a device or start an emulator.
4. Click **Run ▶** (or press `Shift + F10`).

---

## How to Test the Lifecycle

Once the app is running, perform the following actions and watch the event log on screen:

| Action | Expected lifecycle events |
|---|---|
| App opens | `onCreate` → `onStart` → `onResume` |
| Press **Home** button | `onPause` → `onStop` |
| Re-open the app from recents | `onRestart` → `onStart` → `onResume` |
| Tap **Open Second Activity** | `onPause` → `onStop` (main activity hidden) |
| Press **Go Back** in second activity | `onRestart` → `onStart` → `onResume` |
| Rotate the device | `onPause` → `onStop` → `onDestroy` → `onCreate` → `onStart` → `onResume` |

### Testing State Saving (Rotation)

1. Type something in the **text field**.
2. Rotate the device.
3. The activity is destroyed and recreated, but the text is restored automatically via `onSaveInstanceState`.
