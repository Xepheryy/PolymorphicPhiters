package com.mygdx.game.LeaderBoard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains the name and score of a winner
 *
 * <p>The {@link com.mygdx.game.LeaderBoard.LeaderBoard} will then contain a list of these records
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderBoardRecord implements Comparable<LeaderBoardRecord> {
  private String name;
  private long score;

  @Override
  public int compareTo(LeaderBoardRecord o) {
    return Long.compare(o.getScore(), this.score);
  }
}
