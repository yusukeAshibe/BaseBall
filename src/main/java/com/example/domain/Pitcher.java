package com.example.domain;

public class Pitcher {

	private String uniformNumber;// 背番号
	private String firstName;// 選手名
	private String lastName;// 名前
	private Double defenceRate;// 防御率
	private Integer pitching;// 登板数
	private Integer starter;// 先発回数
	private Integer completeGame;// 完投数
	private Integer shutout;// 完封
	private Integer quarityStart;// QS回数
	private Integer win;// 勝ち数
	private Integer lose;// 負け数;
	private Integer hold;// ホールド数
	private Integer holdPoint;// ホールドポイント数
	private Integer save;// セーブ数
	private Double WinRate;// 勝率
	private Double inning;// 投球回数
	private Integer hit;// 被安打
	private Integer homerun;// 被本塁打
	private Integer strikeout;// 奪三振
	private Double strikeoutRate;// 奪三振率
	private Integer fourBall;// 与四球数
	private Integer deadball;// 与死球数
	private Integer wildPitch;// 暴投数
	private Integer balk;// ボーク数
	private Integer goal;// 失点
	private Integer earnedRun; // 自責点
	private Double battingAverage;// 被打率
	private Double KBB;// 奪三振 (K:strikeout)と与四球 (BB:Base on Balls)の比率で、投手の制球力を示す指標の1つ。
	private Double WHIP;// 1投球回あたり何人の走者を出したかを表す数値
	/**
	 * 所属チーム名
	 */
	private String TeamName;

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

	public Double getDefenceRate() {
		return defenceRate;
	}

	public void setDefenceRate(Double defenceRate) {
		this.defenceRate = defenceRate;
	}

	public Integer getPitching() {
		return pitching;
	}

	public void setPitching(Integer pitching) {
		this.pitching = pitching;
	}

	public Integer getStarter() {
		return starter;
	}

	public void setStarter(Integer starter) {
		this.starter = starter;
	}

	public Integer getCompleteGame() {
		return completeGame;
	}

	public void setCompleteGame(Integer completeGame) {
		this.completeGame = completeGame;
	}

	public Integer getShutout() {
		return shutout;
	}

	public void setShutout(Integer shutout) {
		this.shutout = shutout;
	}

	public Integer getQuarityStart() {
		return quarityStart;
	}

	public void setQuarityStart(Integer quarityStart) {
		this.quarityStart = quarityStart;
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

	public Integer getHold() {
		return hold;
	}

	public void setHold(Integer hold) {
		this.hold = hold;
	}

	public Integer getHoldPoint() {
		return holdPoint;
	}

	public void setHoldPoint(Integer holdPoint) {
		this.holdPoint = holdPoint;
	}

	public Integer getSave() {
		return save;
	}

	public void setSave(Integer save) {
		this.save = save;
	}

	public Double getWinRate() {
		return WinRate;
	}

	public void setWinRate(Double winRate) {
		WinRate = winRate;
	}

	public Double getInning() {
		return inning;
	}

	public void setInning(Double inning) {
		this.inning = inning;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getHomerun() {
		return homerun;
	}

	public void setHomerun(Integer homerun) {
		this.homerun = homerun;
	}

	public Integer getStrikeout() {
		return strikeout;
	}

	public void setStrikeout(Integer strikeout) {
		this.strikeout = strikeout;
	}

	public Double getStrikeoutRate() {
		return strikeoutRate;
	}

	public void setStrikeoutRate(Double strikeoutRate) {
		this.strikeoutRate = strikeoutRate;
	}

	public Integer getFourBall() {
		return fourBall;
	}

	public void setFourBall(Integer fourBall) {
		this.fourBall = fourBall;
	}

	public Integer getDeadball() {
		return deadball;
	}

	public void setDeadball(Integer deadball) {
		this.deadball = deadball;
	}

	public Integer getWildPitch() {
		return wildPitch;
	}

	public void setWildPitch(Integer wildPitch) {
		this.wildPitch = wildPitch;
	}

	public Integer getBalk() {
		return balk;
	}

	public void setBalk(Integer balk) {
		this.balk = balk;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public Integer getEarnedRun() {
		return earnedRun;
	}

	public void setEarnedRun(Integer earnedRun) {
		this.earnedRun = earnedRun;
	}

	public Double getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(Double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public Double getKBB() {
		return KBB;
	}

	public void setKBB(Double kBB) {
		KBB = kBB;
	}

	public Double getWHIP() {
		return WHIP;
	}

	public void setWHIP(Double wHIP) {
		WHIP = wHIP;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	@Override
	public String toString() {
		return "Pitcher [uniformNumber=" + uniformNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", defenceRate=" + defenceRate + ", pitching=" + pitching + ", starter=" + starter + ", completeGame="
				+ completeGame + ", shutout=" + shutout + ", quarityStart=" + quarityStart + ", win=" + win + ", lose="
				+ lose + ", hold=" + hold + ", holdPoint=" + holdPoint + ", save=" + save + ", WinRate=" + WinRate
				+ ", inning=" + inning + ", hit=" + hit + ", homerun=" + homerun + ", strikeout=" + strikeout
				+ ", strikeoutRate=" + strikeoutRate + ", fourBall=" + fourBall + ", deadball=" + deadball
				+ ", wildPitch=" + wildPitch + ", balk=" + balk + ", goal=" + goal + ", earnedRun=" + earnedRun
				+ ", battingAverage=" + battingAverage + ", KBB=" + KBB + ", WHIP=" + WHIP + ", TeamName=" + TeamName
				+ "]";
	}

}