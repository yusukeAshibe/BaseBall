package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.ResultGame;

/**
 * 試合結果情報を操作するレポジトリ.
 * 
 * @author ashibe
 *
 */
@Repository
public class GameResultRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final RowMapper<ResultGame> RESULTGAME_ROW_MAPPER = (rs, i) -> {
		ResultGame resultGame = new ResultGame();
		resultGame.setStadium(rs.getString("stadium"));
		resultGame.setTeam1(rs.getString("team1"));
		resultGame.setTeam2(rs.getString("team2"));
		resultGame.setScore1(rs.getInt("score1"));
		resultGame.setScore2(rs.getInt("score2"));
		resultGame.setDate(rs.getDate("date"));
		return resultGame;

	};

	/**
	 * 試合結果情報をインサート
	 * 
	 * @param resultGameList
	 */
	public void insert(List<ResultGame> resultGameList) {

		for (ResultGame resultGame : resultGameList) {

			String sql = "insert into result_games(stadium,team1,team2,score1,score2,date) "
					+ "values(:stadium,:team1,:team2,:score1,:score2,:date)";
			SqlParameterSource param = new BeanPropertySqlParameterSource(resultGame);
			template.update(sql, param);

		}

	}

	/**
	 * 入力された日付の試合情報を試合情報を取得.
	 * 
	 * @param day
	 * @return
	 */
	public List<ResultGame> ResultSearchByDay(Date day) {

		String sql = "select * from result_games where date=:date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", day);
		List<ResultGame> resultGameList = template.query(sql, param, RESULTGAME_ROW_MAPPER);
		return resultGameList;

	}

	/**
	 * チーム別に試合結果を取得.
	 * 
	 * @param teamName
	 * @return
	 */
	public List<ResultGame> ResultSearchByTeamName(String teamName) {
		String sql = "select * from result_games  where team1=:teamName or team2=:teamName";
		SqlParameterSource param = new MapSqlParameterSource().addValue("teamName", teamName);
		List<ResultGame> teamResultList = template.query(sql, param, RESULTGAME_ROW_MAPPER);
		System.out.println(sql);
		return teamResultList;

	}

}
