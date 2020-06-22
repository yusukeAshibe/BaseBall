package com.example.domain;

/**
 * 巨人の情報を扱うドメイン .
 * 
 * @author ashibe
 *
 */
public class Giants {

	/**
	 * 試合日
	 */
	private String day;

	/**
	 * 勝敗
	 */
	private String victoryOrDefeat;

	/**
	 * 勝ち負け
	 */
	private String score;

	/**
	 * 対戦相手
	 */
	private String Opponent;
	/**
	 * 先発投手
	 */
	private String starter;

	/**
	 * 責任投手
	 */
	private String responsibilityPitcher;

	/**
	 * 球場
	 */
	private String stadium;

	/**
	 * 開始時間
	 */
	private String startTime;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getVictoryOrDefeat() {
		return victoryOrDefeat;
	}

	public void setVictoryOrDefeat(String victoryOrDefeat) {
		this.victoryOrDefeat = victoryOrDefeat;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOpponent() {
		return Opponent;
	}

	public void setOpponent(String opponent) {
		Opponent = opponent;
	}

	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public String getResponsibilityPitcher() {
		return responsibilityPitcher;
	}

	public void setResponsibilityPitcher(String responsibilityPitcher) {
		this.responsibilityPitcher = responsibilityPitcher;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Giants [day=" + day + ", victoryOrDefeat=" + victoryOrDefeat + ", score=" + score + ", Opponent="
				+ Opponent + ", starter=" + starter + ", responsibilityPitcher=" + responsibilityPitcher + ", stadium="
				+ stadium + ", startTime=" + startTime + "]";
	}

}
