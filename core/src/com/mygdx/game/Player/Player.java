package com.mygdx.game.Player;

import com.mygdx.game.Character.PlayableCharacter;
import com.mygdx.game.KeyboardControl.KeyMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Represents the player playing the game */
@Data
@AllArgsConstructor
public class Player {
  private long score;
  private int roundsWon, timeTaken, combo, perfect, itemScore;
  private KeyMapping keyMapping;
  private PlayableCharacter characterSelected;
}
