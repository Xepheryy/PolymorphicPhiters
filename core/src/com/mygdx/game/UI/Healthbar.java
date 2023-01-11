package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Used to represent the current health of a {@link com.mygdx.game.Character.PlayableCharacter} */
@Data
@EqualsAndHashCode(callSuper = true)
public class Healthbar extends Actor {
  private ShapeRenderer shapeRenderer;
  private float x, y, maxWidth, height;
  private Color color;

  public Healthbar(float x, float y, float height) {
    shapeRenderer = new ShapeRenderer();
    this.x = x;
    this.y = y;
    this.height = height;
    this.color = Color.GREEN;
  }

  public Healthbar(float x, float y, float height, float maxWidth) {
    this(x, y, height);
    this.maxWidth = maxWidth;
    this.color = Color.RED;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.end();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(color);
    shapeRenderer.rect(x, y, maxWidth, height);
    shapeRenderer.end();
    batch.begin();
  }

  /**
   * A custom draw method to draw the green health bar
   *
   * @param batch
   * @param maxWidth
   * @param width
   */
  public void draw(Batch batch, float maxWidth, float width) {
    batch.end();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(color);
    if (x != 0) shapeRenderer.rect(x + (maxWidth - width), y, width, height);
    else shapeRenderer.rect(x, y, width, height);
    shapeRenderer.end();
    batch.begin();
  }
}
