# 🐟 Aqua Eater (libGDX)

Aqua Eater is a 2D arcade-style game developed in **Java** using the **libGDX** framework.

This project is a **remake of my original Aqua Eater game built with Pygame**. The goal was not to redesign the game, but to recreate it using **libGDX** in order to learn the framework, understand its architecture, and gain experience with Java game development.

---

## Gameplay

The player controls a fish that must survive in the ocean by eating smaller fish while avoiding larger ones.

As the player grows, new fish skins become available, boosters appear, and the game ends once the player becomes the biggest fish in the ocean.

---

## Features

- 🐟 Fish selection system with unlockable skins
- 📈 High score system
- 🦈 Dynamic enemy fish with different sizes
- ⚡ Booster fish with temporary effects
- 🎯 Win condition
- 💀 Game Over screen
- 🎉 Victory screen
- 🔊 Sound effects
- 💾 Persistent high score using Preferences

---

## Booster System

There are 4 different booster fish:

- 🛡️ **Armor Fish** – protects the player from being eaten
- 🔥 **Frenzy Fish** – allows eating any fish regardless of size
- ⚡ **Speed Fish** – increases movement speed
- ☠️ **Poison Fish** – decreases movement speed and score

> Only one booster can be active at a time.  
> Booster effects last **8 seconds**.

---

## Controls

- ⬅️ ➡️ Select fish
- **ENTER** Start game
- **W A S D** or **Arrow Keys** Move the player

---

## Tech Stack

- **Java**
- **libGDX**
- **LWJGL3**

---

## Project Structure

```text
AquaEater/
├── assets/
│   ├── images/
│   ├── sounds/
│   └── fonts/
├── core/
├── lwjgl3/
└── build.gradle
```

---

## Sound Effects

- `eat.wav` – played when eating a fish
- `victory.wav` – played after winning
- `game_over.wav` – played when the player loses

---

## High Score System

The game stores the highest score locally using **libGDX Preferences**, allowing progress to persist between sessions.

---

## Learning Goals

This project was created to:

- Learn the libGDX framework
- Practice game architecture in Java
- Recreate an existing game using a different technology stack
- Compare game development in **Pygame** and **libGDX**

---

## Run the Project

```bash
./gradlew lwjgl3:run
```
