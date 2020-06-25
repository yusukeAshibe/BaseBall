package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.ResultGame;
import com.example.repository.GameResultRepository;

@Service
@Transactional
public class ShowResultGameService {

	@Autowired
	private GameResultRepository scrapeRepository;

	/**
	 * 画面に試合結果の表示
	 * 
	 * @param day
	 * @return
	 */
	public List<ResultGame> resultGameList(String day) {

		// string型の日付をDate型に変更。
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 日付で試合情報の取得
		List<ResultGame> resultGameList = scrapeRepository.ResultSearchByDay(date);

		// 検索された日付に試合がなかった場合nullを返す。
		if (resultGameList.size() == 0) {
			return null;
		}
		return resultGameList;

	}

}
