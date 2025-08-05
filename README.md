# "Hitmans" Game Role Generator
**"Hitmans"** - my fictional multiplayer role-playing game.  
Players are randomly assigned roles with specific missions and targets. The game revolves around survival, strategy, and intrigue.

I didn’t create a full game because I’m not an AAA game studio; in this pet project, I focused entirely on the ***logic of assigning roles, missions, and targets*** to players; all running in a plain console interface.



## Technologies

- Fully implemented in **Java**.
- Console-based application without graphical interface.

## Features:
- Random assignment of roles such as Hitman, Survivor, Bodyguard, Civilian, etc. Each role has unique missions and rules for assigning targets.
- Logical constraints ensure roles and targets are assigned following specific rules.
- Console-based UI for interaction — simple and clear.

## Roles

- **🎯 Hitman**  
  Assigned as a hitman whose mission is to eliminate a specific player.  
  The Hitman’s task is direct: _“Eliminate player X. Don’t ask unnecessary questions.”_

- **🏃🏻‍️ Survivor**  
  The Survivor’s goal is pure survival: _“Survive at any cost.”_  
  They receive information about who is hunting them, so they can avoid danger.

- **🛡️ Bodyguard**  
  Protects another player instead of killing.  
  The Bodyguard’s mission is to safeguard their assigned target.
  This role has special rules to avoid dangerous or prohibited targets.

- **💩 Civilian**  
  The “ordinary” role with no special offensive or defensive abilities.  
  The goal is simply to survive.

- **💀 Juggernaut**  
  A relentless killer whose mission is to eliminate all other players without mercy.

- **🗡️ Pro Killer**  
  A skilled assassin who can kill any player of their choice, deciding who lives or dies.

- **🤝 Right Hand**  
  An assistant role that supports a killer ally, helping to eliminate their target.

- **🧟 Zombie**  
  A fearsome undead whose goal is to kill and infect other players, growing an army of the undead.

- **🦎 Chameleon**  
  Can choose between two different roles during the game.

- **🎩 President**  
  A Survivor variant whose death triggers penalties for others, making survival critical.

- **🐗 Prey**  
  The hunted player — must survive - anyone who kills the Prey receives a reward.

- **🔮 Seer**  
  A non-killing role that predicts the fates of other players, gaining strategic insight into other's targets.

## Technical Highlights

- **Object-Oriented Design:**  
  Uses a clean object-oriented architecture with an abstract `Role` class and subclasses for specific roles (`Hitman`, `Survivor`, `Bodyguard`, `Civilian`).  
  This makes it easy to extend the game with new roles without changing existing code.

- **Factory Pattern:**  
  The `RolesFactory` class implements the factory pattern to create role objects.  
  This centralizes role creation and enforces role limits.

- **Encapsulation and Modularity:**  
  The logic for assigning roles and targets is separated into different classes (`RolesAssigner`, `TargetsAssigner`), which simplifies maintenance and scalability.

- **Use of Collections:**  
  Collections like `ArrayList` are used for storing and checking target assignment rules, providing flexibility in logic configuration.

- **Unique Logic and Rules:**  
  Each role contains its own logic and conditions for target assignment by overriding methods from the abstract `Role` class, enabling extensibility and customized behavior.

- **Easy Extensibility:**  
  To add a new role, simply create a subclass of `Role` and register it in `RolesFactory`.  
  This follows the Open/Closed Principle from SOLID design principles.

## Example
Here is a sample output of the game running in the console:
```
Enter the number of players: 4
Please enter the names one by one:
Player 1: Jack
Player 2: SCRIPT
Player 3: Player3
Player 4: Michael

Showing roles for players...

Press Enter to finish...

Player Jack - 🎯 Hitman
Your mission: eliminate player Michael.
Don’t ask unnecessary questions.

Player SCRIPT - 🛡️ Bodyguard
Your mission: protect player Jack.
If they fall, you lose everything.

Player Player3 - 🏃🏻‍️ Survivor
Your mission: survive at any cost
Nobody is hunting you

Player Michael - 🏃🏻‍️ Survivor
Your mission: survive at any cost
You are a target of Jack
```