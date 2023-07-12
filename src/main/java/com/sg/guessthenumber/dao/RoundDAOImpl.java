package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;


    @Repository
    public class RoundDAOImpl implements RoundDAO {

        private final JdbcTemplate jdbcTemplate;

        public RoundDAOImpl(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public int createRound(Round round) {
            String sql = "INSERT INTO round (game_id, guess, guess_time, result) VALUES (?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, round.getGameId());
                ps.setString(2, round.getGuess());
                ps.setTimestamp(3, Timestamp.valueOf(round.getGuessTime()));
                ps.setString(4, round.getResult());
                return ps;
            }, keyHolder);

            return keyHolder.getKey().intValue();
        }

        @Override
        public List<Round> getRoundsByGameId(int gameId) {
            String sql = "SELECT * FROM round WHERE game_id = ?";
            return jdbcTemplate.query(sql, new RoundRowMapper(), gameId);
        }

        private static class RoundRowMapper implements RowMapper<Round> {
            @Override
            public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
                int roundId = rs.getInt("round_id");
                int gameId = rs.getInt("game_id");
                String guess = rs.getString("guess");
                LocalDateTime guessTime = rs.getTimestamp("guess_time").toLocalDateTime();
                String result = rs.getString("result");
                return new Round(roundId, gameId, guess, guessTime, result);
            }
        }
    }


