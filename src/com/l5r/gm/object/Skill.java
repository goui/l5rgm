package com.l5r.gm.object;

import java.util.Observable;
import java.util.Observer;

public class Skill implements Observer {

	private String _name;

	private int _rank;

	private int _statIndex;

	// TODO skill roll
	// private int _roll;

	// TODO skill abilities
	// private int _abilities;

	public Skill(String name_p, int statIndex_p, Character character_p) {
		_name = name_p;
		_statIndex = statIndex_p;
		_rank = 1;
		character_p.addObserver(this);
	}

	public String getName() {
		return _name;
	}

	public int getRank() {
		return _rank;
	}

	public void setRankUp() {
		_rank++;
	}

	public int getStatIndex() {
		return _statIndex;
	}

	@Override
	public void update(Observable observable_p, Object data_p) {
		if (observable_p instanceof Character) {
			Character character = (Character) observable_p;
			int statIndex = (Integer) data_p;
			if (statIndex == _statIndex) {
				_statIndex = character.getStatistic(statIndex);
			}
		}
	}

	@Override
	public boolean equals(Object o_p) {
		boolean equals = false;
		if (o_p instanceof Arrow) {
			Skill obj = (Skill) o_p;
			equals = obj.getName().equals(_name) && obj.getRank() == _rank
					&& obj.getStatIndex() == _statIndex;
		}
		return equals;
	}

	@Override
	public String toString() {
		return "Skill: " + _name + ", Rank: " + _rank + ".";
	}
}
