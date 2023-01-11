package com.mygdx.game.ChatBot;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** An abstraction for a hashmap containing the questions and responses for the help interface. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBot {
  private HashMap<String, String> chatHistory;

  /**
   * Gets the response to a given question
   *
   * @param question The question to retrieve the response for
   * @return The retrieved response
   */
  public String getResponse(String question) {
    if (chatHistory == null) {
      chatHistory = new HashMap<String, String>();
    }
    return chatHistory.get(question);
  }

  /**
   * Saves a given response for a given question
   *
   * @param question The question to save the response for
   * @param response The response to the question
   */
  public void saveResponse(String question, String response) {
    chatHistory.put(question, response);
  }
}
