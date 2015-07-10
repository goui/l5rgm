package com.l5r.gm.object;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private String _name;

	private List<Player> _players;

	private int _nbOfSessions;

	public Game(String name_p) {
		_name = name_p;
		_players = new ArrayList<Player>();
	}

	public String getName() {
		return _name;
	}

	public List<Player> getPlayers() {
		return _players;
	}

	public void addPlayer(Player player_p) {
		_players.add(player_p);
	}

	public void removePlayer(Player player_p) {
		_players.remove(player_p);
	}

	public int getNbOfSessions() {
		return _nbOfSessions;
	}

	public void setNbOfSessionsUp() {
		_nbOfSessions++;
	}

	@Override
	public boolean equals(Object o_p) {
		boolean equals = false;
		if (o_p instanceof Arrow) {
			Game obj = (Game) o_p;
			equals = obj.getName().equals(_name) && obj.getPlayers().size() == _players.size()
					&& obj.getNbOfSessions() == _nbOfSessions;
		}
		return equals;
	}

	@Override
	public String toString() {
		return "Game: " + _name + " - " + _players.size() + " players, " + _nbOfSessions
				+ " session(s) played.";
	}
}
