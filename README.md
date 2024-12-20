# Snake Game in Java

## Overview
This project is a classic Snake game implemented in Java. The game features a graphical interface where the player controls a snake to collect apples, growing longer with each apple eaten. The game ends when the snake collides with itself or when out of bounds. 

## Features
- Grid-based game mechanics
- Randomly placed apples
- Colorful snake body
- Score tracking
- Game over and retry functionality

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Ensure you have JDK 8 or later installed.
- **IDE**: Any Java IDE like IntelliJ IDEA, Eclipse, or VS Code is recommended.

### Installation
1. Clone the repository or download the ZIP file.
    ```bash
    git clone https://github.com/elbrimyo/snakegonga.git
    ```
2. Open the project in your preferred IDE.
3. Ensure the source files (`Main.java`, `gameFrame.java`, `gamePanel.java`) are correctly loaded.
4. Compile and run the `Main.java` file to start the game.

### How to Play
1. Use the arrow keys to control the snake's movement:
   - **Up Arrow**: Move up
   - **Down Arrow**: Move down
   - **Left Arrow**: Move left
   - **Right Arrow**: Move right
2. Collect red apples to grow longer and increase your score.
3. Avoid colliding with the snake's own body or the game boundaries.
4. When the game is over, press `R` to restart.

## File Structure
- **Main.java**: Contains the main method to initialize the game.
- **gameFrame.java**: Sets up the game window using Java Swing.
- **gamePanel.java**: Implements the game mechanics, rendering, and logic.

## Code Highlights
- **Random Apple Placement**: Apples are placed randomly within the grid.
- **Snake Movement and Collision Detection**: Movement logic updates the snake's position while detecting collisions with itself or the borders.
- **Dynamic Color Effects**: The snake's body segments are rendered with dynamic colors for a more engaging visual experience.

## Development Notes
- The game is built using Java Swing for the graphical interface.
- The gameplay loop is managed using a timer to control the snake's movement.
- Scoring and game over messages are displayed directly on the game panel.

## Screenshots
![image](https://github.com/user-attachments/assets/0030c3f1-a414-4792-b619-b2f679fcdc52)

![image](https://github.com/user-attachments/assets/c14fefec-67a9-4bbd-a6b8-6b6b902b716a)
