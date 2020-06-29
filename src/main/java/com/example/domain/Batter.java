package com.example.domain;

/**
 * 打者情報を扱うドメイン.
 * 
 * @author ashibe
 *
 */
public class Batter {

	private String uniformNumber;// 背番号
	private String firstName;// 苗字
	private String lastName;// 名前
	private Double battingAverage;// 打率
	private Integer game;// 試合数
	private Integer plateAppearance;// 打数
	private Integer atBat;// 打席
	private Integer hit;// 安打数
	private Integer twoBase;// 二塁打数
	private Integer threeBase;// 三塁打数
	private Integer homerun;// 本塁打数
	private Integer baseHit;// 塁打数
	private Integer RBI;// 打点
	private Integer score;// 得点
	private Integer strikeOut;// 三振数
	private Integer fourBall;// 四球数
	private Integer deadBall;// 死球数
	private Integer bunt;// 犠打数
	private Integer SF;// 犠飛数
	private Integer steal;// 盗塁数
	private Integer caughtSteal;// 盗塁殺数
	private Integer doublePlay;// 併殺打数
	private Double onBasePercent;// 出塁率
	private Double sluggingPercent;// 長打率
	private Double OPS;// 出塁率＋長打率
	private Double scoringArea;// 得点圏打率
	private Integer error;// 失策数
	private String teamName;// 所属チーム名

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getUniformNumber() {
		return uniformNumber;
	}

	public void setUniformNumber(String uniformNumber) {
		this.uniformNumber = uniformNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(Double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
	}

	public Integer getPlateAppearance() {
		return plateAppearance;
	}

	public void setPlateAppearance(Integer plateAppearance) {
		this.plateAppearance = plateAppearance;
	}

	public Integer getAtBat() {
		return atBat;
	}

	public void setAtBat(Integer atBat) {
		this.atBat = atBat;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getTwoBase() {
		return twoBase;
	}

	public void setTwoBase(Integer twoBase) {
		this.twoBase = twoBase;
	}

	public Integer getThreeBase() {
		return threeBase;
	}

	public void setThreeBase(Integer threeBase) {
		this.threeBase = threeBase;
	}

	public Integer getHomerun() {
		return homerun;
	}

	public void setHomerun(Integer homerun) {
		this.homerun = homerun;
	}

	public Integer getBaseHit() {
		return baseHit;
	}

	public void setBaseHit(Integer baseHit) {
		this.baseHit = baseHit;
	}

	public Integer getRBI() {
		return RBI;
	}

	public void setRBI(Integer rBI) {
		RBI = rBI;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStrikeOut() {
		return strikeOut;
	}

	public void setStrikeOut(Integer strikeOut) {
		this.strikeOut = strikeOut;
	}

	public Integer getFourBall() {
		return fourBall;
	}

	public void setFourBall(Integer fourBall) {
		this.fourBall = fourBall;
	}

	public Integer getDeadBall() {
		return deadBall;
	}

	public void setDeadBall(Integer deadBall) {
		this.deadBall = deadBall;
	}

	public Integer getBunt() {
		return bunt;
	}

	public void setBunt(Integer bunt) {
		this.bunt = bunt;
	}

	public Integer getSF() {
		return SF;
	}

	public void setSF(Integer sF) {
		SF = sF;
	}

	public Integer getSteal() {
		return steal;
	}

	public void setSteal(Integer steal) {
		this.steal = steal;
	}

	public Integer getCaughtSteal() {
		return caughtSteal;
	}

	public void setCaughtSteal(Integer caughtSteal) {
		this.caughtSteal = caughtSteal;
	}

	public Integer getDoublePlay() {
		return doublePlay;
	}

	public void setDoublePlay(Integer doublePlay) {
		this.doublePlay = doublePlay;
	}

	public Double getOnBasePercent() {
		return onBasePercent;
	}

	public void setOnBasePercent(Double onBasePercent) {
		this.onBasePercent = onBasePercent;
	}

	public Double getSluggingPercent() {
		return sluggingPercent;
	}

	public void setSluggingPercent(Double sluggingPercent) {
		this.sluggingPercent = sluggingPercent;
	}

	public Double getOPS() {
		return OPS;
	}

	public void setOPS(Double oPS) {
		OPS = oPS;
	}

	public Double getScoringArea() {
		return scoringArea;
	}

	public void setScoringArea(Double scoringArea) {
		this.scoringArea = scoringArea;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Batter [uniformNumber=" + uniformNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", battingAverage=" + battingAverage + ", game=" + game + ", plateAppearance=" + plateAppearance
				+ ", atBat=" + atBat + ", hit=" + hit + ", twoBase=" + twoBase + ", threeBase=" + threeBase
				+ ", homerun=" + homerun + ", baseHit=" + baseHit + ", RBI=" + RBI + ", score=" + score + ", strikeOut="
				+ strikeOut + ", fourBall=" + fourBall + ", deadBall=" + deadBall + ", bunt=" + bunt + ", SF=" + SF
				+ ", steal=" + steal + ", caughtSteal=" + caughtSteal + ", doublePlay=" + doublePlay
				+ ", onBasePercent=" + onBasePercent + ", sluggingPercent=" + sluggingPercent + ", OPS=" + OPS
				+ ", scoringArea=" + scoringArea + ", error=" + error + ", teamName=" + teamName + "]";
	}

}