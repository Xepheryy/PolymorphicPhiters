package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;
import lombok.Setter;

/** Represents the background image for screens */
@Getter
@Setter
public class Background extends Actor {
  private Texture background;
  float screenWidth, screenHeight;

  public Background(Texture backgroundImage, float screenWidth, float screenHeight) {
    this.background = backgroundImage;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.draw(this.background, 0, 0, this.screenWidth, this.screenHeight);
  }
}
