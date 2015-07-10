package com.l5r.gm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.l5r.gm.object.Character;

public class CharactersModel extends Observable {

	public static CharactersModel _instance;

	private List<Character> _characters;

	private CharactersModel() {
		_characters = new ArrayList<Character>();
	}

	public static CharactersModel getInstance() {
		if (_instance == null) {
			_instance = new CharactersModel();
		}

		return _instance;
	}

	public void addCharacter(Character character_p) {
		_characters.add(character_p);
	}

	public List<Character> getCharacters() {
		return _characters;
	}
}
