package com.mygdx.game.Screen;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Character.CharacterState;
import com.mygdx.game.Character.PlayableCharacter;
import com.mygdx.game.PolymorphicPhiters;

/** An input processor for the {@link com.mygdx.game.Screen.GameScreen} */
public class GameInputProcessor implements InputProcessor {
  private final PolymorphicPhiters game;
  PlayableCharacter player1Character, player2Character;

  public GameInputProcessor(PolymorphicPhiters game) {
    this.game = game;
    this.player1Character = game.getPlayer1().getCharacterSelected();
    this.player2Character = game.getPlayer2().getCharacterSelected();
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean scrolled(float amountX, float amountY) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    // Player 1
    if (keycode == game.getPlayer1().getKeyMapping().getLeft()) {
      this.player1Character.getKeyboardState().unsetPressedLeft();
      this.player1Character.getKeyboardState().unsetPressedRight();
    }

    if (keycode == game.getPlayer1().getKeyMapping().getRight()) {
      this.player1Character.getKeyboardState().unsetPressedLeft();
      this.player1Character.getKeyboardState().unsetPressedRight();
    } else if (keycode == game.getPlayer1().getKeyMapping().getSpecial()) {
      this.player1Character.getKeyboardState().unsetPressedSpecial();
    }

    // Player 2
    if (keycode == game.getPlayer2().getKeyMapping().getLeft()) {
      this.player2Character.getKeyboardState().unsetPressedLeft();
      this.player2Character.getKeyboardState().unsetPressedRight();
    }

    if (keycode == game.getPlayer2().getKeyMapping().getRight()) {
      this.player2Character.getKeyboardState().unsetPressedLeft();
      this.player2Character.getKeyboardState().unsetPressedRight();
    } else if (keycode == game.getPlayer2().getKeyMapping().getSpecial()) {
      this.player2Character.getKeyboardState().unsetPressedSpecial();
    }

    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean keyDown(int keycode) {
    // Player 1
    if (keycode == game.getPlayer1().getKeyMapping().getJump()) {
      this.player1Character.getKeyboardState().setPressedJump();
    } else if (game.getPlayer1().getCharacterSelected().getState() != CharacterState.JUMP) {
      if (keycode == game.getPlayer1().getKeyMapping().getLeft()) {
        this.player1Character.getKeyboardState().setPressedLeft();
        this.player1Character.getKeyboardState().unsetPressedRight();
      } else if (keycode == game.getPlayer1().getKeyMapping().getRight()) {
        this.player1Character.getKeyboardState().setPressedRight();
        this.player1Character.getKeyboardState().unsetPressedLeft();
      } else if (keycode == game.getPlayer1().getKeyMapping().getAttack()) {
        this.player1Character.getKeyboardState().setPressedAttack();
      } else if (keycode == game.getPlayer1().getKeyMapping().getSpecial()) {
        this.player1Character.getKeyboardState().setPressedSpecial();
      }
    }

    // Player 2
    if (keycode == game.getPlayer2().getKeyMapping().getJump()) {
      this.player2Character.getKeyboardState().setPressedJump();
    } else if (game.getPlayer2().getCharacterSelected().getState() != CharacterState.JUMP) {
      if (keycode == game.getPlayer2().getKeyMapping().getLeft()) {
        this.player2Character.getKeyboardState().setPressedLeft();
        this.player2Character.getKeyboardState().unsetPressedRight();
      } else if (keycode == game.getPlayer2().getKeyMapping().getRight()) {
        this.player2Character.getKeyboardState().setPressedRight();
        this.player2Character.getKeyboardState().unsetPressedLeft();
      } else if (keycode == game.getPlayer2().getKeyMapping().getAttack()) {
        this.player2Character.getKeyboardState().setPressedAttack();
      } else if (keycode == game.getPlayer2().getKeyMapping().getSpecial()) {
        this.player2Character.getKeyboardState().setPressedSpecial();
      }
    }

    return false;
  }
}
