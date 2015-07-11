package com.l5r.gm.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.l5r.gm.R;
import com.l5r.gm.activity.DevicesActivity;
import com.l5r.gm.adapter.PlayersListAdapter;
import com.l5r.gm.listener.OnPlayerSelectedListener;
import com.l5r.gm.model.Constants;
import com.l5r.gm.model.GamesModel;
import com.l5r.gm.object.Game;
import com.l5r.gm.object.Player;

public class GameFragment extends Fragment {

	private Game _game;

	private TextView _txtNbOfSessions;

	private TextView _txtNoPlayers;

	private ListView _listPlayers;

	private PlayersListAdapter _listPlayersAdapter;

	private boolean _areTherePlayers;

	private OnPlayerSelectedListener _callback;

	/**
	 * Local Bluetooth adapter
	 */
	private BluetoothAdapter _bluetoothAdapter = null;

	private boolean _displayDeviceList;

	@Override
	public void onCreate(Bundle savedInstanceState_p) {
		super.onCreate(savedInstanceState_p);
		setHasOptionsMenu(true);
		_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		// If the adapter is null, then Bluetooth is not supported
		if (_bluetoothAdapter == null) {
			Activity activity = getActivity();
			Toast.makeText(activity, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
			activity.finish();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater_p, ViewGroup container_p,
			Bundle savedInstanceState_p) {
		View rootView = inflater_p.inflate(R.layout.fragment_game, container_p, false);
		_txtNbOfSessions = (TextView) rootView.findViewById(R.id.fragment_game_session_text);
		_txtNoPlayers = (TextView) rootView.findViewById(R.id.fragment_game_no_player_text);
		_txtNoPlayers.setText(R.string.no_players_found);
		_listPlayers = (ListView) rootView.findViewById(R.id.fragment_game_players_list);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState_p) {
		super.onActivityCreated(savedInstanceState_p);

		int position = getArguments().getInt(Constants.GAME_POSITION);
		_game = GamesModel.getInstance().getGames().get(position);
		getActivity().setTitle(_game.getName());
		_txtNbOfSessions.setText(_game.getNbOfSessions() + " session"
				+ (_game.getNbOfSessions() == 1 ? "" : "s") + " played.");

		_listPlayersAdapter = new PlayersListAdapter(getActivity(), _game);
		_listPlayers.setAdapter(_listPlayersAdapter);
		_listPlayers.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				_callback.onPlayerSelected(position_p);
			}
		});

		updateTextNoPlayersVisibility();
	}

	@Override
	public void onAttach(Activity activity_p) {
		super.onAttach(activity_p);
		try {
			_callback = (OnPlayerSelectedListener) activity_p;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity_p.toString()
					+ " must implement OnPlayerSelectedListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		_callback = null;
	}

	private void updateTextNoPlayersVisibility() {
		int visibility = _listPlayers.getCount() == 0 ? View.VISIBLE : View.GONE;
		_txtNoPlayers.setVisibility(visibility);
		_areTherePlayers = _listPlayers.getCount() != 0;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu_p, MenuInflater inflater_p) {
		super.onCreateOptionsMenu(menu_p, inflater_p);
		menu_p.clear();
		inflater_p.inflate(R.menu.menu_game_fragment, menu_p);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item_p) {
		switch (item_p.getItemId()) {
		case R.id.action_game_launch_session:
			if (_areTherePlayers) {
				launchSession();
			} else {
				Toast toast = Toast.makeText(getActivity(), R.string.no_players_found,
						Toast.LENGTH_SHORT);
				toast.show();
			}
			return true;
		case R.id.action_game_fragment_make_discoverable:
			_displayDeviceList = false;
			activateBluetooth();
			return true;
		case R.id.action_game_fragment_player_search:
			_displayDeviceList = true;
			activateBluetooth();
			return true;
		default:
			return super.onOptionsItemSelected(item_p);
		}
	}

	private void activateBluetooth() {
		if (!_bluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, Constants.REQUEST_ENABLE_BT);
		} else {
			makeDiscoverable();
		}
	}

	private void makeDiscoverable() {
		if (_bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
					Constants.DISCOVERABLE_DURATION);
			startActivityForResult(discoverableIntent, Constants.REQUEST_DISCOVERABLE);
		} else if (_displayDeviceList) {
			_displayDeviceList = false;
			displaySearchList();
		}
	}

	@Override
	public void onActivityResult(int requestCode_p, int resultCode_p, Intent data_p) {
		switch (requestCode_p) {

		// When DeviceListActivity returns with a device to connect
		case Constants.REQUEST_CONNECT_DEVICE_SECURE:
			if (resultCode_p == Activity.RESULT_OK) {
				String address = data_p.getStringExtra(Constants.DEVICE_ADDRESS);
				openCreatePlayerDialog(address);
			}
			break;

		// When DeviceListActivity returns with a device to connect
		case Constants.REQUEST_CONNECT_DEVICE_INSECURE:
			if (resultCode_p == Activity.RESULT_OK) {
				String address = data_p.getStringExtra(Constants.DEVICE_ADDRESS);
				openCreatePlayerDialog(address);
			}
			break;

		// When the request to enable Bluetooth returns
		case Constants.REQUEST_ENABLE_BT:
			if (resultCode_p == Activity.RESULT_OK) {
				makeDiscoverable();
			}
			break;

		// When the request to make discoverable returns
		case Constants.REQUEST_DISCOVERABLE:
			if (resultCode_p == Activity.RESULT_OK
					|| resultCode_p == Constants.DISCOVERABLE_DURATION) {
				if (_displayDeviceList) {
					_displayDeviceList = false;
					displaySearchList();
				}
			}
			break;
		}
	}

	private void openCreatePlayerDialog(final String address_p) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.dialog_create);
		dialog.setTitle(R.string.create_new_player);
		dialog.setCancelable(true);

		final EditText txtName = (EditText) dialog.findViewById(R.id.dialog_create_name_edit);
		Button btnOk = (Button) dialog.findViewById(R.id.dialog_create_ok_button);
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v_p) {
				createPlayer(txtName.getText().toString(), address_p);
				dialog.dismiss();
			}
		});
		Button btnCancel = (Button) dialog.findViewById(R.id.dialog_create_cancel_button);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v_p) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	protected void createPlayer(String name_p, String address_p) {
		Player player = new Player(name_p);
		player.setMACAddress(address_p);
		_game.addPlayer(player);

		_listPlayersAdapter.notifyDataSetChanged();
		updateTextNoPlayersVisibility();
	}

	private void displaySearchList() {
		Intent serverIntent = new Intent(getActivity(), DevicesActivity.class);
		startActivityForResult(serverIntent, Constants.REQUEST_CONNECT_DEVICE_SECURE);
	}

	private void launchSession() {
		// TODO game fragment launch session

	}
}
