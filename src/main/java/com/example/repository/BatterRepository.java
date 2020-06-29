package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Batter;

/**
 * 打者情報を操作するレポジトリ.
 * 
 * @author ashibe
 *
 */
@Repository
public class BatterRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Batter> BATTER_ROW_MAPPER = new BeanPropertyRowMapper<>(Batter.class);

	/**
	 * 打者情報をDBにインサートする
	 * 
	 * @param batterList
	 */
	public void insertBatterDate(List<Batter> batterList) {

		for (Batter batter : batterList) {

			String sql = "insert into batter(nuiformNumber,firstName,lastName,battingAverage,game,plateAppearance,atBat,hit,twoBase,threeBase,homerun,baseHit,RBI,score,strikeOut,fourBall,deadBall,bunt,SF,steal,caughtSteal,doublePlay,onBasePercent,sluggingPercent,OPS,scoringArea,error,teamName) values(:uniformNumber,:firstName,:lastName,:battingAverage,:game,:plateAppearance,:atBat,:hit,:twoBase,:threeBase,:homerun,:baseHit,:RBI,:score,:strikeOut,:fourBall,:deadBall,:bunt,:SF,:steal,:caughtSteal,:doublePlay,:onBasePercent,:sluggingPercent,:OPS,:scoringArea,:error,:teamName) ";
			SqlParameterSource param = new BeanPropertySqlParameterSource(batter);
			// System.out.println(batter);
			template.update(sql, param);

		}

	}

}
