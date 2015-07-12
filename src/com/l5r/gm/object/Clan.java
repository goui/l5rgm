package com.l5r.gm.object;

import java.util.ArrayList;
import java.util.List;

public class Clan {

	private String _name;

	private List<String> _families;

	private List<String> _schools;

	public Clan(String name_p) {
		_name = name_p;
		_families = new ArrayList<String>();
		_schools = new ArrayList<String>();
	}

	public List<String> getFamilies() {
		return _families;
	}

	public void addFamily(String family_p) {
		_families.add(family_p);
	}

	public List<String> getSchools() {
		return _schools;
	}

	public void addSchool(String school_p) {
		_schools.add(school_p);
	}

	public String getName() {
		return _name;
	}

	@Override
	public boolean equals(Object o_p) {
		boolean equals = false;
		if (o_p instanceof Clan) {
			Clan clan = (Clan) o_p;
			equals = clan.getName().equals(_name);
		}
		return equals;
	}

	@Override
	public String toString() {
		return _name;
	}

}
