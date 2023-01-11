package com.mygdx.game.Character;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents Jerry
 *
 * <p>Special abilities: - Heal
 */
@Getter
@Setter
public class Jerry extends PlayableCharacter {
  private double healValue;

  public Jerry(
      String name,
      AssetManager manager,
      float xCoordinate,
      float yCoordinate,
      boolean direction,
      int playerNumber)
      throws GdxRuntimeException {
    super(name, 150, direction, 1, 2f, 102, 80, 120, playerNumber);
    setXCoordinate(xCoordinate);
    setYCoordinate(yCoordinate);
    this.healValue = this.getMaxHealth() * 0.05;
    loadAssets(manager, "Jerry.txt");
  }

  @Override
  public void special(boolean isAnimationFinished) {
    // heal
    if (isAnimationFinished) {
      if (this.getHealth() >= this.getMaxHealth()) {
        return;
      } else if (this.getHealth() + this.getHealValue() > this.getMaxHealth()) {
        this.setHealth(this.getMaxHealth());
        return;
      }
      this.setHealth(this.getHealth() + this.getHealValue());
    }
  }
}
