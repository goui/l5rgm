package com.l5r.gm.object;

public class School {

	private String _name;

	private int _indexOfStat;

	private float _honor;

	public School(String name_p, int indexOfStat_p, float honor_p) {
		_name = name_p;
		_indexOfStat = indexOfStat_p;
		_honor = honor_p;
	}

	public String getName() {
		return _name;
	}

	public int getIndexOfStat() {
		return _indexOfStat;
	}

	public float getHonor() {
		return _honor;
	}
}
