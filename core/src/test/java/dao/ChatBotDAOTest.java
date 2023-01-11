package src.test.java.dao;

import static org.junit.Assert.assertEquals;

import com.mygdx.game.ChatBot.ChatBot;
import com.mygdx.game.ChatBot.ChatBotDao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/** Test Class for ChatBotDAO */
public class ChatBotDAOTest {
  File chatHistoryFile;

  @Rule public TemporaryFolder folder = new TemporaryFolder();

  @Before
  public void setUp() {
    try {
      chatHistoryFile = folder.newFile("chatHistoryTest.json");
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    try {
      FileWriter fw = new FileWriter(chatHistoryFile);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("{\"chatHistory\":{\"hello\": \"hi this is a test\"}}");
      bw.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Test
  public void testGetChatHistory() {
    HashMap<String, String> testMap = new HashMap<>();
    testMap.put("hello", "hi this is a test");
    ChatBot chatBot = ChatBotDao.getChatHistoryTest(chatHistoryFile);
    assert chatBot != null;
    assertEquals(testMap, chatBot.getChatHistory());
  }

  @Test
  public void testSaveChatHistory() {
    HashMap<String, String> testMap = new HashMap<>();
    testMap.put("hello", "hi this is a test");
    ChatBot chatBot = ChatBotDao.getChatHistoryTest(chatHistoryFile);
    assert chatBot != null;
    chatBot.saveResponse("this is a test", "testing123");
    ChatBotDao.saveChatHistoryTest(chatBot, chatHistoryFile);
    testMap.put("this is a test", "testing123");
    assertEquals(testMap, ChatBotDao.getChatHistoryTest(chatHistoryFile).getChatHistory());
  }
}
