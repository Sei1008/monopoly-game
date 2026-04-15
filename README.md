# Monopoly Game

A digital implementation of the classic board game Monopoly, built in Java using the MVC (Model-View-Controller) architecture.

## 📂 Project Structure

To maintain clean code and a strict separation of concerns, this project follows a specific directory structure. All code should be organized into the following folders based on its purpose:

| Folder | Purpose | Examples |
| :--- | :--- | :--- |
| **`models/`** | Data classes (nouns) representing the game state. | `Player`, `Board`, `Property`, `Dice`, `Square` |
| **`services/`** | Business logic (verbs) that manipulate the models. | `GameService`, `GameLogic`, `PropertyService` |
| **`controllers/`** | Handles user actions, processes input, and calls services. | User input processing, main game loop control |
| **`views/`** | Displays information and UI to the user. | Print board, show player info, graphical/console UI |
| **`utils/`** | Helper and utility functions. | Constants, text formatting, input validation |
| **`config/`** | Configuration settings. | Game constants, difficulty settings, startup configs |

## 🛠️ Getting Started

1. Clone the repository to your local machine using Git.
Clone project:
```bash
   git clone [https://github.com/Sei1008/monopoly-game.git](https://github.com/Sei1008/monopoly-game.git)
```
2. Open the project in your preferred IDE (such as VS Code).
3. Ensure you have the Java Development Kit (JDK) installed and your environment variables configured.
4. Compile and run the main entry point of the application to start a new game.

## 📝 Commit Message Conventions

When collaborating on this repository, please adhere to the following commit message prefixes to keep the version control history clean, readable, and standardized:

* **`feat:`** Add a new feature to the system.
* **`fix:`** Fix a bug.
* **`refactor:`** Refactor code without changing the logic (no new features, no bug fixes).
* **`perf:`** Optimize code and improve performance.
* **`style:`** Edit code formatting (spaces, indentation, semicolons, etc.) without affecting functionality.
* **`docs:`** Add or update documentation (e.g., updating this `README.md` or Swagger).
* **`test:`** Add new test cases or modify existing ones.
* **`chore:`** General maintenance tasks, library updates, or minor configuration changes.
* **`build / ci:`** Changes related to the project build process or CI/CD pipelines (e.g., Webpack, Docker, GitHub Actions).

---
**Example Commit:** `feat: implement dice rolling logic in GameService`