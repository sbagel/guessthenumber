package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dto.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createGame(Game game) {
        final String sql = "INSERT INTO game(answer, finished) VALUES(?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn ) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isFinished());
            return statement;
        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());
        return game.getGameId();
    }

    @Override
    public Game getGameById(int id) {
        String sql = "SELECT * FROM game WHERE game_id = ?";
        List<Game> games = jdbcTemplate.query(sql, new GameRowMapper(), id);
        return games.isEmpty() ? null : games.get(0);
    }

    @Override
    public List<Game> getAllGames() {
        String sql = "SELECT * FROM game";
        return jdbcTemplate.query(sql, new GameRowMapper());
    }

    @Override
    public void updateGame(Game game) {
        String sql = "UPDATE game SET answer = ?, finished = ? WHERE game_id = ?";
        jdbcTemplate.update(sql, game.getAnswer(), game.isFinished(), game.getGameId());
    }

    @Override
    public void deleteGame(int gameId) {
        final String roundSql ="DELETE FROM round WHERE game_id = ?";
        jdbcTemplate.update(roundSql, gameId);
        final String gameSql = "DELETE FROM game WHERE game_id = ?";
        jdbcTemplate.update(gameSql, gameId);
    }


    private static class GameRowMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            int gameId = rs.getInt("game_id");
            String answer = rs.getString("answer");
            boolean finished = rs.getBoolean("finished");
            return new Game(gameId, answer, finished);
        }
    }
}



