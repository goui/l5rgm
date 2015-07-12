package com.l5r.gm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.adapter.HighlightStringListAdapter;

public class CharacterCreationFragment extends Fragment {
	private final static String TAG = "CharacterCreationFragment";

	private static String[] _clans = { "Crab", "Crane", "Dragon", "Lion", "Mantis", "Phoenix",
			"Scorpion", "Unicorn" };

	private static String[][] _families = {
			{ "Hida", "Hiruma", "Kaiu", "Kuni", "Toritaka", "Yasuki" },
			{ "Asahina", "Daidoji", "Doji", "Kakita" },
			{ "Kitsuki", "Mirumoto", "Tamori", "Togashi" }, { "Akodo", "Ikoma", "Kitsu", "Matsu" },
			{ "Kitsune", "Moshi", "Tsuruchi", "Yoritomo" },
			{ "Agasha", "Asako", "Isawa", "Shiba" }, { "Bayushi", "Shosuro", "Soshi", "Yogo" },
			{ "Horuichi", "Ide", "Iuchi", "Moto", "Shinjo", "Utaku" } };

	private static String[][] _schools = {
			{ "Hida Bushi", "Kuni Shugenja", "Yasuki Courtier", "Hiruma Bushi" },
			{ "Kakita Bushi", "Asahina Shugenja", "Doji Courtier", "Daidoji Iron Warriors" },
			{ "Mirumoto Bushi", "Tamori Shugenja", "Kitsuki Investigation",
					"Togashi Tattooed Order" },
			{ "Akodo Bushi", "Kitsu Shugenja", "Ikoma Bard", "Matsu Berserker" },
			{ "Yoritomo Bushi", "Moshi Shugenja", "Yoritomo Courtier", "Tsuruchi Archer" },
			{ "Shiba Bushi", "Isawa Shugenja", "Asako Loremaster", "Agasha Shugenja" },
			{ "Bayushi Bushi", "Soshi Shugenja", "Bayushi Courtier", "Shosuro Infiltrator" },
			{ "Moto Bushi", "Iuchi Shugenja", "Ide Emissary", "Utaku Battle Maiden" } };

	private ListView _clanListView;

	private ListView _familyListView;

	private ListView _schoolListView;

	private HighlightStringListAdapter _clanListAdapter;

	private HighlightStringListAdapter _familyListAdapter;

	private HighlightStringListAdapter _schoolListAdapter;

	private TextView _txtLastname;

	private EditText _txtFirstname;

	private Button _btnRandomize;

	private int _clanSelectedIndex;

	private int _familySelectedIndex;

	private int _schoolSelectedIndex;

	@Override
	public View onCreateView(LayoutInflater inflater_p, ViewGroup container_p,
			Bundle savedInstanceState_p) {
		View rootView = inflater_p
				.inflate(R.layout.fragment_character_creation, container_p, false);
		_clanListView = (ListView) rootView
				.findViewById(R.id.fragment_character_creation_clan_list);
		_familyListView = (ListView) rootView
				.findViewById(R.id.fragment_character_creation_family_list);
		_schoolListView = (ListView) rootView
				.findViewById(R.id.fragment_character_creation_school_list);
		_txtLastname = (TextView) rootView
				.findViewById(R.id.fragment_character_creation_lastname_text);
		_txtFirstname = (EditText) rootView
				.findViewById(R.id.fragment_character_creation_firstname_text);
		_btnRandomize = (Button) rootView
				.findViewById(R.id.fragment_character_creation_randomize_button);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState_p) {
		super.onActivityCreated(savedInstanceState_p);

		_clanListAdapter = new HighlightStringListAdapter(getActivity(), _clans);
		_clanListView.setAdapter(_clanListAdapter);
		_clanListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				_clanListAdapter.setSelected(position_p);
				_familyListAdapter.setList(_families[position_p]);
				_schoolListAdapter.setList(_schools[position_p]);
				_clanSelectedIndex = position_p;
			}
		});

		_familyListAdapter = new HighlightStringListAdapter(getActivity(), new String[0]);
		_familyListView.setAdapter(_familyListAdapter);
		_familyListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				// TODO character creation fragment family list click
				_familyListAdapter.setSelected(position_p);
				_txtLastname.setText(_families[_clanSelectedIndex][position_p]);
				_familySelectedIndex = position_p;
			}
		});

		_schoolListAdapter = new HighlightStringListAdapter(getActivity(), new String[0]);
		_schoolListView.setAdapter(_schoolListAdapter);
		_schoolListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				// TODO character creation fragment school list click
				_schoolListAdapter.setSelected(position_p);
				_schoolSelectedIndex = position_p;
			}
		});
	}

	@Override
	public String toString() {
		return "CREATION";
	}

}
