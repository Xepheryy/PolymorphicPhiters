package com.mygdx.game.Character;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents the state of the keyboard, more specifically the keys required for each action in the
 * game (e.g. left, right, attack, etc.)
 */
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardState {
  private boolean pressedLeft, pressedRight, pressedJump, pressedAttack, pressedSpecial;

  public boolean pressedLeft() {
    return this.pressedLeft;
  }

  public void setPressedLeft() {
    this.pressedLeft = true;
  }

  public void unsetPressedLeft() {
    this.pressedLeft = false;
  }

  public boolean pressedRight() {
    return this.pressedRight;
  }

  public void setPressedRight() {
    this.pressedRight = true;
  }

  public void unsetPressedRight() {
    this.pressedRight = false;
  }

  public boolean pressedJump() {
    return this.pressedJump;
  }

  public void setPressedJump() {
    this.pressedJump = true;
  }

  public void unsetPressedJump() {
    this.pressedJump = false;
  }

  public boolean pressedAttack() {
    return this.pressedAttack;
  }

  public void setPressedAttack() {
    this.pressedAttack = true;
  }

  public void unsetPressedAttack() {
    this.pressedAttack = false;
  }

  public boolean pressedSpecial() {
    return this.pressedSpecial;
  }

  public void setPressedSpecial() {
    this.pressedSpecial = true;
  }

  public void unsetPressedSpecial() {
    this.pressedSpecial = false;
  }
}
