## Polymorphic Phiters

> Welcome to the game repo!

#### Game mechanics

Two players each choose a unique character and duke it out in a best-of format of their choice!

Win by reducing your opponent's health to 0. Items in the stage may give an advantage or result in frustration.

The winner's score is calculated at the end and if it is good enough, it will be on the leaderboard!

- Jerry

![alt text](https://github.com/sgtechICT1009/ict1009-team27-2022/blob/master/images/jerry.PNG)

Has a heal as his special ability

- Tammy

![alt text](https://github.com/sgtechICT1009/ict1009-team27-2022/blob/master/images/tammy.PNG)

Has a dash as his special ability

- Attack Buff

![alt text](https://github.com/sgtechICT1009/ict1009-team27-2022/blob/master/images/attackBuff.PNG)

Increases receiving player's attack, allowing him to deal more damage. Gives 50 points to their final score

- Attack Debuff

![alt text](https://github.com/sgtechICT1009/ict1009-team27-2022/blob/master/images/attackDebuff.PNG)

Decreases receiving player's attack, reducing his damage dealt, but gives more points. Gives 75 points to their final score

### Running the game on cmdline

```bash
# Windows
.\gradlew.bat run

# Linux/Mac
./gradlew run
```

### Running the game from `releases`

1. Download `PolymorphicPhiters.jar` & `chat_history.json`
2. Ensure chat_history.json is in the same directory as the jar
3. double click the jar or run the command `java -jar PolymorphicPhiters.jar`

#### Configuring `key_mappings.json`

The instruction and example for configuring `key_mappings.json` (to customise the keyboard controls used in the game) can be found [here](./docs/CONFIGURE_KEY_MAPPINGS.md)


### Developing

- jdk 8-15
- Your java ide of choice (eclipse/netbeans/intellij)
  - install lombok for your ide
- Vscode
  - install java extensions
  - install lombok support (lombok-vscode)

> Samples of leaderboard, key_mappings and chat_history can be found [here](./docs)
