package com.example.domain;

/**
 * 試合結果を扱うドメイン.
 * 
 * @author ashibe
 *
 */
public class ResultGame {

	/**
	 * 球場
	 */
	private String stadium;
	/**
	 * チーム名１
	 */
	private String team1;
	/**
	 * チーム名２
	 */
	private String team2;
	/**
	 * スコア１
	 */
	private Integer score1;
	/**
	 * スコア２
	 */
	private Integer score2;

	/**
	 * 試合日程
	 */
	private String day;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	@Override
	public String toString() {
		return "ResultGame [stadium=" + stadium + ", team1=" + team1 + ", team2=" + team2 + ", score1=" + score1
				+ ", score2=" + score2 + ", day=" + day + "]";
	}

}
