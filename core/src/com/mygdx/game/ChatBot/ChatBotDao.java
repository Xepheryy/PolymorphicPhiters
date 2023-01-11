package com.mygdx.game.ChatBot;

import com.mygdx.game.dao.DataAccessObject;
import java.io.File;

/** The Data Access object to retrieve and save {@link com.mygdx.game.ChatBot.ChatBot} objects */
public class ChatBotDao extends DataAccessObject {
  private static final String CHAT_HISTORY = "chat_history.json";

  /**
   * Retrieves the chatbot history from some source (can be from a file, database, etc.)
   *
   * @return The ChatBot object retrieved
   */
  public static ChatBot getChatHistory() {
    ChatBot chatBot = readFromFile(CHAT_HISTORY, ChatBot.class);
    if (chatBot == null) {
      chatBot = new ChatBot();
      saveChatHistory(chatBot);
    }
    return chatBot;
  }

  /**
   * Saves a given ChatBot object to some destination (can be to a file, database, etc.)
   *
   * @param chatBot a ChatBot object to be saved
   */
  public static void saveChatHistory(ChatBot chatBot) {
    writeToFile(CHAT_HISTORY, chatBot);
  }

  /** NOTE: For testing purposes only */
  public static ChatBot getChatHistoryTest(File chatHistoryFile) {
    return readFromFile(chatHistoryFile.getAbsolutePath(), ChatBot.class);
  }

  /** NOTE: For testing purposes only */
  public static void saveChatHistoryTest(ChatBot chatBot, File chatHistoryFile) {
    writeToFile(chatHistoryFile.getAbsolutePath(), chatBot);
  }
}
