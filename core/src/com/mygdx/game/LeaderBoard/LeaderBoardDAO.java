package com.mygdx.game.LeaderBoard;

import com.mygdx.game.dao.DataAccessObject;

/**
 * The Data Access object to retrieve and save {@link com.mygdx.game.LeaderBoard.LeaderBoard}
 * objects
 */
public class LeaderBoardDAO extends DataAccessObject {
  private static final String LEADER_BOARD_FILE = "leaderboard.json";

  /**
   * Retrieves a LeaderBoard object from some source (can be from a file, database, etc.)
   *
   * @return The LeaderBoard object retrieved
   */
  public static LeaderBoard getLeaderBoard() {
    return readFromFile(LEADER_BOARD_FILE, LeaderBoard.class);
  }

  /** NOTE: For testing purposes only */
  public static LeaderBoard getLeaderBoardTest(String fileName) {
    return readFromFile(fileName, LeaderBoard.class);
  }

  /**
   * Saves a given LeaderBoard object to some destination (can be to a file, database, etc.)
   *
   * @param leaderBoard a LeaderBoard object to be saved
   */
  public static void saveLeaderBoard(LeaderBoard leaderBoard) {
    writeToFile(LEADER_BOARD_FILE, leaderBoard);
  }

  /** NOTE: For testing purposes only */
  public static void saveLeaderBoardTest(LeaderBoard leaderBoard, String fileName) {
    writeToFile(fileName, leaderBoard);
  }
}
