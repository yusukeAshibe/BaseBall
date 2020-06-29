package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;

@Repository
public class RankingTableRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<CentralLeagueRanking> CENTRALLEAGU_ROW_MAPPER = new BeanPropertyRowMapper<>(
			CentralLeagueRanking.class);

	private static final RowMapper<PacificLeagueRanking> PACIFICLEAGU_ROW_MAPPER = new BeanPropertyRowMapper<>(
			PacificLeagueRanking.class);

	// ここからセリーグの情報
	/**
	 * セリーグの順位表情報をインサート.
	 * 
	 * @param centraLLeagueList
	 */
	public void insertCentralLeagueRanking(List<CentralLeagueRanking> centralLeagueList) {

		for (CentralLeagueRanking central : centralLeagueList) {
			String sql = "insert into centralLeague_ranking(rank,game,teamName,win,lose,drow,winRate,difference,remaindingMatch,score,goal,homerun,steal,battingAverage,defenceRate,date)  \r\n"
					+ "values(:rank,:game,:teamName,:win,:lose,:drow,:winRate,:difference,:remaindingMatch,:score,:goal,:homerun,:steal,:battingAverage,:defenceRate,:date)";
			SqlParameterSource param = new BeanPropertySqlParameterSource(central);

			template.update(sql, param);
		}

	}

	/**
	 * セリーグ指定された日程の順位表情報を取得（ない場合はnullを返す）
	 * 
	 * @param date
	 * @return
	 */
	public List<CentralLeagueRanking> centralLeagueRankingFindByDate(Date date) {
		String sql = "select * from centralLeague_ranking where date=:date order by rank";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", date);
		List<CentralLeagueRanking> centralLeagueList = template.query(sql, param, CENTRALLEAGU_ROW_MAPPER);
//		if (centralLeagueList.size() == 0) {
//			return null;
//		} else {
		return centralLeagueList;

	}

	/**
	 * セリーグ指定されたチームの詳細情報を取得(チーム詳細ページに利用)
	 * 
	 * @param date
	 * @return
	 */
	public CentralLeagueRanking CentralTeamDetailFindByTeamName(String teamName) {
		String sql = "select * from centralLeague_ranking where teamName=:teamName order by id desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("teamName", teamName);
		List<CentralLeagueRanking> teamDetailList = template.query(sql, param, CENTRALLEAGU_ROW_MAPPER);
		if (CollectionUtils.isEmpty(teamDetailList)) {
			return null;
		} else {
			CentralLeagueRanking todayTeamDetail = teamDetailList.get(0);
			return todayTeamDetail;
		}
	}

	/**
	 * セリーグの順位表のためのリスト（各球団）
	 * 
	 * @param teamName
	 * @return
	 */
	public List<CentralLeagueRanking> CentralLeagueRankingList(String teamName) {
		String sql = "select * from centralLeague_ranking where teamName=:teamName order by date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("teamName", teamName);
		List<CentralLeagueRanking> teamRankList = template.query(sql, param, CENTRALLEAGU_ROW_MAPPER);
		return teamRankList;

	}

	// ここからパリーグの情報

	/**
	 * パリーグの順位表情報をインサート.
	 * 
	 * @param centraLLeagueList
	 */
	public void insertPacificLeagueRanking(List<PacificLeagueRanking> pacificLeagueList) {

		for (PacificLeagueRanking pacific : pacificLeagueList) {
			String sql = "insert into pacificLeague_ranking(rank,game,teamName,win,lose,drow,winRate,difference,remaindingMatch,score,goal,homerun,steal,battingAverage,defenceRate,date)  \r\n"
					+ "values(:rank,:game,:teamName,:win,:lose,:drow,:winRate,:difference,:remaindingMatch,:score,:goal,:homerun,:steal,:battingAverage,:defenceRate,:date)";
			SqlParameterSource param = new BeanPropertySqlParameterSource(pacific);
			template.update(sql, param);
		}

	}

	/**
	 * パリーグ指定された日程の順位表情報を取得（ない場合はnullを返す）
	 * 
	 * @param date
	 * @return
	 */
	public List<PacificLeagueRanking> pacificLeagueRankingFindByDate(Date date) {
		String sql = "select * from pacificLeague_ranking where date=:date order by rank";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", date);
		List<PacificLeagueRanking> pacificLeagueList = template.query(sql, param, PACIFICLEAGU_ROW_MAPPER);
//		if (centralLeagueList.size() == 0) {
//			return null;
//		} else {
		return pacificLeagueList;

	}

	/**
	 * パリーグ指定されたチームの詳細情報表示に利用.
	 * 
	 * @param date
	 * @return
	 */
	public PacificLeagueRanking PacificTeamDetailFindByTeamName(String teamName) {
		String sql = "select * from pacificLeague_ranking where teamName=:teamName order by id desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("teamName", teamName);
		List<PacificLeagueRanking> pacificLeagueList = template.query(sql, param, PACIFICLEAGU_ROW_MAPPER);
		if (CollectionUtils.isEmpty(pacificLeagueList)) {
			return null;
		} else {
			PacificLeagueRanking todayteamDetail = pacificLeagueList.get(0);
			return todayteamDetail;
		}
	}

	/**
	 * パリーグの順位表のためのリスト（1球団）
	 * 
	 * @param teamName
	 * @return
	 */
	public List<PacificLeagueRanking> PacificLeagueRankingList(String teamName) {
		String sql = "select * from pacificLeague_ranking where teamName=:teamName order by date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("teamName", teamName);
		List<PacificLeagueRanking> teamRankList = template.query(sql, param, PACIFICLEAGU_ROW_MAPPER);
		return teamRankList;

	}
}
