package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;
import com.example.service.ShowRankingTableService;

@Controller
@RequestMapping("/ranking")
public class ShowRankingTableController {

	@Autowired
	private ShowRankingTableService showRankingTableService;

	/**
	 * チーム情報詳細ページへの遷移
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showRanking")
	public String showRanking(Model model) {

		// 今日現在までのチーム詳細情報を表示
		Date date = new Date();
		List<CentralLeagueRanking> centralLeagueRankingList = showRankingTableService.showCentralLeagueRanking();
		List<PacificLeagueRanking> pacificLeagueRankingList = showRankingTableService.showPacificLeaguRanking();
		model.addAttribute("centralLeagueRankingList", centralLeagueRankingList);
		model.addAttribute("pacificLeagueRankingList", pacificLeagueRankingList);
		model.addAttribute("date", date);

		return "Ranking.html";

	}

}
