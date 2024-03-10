package com.example.player.model;

public class Player {
    private int playerId;
    private String playerName;
    private int jerseyNumber;
    private String role;

    public Player(int id, String name, int jersey, String playerRole) {
        playerId = id;
        playerName = name;
        jerseyNumber = jersey;
        role = playerRole;
    }

    public void setPlayerId(int id) {
        playerId = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setJerseyNumber(int jersey) {
        jerseyNumber = jersey;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setRole(String playerRole) {
        role = playerRole;
    }

    public String getRole() {
        return role;
    }

}