package com.mygdx.game.KeyboardControl;

import com.mygdx.game.dao.DataAccessObject;

/**
 * The Data Access Object to retrieve and save {@link
 * com.mygdx.game.KeyboardControl.KeyboardControl} objects
 */
public class KeyboardControlDAO extends DataAccessObject {
  private static final String KEY_MAPPING_FILE = "key_mappings.json";

  /**
   * Retrieves a KeyboardControl object from some source (can be from a file, database, etc.)
   *
   * @return The KeyboardControl object retrieved
   */
  public static KeyboardControl getKeyboardControl() {
    KeyboardControl keyboardControl = readFromFile(KEY_MAPPING_FILE, KeyboardControl.class);
    if (keyboardControl == null) {
      KeyMapping playerOneKeyMapping = new KeyMapping("A", "D", "W", "S", "Q", "E");
      KeyMapping playerTwoKeyMapping =
          new KeyMapping("Left", "Right", "Up", "Down", "R-Alt", "R-Ctrl");
      keyboardControl = new KeyboardControl(playerOneKeyMapping, playerTwoKeyMapping);
      saveKeyboardControl(keyboardControl);
    }
    return keyboardControl;
  }

  /** NOTE: For testing purposes only */
  public static KeyboardControl getKeyboardControlTest(String fileName) {
    return readFromFile(fileName, KeyboardControl.class);
  }

  /**
   * Saves a given KeyboardControl object to some destination (can be to a file, database, etc.)
   *
   * @param control a KeyboardControl object to be saved
   */
  public static void saveKeyboardControl(KeyboardControl control) {
    writeToFile(KEY_MAPPING_FILE, control);
  }

  /** NOTE: For testing purposes only */
  public static void saveKeyboardControlTest(KeyboardControl control, String fileName) {
    writeToFile(fileName, control);
  }
}
