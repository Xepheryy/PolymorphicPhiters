package src.test.java.dao;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.game.KeyboardControl.KeyboardControl;
import com.mygdx.game.KeyboardControl.KeyboardControlDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** Test Class for KeyboardControlDAO */
public class KeyboardControlDAOTest {
  private static final String TEST_DATA =
      "{\"playerOneKeyMapping\":{\"left\":\"H\",\"right\":\"K\",\"jump\":\"U\",\"crouch\":\"J\",\"attack\":\"I\",\"special\":\"Y\"},\"playerTwoKeyMapping\":{\"left\":\"Z\",\"right\":\"X\",\"jump\":\"C\",\"crouch\":\"V\",\"attack\":\"A\",\"special\":\"S\"}}";

  private static KeyboardControl testKeyboardControlObject;

  private static final String TEST_READ_FILE = "key_mappings_read_test.json";
  private static final String TEST_WRITE_FILE = "key_mappings_write_test.json";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    // Set up json serializer
    Json json = new Json();
    json.setOutputType(JsonWriter.OutputType.json);

    // Set up test objects (will be used to test both reading and writing)
    testKeyboardControlObject = json.fromJson(KeyboardControl.class, TEST_DATA);

    String keyboardControlTestFileContents = json.prettyPrint(testKeyboardControlObject);

    FileWriter file_writer = new FileWriter(TEST_READ_FILE);
    BufferedWriter writer = new BufferedWriter(file_writer);
    writer.write(keyboardControlTestFileContents, 0, keyboardControlTestFileContents.length());
    writer.close();
    file_writer.close();
  }

  @AfterClass
  public static void tearDownAfterClass() {
    // Remove files created by/for write file operation and
    // read file operation respectively
    File[] files = {new File(TEST_READ_FILE), new File(TEST_WRITE_FILE)};

    try {
      for (File file : files) {
        if (!file.delete())
          throw new IOException("[-] Unable to delete '" + file.getAbsolutePath() + "'");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testReadKeyboardControlFromFile() {
    assertEquals(
        KeyboardControlDAO.getKeyboardControlTest(TEST_READ_FILE).toString(),
        testKeyboardControlObject.toString());
  }

  @Test
  public void testWriteKeyboardControlToFile() {
    KeyboardControlDAO.saveKeyboardControlTest(testKeyboardControlObject, TEST_WRITE_FILE);
    Json json = new Json();
    assertEquals(
        json.prettyPrint(KeyboardControlDAO.getKeyboardControlTest(TEST_WRITE_FILE)),
        json.prettyPrint(testKeyboardControlObject));
  }
}
