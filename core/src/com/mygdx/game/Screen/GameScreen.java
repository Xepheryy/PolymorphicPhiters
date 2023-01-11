package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Character.CharacterState;
import com.mygdx.game.Character.PlayableCharacter;
import com.mygdx.game.Items.AttackBuff;
import com.mygdx.game.Items.AttackDebuff;
import com.mygdx.game.Items.Items;
import com.mygdx.game.KeyboardControl.KeyboardControl;
import com.mygdx.game.KeyboardControl.KeyboardControlDAO;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.ScoreCalculator;
import com.mygdx.game.PolymorphicPhiters;
import com.mygdx.game.UI.Background;
import com.mygdx.game.UI.GameUI;
import com.mygdx.game.UI.Soundtrack;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/** Represents the main Game screen */
public class GameScreen extends AbstractScreen {
  private final PlayableCharacter[] characters = new PlayableCharacter[2];
  private int timer, comboTimer, numberOfRounds, player1Combo, player2Combo;
  private float secondCount;
  private final ArrayList<Items> items = new ArrayList<>();
  private Soundtrack soundtrack;
  private Sound hitSound;
  private GameUI gameUI;
  private boolean player1Hit, player2Hit;

  @SuppressWarnings("rawtypes")
  public GameScreen(
      PolymorphicPhiters game,
      String player1Selected,
      String player2Selected,
      String numberOfRounds) {
    super(game);
    KeyboardControl controls = KeyboardControlDAO.getKeyboardControl();
    this.numberOfRounds = Integer.parseInt(numberOfRounds);
    try {
      Class[] types = {
        String.class, AssetManager.class, Float.TYPE, Float.TYPE, Boolean.TYPE, Integer.TYPE
      };

      Constructor player1Constructor =
          Class.forName("com.mygdx.game.Character." + player1Selected).getConstructor(types);
      Constructor player2Constructor =
          Class.forName("com.mygdx.game.Character." + player2Selected).getConstructor(types);

      Object[] player1Parameters = {player1Selected, game.getAssetManager(), 0, 0, true, 1};
      Object[] player2Parameters = {player2Selected, game.getAssetManager(), 500, 0, false, 2};

      game.setPlayer1(
          new Player(
              0,
              0,
              0,
              0,
              0,
              0,
              controls.getPlayerOneKeyMapping(),
              (PlayableCharacter) player1Constructor.newInstance(player1Parameters)));
      game.setPlayer2(
          new Player(
              0,
              0,
              0,
              0,
              0,
              0,
              controls.getPlayerTwoKeyMapping(),
              (PlayableCharacter) player2Constructor.newInstance(player2Parameters)));

    } catch (GdxRuntimeException e) {
      System.err.println(e.getMessage());
      System.exit(-1);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    this.numberOfRounds = Integer.parseInt(numberOfRounds);

    this.player1Combo = 0;
    this.player2Combo = 0;

    this.secondCount = 0;
    this.comboTimer = 3;

    player1Hit = false;
    player2Hit = false;

    // generateItems();
  }

  /** Generate augment items */
  private void generateItems() {
    for (Items i : items) i.setRectExist(false);
    items.clear();
    int choice;
    for (int i = 0; i < 3; i++) {
      choice = (int) (Math.floor(Math.random() * (2 - 1 + 1) + 1));
      switch (choice) {
        case 1:
          items.add(
              new AttackBuff(
                  game.getAssetManager().get("AttackBuff.png", Texture.class),
                  Gdx.graphics.getWidth() / 6 * (i + 2)));
          break;
        case 2:
          items.add(
              new AttackDebuff(
                  game.getAssetManager().get("AttackDebuff.png", Texture.class),
                  Gdx.graphics.getWidth() / 6 * (i + 2)));
          break;
        default:
          break;
      }
    }
  }

  /** Handles the collision between items and characters */
  private void checkItemInteraction() {
    PlayableCharacter player1Character = characters[0], player2Character = characters[1];
    for (Items i : items) {
      if (player1Character.getHurtBox().overlaps(i.getRect())) {
        i.setRectExist(false);
        i.augmentPlayer(game.getPlayer1(), player1Character);
      } else if (player2Character.getHurtBox().overlaps(i.getRect())) {
        i.setRectExist(false);
        i.augmentPlayer(game.getPlayer2(), player2Character);
      }
    }
  }

  /** Performs set up for a new round */
  private void setNewRound() {
    timer = 60;
    for (Items i : items) i.removeAugment();
    generateItems();
    player1Hit = false;
    player2Hit = false;
    for (PlayableCharacter character : characters) {
      character.setHealth(character.getMaxHealth());
    }
  }

  /** Governs the boundaries of the characters on screen */
  private void governBoundaries() {
    for (PlayableCharacter character : characters) {
      float minimumBoundary = 0;
      float maximumBoundary = Gdx.graphics.getWidth() - character.getWidth();
      if (character.getXCoordinate() >= maximumBoundary) {
        character.setXCoordinate(maximumBoundary);
      }
      if (character.getXCoordinate() <= minimumBoundary) {
        character.setXCoordinate(minimumBoundary);
      }
    }
  }

  /** Governs the current state of the characters on screen */
  private void governState() {
    PlayableCharacter player1Character = characters[0], player2Character = characters[1];

    // Player One
    if (player1Character
        .getAnimation(player1Character.getState())
        .isAnimationFinished(player1Character.getAnimationTime())) {
      if (player1Character.getState() == CharacterState.ATTACK) {
        if (player1Character.getHitBox().overlaps(player2Character.getHurtBox())) {
          hitSound.play();
          player1Combo++;
          player2Combo = 0;
          comboTimer = 0;
          player2Hit = true;
          if (player2Character.getHealth() - player1Character.getDamage() > 0) {
            player2Character.setHealth(player2Character.getHealth() - player1Character.getDamage());
          } else if (player2Character.getHealth() - player1Character.getDamage() <= 0) {
            player2Character.setHealth(0);
            this.game.getPlayer1().setTimeTaken(this.game.getPlayer1().getTimeTaken() + 60 - timer);
            game.getPlayer1().setRoundsWon(game.getPlayer1().getRoundsWon() + 1);
            if (!player1Hit)
              this.game.getPlayer1().setPerfect(this.game.getPlayer1().getPerfect() + 1);
          }
          if (player2Character.getDirection() == PlayableCharacter.DIRECTION_LEFT)
            player2Character.setXCoordinate(
                player2Character.getXCoordinate() + player2Character.getMovementScale());
          else if (player2Character.getDirection() == PlayableCharacter.DIRECTION_RIGHT)
            player2Character.setXCoordinate(
                player2Character.getXCoordinate() - player2Character.getMovementScale());
        }
        player1Character.getKeyboardState().unsetPressedAttack();
        player1Character.setState(CharacterState.RUN);
      }
      player1Character.setAnimationTime(0);
    }

    // Player Two
    if (player2Character
        .getAnimation(player2Character.getState())
        .isAnimationFinished(player2Character.getAnimationTime())) {
      if (player2Character.getState() == CharacterState.ATTACK) {
        if (player2Character.getHitBox().overlaps(player1Character.getHurtBox())) {
          hitSound.play();
          player2Combo++;
          player1Combo = 0;
          comboTimer = 0;
          player1Hit = true;
          if (player1Character.getHealth() - player2Character.getDamage() > 0) {
            player1Character.setHealth(player1Character.getHealth() - player2Character.getDamage());
          } else if (player1Character.getHealth() - player2Character.getDamage() <= 0) {
            player1Character.setHealth(0);
            this.game.getPlayer2().setTimeTaken(this.game.getPlayer2().getTimeTaken() + 60 - timer);
            game.getPlayer2().setRoundsWon(game.getPlayer2().getRoundsWon() + 1);
            if (!player2Hit)
              this.game.getPlayer2().setPerfect(this.game.getPlayer2().getPerfect() + 1);
          }
          if (player1Character.getDirection() == PlayableCharacter.DIRECTION_LEFT)
            player1Character.setXCoordinate(
                player1Character.getXCoordinate() + player1Character.getMovementScale());
          else if (player1Character.getDirection() == PlayableCharacter.DIRECTION_RIGHT)
            player1Character.setXCoordinate(
                player1Character.getXCoordinate() - player1Character.getMovementScale());
        }
        player2Character.getKeyboardState().unsetPressedAttack();
        player2Character.setState(CharacterState.RUN);
      }
      player2Character.setAnimationTime(0);
    }
  }

  /**
   * Update the timer based on the animation delta time
   *
   * @param delta the animation delta time
   */
  private void timerUpdate(float delta) {
    secondCount += delta;
    if (secondCount >= 1) {
      timer--;
      this.gameUI.setTime(timer);
      secondCount = 0;
      if (comboTimer < 3) {
        comboTimer++;
      }
      if (comboTimer == 3) {
        this.game.getPlayer1().setCombo(this.game.getPlayer1().getCombo() + player1Combo);
        this.game.getPlayer2().setCombo(this.game.getPlayer2().getCombo() + player2Combo);
        player1Combo = 0;
        player2Combo = 0;
      }
    }
  }

  @Override
  public void show() {
    GameInputProcessor gameInputProcessor = new GameInputProcessor(this.game);
    Gdx.input.setInputProcessor(gameInputProcessor);

    game.getAssetManager().load("bensound-epic.mp3", Music.class);
    game.getAssetManager().load("hit.mp3", Sound.class);
    game.getAssetManager().load("fire_map.jpg", Texture.class);
    game.getAssetManager().load("AttackBuff.png", Texture.class);
    game.getAssetManager().load("AttackDebuff.png", Texture.class);
    game.getAssetManager().finishLoading();
    Music music = game.getAssetManager().get("bensound-epic.mp3", Music.class);
    hitSound = game.getAssetManager().get("hit.mp3", Sound.class);
    Texture backgroundImage = game.getAssetManager().get("fire_map.jpg", Texture.class);

    this.characters[0] = game.getPlayer1().getCharacterSelected();
    this.characters[1] = game.getPlayer2().getCharacterSelected();
    this.soundtrack = new Soundtrack(music);

    Background background =
        new Background(backgroundImage, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    this.player1Combo = 0;
    this.player2Combo = 0;
    getStage().addActor(background);

    this.timer = 60;
    this.secondCount = 0;
    this.comboTimer = 3;

    gameUI =
        new GameUI(
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight(),
            game.getPlayer1(),
            game.getPlayer2(),
            characters[0],
            characters[1]);
    gameUI.setTime(timer);
    for (PlayableCharacter character : characters) {
      getStage().addActor(character);
    }
    getStage().addActor(gameUI);
    getStage().addActor(this.soundtrack);

    generateItems();
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    game.getCamera().update();
    getStage().getBatch().setProjectionMatrix(game.getCamera().combined);
    getStage().act(delta);

    governBoundaries();

    getStage().draw();

    governState();

    if (characters[0].getHealth() <= 0 || characters[1].getHealth() <= 0) {
      ScoreCalculator scoreCalculator;
      if (game.getPlayer1().getRoundsWon() == numberOfRounds / 2 + 1) {
        scoreCalculator =
            new ScoreCalculator(
                game.getPlayer1().getRoundsWon(),
                game.getPlayer2().getRoundsWon(),
                game.getPlayer1().getTimeTaken(),
                game.getPlayer1().getCombo(),
                game.getPlayer1().getPerfect(),
                game.getPlayer1().getItemScore());
        game.setScreen(new ScoreBoardScreen(this.game, scoreCalculator, 1));
        dispose();
      } else if (game.getPlayer2().getRoundsWon() == numberOfRounds / 2 + 1) {
        scoreCalculator =
            new ScoreCalculator(
                game.getPlayer2().getRoundsWon(),
                game.getPlayer1().getRoundsWon(),
                game.getPlayer2().getTimeTaken(),
                game.getPlayer2().getCombo(),
                game.getPlayer2().getPerfect(),
                game.getPlayer2().getItemScore());
        game.setScreen(new ScoreBoardScreen(this.game, scoreCalculator, 2));
        dispose();
      } else {
        setNewRound();
      }
    } else if (timer <= 0) {
      setNewRound();
    }

    for (Items i : items) {
      getStage().addActor(i);
    }

    getStage().draw();
    checkItemInteraction();

    timerUpdate(delta);
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
    Gdx.input.setInputProcessor(null);
    soundtrack.stop();
    hitSound.dispose();
  }

  @Override
  void inputHandler() {}
}
