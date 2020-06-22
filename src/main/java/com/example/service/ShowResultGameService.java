package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.ResultGame;
import com.example.repository.ScrapeRepository;

@Service
@Transactional
public class ShowResultGameService {

	@Autowired
	private ScrapeRepository scrapeRepository;

	/**
	 * 画面に試合結果の表示
	 * 
	 * @param day
	 * @return
	 */
	public List<ResultGame> resultGameList(String day) {

		List<ResultGame> resultGameList = scrapeRepository.ResultSearchByDay(day);
		return resultGameList;

	}

}
