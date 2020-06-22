package com.example.controller;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.ParseException;
import java.lang.InterruptedException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sokuho {
	static Document game_document = null;
	static String channel = "";
	static String strDate = "";
	static String checkDate = "";
	static String base_tmpdir = "";
	static String end_file = "";
	static String schedule_url = "";
	static String c_rank_file = "";
	static String p_rank_file = "";
	static HashMap<Integer, Map<String, String>> schedule_data = new HashMap<Integer, Map<String, String>>();

	// 定数
	private static final int FIRST_CHECH_FIRSTACCESS = 1;
	private static final int FIRST_CHECH_GAMENOTEND = 2;
	private static final int FIRST_CHECH_GAMEALLEND = 3;

	public static void main(String[] args) throws IOException, ParseException {
		channel = args[0];
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		strDate = sdf.format(cal.getTime());

		SimpleDateFormat checksdf = new SimpleDateFormat("M月d日");
		checkDate = checksdf.format(cal.getTime());

		// 確認ファイルがいたら何もしない
		String dir = new File(".").getAbsoluteFile().getParent();
		base_tmpdir = dir + "/tmp/" + strDate;
		end_file = base_tmpdir + "/.end";
		c_rank_file = dir + "/tmp/c_rank.txt";
		p_rank_file = dir + "/tmp/p_rank.txt";

		schedule_url = "http://○○/?date=" + strDate;
		Integer first_result = firstCheck();
		// 初回アクセス時か、すでに処理が終了しているので処理終了
		if (first_result == FIRST_CHECH_FIRSTACCESS) {
			return;
		}
		if (first_result == FIRST_CHECH_GAMEALLEND) {
			// 順位表の確認
			rankCheck();
			return;
		}

		stamenSokuho();
		gameCheck();
	}

	private static Integer firstCheck() throws IOException, ParseException {
		File base_tmpdir_ob = new File(base_tmpdir);
		// 初回アクセスなので初回対応を行う
		if (!base_tmpdir_ob.exists()) {
			// ディレクトリ作成
			base_tmpdir_ob.mkdir();
			game_document = Jsoup.connect(schedule_url).get();
			// 本日のゲームを抽出
			parseTable();
			String start_text = "";
			String stop_text = "";

			for (Map.Entry<Integer, Map<String, String>> game_info : schedule_data.entrySet()) {
				start_text += game_info.getValue().get("team2") + " - " + game_info.getValue().get("team1") + " "
						+ game_info.getValue().get("start_time") + "\\r\\n";
				if (game_info.getValue().get("eninng").equals("中止")) {
					stop_text += game_info.getValue().get("team2") + " - " + game_info.getValue().get("team1")
							+ "\\r\\n";
				}
			}

			if (!start_text.equals("")) {
				start_text = "*本日のゲーム*```" + start_text + "```";
				if (!stop_text.equals("")) {
					start_text += " *中止が発表されたゲーム*```" + stop_text + "```";
				}
			} else {
				start_text = "```本日のゲームはありません```";
			}
			// slackSend(start_text);
			// ログ書き込み
			// makeLog();
			return FIRST_CHECH_FIRSTACCESS;
		} else {
			// 初回アクセスじゃないので処理終了かのチェック。
			File end_file_ob = new File(end_file);
			if (end_file_ob.exists()) {
				// 処理終了
				return FIRST_CHECH_GAMEALLEND;
			}
		}
		// 処理中
		game_document = Jsoup.connect(schedule_url).get();
		parseTable();
		return FIRST_CHECH_GAMENOTEND;
	}

	private static void parseTable() {
		schedule_data = new HashMap<Integer, Map<String, String>>();
		Elements tables = game_document.select("table.teams");
		Integer count = 0;
		for (Element table : tables) {
			count++;
			schedule_data.put(count, new HashMap<String, String>());
			Elements teams = table.select("td.yjMS");
			String team1 = table.select("tr:nth-child(1) td.yjMS").text();
			String team2 = table.select("tr:nth-child(2) td.yjMS").text();
			String score1 = table.select("table.score tr:nth-child(1) td.score_r").text();
			String score2 = table.select("table.score tr:nth-child(3) td.score_r").text();
			String eninng = table.select("table.score .yjMSt").text();
			String start_time = table.select("table.score tr:nth-child(3) td.yjSt").text();
			String target_href = table.select("td.yjMSt a").attr("href");
			schedule_data.get(count).put("team1", team1);
			schedule_data.get(count).put("team2", team2);
			schedule_data.get(count).put("score1", score1);
			schedule_data.get(count).put("score2", score2);
			schedule_data.get(count).put("eninng", eninng);
			schedule_data.get(count).put("start_time", start_time);
			schedule_data.get(count).put("target_href", target_href);
		}
		return;
	}

//	private static void slackSend(String text) {
//		String jsonfilePath = base_tmpdir + "/hoge.txt";
//		try {
//			File jsonfile = new File(jsonfilePath);
//
//			jsonfile.createNewFile();
//			FileWriter jsonfilewriter = new FileWriter(jsonfile);
//			String jsontext = "payload={\"channel\": \"" + channel + "\", \"username\": \"score_sokuhou\", \"text\": \""
//					+ text + "\", \"icon_emoji\": \":baseball:\"}";
//			jsonfilewriter.write(jsontext);
//			jsonfilewriter.close();
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//
//		// slack実行
//		String command = "/usr/bin/curl -X POST https://hooks.slack.com/services/○○ -d @" + jsonfilePath;
//		try {
//			Process process = Runtime.getRuntime().exec(command);
//			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				System.out.println(line);
//			}
//			in.close();
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//	}
//
//	private static void makeLog() throws IOException, ParseException {
//		for (Map.Entry<Integer, Map<String, String>> game_info : schedule_data.entrySet()) {
//			String game_file_path = base_tmpdir + "/" + game_info.getKey() + "_game.txt";
//			File gamefile = new File(game_file_path);
//			gamefile.createNewFile();
//			FileWriter filewriter = new FileWriter(gamefile);
//			String text = game_info.getValue().get("team1") + "\r\n" + game_info.getValue().get("team2") + "\r\n"
//					+ game_info.getValue().get("score1") + "\r\n" + game_info.getValue().get("score2") + "\r\n"
//					+ game_info.getValue().get("eninng");
//			filewriter.write(text);
//			filewriter.close();
//		}
//	}

	private static void gameCheck() throws IOException, ParseException {
		parseTable();

		Boolean active_flag = false;
		// 中止や終了の際に何もしないようにするため
		loop: for (Map.Entry<Integer, Map<String, String>> game_info : schedule_data.entrySet()) {
			// 前回取得情報の取得
			String game_file_path = base_tmpdir + "/" + game_info.getKey() + "_game.txt";
			File newfile = new File(game_file_path);
			BufferedReader br = new BufferedReader(new FileReader(newfile));
			String str = br.readLine();

			Integer row = 0;
			String before_score_1 = "";
			String before_score_2 = "";
			String now;
			while (str != null) {
				row++;
				if (row == 3) {
					before_score_1 = str;
				}
				if (row == 4) {
					before_score_2 = str;
				}
				if (str.equals("結果") || str.equals("中止")) {
					// 処理終了
					continue loop;
				}
				str = br.readLine();
			}
			br.close();

			String eninng = game_info.getValue().get("eninng");
			String score1 = game_info.getValue().get("score1");
			String score2 = game_info.getValue().get("score2");
			String team1 = game_info.getValue().get("team1");
			String team2 = game_info.getValue().get("team2");

			// 現在まだ試合が行われているかのチェック
			if (!eninng.equals("結果") && !eninng.equals("中止")) {
				active_flag = true;
			}

			System.out.println(eninng);
			if (eninng.equals("結果") || eninng.equals("中止") || !score1.equals(before_score_1)
					|| !score2.equals(before_score_2)) {
				// 出力
				String alltext = "";
				// if (
				alltext += "_";
				alltext += eninng;
				alltext += "_\\r\\n";
				if (!score2.equals(before_score_2)) {
					alltext += "*";
				}
				alltext += team2 + " " + score2;
				if (!score2.equals(before_score_2)) {
					alltext += "*";
				}
				alltext += " - ";
				if (!score1.equals(before_score_1)) {
					alltext += "*";
				}
				alltext += score1 + " " + team1;
				if (!score1.equals(before_score_1)) {
					alltext += "*";
				}
				// System.out.println(alltext);

				// 処理が一回完了してたらファイルを作成して作らせないようにする + 必要なJSONを作成する
//				slackSend(alltext);

			}
		}

		// すでにすべての試合が終わっている場合は.endファイルを作成する
		if (active_flag == false) {
			File end_file_ob = new File(end_file);
			end_file_ob.createNewFile();
		}

//		// ログ書き込み
//		makeLog();

	}

	private static void stamenSokuho() throws IOException, ParseException {
		for (Map.Entry<Integer, Map<String, String>> game_info : schedule_data.entrySet()) {
			String team1 = game_info.getValue().get("team1");
			String team2 = game_info.getValue().get("team2");
			String stamen_end_file = base_tmpdir + "/" + game_info.getKey() + "_s.txt";
			File stamen_end_file_ob = new File(stamen_end_file);
			if (stamen_end_file_ob.exists()) {
				System.out.println("処理終了(既に処理済)");
				continue;
			}

			String stamenurl = "http://○○" + game_info.getValue().get("target_href") + "top";

			String alltext = "";
			alltext = team2 + "対" + team1 + "のスタメン" + "\\r\\n";
			alltext += "```";
			Document game_document = Jsoup.connect(stamenurl).get();
			// スタメン
			Elements team1_dom = game_document.select("#yjSNLiveStartingmember div.column-right");
			Elements team2_dom = game_document.select("#yjSNLiveStartingmember div.column-left");
			if (team1_dom.text().equals("") || team2_dom.text().equals("")) {
				System.out.println("処理終了(試合開始前)");
				continue;
			}
			alltext += team1 + "\\r\\n";
			alltext += stamenCheck(team1_dom) + "\\r\\n";
			alltext += "\\r\\n";
			alltext += team2 + "\\r\\n";
			alltext += stamenCheck(team2_dom) + "\\r\\n";
			alltext += "```";
			// slackSend(alltext);

			stamen_end_file_ob.createNewFile();
		}

	}

	private static String stamenCheck(Elements team_dom) {
		String text = "";
		Elements team1_fielder_dom = team_dom.select("table:nth-child(2)");
		for (int i = 1; i <= 9; i++) {
			Elements player_dom = team1_fielder_dom.select("tr:nth-child(" + (2 + i) + ")");
			String player_no = player_dom.select("td:nth-child(1)").text();
			String player_position = player_dom.select("td:nth-child(2)").text();
			String player_name = player_dom.select("td:nth-child(3)").text();
			text += player_no + player_position + player_name + "\\r\\n";
		}
		Elements team1_pitcher_dom = team_dom.select("table:nth-child(1)");
		Elements pitcher_dom = team1_pitcher_dom.select("tr:nth-child(3)");
		String pitcher_no = pitcher_dom.select("td:nth-child(1)").text();
		String pitcher_position = pitcher_dom.select("td:nth-child(2)").text();
		String pitcher_name = pitcher_dom.select("td:nth-child(3)").text();
		text += pitcher_no + pitcher_position + pitcher_name + "\\r\\n";

		return text;
	}

	private static void rankCheck() throws IOException, ParseException {
		String rank_url = "http://○○/";
		Document game_document = Jsoup.connect(rank_url).get();

		rankDetail(game_document, "#sta_c", c_rank_file, "セリーグ");
		rankDetail(game_document, "#sta_p", p_rank_file, "パリーグ");
	}

	private static void rankDetail(Document game_document, String div_id, String rank_file, String league_name)
			throws IOException, ParseException {
		Elements data = game_document.select(div_id + " table.NpbPlSt");
		String rank_text = league_name + "の順位表" + "\\r\\n";
		rank_text += "```";
		for (int i = 2; i <= 7; i++) {
			Elements team_data = data.select("tr:nth-child(" + i + ")");
			String rank = team_data.select("td:nth-child(1)").text();
			String team = team_data.select("td:nth-child(2)").text();
			String game = team_data.select("td:nth-child(3)").text();
			String win = team_data.select("td:nth-child(4)").text();
			String lose = team_data.select("td:nth-child(5)").text();
			String draw = team_data.select("td:nth-child(6)").text();
			String ratio = team_data.select("td:nth-child(7)").text();
			String sa = team_data.select("td:nth-child(8)").text();
			rank_text += rank + " " + team + "\\r\\n";
			rank_text += game + "試合" + win + "勝" + lose + "敗" + draw + "分" + "(" + ratio + ") [" + sa + "]" + "\\r\\n";
		}
		rank_text += "```";

		File rank_ob = new File(rank_file);
		// ない場合はとりあえず書き込み
		if (rank_ob.exists()) {
			// 指定のファイル URL のファイルをバイト列として読み込む
			BufferedReader br = new BufferedReader(new FileReader(rank_ob));
			// 読み込んだバイト列を UTF-8 でデコードして文字列にする
			String before_rank = br.readLine();
			if (!rank_text.equals(before_rank)) {
				// slackSend(rank_text);
			}
		}
		rank_ob.createNewFile();
		FileWriter filewriter = new FileWriter(rank_ob);
		filewriter.write(rank_text);
		filewriter.close();
	}

}