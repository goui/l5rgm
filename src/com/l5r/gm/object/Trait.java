package com.l5r.gm.object;

public class Trait {

	private String _name;

	private int _points;

	public Trait(String name_p, int points_p) {
		_name = name_p;
		_points = points_p;
	}

	public String getName() {
		return _name;
	}

	public int getPoints() {
		return _points;
	}

	@Override
	public boolean equals(Object o_p) {
		boolean equals = false;
		if (o_p instanceof Arrow) {
			Trait obj = (Trait) o_p;
			equals = obj.getName().equals(_name) && obj.getPoints() == _points;
		}
		return equals;
	}

	@Override
	public String toString() {
		return "Trait: " + _name + ", " + _points + " points.";
	}
}
