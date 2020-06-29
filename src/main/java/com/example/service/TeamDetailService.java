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

	/**
	 * セリーグの選択された球団の順位情報を取得
	 * 
	 * @param teamName
	 * @return
	 */
	public StringBuilder CentralTeamrankListSearchByTeamName(String teamName) {
		List<CentralLeagueRanking> teamRankList = rankingTableRepository.CentralLeagueRankingList(teamName);
		StringBuilder teamRankCentral = new StringBuilder();
		int count = 0;
		for (CentralLeagueRanking teamRank : teamRankList) {
			teamRankCentral.append(teamRank.getRank());
			count++;
			if (count != teamRankList.size()) {
				teamRankCentral.append(",");
			}
		}
		return teamRankCentral;
		// return teamRankList;
	}

	/**
	 * パリーグの選択された球団の順位情報を取得
	 * 
	 * @param teamName
	 * @return
	 */
	public StringBuilder PacificTeamrankListSearchByTeamName(String teamName) {
		List<PacificLeagueRanking> teamRankList = rankingTableRepository.PacificLeagueRankingList(teamName);
		StringBuilder teamRankPacific = new StringBuilder();
		int count = 0;
		for (PacificLeagueRanking teamRank : teamRankList) {
			teamRankPacific.append(teamRank.getRank());
			count++;
			if (count != teamRankList.size()) {
				teamRankPacific.append(",");
			}
		}
		return teamRankPacific;
		// return teamRankList;
	}

	/**
	 * セリーグの試合の日付けを取得
	 * 
	 * @param teamName
	 * @return
	 */
	public StringBuilder CentralGameDateListSearchByTeamName(String teamName) {
		List<CentralLeagueRanking> teamRankList = rankingTableRepository.CentralLeagueRankingList(teamName);
		StringBuilder gameDayCentral = new StringBuilder();
		int count = 0;
		for (CentralLeagueRanking teamRank : teamRankList) {
			gameDayCentral.append(teamRank.getRank());
			count++;
			if (count != teamRankList.size()) {
				gameDayCentral.append(",");
			}
		}
		return gameDayCentral;
		// return teamRankList;
	}

	/**
	 * パリーグの試合の日付けを取得(順位遷移表に必要)
	 * 
	 * @param teamName
	 * @return
	 */
	public StringBuilder PacificGameDateListSearchByName(String teamName) {
		List<PacificLeagueRanking> teamRankList = rankingTableRepository.PacificLeagueRankingList(teamName);
		StringBuilder gameDayPacific = new StringBuilder();
		int count = 0;
		for (PacificLeagueRanking teamRank : teamRankList) {
			gameDayPacific.append(teamRank.getDate());
			count++;
			if (count != teamRankList.size()) {
				gameDayPacific.append(",");
			}
		}
		return gameDayPacific;

	}

}
