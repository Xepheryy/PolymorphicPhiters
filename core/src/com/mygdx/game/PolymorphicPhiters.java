package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screen.StartScreen;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The main game object
 *
 * @author Tan Wei Shaun
 * @author Chng Wei Cheng
 * @author Wong Ding Jie, Darren
 * @author Harish Balamurugan
 * @author Zames Li Chengyang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PolymorphicPhiters extends Game {

  // camera to set viewport
  private OrthographicCamera camera;
  private AssetManager assetManager;
  private SpriteBatch spriteBatch;
  private BitmapFont bitmapFont;
  private String[] characters = {"Jerry", "Tammy"};

  private Player player1, player2;

  @Override
  public void create() {
    this.assetManager = new AssetManager();
    this.spriteBatch = new SpriteBatch();
    this.camera = new OrthographicCamera();

    this.bitmapFont = new BitmapFont(Gdx.files.internal("RaccoonSerif-Medium.fnt"));

    setScreen(new StartScreen(this));
  }

  public void render() {
    super.render(); // important!
  }

  @Override
  public void dispose() {
    spriteBatch.dispose();
    bitmapFont.dispose();
    assetManager.dispose();
  }
}
