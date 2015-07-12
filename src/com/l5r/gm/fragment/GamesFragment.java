package com.l5r.gm.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
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

import com.l5r.gm.R;
import com.l5r.gm.adapter.GamesListAdapter;
import com.l5r.gm.listener.OnGameSelectedListener;
import com.l5r.gm.model.GamesModel;
import com.l5r.gm.object.Game;

public class GamesFragment extends Fragment {
	private static final String FRAGMENT_TITLE = "Games";

	private TextView _txtNoGames;

	private ListView _listGames;

	private GamesListAdapter _listGamesAdapter;

	private OnGameSelectedListener _callback;

	@Override
	public void onCreate(Bundle savedInstanceState_p) {
		super.onCreate(savedInstanceState_p);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater_p, ViewGroup container_p,
			Bundle savedInstanceState_p) {
		View rootView = inflater_p.inflate(R.layout.fragment_games, container_p, false);
		_txtNoGames = (TextView) rootView.findViewById(R.id.fragment_games_text);
		_txtNoGames.setText(R.string.no_games_found);
		_listGames = (ListView) rootView.findViewById(R.id.fragment_games_list);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState_p) {
		super.onActivityCreated(savedInstanceState_p);

		getActivity().getActionBar().setTitle(FRAGMENT_TITLE);
		_listGamesAdapter = new GamesListAdapter(getActivity());
		_listGames.setAdapter(_listGamesAdapter);
		_listGames.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent_p, View view_p, int position_p, long id_p) {
				_callback.onGameSelected(position_p);
			}
		});

		updateTextNoGamesVisibility();
	}

	@Override
	public void onAttach(Activity activity_p) {
		super.onAttach(activity_p);
		try {
			_callback = (OnGameSelectedListener) activity_p;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity_p.toString()
					+ " must implement OnGameSelectedListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		_callback = null;
	}

	private void updateTextNoGamesVisibility() {
		int visibility = _listGames.getCount() == 0 ? View.VISIBLE : View.GONE;
		_txtNoGames.setVisibility(visibility);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu_p, MenuInflater inflater_p) {
		super.onCreateOptionsMenu(menu_p, inflater_p);
		menu_p.clear();
		inflater_p.inflate(R.menu.menu_games_fragment, menu_p);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item_p) {
		switch (item_p.getItemId()) {
		case R.id.action_games_fragment_create:
			openNewGameDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item_p);
		}
	}

	private void openNewGameDialog() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.dialog_create);
		dialog.setTitle(R.string.create_new_game);
		dialog.setCancelable(true);

		final EditText txtName = (EditText) dialog.findViewById(R.id.dialog_create_name_edit);
		Button btnOk = (Button) dialog.findViewById(R.id.dialog_create_ok_button);
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v_p) {
				createNewGame(txtName.getText().toString());
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

	protected void createNewGame(String gameName_p) {
		GamesModel.getInstance().addGame(new Game(gameName_p));
		_listGamesAdapter.notifyDataSetChanged();
		updateTextNoGamesVisibility();
	}
}
