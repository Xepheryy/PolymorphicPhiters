package com.mygdx.game.Player;

import lombok.Data;

/** Used to calculate a player's score */
@Data
public class ScoreCalculator {
  private long winnerRounds, loserRounds;
  private int timeTaken, comboCount, perfectRounds, itemScore;

  public ScoreCalculator(
      long winnerRounds,
      long loserRounds,
      int timeTaken,
      int comboCount,
      int perfectRounds,
      int itemScore) {
    this.winnerRounds = winnerRounds;
    this.loserRounds = loserRounds;
    this.timeTaken = timeTaken;
    this.comboCount = comboCount;
    this.perfectRounds = perfectRounds;
    this.itemScore = itemScore;
  }

  /**
   * Checks if the player has not lost any rounds while winning multiple rounds and returns the
   * score accordingly
   *
   * @return The final score for this category
   */
  public int noRoundLoss() {
    if (this.loserRounds == 0 && winnerRounds > 1) return 10;
    return 0;
  }

  /**
   * Checks and returns a score based on the time remaining in the round
   *
   * @return The final score for this category
   */
  public int timeBonus() {
    return (int) this.winnerRounds * 60 - this.timeTaken;
  }

  /**
   * Checks and returns the score for the number of perfect rounds
   *
   * <p>A perfect round refers to a round where the player has not received a single hit
   *
   * @return The final score for this category
   */
  public int perfect() {
    return this.perfectRounds * 20;
  }

  /**
   * Returns the final score, after tabulating the scores from all the categories
   *
   * @return The accumulated final score
   */
  public int calculateScore() {
    int score = 0;
    score += noRoundLoss();
    score += timeBonus();
    score += this.comboCount;
    score += perfect();
    score += this.itemScore;
    return score;
  }
}
