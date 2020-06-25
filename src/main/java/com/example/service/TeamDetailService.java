package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;
import com.example.domain.ResultGame;
import com.example.repository.GameResultRepository;
import com.example.repository.RankingTableRepository;

/**
 * チームの詳細情報を操作するサービス.
 * 
 * @author ashibe
 *
 */
@Transactional
@Service
public class TeamDetailService {

	@Autowired
	private RankingTableRepository rankingTableRepository;
	@Autowired
	private GameResultRepository gameResultRepository;

	/**
	 * セリーグのチーム今日時点での詳細情報の取得.
	 * 
	 * @param teamName
	 * @return
	 */
	public CentralLeagueRanking CentralTeamDetailFindByTeamName(String teamName) {
		CentralLeagueRanking centralTeamDetail = rankingTableRepository.CentralTeamDetailFindByTeamName(teamName);
		return centralTeamDetail;

	}

	/**
	 * パリーグのチーム今日時点での詳細情報の取得.
	 * 
	 * @param teamName
	 * @return
	 */
	public PacificLeagueRanking PacificTeamDetailFindByTeamName(String teamName) {
		PacificLeagueRanking pacificTeamDetail = rankingTableRepository.PacificTeamDetailFindByTeamName(teamName);
		return pacificTeamDetail;

	}

	/**
	 * チーム別の試合結果一覧を取得
	 * 
	 * @param teamName
	 * @return
	 */
	public List<ResultGame> ResultsearchByTeamName(String teamName) {
		List<ResultGame> teamResultList = gameResultRepository.ResultSearchByTeamName(teamName);
		return teamResultList;

	}

}
