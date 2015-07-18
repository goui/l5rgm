package com.l5r.gm.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.activity.CharacterActivity;
import com.l5r.gm.listener.OnCharacterModifiedListener;
import com.l5r.gm.object.Character;

public class CharacterOverallFragment extends Fragment implements OnCharacterModifiedListener {

	private Character _character;

	/*
	 * OVERALL
	 */
	private TextView _txtName;
	private TextView _txtRank;
	private TextView _txtClan;
	private TextView _txtXp;
	private TextView _txtSchool;
	private TextView _txtInsight;

	/*
	 * STATS
	 */
	private TextView _txtEarth;
	private TextView _txtStamina;
	private TextView _txtWillpower;
	private TextView _txtWater;
	private TextView _txtStrenght;
	private TextView _txtPerception;
	private TextView _txtAir;
	private TextView _txtReflexes;
	private TextView _txtAwareness;
	private TextView _txtFire;
	private TextView _txtAgility;
	private TextView _txtIntelligence;
	private TextView _txtVoid;

	private CharacterActivity _activity;

	@Override
	public View onCreateView(LayoutInflater inflater_p, ViewGroup container_p,
			Bundle savedInstanceState_p) {
		View rootView = inflater_p.inflate(R.layout.fragment_character_overall, container_p, false);
		_txtName = (TextView) rootView.findViewById(R.id.fragment_character_overall_name_text);
		_txtRank = (TextView) rootView.findViewById(R.id.fragment_character_overall_rank_text);
		_txtClan = (TextView) rootView.findViewById(R.id.fragment_character_overall_clan_text);
		_txtXp = (TextView) rootView.findViewById(R.id.fragment_character_overall_xp_text);
		_txtSchool = (TextView) rootView.findViewById(R.id.fragment_character_overall_school_text);
		_txtInsight = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_insight_text);

		_txtEarth = (TextView) rootView.findViewById(R.id.fragment_character_overall_earth_text);
		_txtStamina = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_stamina_text);
		_txtWillpower = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_willpower_text);
		_txtWater = (TextView) rootView.findViewById(R.id.fragment_character_overall_water_text);
		_txtStrenght = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_strenght_text);
		_txtPerception = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_perception_text);
		_txtAir = (TextView) rootView.findViewById(R.id.fragment_character_overall_air_text);
		_txtReflexes = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_reflexes_text);
		_txtAwareness = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_awareness_text);
		_txtFire = (TextView) rootView.findViewById(R.id.fragment_character_overall_fire_text);
		_txtAgility = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_agility_text);
		_txtIntelligence = (TextView) rootView
				.findViewById(R.id.fragment_character_overall_intelligence_text);
		_txtVoid = (TextView) rootView.findViewById(R.id.fragment_character_overall_void_text);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState_p) {
		super.onActivityCreated(savedInstanceState_p);

		_character = _activity.getCharacter();

		updateCharacterInfo();
	}

	private void updateCharacterInfo() {
		if (_character != null) {
			_txtName.setText(_character.getLastName() + " " + _character.getFirstName());
			_txtRank.setText("" + _character.getRank());

			if (_character.getClan() != null) {
				_txtClan.setText(_character.getClan().getName());
			} else {
				_txtClan.setText("");
			}

			_txtXp.setText("" + _character.getXp());

			if (_character.getSchool() != null) {
				_txtSchool.setText(_character.getSchool().getName());
			} else {
				_txtSchool.setText("");
			}

			_txtInsight.setText("" + _character.getInsight());

			_txtEarth.setText("" + _character.getEarthPoints());
			_txtStamina.setText("" + _character.getStamina());
			_txtWillpower.setText("" + _character.getWillPower());
			_txtWater.setText("" + _character.getWaterPoints());
			_txtStrenght.setText("" + _character.getStrength());
			_txtPerception.setText("" + _character.getPerception());
			_txtAir.setText("" + _character.getAirPoints());
			_txtReflexes.setText("" + _character.getReflexes());
			_txtAwareness.setText("" + _character.getAwareness());
			_txtFire.setText("" + _character.getFirePoints());
			_txtAgility.setText("" + _character.getAgility());
			_txtIntelligence.setText("" + _character.getIntelligence());
			_txtVoid.setText("" + _character.getVoidPoints());
		}
	}

	@Override
	public void onCharacterModified() {
		updateCharacterInfo();
	}

	@Override
	public void onAttach(Activity activity_p) {
		super.onAttach(activity_p);
		_activity = ((CharacterActivity) activity_p);
	}

	@Override
	public String toString() {
		return "OVERALL";
	}
}
