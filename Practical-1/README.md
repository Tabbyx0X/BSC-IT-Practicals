# Practical 1 – Calculator App

## Aim

Design and implement a simple **Calculator** Android application using **Kotlin** and **XML Views** that supports basic arithmetic operations (addition, subtraction, multiplication, and division) with a clean, dark-themed UI modelled after the standard iOS/Android system calculator.

---

## UI Overview

The main screen consists of:

| Element | Description |
|---|---|
| **Display** | Single-line `TextView` (right-aligned, large text) showing the current input or result. |
| **Button grid** | 4 × 5 `GridLayout` inside a `ConstraintLayout`. |

### Button layout

```
[ C ]  [ +/− ]  [ ⌫ ]  [ ÷ ]
[ 7 ]  [  8  ]  [ 9 ]  [ × ]
[ 4 ]  [  5  ]  [ 6 ]  [ − ]
[ 1 ]  [  2  ]  [ 3 ]  [ + ]
[   0   ]        [ . ]  [ = ]
```

Three button styles are used:
- **Digit** buttons – dark grey background.
- **Function** buttons (`C`, `+/−`, `⌫`) – medium grey background.
- **Operator/Equals** buttons (`÷`, `×`, `−`, `+`, `=`) – orange background.

---

## Logic Approach

The app uses an **immediate-execution** style (like most physical calculators):

1. The user enters a number (first operand).
2. The user presses an operator key.  The current display value is stored as the running result and the operator is recorded.
3. The user enters a second number.
4. Pressing `=` (or another operator) applies the pending operation:
   ```
   result = result  <operator>  currentInput
   ```
5. The display shows the new result, which becomes the next first operand.

> **Note:** Because of the immediate-execution model, operator precedence is *not* applied.  For example, `2 + 3 × 4 =` evaluates as `(2 + 3) × 4 = 20`, not `2 + 12 = 14`.  This matches the behaviour of classic pocket calculators.

### Special cases

| Scenario | Behaviour |
|---|---|
| **Divide by zero** | Display shows `Error: ÷0`.  All state is reset; press `C` to continue. |
| **Toggle sign** (`+/−`) | Negates the currently displayed number. |
| **Backspace** (`⌫`) | Removes the last digit of the current input.  Has no effect after `=` is pressed. |
| **Decimal point** (`.`) | Added once per number; prepends `0` if input is empty. |

### State preservation

All calculator state (`currentInput`, `result`, `pendingOperator`, display text, error flag) is saved in `onSaveInstanceState` and restored in `onCreate`, so the display survives screen rotation.

---

## Project Structure

```
Practical-1/
├── README.md                       ← this file
└── CalculatorApp/                  ← Android Studio project root
    ├── gradlew / gradlew.bat
    ├── gradle/
    │   ├── libs.versions.toml
    │   └── wrapper/
    ├── settings.gradle.kts
    ├── build.gradle.kts
    ├── gradle.properties
    └── app/
        ├── build.gradle.kts
        └── src/main/
            ├── AndroidManifest.xml
            ├── kotlin/com/example/calculatorapp/
            │   └── MainActivity.kt
            └── res/
                ├── layout/activity_main.xml
                ├── values/
                │   ├── colors.xml
                │   ├── strings.xml
                │   └── themes.xml
                └── mipmap-anydpi-v26/
                    ├── ic_launcher.xml
                    └── ic_launcher_round.xml
```

---

## How to Run

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or newer.
- **Android SDK** with compile SDK 34 / target SDK 34 installed.
- An AVD (emulator) or physical device running Android 7.0 (API 24) or higher.

### Steps

1. Open Android Studio → **File → Open** → select the `Practical-1/CalculatorApp/` folder.
2. Wait for Gradle sync to finish.
3. Click **Run ▶** (or press `Shift + F10`) to build and deploy.

Alternatively, build from the command line:

```bash
cd Practical-1/CalculatorApp
./gradlew assembleDebug
```

The debug APK will be placed at `app/build/outputs/apk/debug/app-debug.apk`.

---

## Screenshots

> _Screenshots will be added after first device run._

| Screen | Preview |
|---|---|
| Calculator (idle) | _(placeholder)_ |
| After calculation | _(placeholder)_ |
| Divide-by-zero error | _(placeholder)_ |
