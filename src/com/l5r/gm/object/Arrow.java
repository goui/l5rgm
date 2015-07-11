package com.l5r.gm.object;

public class Arrow {

	// TODO arrow type
	// private int _type;

	private int _damage;

	private int _quantity;

	public Arrow(/* int type_p, */int damage_p, int quantity_p) {
		// _type = type_p
		_damage = damage_p;
		_quantity = quantity_p;
	}

	public int getDamage() {
		return _damage;
	}

	public void changeDamage(int damage_p) {
		_damage += damage_p;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void changeQuantity(int quantity_p) {
		_quantity += quantity_p;
	}

	@Override
	public boolean equals(Object o_p) {
		// TODO arrow equals
		boolean equals = false;
		if (o_p instanceof Arrow) {
			Arrow obj = (Arrow) o_p;
			equals = obj.getDamage() == _damage && obj.getQuantity() == _quantity;
		}
		return equals;
	}

	@Override
	public String toString() {
		// TODO arrow tostring
		return "Arrow: (" + _quantity + ", " + _damage + "dmg)";
	}
}
