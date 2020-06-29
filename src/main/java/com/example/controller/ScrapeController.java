package com.example.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ScrapeCarpService;
import com.example.service.ScrapeDeNAService;
import com.example.service.ScrapeDragonsService;
import com.example.service.ScrapeEaglesService;
import com.example.service.ScrapeFitersService;
import com.example.service.ScrapeGiantsService;
import com.example.service.ScrapeLionsService;
import com.example.service.ScrapeOrixService;
import com.example.service.ScrapeRankService;
import com.example.service.ScrapeResultGameService;
import com.example.service.ScrapeRotteService;
import com.example.service.ScrapeSoftBankService;
import com.example.service.ScrapeTigersService;
import com.example.service.ScrapeYakultService;

/**
 * スクレイピングの操作を行うコントローラー
 * 
 * @author ashibe
 *
 */
@Controller
@RequestMapping("/scrape")
public class ScrapeController {

	@Autowired
	private ScrapeRankService scrapeRankService;
	@Autowired
	private ScrapeResultGameService scrapeResultGameService;

	// セリーグ

	@Autowired
	private ScrapeGiantsService scrapeGiantsService;
	@Autowired
	private ScrapeTigersService scrapeTigersService;
	@Autowired
	private ScrapeDragonsService scrapeDradonsService;
	@Autowired
	private ScrapeDeNAService scrapeDeNaService;
	@Autowired
	private ScrapeYakultService scrapeYakultService;
	@Autowired
	private ScrapeCarpService scrapeCarpService;

	// パリーグ

	@Autowired
	private ScrapeOrixService ScrapeOrixService;
	@Autowired
	private ScrapeFitersService scrapeFitersService;
	@Autowired
	private ScrapeLionsService scrapeLionsService;
	@Autowired
	private ScrapeRotteService scrapeRotteService;
	@Autowired
	private ScrapeSoftBankService scrapeSoftBankService;
	@Autowired
	private ScrapeEaglesService scrapeEaglesSrevice;

	/**
	 * 順位表情報をスクレイピング.
	 * 
	 * @return
	 */
	@RequestMapping("/ranking")
	public String scrapeRanking(Model model) {

		Date date = new Date();
		model.addAttribute("today", date);

		// scrapeRankService.scrapeCentral();
		// scrapeRankService.scrapePacific();
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

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/pitcher")
	public String scrapePitcher() {
		scrapeGiantsService.scrapePitcher();
		scrapeTigersService.scrapePitcher();
		scrapeDradonsService.scrapePitcher();
		scrapeDeNaService.scrapePitcher();
		scrapeCarpService.scrapePitcher();
		scrapeYakultService.scrapePitcher();

		scrapeEaglesSrevice.scrapePitcher();
		scrapeFitersService.scrapePitcher();
		scrapeLionsService.scrapePitcher();
		scrapeRotteService.scrapePitcher();
		scrapeSoftBankService.scrapePitcher();
		ScrapeOrixService.scrapePitcher();
		return "Exercise.html";
	}

	@RequestMapping("/batter")
	public String scrapeBatter() {
		scrapeGiantsService.scrapeBatter();
		scrapeTigersService.scrapeBatter();
		scrapeDradonsService.scrapeBatter();
		scrapeDeNaService.scrapeBatter();
		scrapeCarpService.scrapeBatter();
		scrapeYakultService.scrapeBatter();

		scrapeEaglesSrevice.scrapeBatter();
		scrapeFitersService.scrapeBatter();
		scrapeLionsService.scrapeBatter();
		scrapeRotteService.scrapeBatter();
		scrapeSoftBankService.scrapeBatter();
		ScrapeOrixService.scrapeBatter();
		return "Exercise.html";
	}
}
