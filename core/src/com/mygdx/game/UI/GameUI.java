package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Character.PlayableCharacter;
import com.mygdx.game.Player.Player;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/** Represents the UI elements needed by the game screen */
@Data
@EqualsAndHashCode(callSuper = true)
public class GameUI extends AbstractUI {
  private int screenWidth, screenHeight;
  private int time;

  @Setter(AccessLevel.PRIVATE)
  @Getter(AccessLevel.PRIVATE)
  private CharacterUI[] characterUIs = new CharacterUI[2];

  private BitmapFont font;

  @Setter(AccessLevel.PRIVATE)
  @Getter(AccessLevel.PRIVATE)
  private Player[] players = new Player[2];

  public GameUI(
      int screenWidth,
      int screenHeight,
      Player player1,
      Player player2,
      PlayableCharacter... playableCharacters) {
    super(screenWidth, screenHeight);
    int character1X = 0;
    int character2X = screenWidth / 7 * 4;
    font = new BitmapFont(Gdx.files.internal("RaccoonSerif-Medium.fnt"));

    characterUIs[0] =
        new CharacterUI(character1X, screenWidth, screenHeight, playableCharacters[0]);
    characterUIs[1] =
        new CharacterUI(character2X, screenWidth, screenHeight, playableCharacters[1]);

    this.players[0] = player1;
    this.players[1] = player2;
  }

  public void setTime(int time) {
    this.time = time;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    for (CharacterUI characterUI : characterUIs) {
      characterUI.draw(batch, parentAlpha);
    }
    font.draw(
        batch, String.format("%02d", time), super.getScreenWidth() / 2f, super.getScreenHeight());
    font.draw(batch, "Player 1", 0, super.getScreenHeight() - super.getScreenHeight() / 16f);
    font.draw(
        batch,
        String.format("Score: %d", this.getPlayers()[0].getScore()),
        100,
        super.getScreenHeight() - super.getScreenHeight() / 16f);
    font.draw(
        batch,
        "Player 2",
        super.getScreenWidth() / 8f * 7,
        super.getScreenHeight() - super.getScreenHeight() / 16f);
    font.draw(
        batch,
        String.format("Score: %d", this.getPlayers()[1].getScore()),
        super.getScreenWidth() / 8f * 7 - 100,
        super.getScreenHeight() - super.getScreenHeight() / 16f);
  }
}
