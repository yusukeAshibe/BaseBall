package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;
import com.example.repository.RankingTableRepository;

/**
 * 順位表に掲載したい情報＋αをスクレイピング(毎日0:00:00にスクレイピング)
 * 
 * @author ashibe
 *
 */
@Service
@Transactional
public class ScrapeRankService {

	@Autowired
	private RankingTableRepository rankingTebleRepository;

	/**
	 * セリーグの順位表情報をスクレイピング
	 */
	@Scheduled(cron = "0 0 * * * *", zone = "Asia/Tokyo")
	public void scrapeCentral() {

		Document document = null;

		try {
			document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/standings/detail/1").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		org.jsoup.select.Elements elements = document.select("tr.bb-rankTable__row");

		String rank;// 順位
		String teamName;// チーム名
		String game;// 消化済試合数
		String win;// 勝ち数
		String lose;// 負け数
		String drow;// 引き分け数
		String winRate;// 勝率
		String difference;// ゲーム差
		String remaindingMatch;// 残り試合数
		String score;// 総得点
		String goal;// 総失点
		String homerun;// 総本塁打
		String steal;// 総盗塁
		String battingAverage;// チーム打率
		String defenceRate;// チーム防御率
		Date date = new Date();//
		System.out.println(date);

		CentralLeagueRanking central = new CentralLeagueRanking();
		List<CentralLeagueRanking> centralLeagueList = new ArrayList<>();

		for (Element element : elements) {

			// 変数の初期化
			rank = null;
			teamName = null;
			game = null;
			win = null;
			lose = null;
			drow = null;
			winRate = null;
			difference = null;
			remaindingMatch = null;
			score = null;
			goal = null;
			homerun = null;
			steal = null;
			battingAverage = null;
			defenceRate = null;

			// stringの配列に抜き取ってきた要素を移し替え
			String[] centralLeague = null;
			centralLeague = element.text().split(" ");// 空白区切りで一つの要素にする.
			// System.out.println(Arrays.toString(centralLeague));
			int i = 0;// 何番目の要素を入れるかを指定する変数.
			// 順番に詰めていく
			rank = centralLeague[i++];
			teamName = centralLeague[i++];
			game = centralLeague[i++];
			win = centralLeague[i++];
			lose = centralLeague[i++];
			drow = centralLeague[i++];
			winRate = centralLeague[i++];
			difference = centralLeague[i++];
			remaindingMatch = centralLeague[i++];
			score = centralLeague[i++];
			goal = centralLeague[i++];
			homerun = centralLeague[i++];
			steal = centralLeague[i++];
			battingAverage = centralLeague[i++];
			defenceRate = centralLeague[i++];

			// CentralLeaguRankingオブジェクに詰め替え(型変換)
			central = new CentralLeagueRanking();
			central.setRank(Integer.parseInt(rank));
			central.setTeamName(teamName);
			central.setGame(Integer.parseInt(game));
			central.setWin(Integer.parseInt(win));
			central.setLose(Integer.parseInt(lose));
			central.setDrow(Integer.parseInt(drow));
			central.setWinRate(Double.parseDouble(winRate));
			central.setDifference(difference);// 一位の場合-が入るためString型のまま
			central.setRemaindingMatch(Integer.parseInt(remaindingMatch));
			central.setScore(Integer.parseInt(score));
			central.setGoal(Integer.parseInt(goal));
			central.setHomerun(Integer.parseInt(homerun));
			central.setSteal(Integer.parseInt(steal));
			central.setBattingAverage(Double.parseDouble(battingAverage));
			central.setDefenceRate(Double.parseDouble(defenceRate));
			central.setDate(date);

			// 一つcentralオブジェクトを詰め終わったらその都度centralLeagueListに入れる。
			centralLeagueList.add(central);

		}
		// 既にその日の順位情報が入っていないか確認
		List<CentralLeagueRanking> searchCentralLeagueList = rankingTebleRepository
				.centralLeagueRankingFindByDate(date);
		System.out.println(date);
		if (searchCentralLeagueList.size() == 0) {
			rankingTebleRepository.insertCentralLeagueRanking(centralLeagueList);

		}

		System.out.println(centralLeagueList);
	}

	/**
	 * パリーグの順位表情報をスクレイピング
	 */
	@Scheduled(cron = "0 * * * * *", zone = "Asia/Tokyo")
	public void scrapePacific() {

		Document document = null;

		try {
			document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/standings/detail/2").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		org.jsoup.select.Elements elements = document.select("tr.bb-rankTable__row");

		String rank;// 順位
		String teamName;// チーム名
		String game;// 消化済試合数
		String win;// 勝ち数
		String lose;// 負け数
		String drow;// 引き分け数
		String winRate;// 勝率
		String difference;// ゲーム差
		String remaindingMatch;// 残り試合数
		String score;// 総得点
		String goal;// 総失点
		String homerun;// 総本塁打
		String steal;// 総盗塁
		String battingAverage;// チーム打率
		String defenceRate;// チーム防御率
		Date date = new Date();

		PacificLeagueRanking pacific = new PacificLeagueRanking();
		List<PacificLeagueRanking> pacificLeagueList = new ArrayList<>();

		for (Element element : elements) {

			rank = null;
			teamName = null;
			game = null;
			win = null;
			lose = null;
			drow = null;
			winRate = null;
			difference = null;
			remaindingMatch = null;
			score = null;
			goal = null;
			homerun = null;
			steal = null;
			battingAverage = null;
			defenceRate = null;

			// stringの配列に抜き取ってきた要素を移し替え
			String[] pacificLeague = null;
			pacificLeague = element.text().split(" ");// 空白区切りで一つの要素にする.
			// System.out.println(Arrays.toString(centralLeague));
			int i = 0;// 何番目の要素を入れるかを指定する変数.
			// 順番に詰めていく
			rank = pacificLeague[i++];
			teamName = pacificLeague[i++];
			game = pacificLeague[i++];
			win = pacificLeague[i++];
			lose = pacificLeague[i++];
			drow = pacificLeague[i++];
			winRate = pacificLeague[i++];
			difference = pacificLeague[i++];
			remaindingMatch = pacificLeague[i++];
			score = pacificLeague[i++];
			goal = pacificLeague[i++];
			homerun = pacificLeague[i++];
			steal = pacificLeague[i++];
			battingAverage = pacificLeague[i++];
			defenceRate = pacificLeague[i++];

			// pacificLeaguRankingオブジェクに詰め替え(型変換)
			pacific = new PacificLeagueRanking();
			pacific.setRank(Integer.parseInt(rank));
			pacific.setTeamName(teamName);
			pacific.setGame(Integer.parseInt(game));
			pacific.setWin(Integer.parseInt(win));
			pacific.setLose(Integer.parseInt(lose));
			pacific.setDrow(Integer.parseInt(drow));
			pacific.setWinRate(Double.parseDouble(winRate));
			pacific.setDifference(difference);// 一位の場合-が入るためString型のまま
			pacific.setRemaindingMatch(Integer.parseInt(remaindingMatch));
			pacific.setScore(Integer.parseInt(score));
			pacific.setGoal(Integer.parseInt(goal));
			pacific.setHomerun(Integer.parseInt(homerun));
			pacific.setSteal(Integer.parseInt(steal));
			pacific.setBattingAverage(Double.parseDouble(battingAverage));
			pacific.setDefenceRate(Double.parseDouble(defenceRate));
			pacific.setDate(date);
			// 一つpacificオブジェクトを詰め終わったらその都度pacificLeagueListに入れる。
			pacificLeagueList.add(pacific);

		}
		// 既にその日の順位情報が入っていないか確認
		List<PacificLeagueRanking> searchPacificLeagueList = rankingTebleRepository
				.pacificLeagueRankingFindByDate(date);
		if (searchPacificLeagueList.size() == 0) {
			rankingTebleRepository.insertPacificLeagueRanking(pacificLeagueList);
		}
		System.out.println(pacificLeagueList);
	}
}
