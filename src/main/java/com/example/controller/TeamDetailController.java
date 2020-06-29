package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;
import com.example.domain.ResultGame;
import com.example.service.TeamDetailService;

/**
 * チーム詳細画面への遷移.
 * 
 * @author ashibe
 *
 */
@Controller
@RequestMapping("/teamDetail")
public class TeamDetailController {

	@Autowired
	private TeamDetailService teamDetailService;

	/**
	 * 指定されたチームの詳細情報を表示
	 * 
	 * @param teamName
	 * @param model
	 * @return
	 */
	@RequestMapping("/show")
	public String showTeamDetail(String teamName, Model model) {

		// チーム検索（まずはセリーグの検索、なかったらパリーグの検索を行う）
		CentralLeagueRanking centralTeamDetail = teamDetailService.CentralTeamDetailFindByTeamName(teamName);

		if (centralTeamDetail != null) {
			StringBuilder centralTeamRankingList = teamDetailService.CentralTeamrankListSearchByTeamName(teamName);
			StringBuilder centralGameDayList = teamDetailService.CentralGameDateListSearchByTeamName(teamName);

			model.addAttribute("rank", centralTeamRankingList);
			model.addAttribute("gameDate", centralGameDayList);
			model.addAttribute("teamDetail", centralTeamDetail);

		} else {
			PacificLeagueRanking pacificTeamDetail = teamDetailService.PacificTeamDetailFindByTeamName(teamName);
			StringBuilder pacificTeamRankingList = teamDetailService.PacificTeamrankListSearchByTeamName(teamName);
			StringBuilder pacificGameDayList = teamDetailService.PacificGameDateListSearchByName(teamName);
			System.out.println(pacificGameDayList);
			model.addAttribute("gameDate", pacificGameDayList);
			model.addAttribute("rank", pacificTeamRankingList);
			model.addAttribute("teamDetail", pacificTeamDetail);
		}

		// 検索したチームがその日試合に勝ったかどうか検索
		List<ResultGame> teamResultGameList = teamDetailService.ResultsearchByTeamName(teamName);
		// List<String> winOrLoseList = new ArrayList<>();
		// List<String> oppounentList = new ArrayList<>();
		String winOrLose = null;// 勝敗
		String opponent = null;// 対戦相手
		List<ResultGame> showResultGameList = new ArrayList<>();
		ResultGame resultGame = null;
		for (ResultGame teamResultGame : teamResultGameList) {

			resultGame = new ResultGame();
			// 球場
			resultGame.setStadium(teamResultGame.getStadium());
			// スコア
			resultGame.setScore1(teamResultGame.getScore1());
			resultGame.setScore2(teamResultGame.getScore2());
			// 日付
			resultGame.setDate(teamResultGame.getDate());
			model.addAttribute("gameDate", teamResultGame.getDate());

			// 勝敗
			if (teamResultGame.getTeam1().equals(teamName)) {
				if (teamResultGame.getScore1() > teamResultGame.getScore2()) {
					winOrLose = "○";
				} else if (teamResultGame.getScore1() < teamResultGame.getScore2()) {
					winOrLose = "●";
				} else {
					winOrLose = "△";
				}
			} else if (teamResultGame.getTeam2().equals(teamName)) {
				if (teamResultGame.getScore2() > teamResultGame.getScore1()) {
					winOrLose = "○";
				} else if (teamResultGame.getScore2() < teamResultGame.getScore1()) {
					winOrLose = "●";
				} else {
					winOrLose = "△";
				}
			}
			resultGame.setWinOrLose(winOrLose);

			if (teamName.equals(teamResultGame.getTeam1())) {
				opponent = teamResultGame.getTeam2();
			} else {
				opponent = teamResultGame.getTeam1();
			}
			resultGame.setOpponent(opponent);

			showResultGameList.add(resultGame);

		}

		model.addAttribute("teamResultGameList", showResultGameList);

		// 日付表示

		Date date = new Date();
		model.addAttribute("date", date);

		return "teamDetail";

	}

}
