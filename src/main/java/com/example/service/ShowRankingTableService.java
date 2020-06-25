package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;
import com.example.repository.RankingTableRepository;

/**
 * 順位表情報を操作するためのメソッド
 * 
 * @author ashibe
 *
 */
@Service
@Transactional
public class ShowRankingTableService {

	@Autowired
	private RankingTableRepository rankingTableRepository;

	/**
	 * 今日時点でのセリーグの順位表情報を取得
	 * 
	 * @return
	 */
	public List<CentralLeagueRanking> showCentralLeagueRanking() {

		Date date = new Date();// 今日の日付
		// 今日日付時点での順位表情報を取得
		List<CentralLeagueRanking> centalLeagueRanking = rankingTableRepository.centralLeagueRankingFindByDate(date);
		return centalLeagueRanking;
	}

	/**
	 * 今日時点でのパリーグの順位表情報を取得
	 * 
	 * @return
	 */
	public List<PacificLeagueRanking> showPacificLeaguRanking() {
		Date date = new Date();
		List<PacificLeagueRanking> pacificLeagueRanking = rankingTableRepository.pacificLeagueRankingFindByDate(date);
		return pacificLeagueRanking;
	}
}
