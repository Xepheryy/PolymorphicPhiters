package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Character.PlayableCharacter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Represents the UI elements needed for Characters, namely the Healthbar */
@Data
@EqualsAndHashCode(callSuper = true)
public class CharacterUI extends AbstractUI {
  private PlayableCharacter playableCharacter;
  private Healthbar healthBarRed, healthBarGreen;
  private float maxWidth;

  public CharacterUI(
      int x, int screenWidth, int screenHeight, PlayableCharacter playableCharacter) {
    super(screenWidth, screenHeight);
    this.playableCharacter = playableCharacter;
    float y = screenHeight - screenHeight / 16f, height = screenHeight / 16f;
    this.maxWidth = screenWidth / 7f * 3;
    healthBarRed = new Healthbar(x, y, height, maxWidth);
    healthBarGreen = new Healthbar(x, y, height);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    healthBarRed.draw(batch, parentAlpha);
    healthBarGreen.draw(
        batch,
        maxWidth,
        (float) (maxWidth * playableCharacter.getHealth() / playableCharacter.getMaxHealth()));
  }
}
