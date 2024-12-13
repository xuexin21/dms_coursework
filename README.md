# Project Title: Sky Battle

## GitHub Repository
[Sky Battle GitHub Repository](https://github.com/your-repository-link)

## Compilation Instructions
1. Clone the repository using:
   ```bash
   git clone https://github.com/your-repository-link.git
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure you have the following dependencies installed:
   - JavaFX SDK 11 or later
   - JUnit 5 for testing
4. Set the JavaFX module path in your IDE settings.
   - For IntelliJ IDEA:
     1. Go to **File > Project Structure > Libraries**.
     2. Add the JavaFX library path.
   - For Eclipse:
     1. Go to **Properties > Java Build Path > Libraries**.
     2. Add the JavaFX library path.
5. Run the `Main` class located in `com.example.demo.controller` to launch the application.

## Implemented and Working Properly
### Features:
1. **Main Menu**: Includes a background video, play button, sound toggle, music toggle, and exit button.
2. **Pause Menu**: Allows the user to pause the game and resume, restart, or exit to the main menu.
3. **Levels**:
   - Level One: Introduces basic enemies.
   - Level Two: Features a boss battle with dynamic shield mechanics.
   - Level Three: Includes diverse elements like butterflies, obstacles, and advanced enemies.
4. **Win/Lose Screens**: Displays appropriate messages and buttons for user actions.
5. **Music and Sound Effects**: Background music and sound effects for gameplay events.
6. **Dynamic Health Display**: Updates based on player actions.

## Implemented but Not Working Properly
1. **Boss Shield Behavior**: Shield does not deactivate as expected in rare scenarios.
   - **Attempted Fix**: Debugged shield activation logic; needs further testing.
2. **Projectile Sound Overlap**: Multiple projectile sounds overlap excessively.
   - **Attempted Fix**: Adjusted sound playback timing.

## Features Not Implemented
1. **Level Four**: Partially designed but not fully implemented.
   - Reason: Time constraints and dependency on prior level completion.
2. **Multiplayer Mode**: Initially planned but deprioritized due to complexity.

## New Java Classes
1. **`SecondEnemyPlane`**: Represents advanced enemy planes in higher levels.
2. **`LevelViewLevelThree`**: Custom view for Level Three, displaying advanced elements.
3. **`BossHealthDisplay`**: Displays the boss’s health dynamically.
4. **`ShieldImage`**: Visual representation of the boss’s shield.

## Modified Java Classes
1. **`LevelParent`**:
   - Added logic for obstacles and butterflies.
   - Improved spawn rates for dynamic elements.
2. **`Boss`**:
   - Enhanced shield behavior with activation and deactivation probabilities.
3. **`Music`**:
   - Introduced mute/unmute functionality.
4. **`MainMenu`**:
   - Integrated background video and additional toggles.

## Unexpected Problems
1. **JavaFX Initialization Issues**:
   - Problem: `NullPointerException` due to delayed initialization.
   - Solution: Used `CountDownLatch` for synchronization.
2. **Audio Lag**:
   - Problem: Audio playback was delayed during transitions.
   - Solution: Preloaded media files to reduce latency.
3. **Testing with JavaFX**:
   - Problem: JavaFX components required proper thread handling.
   - Solution: Used `JFXPanel` and `Platform.runLater` for test initialization.

---
Thank you for reviewing the project. Please reach out for further clarifications or enhancements.

