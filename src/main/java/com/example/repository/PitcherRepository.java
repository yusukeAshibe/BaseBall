package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Pitcher;

@Repository
public class PitcherRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Pitcher> PITCHER_ROW_MAPPER = new BeanPropertyRowMapper<>(Pitcher.class);

	/**
	 * 初回データ挿入時のみ利用（以降は上書き）
	 * 
	 * @param pitcherList
	 */
	public void insertPitcherDate(List<Pitcher> pitcherList) {

		for (Pitcher pitcher : pitcherList) {

			String sql = "insert into pitcher(nuiformNumber,firstName,lastName,defenceRate,pitching,starter,completeGame,shutout,quarityStart,win,lose,hold,holdPoint,save,winRate,inning,hit,homerun,strikeout,strikeoutRate,fourBall,deadball,wildPitch,balk,goal,earnedRun,battingAverage,KBB,WHIP,teamName) values(:uniformNumber,:firstName,:lastName,:defenceRate ,:pitching ,:starter ,:completeGame ,:shutout ,:quarityStart ,:win ,:lose ,:hold ,:holdPoint ,:save ,:winRate ,:inning ,:hit ,:homerun ,:strikeout ,:strikeoutRate ,:fourBall ,:deadball ,:wildPitch ,:balk ,:goal ,:earnedRun ,:battingAverage ,:KBB ,:WHIP ,:teamName) ";
			SqlParameterSource param = new BeanPropertySqlParameterSource(pitcher);
			template.update(sql, param);
		}

	}

	/**
	 * 選手成績のアップデート（以降は上書き）
	 * 
	 * @param pitcherList
	 */
	public void upDatePitcherDate(List<Pitcher> pitcherList) {

		for (Pitcher pitcher : pitcherList) {

			// String uniformNumber = pitcher.getUniformNumber();
			String sql = "update pitcher(defenceRate,pitching,starter,completeGame,shutout,quarityStart,win,lose,hold,holdPoint,save,winRate,inning,hit,homerun,strikeout,strikeoutRate,fourBall,deadball,wildPitch,balk,goal,earnedRun,battingAverage,KBB,WHIP,teamName) set(:defenceRate ,:pitching ,:starter ,:completeGame ,:shutout ,:quarityStart ,:win ,:lose ,:hold ,:holdPoint ,:save ,:winRate ,:inning ,:hit ,:homerun ,:strikeout ,:strikeoutRate ,:fourBall ,:deadball ,:wildPitch ,:balk ,:goal ,:earnedRun ,:battingAverage ,:KBB ,:WHIP ) where teamName='巨人'　and id=:id";
			SqlParameterSource param = new BeanPropertySqlParameterSource(pitcher);
			System.out.println(pitcher);
			template.update(sql, param);
		}

	}

}
