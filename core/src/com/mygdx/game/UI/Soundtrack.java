package com.mygdx.game.UI;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;
import lombok.Setter;

/** Represents the background music for screens */
@Getter
@Setter
public class Soundtrack extends Actor {

  private FileHandle musicFile;
  private Music music;

  public Soundtrack(Music music) {
    this.music = music;
    this.music.setVolume(0.5f);
    this.music.setLooping(true);
    this.music.play();
  }

  /** */
  public void stop() {
    this.music.stop();
  }
}
