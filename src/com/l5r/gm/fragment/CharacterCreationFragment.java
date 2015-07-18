package com.l5r.gm.fragment;

import java.util.Random;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.activity.CharacterActivity;
import com.l5r.gm.adapter.HighlightStringListAdapter;
import com.l5r.gm.model.ClansModel;
import com.l5r.gm.object.Character;
import com.l5r.gm.object.Family;
import com.l5r.gm.object.School;

public class CharacterCreationFragment extends Fragment {
	private final static String TAG = "CharacterCreationFragment";

	private static String[] _maleNames = { "Abun", "Aikoto", "Akagi", "Aki", "Amoro", "Anou",
			"Anzai", "Aramoro", "Asano", "Ashihei", "Atarasi", "Atsuki", "Atsumichi", "Atsumori",
			"Atsutane", "Bachiatari", "Bachida", "Bairei", "Bakin", "Banuken", "Benjiro", "Bokaru",
			"Bugati", "Chagatai", "Chen", "Chimoto", "Chomei", "Chuai", "Daigo", "Daisetsu",
			"Daishiki", "Daizu", "Dajan", "Dayu", "Domogu", "Doreni", "Doukohito", "Eitoku",
			"Ekei", "Ekiken", "Enai", "Endo", "Fubatsu", "Fuchida", "Fujiki", "Fumetsu",
			"Fumisato", "Fumitake", "Fumoki", "Garou", "Gempachi", "Genichi", "Genji", "Genzo",
			"Gineza", "Gobura", "Godaigo", "Goemon", "Gombei", "Gosuta", "Gudeta", "Hachi",
			"Handen", "Harike", "Haru", "Haruo", "Haku", "Hatsuo", "Hayabusa", "Hayaku",
			"Heikichi", "Heuji", "Hideaki", "Hideki", "Hio", "Hira", "Hiroji", "Hisayuki",
			"Hitaken", "Hitomaro", "Hitoshi", "Hiyao", "Hogai", "Hohiro", "Hoitsu", "Horii",
			"Hosaru", "Hotei", "Hotoke", "Hoturi", "Ichido", "Ichihara", "Ichiro", "Ichita",
			"Ikage", "Ikkaku", "Inukai", "Isamu", "Ishi", "Isoshi", "Itsuma", "Jaijimin",
			"Jakuchu", "Jinn-Kuen", "Josuke", "Jotaro", "Junji", "Junzo", "Jurian", "Jurobei",
			"Kabe", "Kage", "Kagore", "Kaiden", "Kaihei", "Kaii", "Kaikawa", "Kaiketsu",
			"Kaimetsu-Uo", "Kaito", "Kakeguchi", "Kamatari", "Kamoru", "Kamura", "Kashin", "Katai",
			"Katomara", "Katsura", "Kaukatsu", "Kaze", "Kazo", "Kazu", "Kazuma", "Keitaro",
			"Kengo", "Kenru", "Kenzan", "Kikaze", "Kimpira", "Kinashita", "Kinen", "Kirino",
			"Kisada", "Kitao", "Kiyoshi", "Koin", "Koji", "Kojima", "Kojiro", "Konetsu", "Kosedo",
			"Kosho", "Kozan", "Kukojin", "Kuma", "Kuman", "Kuon", "Kuoshi", "Kurama", "Kuri",
			"Kuroda", "Kurohito", "Kuwanan", "Kuzan", "Kuzume", "Kyobu", "Kyuwa", "Latomu",
			"Machigama", "Manabu", "Maruku", "Masado", "Masagaro", "Masaki", "Masaru", "Masatari",
			"Masashigi", "Matahachi", "Matyu", "Midoru", "Mifune", "Mikio", "Minoru", "Misashi",
			"Mizumi", "Mokuna", "Moshibaru", "Motaro", "Munabu", "Nagori", "Naito", "Nakanu",
			"Nakiro", "Namboku", "Naoki", "Naoshige", "Natsukiwa", "Nezu", "Nichi", "Nichiren",
			"Nikoma", "Nio", "Nizomi", "Nobuhide", "Nokatsu", "Norio", "Oda", "Oguri", "Okakura",
			"Okashi", "Okichi", "Osano-Wo", "Osen", "Oshuda", "Osuno", "Osuki", "Otemi", "Otojiro",
			"Otoya", "Quehao", "Raigu", "Reju", "Ren", "Renga", "Rihito", "Rikiya", "Rikyu",
			"Rioto", "Rhyodotsu", "Rohata", "Roukai", "Ryobu", "Ryojiro", "Ryuichi", "Ryunosuke",
			"Ryute", "Sabaru", "Sadaharu", "Sadao", "Saibankan", "Sakamoto", "Satsu", "Satsume",
			"Seiki", "Seishiro", "Sekawa", "Sekidera", "Sensaichi", "Sensin", "Sesshu", "Shihei",
			"Shimura", "Shogusha", "Shoichi", "Shoju", "Shotoku", "Shuzo", "Soetsu", "Soh",
			"Sokokai", "Sosuke", "Sozen", "Sudaro", "Sugimoto", "Sugu", "Sukune", "Suman",
			"Sumata", "Sunao", "Sunshin", "Tadachika", "Tadaka", "Tadashiro", "Taikan", "Tajima",
			"Taka", "Takaaki", "Takaniro", "Takasho", "Takeji", "Takemura", "Taki", "Takihiro",
			"Takuji", "Takuma", "Tamako", "Tampako", "Tamura", "Tanaka", "Tanari", "Tanaro",
			"Tanitsu", "Taru", "Tasuku", "Tatsune", "Tatsuya", "Tatsuzo", "Tenburo", "Tenkazu",
			"Tenshu", "Tensin", "Tenzo", "Teriuhi", "Teruo", "Tetsumaru", "Todori", "Toju",
			"Tokaji", "Toketsu", "Tokichiro", "Tokimune", "Tokito", "Tomo", "Tonoji", "Toshi",
			"Toshinobu", "Toyoaki", "Tozasu", "Tsanumi", "Tsubakita", "Tsubaru", "Tsuburu",
			"Tsukioka", "Tsunemura", "Tsuneo", "Tsunesaburo", "Tsuru", "Uji", "Umasu", "Umibe",
			"Unari", "Unkei", "Usan", "Utaemon", "Utagu", "Utsu", "Wakou", "Waotaka", "Wasutsubo",
			"Wukau", "Xie", "Xushen", "Yachi", "Yagimaki", "Yaichiro", "Yajinden", "Yakamo",
			"Yasamura", "Yasashii", "Yasuhigo", "Yasuhiro", "Yasurugi", "Yataro", "Yojiro", "Yome",
			"Yori", "Yosei", "Yoshi", "Yoshiaga", "Yoshimitsu", "Yoshino", "Yotogi", "Yugira",
			"Yugure", "Yukinaga", "Yuo", "Yurei", "Yuzan", "Zoushi", "Zunguri" };

	private static String[] _femaleNames = { "Akemi", "Akiko", "Ameiko", "Amika", "Angai", "Aoshi",
			"Ariko", "Asano", "Aya", "Ayano", "Barako", "Beniha", "Chieh", "Chieri", "Chiroku",
			"Chitose", "Choshi", "Domotai", "Enko", "Fujie", "Funaki", "Hakumei", "Hakuseki",
			"Haruko", "Hatsuko", "Hejiko", "Hino", "Hiruko", "Hitomi", "Hoketuhime", "Inao",
			"Ineko", "Iseki", "Itoeko", "Iweko", "Jiyuna", "Jorihime", "Jun'ai", "Juneko",
			"Kachiko", "Kaede", "Kaijuko", "Kaneko", "Kaoru", "Kasai", "Katsuko", "Kei", "Kenji",
			"Ketsui", "Kimita", "Kiriko", "Kitao", "Koichi", "Koiso", "Konishiko", "Kumi",
			"Kumiko", "Kurako", "Lixue", "Mai", "Mariko", "Maru", "Masako", "Masiko", "Maya",
			"Megumi", "Megumiko", "Meiko", "Mika", "Miliko", "Mioko", "Miyako", "Mizhime",
			"Murasaki", "Nagiko", "Nahomi", "Namika", "Nari", "Narumi", "Nazoko", "Nichie",
			"Nikako", "Nisobu", "Oborozukiyo", "Ochiai", "Ochiba", "Ochiyo", "Osaku", "O-Ushi",
			"Ran", "Ranmaru", "Reiha", "Reiko", "Rekai", "Riake", "Rina", "Rohiteki", "Rumiko",
			"Sakimi", "Sakurako", "Saori", "Sasumiko", "Satomi", "Seiki", "Seo", "Setsuko",
			"Shahai", "Shaitung", "Shara", "Shiko", "Shizue", "Shoan", "Soh", "Sui", "Tae",
			"Taehime", "Taeruko", "Taiko", "Tama", "Tamiyo", "Tanako", "Tani", "Tansho", "Teika",
			"Teinko", "Tsanuri", "Tsudao", "Tsukimi", "Tsukiyoka", "Tsuko", "Tsukune", "Tsuneko",
			"Tsukiko", "Tsutomu", "Uidori", "Uona", "Xieng Chi", "Xiulian", "Yae", "Yaheiko",
			"Yaruko", "Yasuko", "Yue", "Yumi", "Zokusei" };

	private ListView _clanListView;

	private ListView _familyListView;

	private ListView _schoolListView;

	private HighlightStringListAdapter _clanListAdapter;

	private HighlightStringListAdapter _familyListAdapter;

	private HighlightStringListAdapter _schoolListAdapter;

	private TextView _txtLastname;

	private AutoCompleteTextView _txtFirstname;

	private Button _btnRandomize;

	private RadioButton _btnMale;

	private int _selectedClanIndex;

	private int _selectedFamilyIndex;

	private int _selectedSchoolIndex;

	private Character _character;

	private CharacterActivity _activity;

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
		_txtFirstname = (AutoCompleteTextView) rootView
				.findViewById(R.id.fragment_character_creation_firstname_text);
		_btnMale = (RadioButton) rootView
				.findViewById(R.id.fragment_character_creation_male_button);
		_btnRandomize = (Button) rootView
				.findViewById(R.id.fragment_character_creation_randomize_button);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState_p) {
		super.onActivityCreated(savedInstanceState_p);

		_character = _activity.getCharacter();

		_clanListAdapter = new HighlightStringListAdapter(getActivity(), ClansModel.clans_names);
		_clanListView.setAdapter(_clanListAdapter);
		_clanListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				_clanListAdapter.setSelected(position_p);
				_selectedClanIndex = position_p;
				_character.setClan(ClansModel.getInstance().getClans().get(_selectedClanIndex));
				_familyListAdapter.setList(ClansModel.families_names[position_p]);
				_schoolListAdapter.setList(ClansModel.schools_names[position_p]);
				setFamily(null);
				setSchool(null);
			}
		});

		_familyListAdapter = new HighlightStringListAdapter(getActivity(), new String[0]);
		_familyListView.setAdapter(_familyListAdapter);
		_familyListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				_familyListAdapter.setSelected(position_p);
				_selectedFamilyIndex = position_p;
				setFamily(ClansModel.getInstance().getClans().get(_selectedClanIndex).getFamilies()
						.get(_selectedFamilyIndex));
			}
		});

		_schoolListAdapter = new HighlightStringListAdapter(getActivity(), new String[0]);
		_schoolListView.setAdapter(_schoolListAdapter);
		_schoolListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				_schoolListAdapter.setSelected(position_p);
				_selectedSchoolIndex = position_p;
				setSchool(ClansModel.getInstance().getClans().get(_selectedClanIndex).getSchools()
						.get(_selectedSchoolIndex));
			}
		});

		final ArrayAdapter<String> maleAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, _maleNames);
		final ArrayAdapter<String> femaleAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, _femaleNames);
		_txtFirstname.setAdapter(maleAdapter);

		_btnMale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView_p, boolean isChecked_p) {
				_character.setMale(isChecked_p);
				_txtFirstname.setAdapter(isChecked_p ? maleAdapter : femaleAdapter);
				_activity.onCharacterModified();
			}
		});

		_btnRandomize.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v_p) {
				Random r = new Random();
				int n = _character.isMale() ? _maleNames.length : _femaleNames.length;
				int index = r.nextInt(n);
				setFirstName(_character.isMale() ? _maleNames[index] : _femaleNames[index]);
			}
		});

	}

	private void setFirstName(String firstName_p) {
		_txtFirstname.setText(firstName_p);
		_character.setFirstName(firstName_p);
		_activity.onCharacterModified();
	}

	private void setFamily(Family family_p) {
		if (family_p != null) {
			_txtLastname.setText(family_p.getName());
		} else {
			_txtLastname.setText("");
		}
		_character.setFamily(family_p);
		_activity.onCharacterModified();
	}

	private void setSchool(School school_p) {
		_character.setSchool(school_p);
		_activity.onCharacterModified();
	}

	@Override
	public void onAttach(Activity activity_p) {
		super.onAttach(activity_p);
		_activity = (CharacterActivity) activity_p;
	}

	@Override
	public String toString() {
		return "CREATION";
	}

}
