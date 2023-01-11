package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.PolymorphicPhiters;
import com.mygdx.game.UI.Background;
import com.mygdx.game.UI.Soundtrack;

/** Represents the menu screen shown at the start */
public class StartScreen extends AbstractScreen {
  private Soundtrack soundTrack;

  public StartScreen(final PolymorphicPhiters game) {
    super(game);
  }

  @Override
  void inputHandler() {}

  /**
   * Function to reverse the list of selectable characters that is shown to player 2
   *
   * <p>This is so as to make both players select different characters by default
   *
   * @param characters array of strings representing the list of available selectable characters
   * @return The given array, reversed
   */
  private String[] reverse(String[] characters) {
    String[] reversed = new String[characters.length];
    for (int i = 0; i < characters.length; i++) {
      reversed[i] = characters[characters.length - i - 1];
    }
    return reversed;
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(getStage());
    game.getAssetManager().load("startscreen/waterfall_menu.jpg", Texture.class);
    game.getAssetManager().finishLoading();
    Texture backgroundImage =
        game.getAssetManager().get("startscreen/waterfall_menu.jpg", Texture.class);

    Background background =
        new Background(backgroundImage, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    getStage().addActor(background);

    float cw = Gdx.graphics.getWidth();
    float ch = Gdx.graphics.getHeight();

    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    skin.add("font", game.getBitmapFont());
    Table table = new Table();
    table.setFillParent(true);

    game.getAssetManager().load("bensound-inspire.mp3", Music.class);
    game.getAssetManager().load("startscreen/buttons.atlas", TextureAtlas.class);
    game.getAssetManager().finishLoading();
    Music music = game.getAssetManager().get("bensound-inspire.mp3", Music.class);
    soundTrack = new Soundtrack(music);
    skin.addRegions((TextureAtlas) game.getAssetManager().get("startscreen/buttons.atlas"));
    TextButtonStyle textButtonStyle =
        new TextButtonStyle(
            skin.getDrawable("normal_button_n"),
            skin.getDrawable("normal_button_p"),
            skin.getDrawable("normal_button_h"),
            game.getBitmapFont());

    LabelStyle labelStyle = new LabelStyle(game.getBitmapFont(), null);
    final SelectBox<String> player1Selection = new SelectBox<>(skin);
    final SelectBox<String> player2Selection = new SelectBox<>(skin);
    final SelectBox<String> bestOfSelection = new SelectBox<>(skin);

    Label title = new Label("Polymorphic Phiters", labelStyle);

    Label player1SelectionLabel = new Label("Player 1", labelStyle);
    Label player2SelectionLabel = new Label("Player 2", labelStyle);
    Label bestOfSelectionLabel = new Label("Best of", labelStyle);

    TextButton playButton = new TextButton("Play", textButtonStyle);
    TextButton helpButton = new TextButton("Help", textButtonStyle);
    TextButton leaderBoardButton = new TextButton("Leaderboard", textButtonStyle);
    // leaderBoardButton.getLabel().setFontScale();
    TextButton quitButton = new TextButton("Quit", textButtonStyle);

    // set positions for elements on screen
    player1Selection.setItems(game.getCharacters());

    player2Selection.setItems(reverse(game.getCharacters()));

    bestOfSelection.setItems("1", "3", "5", "7");

    // button and drop down selection handlers
    player1Selection.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {}
        });

    player2Selection.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {}
        });

    bestOfSelection.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {}
        });

    playButton.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(
                new GameScreen(
                    game,
                    player1Selection.getSelected(),
                    player2Selection.getSelected(),
                    bestOfSelection.getSelected()));
            dispose();
          }
        });

    helpButton.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(new HelpScreen(game));
            dispose();
          }
        });

    leaderBoardButton.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(new LeaderBoardScreen(game, 0));
            dispose();
          }
        });

    quitButton.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            Gdx.app.exit();
          }
        });

    table.setHeight(ch * 0.8f);
    table.setWidth(cw * 0.8f);
    table.add(title).colspan(4).spaceBottom(ch * 0.1f).align(Align.top);
    table.row();
    table.add(player1SelectionLabel).pad(10);
    table.add(player1Selection);
    table.add(player2SelectionLabel).pad(10);
    table.add(player2Selection);
    table.row();
    table.add(bestOfSelectionLabel).colspan(2);
    table.add(bestOfSelection).colspan(2);
    table.row();
    table.add(playButton).colspan(4);
    table.row();
    table.add(helpButton).colspan(4);
    table.row();
    table.add(leaderBoardButton).colspan(4);
    table.row();
    table.add(quitButton).colspan(4);
    table.row();
    getStage().addActor(soundTrack);
    getStage().addActor(table);
  }

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    game.getCamera().update();
    getStage().getBatch().setProjectionMatrix(game.getCamera().combined);

    getStage().act();
    getStage().draw();
  }

  @Override
  public void resize(int width, int height) {
    getStage().getViewport().update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    getStage().dispose();
    soundTrack.stop();
  }
}
