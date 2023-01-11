package com.mygdx.game.dao;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Contains common data access operations
 *
 * <p>To be used by child classes
 */
public class DataAccessObject {
  /**
   * Retrieves an object by parsing a JSON formatted text file
   *
   * @param filePath the path of the JSON formatted file to parse
   * @param type The class of the type of object to be retrieved (E.g. {@code
   *     KeyboardControl.class})
   * @return The object retrieved
   */
  protected static <T> T readFromFile(String filePath, Class<T> type) {
    StringBuilder jsonContents = new StringBuilder();
    File file = new File(filePath);
    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader reader = new BufferedReader(fileReader);
      String line;
      while ((line = reader.readLine()) != null) {
        jsonContents.append(line);
      }
      reader.close();
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("[Debug] [-] File '" + filePath + "' was not found.");
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return new Json().fromJson(type, jsonContents.toString());
  }

  /**
   * Saves a given object to a text file in the JSON format
   *
   * @param filePath the path to the file to write to
   * @param object an object to be saved
   */
  protected static void writeToFile(String filePath, Object object) {
    Json json = new Json();
    json.setOutputType(OutputType.json);
    File file = new File(filePath);
    try {
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter writer = new BufferedWriter(fileWriter);
      writer.write(json.prettyPrint(object));
      writer.close();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
