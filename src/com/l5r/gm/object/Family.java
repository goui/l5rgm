package com.l5r.gm.object;

public class Family {

	private String _name;

	private int _indexOfStat;

	public Family(String name_p, int indexOfStat_p) {
		_name = name_p;
		_indexOfStat = indexOfStat_p;
	}

	public String getName() {
		return _name;
	}

	public int getIndexOfStat() {
		return _indexOfStat;
	}

}
