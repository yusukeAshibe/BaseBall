package com.example.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ScrapeRankService;
import com.example.service.ScrapeResultGameService;

/**
 * スクレイピングの操作を行うコントローラー
 * 
 * @author ashibe
 *
 */
@Controller
@RequestMapping("/Scrape")
public class ScrapeController {

	@Autowired
	private ScrapeRankService scrapeRankService;

	@Autowired
	private ScrapeResultGameService scrapeResultGameService;

	/**
	 * 順位表情報をスクレイピング.
	 * 
	 * @return
	 */
	@RequestMapping("/ranking")
	public String scrapeRanking() {

		scrapeRankService.scrapeCentral();
		scrapeRankService.scrapePacific();
		return "Exercise.html";
	}

	/**
	 * 試合結果情報をスクレイピング
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/resultGame")
	public String scrape() throws ParseException {
		scrapeResultGameService.scrape();
		return "Exercise.html";

	}
}
