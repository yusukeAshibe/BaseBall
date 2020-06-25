package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ResultGame;
import com.example.form.SearchShowResultGameForm;
import com.example.service.ShowResultGameService;

@Controller
@RequestMapping("/")
public class ShowTopPageController {

	@Autowired
	private ShowResultGameService showResultGameService;

	@ModelAttribute
	private SearchShowResultGameForm setUpSearchShowResultGameForm() {
		return new SearchShowResultGameForm();
	}

	/**
	 * 初期画面表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping("/")
	public String showHomePage(Model model, SearchShowResultGameForm form) {

		// 初期の日付設定
		Date date = new Date();// 今日の日付を取得（DATE型）
		Calendar calendar = Calendar.getInstance();// （日付の操作を行う為、Calender型に変更）
		calendar.setTime(date);// Calender型に取得した今日の日付を入れる
		calendar.add(Calendar.DATE, -1);// 一日前に戻す。（初期表示）
		Date d1 = calendar.getTime();// Calendar型の日時をDate型に戻す
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// SimpleDateFormat型の形を決める
		String strDate = dateFormat.format(d1);// String型に変換

		if (form.getDate() == null) {// 初期表示の為に前日の日付をformに入れておく
			form.setDate(strDate);
		}

		// 選択された日付をデータ型に変換(選択日表示に利用)
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date selectDate = null;
		try {
			selectDate = sdFormat.parse(form.getDate());
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// 試合結果の表示
		List<ResultGame> resultGameList = showResultGameService.resultGameList(form.getDate());// 指定された日付の試合結果を取得

		model.addAttribute("resultGameList", resultGameList);

		if (resultGameList == null) {// 試合がない日の日付表示
			model.addAttribute("day", selectDate);
		} else {// 試合がある日の日付表示
			model.addAttribute("day", resultGameList.get(0).getDate());
		}

		model.addAttribute("date", date);// 今日の日付表示

		return "list.html";

	}

}
