package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.LeaderBoard.LeaderBoard;
import com.mygdx.game.LeaderBoard.LeaderBoardDAO;
import com.mygdx.game.LeaderBoard.LeaderBoardRecord;
import com.mygdx.game.PolymorphicPhiters;

/** Represents the leader board screen */
public class LeaderBoardScreen extends AbstractScreen {
  private LeaderBoard leaderBoard;
  private TextField usernameField;
  private String username;
  private boolean updateLeaderBoard;
  private Label usernameLabel;
  private final LabelStyle labelStyle;
  private final long score;

  public LeaderBoardScreen(PolymorphicPhiters game, long score) {
    super(game);
    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    labelStyle = new LabelStyle(game.getBitmapFont(), null);
    this.leaderBoard = LeaderBoardDAO.getLeaderBoard();
    if (this.leaderBoard == null) this.leaderBoard = new LeaderBoard();
    updateLeaderBoard = false;
    this.score = score;

    if (this.score == 0) updateLeaderBoard = true;
    else if (this.leaderBoard.getRankings().size() == 10) {
      if (this.score
          < this.leaderBoard
              .getRankings()
              .get(this.leaderBoard.getRankings().size() - 1)
              .getScore()) updateLeaderBoard = true;
    }

    game.getBitmapFont()
        .getData()
        .setScale(Gdx.graphics.getWidth() / 640f, Gdx.graphics.getHeight() / 480f);
    skin.add("font", game.getBitmapFont());

    if (!updateLeaderBoard) {
      usernameLabel = new Label("Username: ", labelStyle);
      usernameLabel.setPosition(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 2f);
      usernameLabel.setSize(10, 10);

      TextFieldStyle textFieldStyle = new TextFieldStyle();
      textFieldStyle.font = skin.getFont("font");
      textFieldStyle.fontColor = Color.WHITE;
      textFieldStyle.cursor = skin.getDrawable("cursor");

      usernameField = new TextField("", textFieldStyle);
      usernameField.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
      usernameField.setSize(100, 10);
      getStage().setKeyboardFocus(usernameField);

      usernameField.setTextFieldListener(
          new TextField.TextFieldListener() {

            @Override
            public void keyTyped(TextField textField, char c) {
              if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
                username = textField.getText();
              }
            }
          });

      getStage().addActor(usernameLabel);
      getStage().addActor(usernameField);
    } else updateLeaderBoard = true;
  }

  @Override
  public void inputHandler() {
    if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
      game.setScreen(new StartScreen(this.game));
      dispose();
    }
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(getStage());
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0, 1);
    int rank = 1;

    getStage().draw();
    getStage().act();

    if (username != null && !username.equals("") && !updateLeaderBoard) {
      leaderBoard.updateRankings(new LeaderBoardRecord(username, this.score));
      LeaderBoardDAO.saveLeaderBoard(leaderBoard);
      updateLeaderBoard = true;
      usernameLabel.remove();
      usernameField.remove();
    }

    if (updateLeaderBoard) {
      Table table = new Table();
      table.defaults().pad(10);
      table.setFillParent(true);

      if (!this.leaderBoard.getRankings().isEmpty()) {
        for (LeaderBoardRecord l : this.leaderBoard.getRankings()) {
          table.add(new Label(Integer.toString(rank), labelStyle));
          table.add(new Label(l.getName(), labelStyle));
          table.add(new Label(Long.toString(l.getScore()), labelStyle));
          table.row();
          rank++;
        }
      } else {
        table.add(new Label("Effective leaders are made, not born.", labelStyle));
        table.row();
      }
      getStage().addActor(table);
    }
    inputHandler();
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
