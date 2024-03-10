
package com.example.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import com.example.player.model.PlayerRowMapper;
import com.example.player.model.Player;
import com.example.player.repository.PlayerRepository;

@Service
public class PlayerH2Service implements PlayerRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public Player getPlayerById(int playerId) {

        try {
            Player playerObj = db.queryForObject("select * from TEAM where playerId = ?", new PlayerRowMapper(),
                    playerId);
            return playerObj;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ArrayList<Player> getPlayers() {

        List<Player> playerList = db.query("select * from TEAM", new PlayerRowMapper());
        ArrayList<Player> playersObj = new ArrayList<>(playerList);
        return playersObj;

    }

    @Override
    public Player addPlayer(Player playerObj) {
        db.update("INSERT INTO TEAM(playerName, jerseyNumber, role) values(?,?,?)", playerObj.getPlayerName(),  playerObj.getJerseyNumber(),  playerObj.getRole());
        Player savedPlayer = db.queryForObject("SELECT * FROM TEAM WHERE playerName=? and jerseyNumber=? and role=?", new PlayerRowMapper(), playerObj.getPlayerName(),  playerObj.getJerseyNumber(),  playerObj.getRole());
        return savedPlayer;
    }

    @Override
    public Player updatePlayer(int playerId, Player playerObj) {
        if (playerObj.getPlayerName() != null) {
            db.update("update TEAM set playerName = ? where playerId = ?", playerObj.getPlayerName(), playerId);
        }
        if (playerObj.getJerseyNumber() != 0) {
            db.update("update TEAM set jerseyNumber = ? where playerId = ?", playerObj.getJerseyNumber(), playerId);
        }
        if (playerObj.getRole() != null) {
            db.update("update TEAM set role = ? where playerId = ?", playerObj.getRole(), playerId);
        }

        return getPlayerById(playerId);
    }

    @Override
    public void deletePlayer(int playerId) {
        try {
            db.update("delete from TEAM where playerId = ?", playerId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}