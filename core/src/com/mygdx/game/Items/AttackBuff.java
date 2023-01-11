package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Character.PlayableCharacter;
import com.mygdx.game.Player.Player;

/** Increase attack of character and increase score of player */
public class AttackBuff extends Items {
  private PlayableCharacter playableCharacter;
  private double originalAttack;

  public AttackBuff(Texture texture, int xCoordinates) {
    super.setTexture(texture);
    super.setXCoordinates(xCoordinates);
  }

  public void augmentPlayer(Player player, PlayableCharacter playableCharacter) {
    player.setItemScore(player.getItemScore() + 50);
    player.setScore(player.getScore() + 50);
    this.playableCharacter = playableCharacter;
    this.originalAttack = playableCharacter.getAttackScale();
    this.playableCharacter.setAttackScale(playableCharacter.getAttackScale() * 1.4);
  }

  public void removeAugment() {
    if (!super.isRectExist()) this.playableCharacter.setAttackScale(this.originalAttack);
  }
}
