package com.mygdx.game.KeyboardControl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains the {@link com.mygdx.game.KeyboardControl.KeyMapping} objects of both player one and
 * player two respectively.
 *
 * <p>To be used when initialising the key mapping to be used by each player
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardControl {
  private KeyMapping playerOneKeyMapping;
  private KeyMapping playerTwoKeyMapping;
}
