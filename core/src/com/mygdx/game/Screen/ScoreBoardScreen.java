package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Player.ScoreCalculator;
import com.mygdx.game.PolymorphicPhiters;

/** Represents the score board screen */
public class ScoreBoardScreen extends AbstractScreen {
  private final ScoreCalculator scoreCalculator;

  public ScoreBoardScreen(PolymorphicPhiters game, ScoreCalculator scoreCalculator, int playerNo) {
    super(game);
    this.scoreCalculator = scoreCalculator;

    Table table = new Table();
    table.defaults().pad(10);
    table.setFillParent(true);

    LabelStyle labelStyle = new LabelStyle(game.getBitmapFont(), Color.WHITE);
    table.add(new Label("Player " + playerNo + " wins!", labelStyle));
    table.row();
    if (scoreCalculator.getPerfectRounds() > 0) {
      table.add(new Label(scoreCalculator.getPerfectRounds() + " perfect rounds", labelStyle));
      table.add(new Label(Integer.toString(scoreCalculator.perfect()), labelStyle));
      table.row();
    }
    if (scoreCalculator.noRoundLoss() > 0) {
      table.add(new Label("No rounds lost", labelStyle));
      table.add(new Label(Integer.toString(scoreCalculator.noRoundLoss()), labelStyle));
      table.row();
    }
    table.add(new Label("Time bonus", labelStyle));
    table.add(new Label(Integer.toString(scoreCalculator.timeBonus()), labelStyle));
    table.row();
    table.add(new Label("Combo score", labelStyle));
    table.add(new Label(Integer.toString(scoreCalculator.getComboCount()), labelStyle));
    table.row();
    table.add(new Label("Item score", labelStyle));
    table.add(new Label(Integer.toString(scoreCalculator.getItemScore()), labelStyle));
    table.row();
    table.add(new Label("Final score", labelStyle));
    table.add(new Label(Integer.toString(scoreCalculator.calculateScore()), labelStyle));

    getStage().addActor(table);
  }

  @Override
  public void inputHandler() {}

  @Override
  public void show() {}

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0, 1);

    getStage().draw();
    getStage().act();

    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
      game.setScreen(new LeaderBoardScreen(this.game, this.scoreCalculator.calculateScore()));
      dispose();
    }
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    getStage().dispose();
  }
}
