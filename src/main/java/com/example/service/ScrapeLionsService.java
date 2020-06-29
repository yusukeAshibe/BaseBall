package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Batter;
import com.example.domain.Pitcher;
import com.example.repository.BatterRepository;
import com.example.repository.PitcherRepository;

/**
 * 埼玉西武ライオンズの選手情報をスクレイプ.
 * 
 * @author ashibe
 *
 */
@Service
@Transactional
public class ScrapeLionsService {
	@Autowired
	private PitcherRepository pitcherRepository;
	@Autowired
	private BatterRepository batterRepository;

	/**
	 * 投手の情報のインサート
	 */
	public void scrapePitcher() {
		Document document = null;

		try {
			document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/teams/7/memberlist?kind=p").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements elements = document.select("tr.bb-playerTable__row");

		// 仮の受け取り変数
		String uniformNumber;// 背番号
		String firstName;// 苗字
		String lastName;// 名前
		String defenceRate;// 防御率
		String pitching;// 登板数
		String starter;// 先発回数
		String completeGame;// 完投数
		String shutout;// 完封
		String quarityStart;// QS回数
		String win;// 勝ち数
		String lose;// 負け数;
		String hold;// ホールド数
		String holdPoint;// ホールドポイント数
		String save;// セーブ数
		String winRate;// 勝率
		String inning;// 投球回数
		String hit;// 被安打
		String homerun;// 被本塁打
		String strikeout;// 奪三振
		String strikeoutRate;// 奪三振率
		String fourBall;// 与四球数
		String deadball;// 与死球数
		String wildPitch;// 暴投数
		String balk;// ボーク数
		String goal;// 失点
		String earnedRun; // 自責点
		String battingAverage;// 被打率
		String KBB;// 奪三振 (K:strikeout)と与四球 (BB:Base on Balls)の比率で、投手の制球力を示す指標の1つ。
		String WHIP;// 1投球回あたり何人の走者を出したかを表す数値

		Pitcher pitcher = new Pitcher();
		List<Pitcher> pitcherList = new ArrayList<>();

		loop: for (Element element : elements) {

			String[] pitcherDetail = null;
			pitcherDetail = element.text().split(" ");
			// System.out.println(Arrays.toString(pitcherDetail));

			// 変数の初期化
			uniformNumber = null;// 背番号
			firstName = null;// 選手名
			lastName = null;
			defenceRate = null;// 防御率
			pitching = null;// 登板数
			starter = null;// 先発回数
			completeGame = null;// 完投数
			shutout = null;// 完封
			quarityStart = null;// QS回数
			win = null;// 勝ち数
			lose = null;// 負け数;
			hold = null;// ホールド数
			holdPoint = null;// ホールドポイント数
			save = null;// セーブ数
			winRate = null;// 勝率
			inning = null;// 投球回数
			hit = null;// 被安打
			homerun = null;// 被本塁打
			strikeout = null;// 奪三振
			strikeoutRate = null;// 奪三振率
			fourBall = null;// 与四球数
			deadball = null;// 与死球数
			wildPitch = null;// 暴投数
			balk = null;// ボーク数
			goal = null;// 失点
			earnedRun = null; // 自責点
			battingAverage = null;// 被打率
			KBB = null;// 奪三振 (K:strikeout)と与四球 (BB:Base on Balls)の比率で、投手の制球力を示す指標の1つ。
			WHIP = null;// 1投球回あたり何人の走者を出したかを表す数値

			int i = 0;// 何番目の要素を入れるか;

			// 文字列が入ってきていたら次のループへ
			if (pitcherDetail[i].equals("背番号")) {
				// System.out.println(pitcherDetail[i]);
				continue loop;
			} else {
				// 背番号
				uniformNumber = pitcherDetail[i++];
			}

			// 選手名
			if (pitcherDetail[i].matches("^[ァ-ヶー]*$")) {
				firstName = pitcherDetail[i++];
				lastName = null;
				// System.out.println(Arrays.toString(pitcherDetail));
			} else {
				firstName = pitcherDetail[i++];
				lastName = pitcherDetail[i++];
				// System.out.println(firstName + lastName);
			}

			// 成績の入っていない（-になっている部分を0に変更）
			// 防御率
			if (pitcherDetail[i].equals("-")) {
				defenceRate = "0.0";
				i++;
			} else {
				defenceRate = pitcherDetail[i++];
			}
			// 登板数
			if (pitcherDetail[i].equals("-")) {
				pitching = "0";
				i++;
			} else {
				pitching = pitcherDetail[i++];
			}
			// 先発回数
			if (pitcherDetail[i].equals("-")) {
				starter = "0";
				i++;
			} else {
				starter = pitcherDetail[i++];
			}
			// 完投数
			if (pitcherDetail[i].equals("-")) {
				completeGame = "0";
				i++;
			} else {
				completeGame = pitcherDetail[i++];
			}
			// 完封
			if (pitcherDetail[i].equals("-")) {
				shutout = "0";
				i++;
			} else {
				shutout = pitcherDetail[i++];
			}
			// QS回数
			if (pitcherDetail[i].equals("-")) {
				quarityStart = "0";
				i++;
			} else {
				quarityStart = pitcherDetail[i++];
			}
			// 勝ち数
			if (pitcherDetail[i].equals("-")) {
				win = "0";
			} else {
				win = pitcherDetail[i++];
			}

			// 負け数;

			if (pitcherDetail[i].equals("-")) {
				lose = "0";
				i++;
			} else {
				lose = pitcherDetail[i++];
			}

			// ホールド数
			if (pitcherDetail[i].equals("-")) {
				hold = "0";
				i++;
			} else {
				hold = pitcherDetail[i++];
			}

			// ホールドポイント数
			if (pitcherDetail[i].equals("-")) {
				holdPoint = "0";
				i++;
			} else {
				holdPoint = pitcherDetail[i++];
			}

			// セーブ数
			if (pitcherDetail[i].equals("-")) {
				save = "0";
				i++;
			} else {
				save = pitcherDetail[i++];
			}

			// 勝率
			if (pitcherDetail[i].equals("-")) {
				winRate = "0.0";
				i++;
			} else {
				winRate = pitcherDetail[i++];
			}

			// 投球回数
			if (pitcherDetail[i].equals("-")) {
				inning = "0.0";
				i++;
			} else {
				inning = pitcherDetail[i++];
			}

			// 被安打
			if (pitcherDetail[i].equals("-")) {
				hit = "0";
				i++;
			} else {
				hit = pitcherDetail[i++];
			}

			// 被本塁打
			if (pitcherDetail[i].equals("-")) {
				homerun = "0";
				i++;
			} else {
				homerun = pitcherDetail[i++];
			}

			// 奪三振
			if (pitcherDetail[i].equals("-")) {
				strikeout = "0";
				i++;
			} else {
				strikeout = pitcherDetail[i++];
			}

			// 奪三振率
			if (pitcherDetail[i].equals("-")) {
				strikeoutRate = "0";
				i++;
			} else {
				strikeoutRate = pitcherDetail[i++];
			}

			// 与四球数
			if (pitcherDetail[i].equals("-")) {
				fourBall = "0";
				i++;
			} else {
				fourBall = pitcherDetail[i++];
			}

			// 与死球数
			if (pitcherDetail[i].equals("-")) {
				deadball = "0";
				i++;
			} else {
				deadball = pitcherDetail[i++];
			}

			// 暴投数
			if (pitcherDetail[i].equals("-")) {
				wildPitch = "0";
				i++;
			} else {
				wildPitch = pitcherDetail[i++];
			}

			// ボーク数
			if (pitcherDetail[i].equals("-")) {
				balk = "0";
				i++;
			} else {
				balk = pitcherDetail[i++];
			}

			// 失点
			if (pitcherDetail[i].equals("-")) {
				goal = "0";
				i++;
			} else {
				goal = pitcherDetail[i++];
			}

			// 自責点
			if (pitcherDetail[i].equals("-")) {
				earnedRun = "0";
				i++;
			} else {
				earnedRun = pitcherDetail[i++];
			}

			// 被打率
			if (pitcherDetail[i].equals("-")) {
				battingAverage = "0.0";
				i++;
			} else {
				battingAverage = pitcherDetail[i++];
			}

			// 奪三振 (K:strikeout)と与四球 (BB:Base on Balls)の比率で、投手の制球力を示す指標の1つ。
			if (pitcherDetail[i].equals("-")) {
				KBB = "0.0";
				i++;
			} else {
				KBB = pitcherDetail[i++];
			}

			// 1投球回あたり何人の走者を出したかを表す数値
			if (pitcherDetail[i].equals("-")) {
				WHIP = "0.0";
				i++;
			} else {
				WHIP = pitcherDetail[i++];

			}

			// pitcherオブジェクトに詰め替え
			pitcher = new Pitcher();

			pitcher.setUniformNumber(uniformNumber);
			pitcher.setFirstName(firstName);
			pitcher.setLastName(lastName);
			pitcher.setDefenceRate(Double.parseDouble(defenceRate));
			pitcher.setPitching(Integer.parseInt(pitching));
			pitcher.setStarter(Integer.parseInt(starter));
			pitcher.setCompleteGame(Integer.parseInt(completeGame));
			pitcher.setShutout(Integer.parseInt(shutout));
			pitcher.setQuarityStart(Integer.parseInt(quarityStart));
			pitcher.setWin(Integer.parseInt(win));
			pitcher.setLose(Integer.parseInt(lose));
			pitcher.setHold(Integer.parseInt(hold));
			pitcher.setHoldPoint(Integer.parseInt(holdPoint));
			pitcher.setSave(Integer.parseInt(save));
			pitcher.setWinRate(Double.parseDouble(winRate));
			pitcher.setInning(Double.parseDouble(inning));
			pitcher.setHit(Integer.parseInt(hit));
			pitcher.setHomerun(Integer.parseInt(homerun));
			pitcher.setStrikeout(Integer.parseInt(strikeout));
			pitcher.setStrikeoutRate(Double.parseDouble(strikeoutRate));
			pitcher.setFourBall(Integer.parseInt(fourBall));
			pitcher.setDeadball(Integer.parseInt(deadball));
			pitcher.setWildPitch(Integer.parseInt(wildPitch));
			pitcher.setBalk(Integer.parseInt(balk));
			pitcher.setGoal(Integer.parseInt(goal));
			pitcher.setEarnedRun(Integer.parseInt(earnedRun));
			pitcher.setBattingAverage(Double.parseDouble(battingAverage));
			pitcher.setKBB(Double.parseDouble(KBB));
			pitcher.setWHIP(Double.parseDouble(WHIP));
			// 選手の所属チームを入れる
			pitcher.setTeamName("西武");
			// 最初の行と最後の行を省く（文字列の情報は入れない）
			if (!pitcher.getUniformNumber().equals("背番号")) {
				pitcherList.add(pitcher);

			}

		}
		// System.out.println(pitcherList);
		pitcherRepository.insertPitcherDate(pitcherList);

	}

	/**
	 * 打者情報のインサート
	 */
	public void scrapeBatter() {

		Document document = null;

		try {
			document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/teams/7/memberlist?kind=b").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements elements = document.select("tr.bb-playerTable__row");

		String uniformNumber;// 背番号
		String firstName;// 苗字
		String lastName;// 名前
		String battingAverage;// 打率
		String game;// 試合数
		String plateAppeearance;// 打席
		String atBat;// 打数
		String hit;// 安打数
		String twoBase;// 二塁打数
		String threeBase;// 三塁打数
		String homerun;// 本塁打数
		String baseHit;// 塁打数
		String RBI;// 打点
		String score;// 得点
		String strikeOut;// 三振数
		String fourBall;// 四球数
		String deadBall;// 死球数
		String bunt;// 犠打数
		String SF;// 犠飛数
		String steal;// 盗塁数
		String caughtSteal;// 盗塁殺数
		String doublePlay;// 併殺打数
		String onBasePercent;// 出塁率
		String sluggingPercent;// 長打率
		String OPS;// 出塁率＋長打率
		String scoringArea;// 得点圏打率
		String error;// 失策数

		Batter batter = new Batter();
		List<Batter> batterList = new ArrayList<>();

		loop: for (Element element : elements) {

			String[] batterDetail = null;
			batterDetail = element.text().split(" ");
			// System.out.println(Arrays.toString(batterDetail));

			uniformNumber = null;// 背番号
			firstName = null;// 苗字
			lastName = null;// 名前
			battingAverage = null;// 打率
			game = null;// 試合数
			plateAppeearance = null;// 打数
			atBat = null;// 打席
			hit = null;// 安打数
			twoBase = null;// 二塁打数
			threeBase = null;// 三塁打数
			homerun = null;// 本塁打数
			baseHit = null;// 塁打数
			RBI = null;// 打点
			score = null;// 得点
			strikeOut = null;// 三振数
			fourBall = null;// 四球数
			deadBall = null;// 死球数
			bunt = null;// 犠打数
			SF = null;// 犠飛数
			steal = null;// 盗塁数
			caughtSteal = null;// 盗塁殺数
			doublePlay = null;// 併殺打数
			onBasePercent = null;// 出塁率
			sluggingPercent = null;// 長打率
			OPS = null;// 出塁率＋長打率
			scoringArea = null;// 得点圏打率
			error = null;// 失策数

			int i = 0;// 何番目の要素を入れるか;

			// 文字列が入ってきていたら次のループへ
			if (batterDetail[i].equals("背番号")) {
				// System.out.println(batterDetail[i]);
				continue loop;
			} else {
				// 背番号
				uniformNumber = batterDetail[i++];
			}

			// 選手名
			if (batterDetail[i].matches("^[ァ-ヶー]*$")) {
				firstName = batterDetail[i++];
				lastName = null;

			} else {
				firstName = batterDetail[i++];
				lastName = batterDetail[i++];
				// System.out.println(firstName + lastName);
			}

			// 成績の入っていない（-になっている部分を0に変更）

			// 打率
			if (batterDetail[i].equals("-")) {
				battingAverage = "0.0";
				i++;
			} else {
				battingAverage = batterDetail[i++];
			}
			// 試合数
			if (batterDetail[i].equals("-")) {
				game = "0";
				i++;
			} else {
				game = batterDetail[i++];
			}
			// 打席
			if (batterDetail[i].equals("-")) {
				plateAppeearance = "0";
				i++;
			} else {
				plateAppeearance = batterDetail[i++];
			}
			// 打数
			if (batterDetail[i].equals("-")) {
				atBat = "0";
				i++;
			} else {
				atBat = batterDetail[i++];
			}
			// 安打数
			if (batterDetail[i].equals("-")) {
				hit = "0";
				i++;
			} else {
				hit = batterDetail[i++];
			}
			// 二塁打
			if (batterDetail[i].equals("-")) {
				twoBase = "0";
				i++;
			} else {
				twoBase = batterDetail[i++];
			}
			// 三塁打
			if (batterDetail[i].equals("-")) {
				threeBase = "0";
			} else {
				threeBase = batterDetail[i++];
			}

			// 本塁打

			if (batterDetail[i].equals("-")) {
				homerun = "0";
				i++;
			} else {
				homerun = batterDetail[i++];
			}

			// 塁打数
			if (batterDetail[i].equals("-")) {
				baseHit = "0";
				i++;
			} else {
				baseHit = batterDetail[i++];
			}

			// 打点
			if (batterDetail[i].equals("-")) {
				RBI = "0";
				i++;
			} else {
				RBI = batterDetail[i++];
			}

			// 得点
			if (batterDetail[i].equals("-")) {
				score = "0";
				i++;
			} else {
				score = batterDetail[i++];
			}

			// 三振数
			if (batterDetail[i].equals("-")) {
				strikeOut = "0";
				i++;
			} else {
				strikeOut = batterDetail[i++];
			}

			// 四球数
			if (batterDetail[i].equals("-")) {
				fourBall = "0";
				i++;
			} else {
				fourBall = batterDetail[i++];
			}

			// 死球数
			if (batterDetail[i].equals("-")) {
				deadBall = "0";
				i++;
			} else {
				deadBall = batterDetail[i++];
			}

			// 犠打
			if (batterDetail[i].equals("-")) {
				bunt = "0";
				i++;
			} else {
				bunt = batterDetail[i++];
			}

			// 犠飛
			if (batterDetail[i].equals("-")) {
				SF = "0";
				i++;
			} else {
				SF = batterDetail[i++];
			}

			// 盗塁
			if (batterDetail[i].equals("-")) {
				steal = "0";
				i++;
			} else {
				steal = batterDetail[i++];
			}

			// 盗塁死
			if (batterDetail[i].equals("-")) {
				caughtSteal = "0";
				i++;
			} else {
				caughtSteal = batterDetail[i++];
			}

			// 併殺打
			if (batterDetail[i].equals("-")) {
				doublePlay = "0";
				i++;
			} else {
				doublePlay = batterDetail[i++];
			}

			// 出塁率
			if (batterDetail[i].equals("-")) {
				onBasePercent = "0.0";
				i++;
			} else {
				onBasePercent = batterDetail[i++];
			}

			// 長打率
			if (batterDetail[i].equals("-")) {
				sluggingPercent = "0.0";
				i++;
			} else {
				sluggingPercent = batterDetail[i++];
			}

			// OPS
			if (batterDetail[i].equals("-")) {
				OPS = "0.0";
				i++;
			} else {
				OPS = batterDetail[i++];
			}

			// 得点圏
			if (batterDetail[i].equals("-")) {
				scoringArea = "0.0";
				i++;
			} else {
				scoringArea = batterDetail[i++];
			}

			// 失策
			if (batterDetail[i].equals("-")) {
				error = "0";
				i++;
			} else {
				error = batterDetail[i++];
			}

			batter = new Batter();
			batter.setUniformNumber(uniformNumber);
			batter.setFirstName(firstName);
			batter.setLastName(lastName);
			batter.setBattingAverage(Double.parseDouble(battingAverage));
			batter.setGame(Integer.parseInt(game));
			batter.setPlateAppearance(Integer.parseInt(plateAppeearance));
			batter.setAtBat(Integer.parseInt(atBat));
			batter.setHit(Integer.parseInt(hit));
			batter.setTwoBase(Integer.parseInt(twoBase));
			batter.setThreeBase(Integer.parseInt(threeBase));
			batter.setHomerun(Integer.parseInt(homerun));
			batter.setBaseHit(Integer.parseInt(baseHit));
			batter.setRBI(Integer.parseInt(RBI));
			batter.setScore(Integer.parseInt(score));
			batter.setStrikeOut(Integer.parseInt(strikeOut));
			batter.setFourBall(Integer.parseInt(fourBall));
			batter.setDeadBall(Integer.parseInt(deadBall));
			batter.setBunt(Integer.parseInt(bunt));
			batter.setSF(Integer.parseInt(SF));
			batter.setSteal(Integer.parseInt(steal));
			batter.setCaughtSteal(Integer.parseInt(caughtSteal));
			batter.setDoublePlay(Integer.parseInt(doublePlay));
			batter.setOnBasePercent(Double.parseDouble(onBasePercent));
			batter.setSluggingPercent(Double.parseDouble(sluggingPercent));
			batter.setOPS(Double.parseDouble(OPS));
			batter.setScoringArea(Double.parseDouble(scoringArea));
			batter.setError(Integer.parseInt(error));
			batter.setTeamName("西武");

			// 最初の行と最後の行を省く（文字列の情報は入れない）
			if (!batter.getUniformNumber().equals("背番号")) {
				batterList.add(batter);

			}

		}
		batterRepository.insertBatterDate(batterList);
		// System.out.println(batterList);

	}
}
