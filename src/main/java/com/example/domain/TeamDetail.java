package com.example.domain;

import java.util.Date;

/**
 * チームの詳細情報を扱うドメインクラス.
 * 
 * @author ashibe
 *
 */
public class TeamDetail {

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

}
