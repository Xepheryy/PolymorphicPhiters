package com.mygdx.game.LeaderBoard;

import java.util.ArrayList;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Contains a list of {@link com.mygdx.game.LeaderBoard.LeaderBoardRecord} objects that will
 * typically be created at the end of each game
 */
@Data
@AllArgsConstructor
public class LeaderBoard {
  private final int NO_OF_RECORDS = 10;

  private ArrayList<LeaderBoardRecord> rankings;

  public LeaderBoard() {
    this.rankings = new ArrayList<>();
  }

  /**
   * Update the list of rankings
   *
   * @param record A {@link com.mygdx.game.LeaderBoard.LeaderBoardRecord} object to be saved to the
   *     list
   */
  public void updateRankings(LeaderBoardRecord record) {
    if (this.rankings.size() == NO_OF_RECORDS) {
      long lastPlaceScore = this.rankings.get(this.rankings.size() - 1).getScore();
      if (record.getScore() > lastPlaceScore) {
        this.rankings.remove(this.rankings.size() - 1);
      }
    }
    if (rankings.size() < NO_OF_RECORDS) this.rankings.add(record);
    Collections.sort(this.rankings);
  }
}
