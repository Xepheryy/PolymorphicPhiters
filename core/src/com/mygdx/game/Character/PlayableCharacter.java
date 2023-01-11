package com.mygdx.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.EnumMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** An abstract class representing the playable characters on screen */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PlayableCharacter extends Actor {

  private float width;
  private float height;
  private Vector2 position;
  private String name;
  private CharacterState state;
  private KeyboardState keyboardState;
  private double health;
  private final double maxHealth;
  private Map<CharacterState, Animation<TextureRegion>> animation;
  private boolean direction;
  private int specialType;
  private float animationTime;
  private double attackScale;
  private float movementScale;
  private Rectangle hurtBox, hitBox;
  private float hurtBoxXCoordinate;
  private float jumpHeight = 0f;
  private final int playerNumber;
  private static final float JUMP_FACTOR = 0.6f;
  public static final boolean DIRECTION_LEFT = false;
  public static final boolean DIRECTION_RIGHT = true;
  public static final double BASE_ATTACK = 10;
  public static final float BASE_MOVEMENT = 1;
  private BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("RaccoonSerif-Medium.fnt"));

  public PlayableCharacter(
      String name,
      double health,
      boolean direction,
      double attackScale,
      float movementScale,
      float hurtBoxXCoordinate,
      float hurtBoxWidth,
      float hurtBoxHeight,
      int playerNumber) {
    this.name = name;
    this.health = health;
    this.maxHealth = health;
    this.state = CharacterState.IDLE;
    this.position = new Vector2(0, 0);
    this.animation = new EnumMap<>(CharacterState.class);
    this.direction = direction;
    this.attackScale = attackScale;
    this.movementScale = movementScale;
    this.keyboardState = new KeyboardState();
    this.hurtBox = new Rectangle();
    this.hurtBoxXCoordinate = hurtBoxXCoordinate;
    this.hurtBox.width = hurtBoxWidth;
    this.hurtBox.height = hurtBoxHeight;
    this.hitBox = new Rectangle();
    this.hitBox.width = hurtBoxWidth * 2;
    this.hitBox.height = hurtBoxHeight;
    this.playerNumber = playerNumber;
  }

  public float getHurtBoxXCoordinate() {
    return this.hurtBox.x;
  }

  public float getHurtBoxWidth() {
    return this.hurtBox.width;
  }

  public float getHurtBoxHeight() {
    return this.hurtBox.height;
  }

  public void setHurtBoxXCoordinate(float hurtBoxXCoordinate) {
    this.hurtBox.x = hurtBoxXCoordinate;
  }

  public void setHurtBoxWidth(float hurtBoxWidth) {
    this.hurtBox.width = hurtBoxWidth;
  }

  public void setHurtBoxHeight(float hurtBoxHeight) {
    this.hurtBox.height = hurtBoxHeight;
  }

  /**
   * Determines the special abilities of each character
   *
   * <p>Implementation to be determined by individual characters
   *
   * @param isAnimationFinished a boolean value indicating if the animation of the character's
   *     sprite has finished
   */
  public abstract void special(boolean isAnimationFinished);

  public TextureRegion getCurrentFrame() {
    return this.getAnimation().getKeyFrame(animationTime, true);
  }

  public Animation<TextureRegion> getAnimation() {
    return animation.get(state);
  }

  public Animation<TextureRegion> getAnimation(CharacterState state) {
    return animation.get(state);
  }

  public float getXCoordinate() {
    return this.position.x;
  }

  public void setXCoordinate(float xCoordinate) {
    this.position.x = xCoordinate;
  }

  public float getYCoordinate() {
    return this.position.y;
  }

  public void setYCoordinate(float yCoordinate) {
    this.position.y = yCoordinate;
  }

  public boolean getDirection() {
    return this.direction;
  }

  public void move(boolean direction) {
    this.setXCoordinate(this.getXCoordinate() + (direction ? 1 : -1) * this.movementScale);
  }

  public void jump() {
    jumpHeight = jumpHeight + JUMP_FACTOR;
    this.setYCoordinate(this.getYCoordinate() - jumpHeight);
    if (this.getYCoordinate() <= 0) {
      this.setState(CharacterState.RUN);
      this.getKeyboardState().unsetPressedJump();
      this.setYCoordinate(0);
    }
  }

  public double getDamage() {
    return getAttackScale() * PlayableCharacter.BASE_ATTACK;
  }

  /**
   * A loader to load animation sprites for the characters
   *
   * @param manager the {@link com.badlogic.gdx.assets.AssetManager} to be used to load the assets
   * @param metaDataPath the path to the metadata file containing details about the animation
   *     sprites
   * @throws GdxRuntimeException when the animation sprite atlas is not found
   */
  protected void loadAssets(AssetManager manager, String metaDataPath) throws GdxRuntimeException {
    FileHandle gameAssets = Gdx.files.internal(metaDataPath);

    String lineSeparatorRegex = "\\r?\\n";

    for (String asset : gameAssets.readString().split(lineSeparatorRegex)) {
      manager.load(asset, TextureAtlas.class);
    }
    manager.finishLoading();

    for (String asset : gameAssets.readString().split(lineSeparatorRegex)) {
      String[] els = asset.split("/");
      String state = els[els.length - 1].split("\\.")[0];

      TextureAtlas texture = manager.get(asset);
      this.animation.put(
          CharacterState.valueOf(state.toUpperCase()),
          new Animation<TextureRegion>(1f / 10f, texture.getRegions()));
    }
  }

  /** Governs the current direction that the character faces */
  private void governDirection() {
    if (this.getDirection() == PlayableCharacter.DIRECTION_LEFT) {
      if (!this.getCurrentFrame().isFlipX()) this.getCurrentFrame().flip(true, false);
    } else {
      if (this.getCurrentFrame().isFlipX()) this.getCurrentFrame().flip(true, false);
    }
  }

  @Override
  public void act(float delta) {
    if (this.animationTime <= 20) this.animationTime = this.animationTime + delta;

    this.hurtBox.x = this.getXCoordinate() + this.hurtBoxXCoordinate;
    this.hurtBox.y = this.getYCoordinate();

    if (getDirection() == PlayableCharacter.DIRECTION_LEFT) {
      this.hitBox.x = this.hurtBox.x - (this.hitBox.width - this.hurtBox.width);
    } else this.hitBox.x = this.hurtBox.x;
    this.hitBox.y = this.hurtBox.y;

    // Player one
    if (this.getKeyboardState().pressedLeft()) {
      this.setState(CharacterState.RUN);
      this.move(PlayableCharacter.DIRECTION_LEFT);
      this.setDirection(PlayableCharacter.DIRECTION_LEFT);
    } else if (this.getKeyboardState().pressedRight()) {
      this.setState(CharacterState.RUN);
      this.move(PlayableCharacter.DIRECTION_RIGHT);
      this.setDirection(PlayableCharacter.DIRECTION_RIGHT);
    } else if (!this.getKeyboardState().pressedLeft() && !this.getKeyboardState().pressedRight()) {
      if ((this.getState() != CharacterState.ATTACK)
          && (this.getState() != CharacterState.SPECIAL)
          && (this.getState() != CharacterState.JUMP)
          && (this.getState() != CharacterState.DEAD)) {
        this.setState(CharacterState.IDLE);
      }
    }
    if (this.getKeyboardState().pressedSpecial()) {
      this.setState(CharacterState.SPECIAL);
      this.special(this.getAnimation(this.getState()).isAnimationFinished(this.animationTime));
    } else if (!this.getKeyboardState().pressedSpecial()) {
      if ((this.getState() != CharacterState.ATTACK)
          && (this.getState() != CharacterState.RUN)
          && (this.getState() != CharacterState.JUMP)
          && (this.getState() != CharacterState.DEAD)) {
        this.setState(CharacterState.IDLE);
      }
    }

    if (this.getKeyboardState().pressedJump()) {
      this.setState(CharacterState.JUMP);
      if (this.getYCoordinate() == 0) {
        this.setJumpHeight(-15f);
        jump();

      } else if (this.getYCoordinate() > 0) {
        jump();
      }
    }

    if (this.getKeyboardState().pressedAttack()) this.setState(CharacterState.ATTACK);

    governDirection();
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    TextureRegion frame = this.getCurrentFrame();
    this.setWidth(
        (int) this.getMaxHealth()
            / 100f
            * frame.getRegionWidth()
            * Gdx.graphics.getWidth()
            / 640f
            * 2f);
    this.setHeight(
        (int) this.getMaxHealth()
            / 100f
            * frame.getRegionHeight()
            * Gdx.graphics.getHeight()
            / 480f
            * 2f);
    batch.draw(frame, this.getXCoordinate(), this.getYCoordinate(), this.width, this.height);
    bitmapFont.draw(
        batch,
        "P" + playerNumber,
        this.width / 2 + this.getXCoordinate(),
        10 + this.height + this.getYCoordinate());
  }
}
