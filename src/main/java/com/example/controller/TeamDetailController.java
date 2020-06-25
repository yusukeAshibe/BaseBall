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
			model.addAttribute("teamDetail", centralTeamDetail);
		} else {
			PacificLeagueRanking pacificTeamDetail = teamDetailService.PacificTeamDetailFindByTeamName(teamName);
			model.addAttribute("teamDetail", pacificTeamDetail);
		}

		// 検索したチームがその日試合に勝ったかどうか検索
		List<ResultGame> teamResultGameList = teamDetailService.ResultsearchByTeamName(teamName);
		List<String> winOrLoseList = new ArrayList<>();
		List<String> oppounentList = new ArrayList<>();
		String winOrLose = null;// 勝敗
		String oppounent = null;// 対戦相手

		for (ResultGame teamResultGame : teamResultGameList) {
			winOrLose = null;
			if (teamResultGame.getTeam1().equals(teamName)) {
				if (teamResultGame.getScore1() > teamResultGame.getScore2()) {
					winOrLose = "〇";

				} else if (teamResultGame.getScore1() < teamResultGame.getScore2()) {
					winOrLose = "×";
				} else {
					winOrLose = "△";
				}
			} else if (teamResultGame.getTeam2().equals(teamName)) {
				if (teamResultGame.getScore2() > teamResultGame.getScore1()) {
					winOrLose = "〇";
				} else if (teamResultGame.getScore2() < teamResultGame.getScore1()) {
					winOrLose = "×";
				} else {
					winOrLose = "△";
				}

			}
			winOrLoseList.add(winOrLose);

			if (teamName.equals(teamResultGame.getTeam1())) {
				oppounent = teamResultGame.getTeam2();
			} else {
				oppounent = teamResultGame.getTeam1();
			}
			oppounentList.add(oppounent);
		}
		System.out.println(winOrLoseList);
		System.out.println(oppounentList);
		model.addAttribute("oppounentList", oppounentList);
		model.addAttribute("winOrLoseList", winOrLoseList);
		model.addAttribute("teamResultGameList", teamResultGameList);

		// 日付表示

		Date date = new Date();
		model.addAttribute("date", date);

		return "teamDetail";

	}

}
