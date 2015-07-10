package com.l5r.gm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.model.GamesModel;
import com.l5r.gm.object.Game;

public class GamesListAdapter extends BaseAdapter {

	private Context _context;

	private LayoutInflater _inflater;

	public GamesListAdapter(Context context_p) {
		_context = context_p;
		_inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return GamesModel.getInstance().getGames().size();
	}

	@Override
	public Object getItem(int location_p) {
		return GamesModel.getInstance().getGames().get(location_p);
	}

	@Override
	public long getItemId(int position_p) {
		return position_p;
	}

	@Override
	public View getView(int position_p, View convertView_p, ViewGroup parent_p) {
		ViewHolder holder = null;
		if (convertView_p == null) {
			convertView_p = _inflater.inflate(R.layout.list_games_item, null);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) convertView_p
					.findViewById(R.id.list_games_item_title_text);
			holder.txtPlayers = (TextView) convertView_p
					.findViewById(R.id.list_games_item_players_text);
			holder.txtSessions = (TextView) convertView_p
					.findViewById(R.id.list_games_item_sessions_text);
			convertView_p.setTag(holder);
		} else {
			holder = (ViewHolder) convertView_p.getTag();
		}

		Game game = (Game) getItem(position_p);
		if (game != null) {
			holder.txtTitle.setText(game.getName());
			holder.txtPlayers.setText(game.getPlayers().size() + " player"
					+ (game.getPlayers().size() == 1 ? "" : "s") + ".");
			holder.txtSessions.setText(game.getNbOfSessions() + " session"
					+ (game.getNbOfSessions() == 1 ? "" : "s") + " played.");
		}

		return convertView_p;
	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtPlayers;
		public TextView txtSessions;
	}

}
