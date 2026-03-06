# Practical 6 – Passing Data Between Activities Using an Explicit Intent

## Aim / Objective

Demonstrate how to pass data from one Android `Activity` to another using an **explicit Intent** and `putExtra` / `getStringExtra`.

---

## What Is an Explicit Intent?

An **Intent** is a messaging object used to request an action from another app component. An **explicit** intent names the exact component (class) to start—unlike an *implicit* intent, which describes the action to perform and lets the OS pick a suitable component.

### Intent Extras

Data is attached to an Intent as key–value pairs called **extras**:

```kotlin
// Sender (MainActivity) — attach data
val intent = Intent(this, SecondActivity::class.java).apply {
    putExtra("com.example.intentdemo.EXTRA_NAME", name)
    putExtra("com.example.intentdemo.EXTRA_MESSAGE", message)
}
startActivity(intent)

// Receiver (SecondActivity) — read data
val name    = intent.getStringExtra("com.example.intentdemo.EXTRA_NAME") ?: ""
val message = intent.getStringExtra("com.example.intentdemo.EXTRA_MESSAGE") ?: ""
```

Using fully-qualified string keys (prefixed with the package name) prevents collisions with extras from other apps.

---

## Project Structure

```
Practical-6/
├── app/
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/com/example/intentdemo/
│       │   ├── MainActivity.kt     ← input fields + Send button + putExtra
│       │   └── SecondActivity.kt   ← receives extras and displays them
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml
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

## Steps to Open / Build / Run in Android Studio

1. Open **Android Studio** → **Open** → select the `Practical-6/` folder.
2. Wait for the **Gradle sync** to finish (first run downloads dependencies).
3. Connect a physical device **or** start an **Android Emulator** (API 24+).
4. Click **Run ▶** (or press `Shift + F10`).

---

## Expected Output

### MainActivity (first screen)

| UI Element | Description |
|---|---|
| **Name** field | `EditText` for the sender's name |
| **Message** field | `EditText` for a short message |
| **Send** button | Validates inputs; starts `SecondActivity` with extras |

Tapping **Send** with empty fields shows an inline error; once both fields are filled the app navigates to the second screen.

### SecondActivity (second screen)

| UI Element | Description |
|---|---|
| **Name:** label + value | Displays the name received via `getStringExtra` |
| **Message:** label + value | Displays the message received via `getStringExtra` |
| **Go Back** button | Calls `finish()` to return to `MainActivity` |

The received data is shown exactly as typed in `MainActivity`, confirming that the Intent extras were passed and read correctly.
