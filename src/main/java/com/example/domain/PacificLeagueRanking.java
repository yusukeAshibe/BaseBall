package com.example.domain;

import java.util.Date;

/**
 * パリーグの順位情報を扱うドメイン.
 * 
 * @author ashibe
 *
 */
public class PacificLeagueRanking {

	/**
	 * 順位
	 */
	private Integer rank;

	/**
	 * チーム名
	 */
	private String teamName;

	/**
	 * 消化済試合数
	 */
	private Integer game;

	/**
	 * 勝ち数
	 */
	private Integer win;

	/**
	 * 負け数
	 */
	private Integer lose;

	/**
	 * 引き分け数
	 */
	private Integer drow;

	/**
	 * 勝率
	 */
	private Double WinRate;

	/**
	 * ゲーム差
	 */
	private String Difference;

	/**
	 * 残り試合数
	 */
	private Integer remaindingMatch;

	/**
	 * 総得点
	 */
	private Integer score;

	/**
	 * 総失点
	 */
	private Integer goal;

	/**
	 * 総本塁打数
	 */
	private Integer homerun;

	/**
	 * 総盗塁数
	 */
	private Integer steal;

	/**
	 * チーム打率
	 */
	private double battingAverage;

	/**
	 * チーム防御率
	 */
	private double defenceRate;

	/**
	 * 試合日付
	 */
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
	}

	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public Integer getLose() {
		return lose;
	}

	public void setLose(Integer lose) {
		this.lose = lose;
	}

	public Integer getDrow() {
		return drow;
	}

	public void setDrow(Integer drow) {
		this.drow = drow;
	}

	public Double getWinRate() {
		return WinRate;
	}

	public void setWinRate(Double winRate) {
		WinRate = winRate;
	}

	public Integer getRemaindingMatch() {
		return remaindingMatch;
	}

	public void setRemaindingMatch(Integer remaindingMatch) {
		this.remaindingMatch = remaindingMatch;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public Integer getSteal() {
		return steal;
	}

	public void setSteal(Integer steal) {
		this.steal = steal;
	}

	public double getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public double getDefenceRate() {
		return defenceRate;
	}

	public void setDefenceRate(double defenceRate) {
		this.defenceRate = defenceRate;
	}

	public String getDifference() {
		return Difference;
	}

	public void setDifference(String difference) {
		Difference = difference;
	}

	public Integer getHomerun() {
		return homerun;
	}

	public void setHomerun(Integer homerun) {
		this.homerun = homerun;
	}

	@Override
	public String toString() {
		return "PacificLeagueRanking [rank=" + rank + ", teamName=" + teamName + ", game=" + game + ", win=" + win
				+ ", lose=" + lose + ", drow=" + drow + ", WinRate=" + WinRate + ", Difference=" + Difference
				+ ", remaindingMatch=" + remaindingMatch + ", score=" + score + ", goal=" + goal + ", homerun="
				+ homerun + ", steal=" + steal + ", battingAverage=" + battingAverage + ", defenceRate=" + defenceRate
				+ ", date=" + date + "]";
	}

}
