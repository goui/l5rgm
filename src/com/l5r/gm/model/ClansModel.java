package com.l5r.gm.model;

import java.util.ArrayList;
import java.util.List;

import com.l5r.gm.object.Clan;

public class ClansModel {
	private static final String TAG = "ClansModel";

	public static String[] clans_names = { "Crab", "Crane", "Dragon", "Lion", "Mantis", "Phoenix",
			"Scorpion", "Unicorn" };

	public static String[][] families_names = {
			{ "Hida", "Hiruma", "Kaiu", "Kuni", "Toritaka", "Yasuki" },
			{ "Asahina", "Daidoji", "Doji", "Kakita" },
			{ "Kitsuki", "Mirumoto", "Tamori", "Togashi" }, { "Akodo", "Ikoma", "Kitsu", "Matsu" },
			{ "Kitsune", "Moshi", "Tsuruchi", "Yoritomo" },
			{ "Agasha", "Asako", "Isawa", "Shiba" }, { "Bayushi", "Shosuro", "Soshi", "Yogo" },
			{ "Horuichi", "Ide", "Iuchi", "Moto", "Shinjo", "Utaku" } };

	public static String[][] schools_names = {
			{ "Hida Bushi", "Kuni Shugenja", "Yasuki Courtier", "Hiruma Bushi" },
			{ "Kakita Bushi", "Asahina Shugenja", "Doji Courtier", "Daidoji Iron Warriors" },
			{ "Mirumoto Bushi", "Tamori Shugenja", "Kitsuki Investigation",
					"Togashi Tattooed Order" },
			{ "Akodo Bushi", "Kitsu Shugenja", "Ikoma Bard", "Matsu Berserker" },
			{ "Yoritomo Bushi", "Moshi Shugenja", "Yoritomo Courtier", "Tsuruchi Archer" },
			{ "Shiba Bushi", "Isawa Shugenja", "Asako Loremaster", "Agasha Shugenja" },
			{ "Bayushi Bushi", "Soshi Shugenja", "Bayushi Courtier", "Shosuro Infiltrator" },
			{ "Moto Bushi", "Iuchi Shugenja", "Ide Emissary", "Utaku Battle Maiden" } };

	public static ClansModel _instance;

	private List<Clan> _clans;

	private ClansModel() {
		_clans = new ArrayList<Clan>();
	}

	public static ClansModel getInstance() {
		if (_instance == null) {
			_instance = new ClansModel();
		}

		return _instance;
	}

	public void addClan(Clan clan_p) {
		_clans.add(clan_p);
	}

	public List<Clan> getClans() {
		return _clans;
	}

	public Clan getClanByName(String name_p) {
		Clan clan = null;
		for (Clan currentClan : _clans) {
			if (currentClan.getName().equals(name_p)) {
				clan = currentClan;
			}
		}
		return clan;
	}
}
