package com.mygdx.game.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Represents UI elements that the screens need */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractUI extends Actor {
  private int screenWidth, screenHeight;
}
