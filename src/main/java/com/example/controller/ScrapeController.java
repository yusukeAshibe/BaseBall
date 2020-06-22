package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ResultGame;
import com.example.form.SearchShowResultGameForm;
import com.example.service.ScrapeService;
import com.example.service.ShowResultGameService;

@Controller
@RequestMapping("/")
public class ScrapeController {

	@Autowired
	private ScrapeService scrapeService;

	@Autowired
	private ShowResultGameService showResultGameService;

	@ModelAttribute
	private SearchShowResultGameForm setUpSearchShowResultGameForm() {
		return new SearchShowResultGameForm();
	}

	@RequestMapping("/")
	public String showHomePage(Model model, SearchShowResultGameForm form) {
		List<ResultGame> resultGameList = showResultGameService.resultGameList("6-21");
		model.addAttribute("resultGameList", resultGameList);
		System.out.println(resultGameList);
		String day = resultGameList.get(0).getDay().replace("-", "月");
		model.addAttribute("day", day);

		return "list.html";

	}

	@RequestMapping("/scrape")
	public String scrape() {
		scrapeService.scrape();
		return "list.html";

	}
}
