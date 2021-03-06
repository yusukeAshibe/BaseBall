package com.example.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.domain.ResultGame;
import com.example.repository.GameResultRepository;

/**
 * 試合結果情報のスクレイピング
 * 
 * @author ashibe
 *
 */
@Service
@Transactional
public class ScrapeResultGameService {

	@Autowired
	private GameResultRepository scrapeRepository;

	/**
	 * サイトから情報をスクレイピングしてきてほしい形に成形.(毎日23:59:00にスクレイピング)
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Scheduled(cron = "0 59 23 * * *", zone = "Asia/Tokyo") // 時間を指定してその時間に処理が行われる.
	public void scrape() throws ParseException {

		// PrintWriter p = null;
		Document document = null;

		try {
			// p = new PrintWriter(new BufferedWriter(
			// new OutputStreamWriter(new FileOutputStream("C:\\mercari\\train2.tsv"),
			// "UTF8")));// 出力するファイルを指定

			// document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/schedule/").get();
			document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/schedule/?date=2020-06-25").get();
			// document = Jsoup.connect("https://baseball-freak.com/game/").get();
			// どこのサイト情報をスクレイピングしてくるのか指定(try/catchしないとエラーが出る）

		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements courses = document.select("li.bb-score__item");// 試合情報
		Elements days = document.select("h1.bb-head02__title");// 日付情報

		String stadium;
		String team1;
		String team2;
		String score1;
		String score2;
		String[] result;
		String[] day = days.text().split("（");
		String today = day[0].replace("月", "-").replace("日", "");
		today = "2020-0" + today;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(today);

		// System.out.println(date);
		// System.out.println(today);

		StringBuilder dateToday = new StringBuilder();
		dateToday.append(today);
		// System.out.println(day);

		String teamList[] = { "巨人", "阪神", "中日", "ＤｅＮＡ", "ヤクルト", "広島", "オリックス", "楽天", "西武", "日本ハム", "ソフトバンク", "ロッテ" };

		List<ResultGame> resultGameList = new ArrayList<>();// 試合情報（最大1日6試合）を詰めるリスト.

		// 今日の情報をインサート
		for (Element course : courses) {

			// 初期化すれば2週目以降も情報が入るようになる。
			int i = 0;
			stadium = null;// 球場
			team1 = null;// チーム名
			team2 = null;// チーム名
			score1 = null;// 得点
			score2 = null;// 得点

			result = course.text().replace("-", " ").split(" "); // stringの配列にスクレイピングしてきた情報を入れる（半角スペース区切り）

			System.out.println(Arrays.toString(result));// 半角スペース区切りでオブジェクトに入っているか確認
//			System.out.println(result[0]); // 球場の確認

			stadium = result[i++];// オブジェクト内最初の文字列
			team1 = result[i++];// 次の文字列
			if (!Arrays.asList(teamList).contains(team1)) {// 見逃し配信を回避しながら次の文字列をチーム1に入れる
				team1 = result[i++];
			}
			team2 = result[i++];// チーム1の次の文字列をチーム2に入れる
			for (String score : result) {// 出てくる数字の最初の要素をスコア１に入れる、スコア1に既に値が入っていたらスコア２に入れる.
				if (Pattern.matches("^[0-9]*$", score)) {
					if (StringUtils.isEmpty(score1)) {
						score1 = score;
					} else {
						score2 = score;
					}

				}

			}

			ResultGame resultGame = new ResultGame();// 上記で取得した文字列をResultGameオブジェクトに入れる
			resultGame.setStadium(stadium);
			resultGame.setTeam1(team1);
			resultGame.setTeam2(team2);
			resultGame.setScore1(Integer.parseInt(score1));
			resultGame.setScore2(Integer.parseInt(score2));
			resultGame.setDate(date);
			resultGameList.add(resultGame);
		}
		// System.out.println(resultGameList);
		// 既にその日の情報が入っているか確認
		List<ResultGame> todayResultGameList = scrapeRepository.ResultSearchByDay(date);// 既にその日の情報が入っているか確認

		// System.out.println(todayResultGameList);
		if (todayResultGameList.size() == 0) {
			scrapeRepository.insert(resultGameList);
		}
		if (resultGameList.size() == 0) {
			System.out.println("今日の試合はありません");
		}

	}

}
