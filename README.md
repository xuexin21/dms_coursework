# COMP2042 Developing Maintainable Software Coursework

## Overview
 **Title** : Sky Battle
 
**Description** : Sky Battle is an engaging 2D shooting plane game where players navigate through thrilling levels filled with enemies, obstacles, and challenges. With vibrant visuals, dynamic gameplay, and immersive sound effects, Sky Battle tests your reflexes and strategy as you battle through waves of enemy planes, dodge obstacles, and face off against formidable bosses.

## Tutorial

- **Level One**  
  - Players must destroy 10 enemy planes to advance. This level introduces the game's basic mechanics.  

- **Level Two**  
  - Players face a boss plane that periodically activates a shield. Damage can only be dealt when the shield is deactivated, requiring strategic timing and patience.  

- **Level Three**  
  - Players must destroy 40 enemy planes. This level introduces butterflies as power-ups, granting benefits like extra lives, increased projectile speed, and enhanced movement speed.  

- **Level Four**  
  - Players face two boss planes. Initially, only one boss is present, but a second boss spawns when the first boss's health drops to 50%. The second boss has a higher fire rate, increasing the challenge. Butterflies remain as power-ups for additional strategy and reward.

## User Interface

- **Main Menu**  
  - The entry point to the game, offering options to start the game, toggle music and sound, and exit.

- **Pause Menu**  
  - Allows players to pause the game and access options to resume, adjust audio settings, or return to the main menu.  

- **Level Completion Menu**  
  - Displays after finishing a level, offering the choice to proceed to the next level or return to the main menu.  

- **Lose Menu**  
  - Appears when a level is failed, providing options to retry the level, exit the game, or return to the main menu.  

- **Win Menu**  
  - Shown upon completing all levels. Players can either return to the main menu or exit the game.  

- **Audio Settings**  
  - Accessible through all menus, allowing players to toggle music and sound on or off, enabling a customizable audio experience.


## GitHub Repository
[Sky Battle GitHub Repository](https://github.com/xuexin21/dms_coursework.git)

## Compilation Instructions
1. - Clone the repository using:
   ```bash
   git clone https://github.com/xuexin21/dms_coursework.git
   ```
   - Download zip file from Github and unzip the file
2. Open the project in IntelliJ IDEA.
3. Ensure the following dependencies installed:
   - JavaFX SDK 19 or later
   - JUnit 5 for testing
4. Set the JavaFX module path in your IDE settings.
     1. Go to **File > Project Structure > Libraries**.
     2. Add the JavaFX library path.
5. Set the Maven Dependencies and Maven will build the project.
6. Run the `Main` class located in `com.example.demo.controller` to launch the application.

## Implemented and Working Properly

### Fixed Bugs:
1. **Progression to Level 2**  
   - **Issue**: Shield image file extension and path were incorrect, and `timeline.stop()` was missing during level transitions.  
   - **Fix**: Corrected the file reference and added `timeline.stop()` upon level completion to ensure smooth transitions.

2. **Shield Tracking**  
   - **Issue**: The shield did not move with the boss.  
   - **Fix**: Implemented logic to dynamically update the shield's position based on the boss's movements.

### Features:
1. **Main Menu**  
   - Includes a background video, play button, sound toggle, music toggle, and exit button.  

2. **Pause Menu**  
   - Allows pausing the game with options to resume, restart, or return to the main menu.  

3. **Level Completion Menu**  
   - Displays options to proceed to the next level or return to the main menu after completing a level.  

4. **Lose Menu**  
   - Displays upon losing the game, with options to retry, exit, or return to the main menu.  

5. **Win Menu**  
   - Displays after completing the game, allowing the player to return to the main menu or exit.  

6. **Pause Button**  
   - Pauses the game and opens the pause menu when clicked.  

7. **Alternative Keys for Firing**  
   - Enables firing using either the space bar or mouse click.  

8. **Alternative Movement Keys**  
   - Supports both arrow keys (`↑`, `↓`, `←`, `→`) and `W`, `A`, `S`, `D` for movement.  

9. **Projectile Collision Handling**  
   - User projectiles can destroy enemy and boss projectiles upon impact.  

10. **Boss Health Progress Bar**  
    - Displays a dynamic progress bar tracking the boss's health.  

11. **Kill Count Display**  
    - Displays the player's kill count during gameplay.  

12. **Level Display**  
    - Displays the current level information prominently.  

13. **Sound Effects**  
    - Integrated sound effects for enhanced gameplay immersion.  

14. **Background Music**  
    - Added background music to enrich the game atmosphere.  

### Levels:
1. **Level One**  
   - Objective: Destroy 10 enemy planes to advance.  

2. **Level Two**  
   - Features a boss with a shield and dynamic health progress bar. The boss's health is set to 50.  

3. **Level Three**  
   - Introduces obstacles and butterflies as power-ups. Players must destroy 40 enemy planes with new designs compared to Level Two.  

4. **Level Four**  
   - Features two boss planes. The second boss spawns when the first boss's health drops to 50%. Includes obstacles and butterflies to balance difficulty.


## Implemented but Not Working Properly

1. **Boss Health Progress Bar**  
   - **Issue**: Occasionally, during the transition to levels with a boss, the health progress bar briefly appears at an incorrect position in the top-right corner before displaying as intended.  
   - **Possible Cause**: Likely related to timing or layout initialization issues during scene transitions.  
   - **Impact**: Minimal, as the issue resolves itself quickly without affecting gameplay.  

2. **Background Video in Main Menu**  
   - **Issue**: The background video sometimes fails to display on the main menu. Restarting the game usually resolves this issue.  
   - **Possible Cause**: Media file preloading or rendering issues in JavaFX.  
   - **Impact**: Affects visual aesthetics, but the functionality of the main menu remains intact.  


## Features Not Implemented
1. **Level Four Advanced Mechanics**:
  - Details : Planned feature where the boss becomes temporarily invisible and summons additional enemy planes as its health drops to specific thresholds.
  - Reason: Time constraints made it challenging to design and test these advanced mechanics effectively within the project timeline.

2. **Leaderboard System**
  -  Details: Intended to store and display player scores, highlighting top performances.
  - Reason : Developing a robust storage mechanism and integrating it with the game flow required more time than was available.

3. **Enhanced User Projectile Mechanics** :
   -  Details: Envisioned feature allowing user projectiles to fire in diagonal directions as part of a power-up.
  -  Reason: Complexity of implementation, including adapting collision detection and animation, could not be addressed due to time limitations.

**Reflection**: These features were deprioritized in favor of ensuring core game functionality and a polished experience. Improved time management and earlier planning would have allowed more focus on these advanced features.

## New Java Classes

| **Class Name**         | **Description**                                                                                         | **File Location**                                       |
|-------------------------|---------------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| **Music**              | Handles background music playback for different game states.                                           | `src/main/java/com/example/demo/audio/Music.java`       |
| **Sound**              | Manages sound effects for in-game actions like shooting or collisions.                                 | `src/main/java/com/example/demo/audio/Sound.java`       |
| **LevelMenu**          | Displays a menu allowing players to either proceed to the next level or return to the main menu.        | `src/main/java/com/example/demo/controller/inGame/LevelMenu.java` |
| **LoseMenu**           | Shows a menu when the player fails a level, with options to retry, exit, or return to the main menu.    | `src/main/java/com/example/demo/controller/inGame/LoseMenu.java` |
| **MainMenu**           | Implements the main menu with a background video, play button, music/sound toggles, and an exit option.| `src/main/java/com/example/demo/controller/inGame/MainMenu.java` |
| **PauseButton**        | Handles the pause functionality by displaying the pause menu when clicked.                              | `src/main/java/com/example/demo/controller/inGame/PauseButton.java` |
| **PauseMenu**          | Provides options to resume the game, adjust sound/music, or return to the main menu during gameplay.    | `src/main/java/com/example/demo/controller/inGame/PauseMenu.java` |
| **WinMenu**            | Displays after all levels are completed, offering options to return to the main menu or exit the game.  | `src/main/java/com/example/demo/controller/inGame/WinMenu.java` |
| **LevelViewLevelOne**  | Manages Level One-specific visuals, such as kill count displays.                                        | `src/main/java/com/example/demo/level/levelview/LevelViewLevelOne.java` |
| **LevelViewLevelThree**| Handles Level Three visuals, including kill count updates and UI adjustments.                          | `src/main/java/com/example/demo/level/levelview/LevelViewLevelThree.java` |
| **LevelViewLevelFour** | Manages Level Four visuals, including shield and boss health progress bar displays.                     | `src/main/java/com/example/demo/level/levelview/LevelViewLevelFour.java` |
| **LevelThree**         | Implements the logic and visuals for Level Three, featuring enemy planes, power-ups, and obstacles.     | `src/main/java/com/example/demo/level/LevelThree.java`  |
| **LevelFour**          | Implements Level Four, including two boss planes and challenging mechanics.                            | `src/main/java/com/example/demo/level/LevelFour.java`   |
| **Butterfly**          | Introduces a power-up mechanic where players can gain extra lives, speed boosts, or projectile upgrades.| `src/main/java/com/example/demo/model/Butterfly.java`   |
| **Obstacle**           | Adds obstacles to the game to increase difficulty by obstructing the player's view of enemies.          | `src/main/java/com/example/demo/model/Obstacle.java`    |
| **SecondBoss**         | A more challenging variant of the boss with a higher fire rate.                                        | `src/main/java/com/example/demo/model/SecondBoss.java`  |
| **SecondEnemyPlane**   | Implements a new type of enemy plane with distinct attributes and behaviors.                           | `src/main/java/com/example/demo/model/SecondEnemyPlane.java` |
| **BossHealthDisplay**  | Displays the boss’s remaining health as a progress bar.                                                | `src/main/java/com/example/demo/view/BossHealthDisplay.java` |
| **KillCountDisplay**   | Updates and shows the player's kill count and remaining kills needed to advance.                       | `src/main/java/com/example/demo/view/KillCountDisplay.java` |
| **LevelFourDisplay**   | Displays a message indicating that the player is currently in Level Four.                              | `src/main/java/com/example/demo/view/LevelFourDisplay.java` |
| **LevelOneDisplay**    | Displays a message indicating that the player is currently in Level One.                               | `src/main/java/com/example/demo/view/LevelOneDisplay.java` |
| **LevelThreeDisplay**  | Displays a message indicating that the player is currently in Level Three.                             | `src/main/java/com/example/demo/view/LevelThreeDisplay.java` |
| **LevelTwoDisplay**    | Displays a message indicating that the player is currently in Level Two.                               | `src/main/java/com/example/demo/view/LevelTwoDisplay.java` |



## Modified Java Classes

1. `Main` 
  - **Changes** : Implemented a Main Menu as the entry point of the application, transitioning to a Model-View-Controller (MVC) pattern.
  - **Purposes** : Enhances organization and maintainability. The Main Menu provides better user experience by allowing players to navigate easily and access different game features without starting the game directly.

2. `LevelView` :
  - **Changes** : Adjusted image positions and sizes for consistency across devices and introduced the `hideHeartDisplay` method. Refactored to follow the MVC pattern.
  - **Purposes** : Improves game aesthetics and maintainability by adapting images for various screen resolutions and aspect ratios. The `hideHeartDisplay` method facilitates dynamic UI updates for heart display management.

3. `LevelViewLevelTwo` : 
- **Changes** : Added methods to show, hide, and update the shield and progress bar. Managed the display of the "Level Two" text. Refactored for MVC compliance.
- **Purposes** : Streamlines the management of UI components specific to Level Two. Shield and progress bar interactions enhance gameplay dynamics, while level-specific text improves user clarity and engagement.

4. `LevelParent` : 
  - **Changes** : Introduced collision handling between enemy and user projectiles, alternative key bindings, and mouse input. Integrated sound, music, menus (`LevelMenu`, `LoseMenu`, `PauseMenu`, `WinMenu`), and the `PauseButton`. Added support for spawning and managing butterflies and obstacles. Refactored to adhere to the MVC pattern.
  - **Purposes** : Centralizes core game logic, enhancing functionality and user accessibility. Adding alternative controls and audio features enriches gameplay. Managing menus and new models ensures a seamless game flow.

5. `LevelOne` :
   - **Changes** : Added dynamic kill count display, show/hide logic for level name and kill count, and a Level Menu for transitions. Integrated music and sound handling. Refactored for MVC compliance.
   - **Purposes** : Simplifies the management of level-specific features like kill counts and menus, enhancing clarity and gameplay experience. Music and sound create a more immersive atmosphere.
  
6. `LevelTwo` :
   - **Changes** : Introduced shield, progress bar, and "Level Two" text management. Managed music, sound, and transitions via a Level Menu. Refactored to align with MVC principles.
   - **Purposes** : Provides a richer gameplay experience by incorporating level-specific features like the shield and progress bar. Menu integration improves navigation and user control.

7. `Boss` :
   - **Changes** : Adjusted the boss's layout for better screen adaptation and resolved shield logic issues. Refactored to MVC pattern.
   - **Purposes** :  Ensures a consistent and visually appealing boss layout on different devices. Proper shield handling enhances gameplay challenge and strategy.

8. `UserPlane` :
   - **Changes** : Added horizontal movement, power-up methods (e.g., speed boosts, extra lives), and reset functionality. Refactored to MVC structure.
   - **Purposes** : Enhances player control and gameplay mechanics. Power-up methods improve replayability, while reset functionality ensures balanced transitions between levels.

9. `UserProjectile`:
    - **Changes** : Adjusted image size and position for better accuracy and added methods to increase and reset projectile speed. Refactored to MVC principles.
    - **Purposes** : Improves gameplay precision and adapts to updated user plane layouts. Speed adjustment methods support power-up functionality.
  
10. `BossProjectile`, `EnemyProjectile` :
    - **Changes** : Optimized image size and position for consistent gameplay visuals. Refactored to MVC structure.
    - **Purposes** : Enhances visual consistency and maintains accuracy across devices and resolutions.
   
11. `HeartDisplay` :
    - **Changes** : Added `hideHeartDisplay` method and aligned the class with MVC standards.
    - **Purposes** : Simplifies heart management, allowing dynamic updates to UI elements when needed.
   
12. `GameOverImage`, `ShieldImage`, `WinImage` :
    - **Changes** : Adjusted image dimensions to fit screen sizes dynamically. Refactored to MVC compliance.
    - **Purposes** : Maintains visual consistency across varying screen resolutions, ensuring a polished and professional game presentation.


## Unexpected Problems

1. **Thread Management Challenges**:
   
   - Issue : Poor initial thread management caused game corruption, leading to erratic behavior and instability.
   - Solution : Initially, an animation timer was used to manage threads, but it resulted in inconsistent game speed. After careful consideration, the implementation was switched to `ExecutorService` for better thread control, leading to improved performance and stability.
  
    
2. **Audio Playback Delay** :
   
  -  Issue : Audio playback experienced noticeable delays during scene transitions, detracting from the user experience.
  -  Solution : Media files were preloaded into memory to minimize latency, ensuring smoother audio transitions during gameplay.

3. **Scene Rendering Lag**:
  - Issue : Scene rendering exhibited lag during intensive graphical updates, particularly when transitioning between levels.
  - Solution : Optimized the graphical assets and rendering logic by reducing redundant updates and using efficient image scaling techniques. This significantly improved performance during scene transitions.
