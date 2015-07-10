package com.l5r.gm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.l5r.gm.object.Game;

public class GamesModel extends Observable {

	public static GamesModel _instance;

	private List<Game> _games;

	private GamesModel() {
		_games = new ArrayList<Game>();
	}

	public static GamesModel getInstance() {
		if (_instance == null) {
			_instance = new GamesModel();
		}

		return _instance;
	}

	public void addGame(Game game_p) {
		_games.add(game_p);
	}

	public List<Game> getGames() {
		return _games;
	}
}
