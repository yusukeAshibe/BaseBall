package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.CentralLeagueRanking;
import com.example.domain.PacificLeagueRanking;

@Repository
public class TeamDetailRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<CentralLeagueRanking> CENTRALLEAGU_ROW_MAPPER = new BeanPropertyRowMapper<>(
			CentralLeagueRanking.class);

	private static final RowMapper<PacificLeagueRanking> PACIFICLEAGU_ROW_MAPPER = new BeanPropertyRowMapper<>(
			PacificLeagueRanking.class);

}
