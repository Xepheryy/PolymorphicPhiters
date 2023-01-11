package src.test.java.dao;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.game.LeaderBoard.LeaderBoard;
import com.mygdx.game.LeaderBoard.LeaderBoardDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** Test Class for LeaderBoardDAO */
public class LeaderBoardDAOTest {
  private static final String TEST_DATA =
      "{\"rankings\":[{\"name\":\"Vader\",\"score\":65},{\"name\":\"Yoda\",\"score\":70}]}";

  private static LeaderBoard testLeaderBoardObject;

  private static final String TEST_READ_FILE = "leaderboard_read_test.json";
  private static final String TEST_WRITE_FILE = "leaderboard_write_test.json";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    // Set up json serializer
    Json json = new Json();
    json.setOutputType(JsonWriter.OutputType.json);

    // Set up test objects (will be used to test both reading and writing)
    testLeaderBoardObject = json.fromJson(LeaderBoard.class, TEST_DATA);

    String leaderBoardTestFileContents = json.prettyPrint(testLeaderBoardObject);

    // Prepare file to be read
    FileWriter file_writer = new FileWriter(TEST_READ_FILE);
    BufferedWriter writer = new BufferedWriter(file_writer);
    writer.write(leaderBoardTestFileContents, 0, leaderBoardTestFileContents.length());
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
  public void testReadLeaderBoardFromFile() {
    assertEquals(
        LeaderBoardDAO.getLeaderBoardTest(TEST_READ_FILE).toString(),
        testLeaderBoardObject.toString());
  }

  @Test
  public void testWriteLeaderBoardToFile() {
    LeaderBoardDAO.saveLeaderBoardTest(testLeaderBoardObject, TEST_WRITE_FILE);
    Json json = new Json();
    assertEquals(
        json.prettyPrint(LeaderBoardDAO.getLeaderBoardTest(TEST_WRITE_FILE)),
        json.prettyPrint(testLeaderBoardObject));
  }
}
