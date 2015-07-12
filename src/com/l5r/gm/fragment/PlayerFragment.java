package com.l5r.gm.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.activity.CharacterActivity;
import com.l5r.gm.model.Constants;
import com.l5r.gm.model.GamesModel;
import com.l5r.gm.object.Character;
import com.l5r.gm.object.Player;

public class PlayerFragment extends Fragment {

	private TextView _txtMacAddress;

	private TextView _txtCharacterName;

	private Button _btnCharacter;

	private Player _player;

	private Character _character;

	@Override
	public void onCreate(Bundle savedInstanceState_p) {
		super.onCreate(savedInstanceState_p);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater_p, ViewGroup container_p,
			Bundle savedInstanceState_p) {
		View rootView = inflater_p.inflate(R.layout.fragment_player, container_p, false);
		_txtMacAddress = (TextView) rootView.findViewById(R.id.fragment_player_mac_address_text);
		_txtCharacterName = (TextView) rootView
				.findViewById(R.id.fragment_player_character_name_text);
		_btnCharacter = (Button) rootView.findViewById(R.id.fragment_player_character_button);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState_p) {
		super.onActivityCreated(savedInstanceState_p);

		int gamePosition = getArguments().getInt(Constants.GAME_POSITION);
		int playerPosition = getArguments().getInt(Constants.PLAYER_POSITION);
		_player = GamesModel.getInstance().getGames().get(gamePosition).getPlayers()
				.get(playerPosition);
		getActivity().getActionBar().setTitle(_player.getName());

		_txtMacAddress.setText(_player.getMACAddress());

		_character = _player.getCharacter();

		final boolean isCharacterNull = _character == null;
		if (isCharacterNull) {
			_txtCharacterName.setText(getString(R.string.no_character_found));
			_btnCharacter.setText(getString(R.string.create_character));
		} else {
			_txtCharacterName.setText(_character.toString());
			_btnCharacter.setText(getString(R.string.see_character));
		}

		_btnCharacter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view_p) {
				displayCharacter(isCharacterNull);
			}
		});
	}

	@Override
	public void onCreateOptionsMenu(Menu menu_p, MenuInflater inflater_p) {
		super.onCreateOptionsMenu(menu_p, inflater_p);
		menu_p.clear();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item_p) {
		switch (item_p.getItemId()) {
		default:
			return super.onOptionsItemSelected(item_p);
		}
	}

	private void displayCharacter(boolean isCharacterNull_p) {
		Intent intent = new Intent(getActivity(), CharacterActivity.class);
		intent.putExtra(Constants.IS_CHARACTER_NULL, isCharacterNull_p);
		startActivityForResult(intent, Constants.REQUEST_CHARACTER_MODIFICATION);
	}

	@Override
	public void onActivityResult(int requestCode_p, int resultCode_p, Intent data_p) {
		switch (requestCode_p) {

		// When CharacterActivity returns with a character to modify
		case Constants.REQUEST_CHARACTER_MODIFICATION:
			if (resultCode_p == Activity.RESULT_OK) {
				// TODO player fragment character modified
			}
			break;
		}
	}
}
