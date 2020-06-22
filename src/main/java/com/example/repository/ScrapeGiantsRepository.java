package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Giants;

@Repository
public class ScrapeGiantsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final RowMapper<Giants> GIANTS_ROW_MAPPER = (rs, i) -> {
		Giants giants = new Giants();
		giants.setDay(rs.getString("day"));
		giants.setVictoryOrDefeat(rs.getString("victoryOrDefeat"));
		giants.setScore(rs.getString("score"));
		giants.setOpponent(rs.getString("opponent"));
		giants.setStarter(rs.getString("starter"));
		giants.setResponsibilityPitcher(rs.getString("responsibilityPitcher"));
		giants.setStadium(rs.getString("stadium"));
		giants.setStartTime(rs.getString("startTime"));
		return giants;
	};

}
