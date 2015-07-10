package com.l5r.gm.object;

public class Player {

	private String _name;

	private String _macAddress;

	private Character _character;

	public Player(String name_p) {
		_name = name_p;
	}

	public String getName() {
		return _name;
	}

	public String getMACAddress() {
		return _macAddress;
	}

	public void setMACAddress(String macAddress_p) {
		_macAddress = macAddress_p;
	}

	public Character getCharacter() {
		return _character;
	}

	public void setCharacter(Character character_p) {
		_character = character_p;
	}

	@Override
	public boolean equals(Object o_p) {
		boolean equals = false;
		if (o_p instanceof Arrow) {
			Player obj = (Player) o_p;
			equals = obj.getName().equals(_name) && obj.getMACAddress().equals(_macAddress)
					&& obj.getCharacter().equals(_character);
		}
		return equals;
	}

	@Override
	public String toString() {
		return _name + ": " + _macAddress;
	}
}
