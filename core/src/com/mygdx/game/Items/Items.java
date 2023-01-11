package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Character.PlayableCharacter;
import com.mygdx.game.Player.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Represents the items in the game */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Items extends Actor {
  private Rectangle rect = new Rectangle();
  private boolean rectExist = true;
  private Texture texture = null;
  private float xCoordinates = 0;
  private Sprite sprite;

  /**
   * Modifies a character's stats and adds to the player's score
   *
   * <p>Implementation depends on individual items
   *
   * @param player The player to be augmented
   * @param playableCharacter The character that the player has chosen, to be augmented
   */
  public abstract void augmentPlayer(Player player, PlayableCharacter playableCharacter);

  /**
   * Used to remove modifications to reset characters' stats
   *
   * <p>Implementation depends on individual items
   */
  public abstract void removeAugment();

  @Override
  public void draw(Batch batch, float parentAlpha) {
    if (rectExist) {

      sprite = new Sprite(texture);
      sprite.setPosition(xCoordinates, 170);
      sprite.setSize(60, 60);
      sprite.draw(batch);

      rect.x = xCoordinates + 15;
      rect.y = 180;
      rect.width = 30;
      rect.height = 30;
    } else {
      rect.y = -100;
    }
  }
}
