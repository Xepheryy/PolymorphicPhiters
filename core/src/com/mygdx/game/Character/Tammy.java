package com.mygdx.game.Character;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Represents Tammy
 *
 * <p>Special abilities: - Dash (costs health)
 */
public class Tammy extends PlayableCharacter {
  public Tammy(
      String name,
      AssetManager manager,
      float xCoordinate,
      float yCoordinate,
      boolean direction,
      int playerNumber)
      throws GdxRuntimeException {
    super(name, 100, direction, 1.5, 5f, 35, 35, 55, playerNumber);
    setXCoordinate(xCoordinate);
    setYCoordinate(yCoordinate);
    loadAssets(manager, "Tammy.txt");
  }

  @Override
  public void special(boolean isAnimationFinished) {

    if (this.getHealth() > 10) {
      this.setHealth(this.getHealth() - 0.05);
      this.setMovementScale(20f);
      if (getDirection() == PlayableCharacter.DIRECTION_LEFT) {
        move(PlayableCharacter.DIRECTION_LEFT);
      } else if (getDirection() == PlayableCharacter.DIRECTION_RIGHT) {
        move(PlayableCharacter.DIRECTION_RIGHT);
      }
      this.setMovementScale(5f);
    }
  }
}
