package com.mygdx.game.Screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.PolymorphicPhiters;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/** Represents an interface for the player to interact with */
@Data
public abstract class AbstractScreen implements Screen {
  public PolymorphicPhiters game;

  @Setter(AccessLevel.PRIVATE)
  private Stage stage;

  public AbstractScreen(PolymorphicPhiters game) {
    this.game = game;
    this.stage = new Stage();
  }

  /** To be overridden to implement custom input handling logic */
  abstract void inputHandler();
}
