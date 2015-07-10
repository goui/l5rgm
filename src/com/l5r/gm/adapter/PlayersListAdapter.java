package com.l5r.gm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.object.Game;
import com.l5r.gm.object.Player;

public class PlayersListAdapter extends BaseAdapter {

	private Context _context;

	private LayoutInflater _inflater;

	private Game _game;

	public PlayersListAdapter(Context context_p, Game game_p) {
		_context = context_p;
		_game = game_p;
		_inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return _game.getPlayers().size();
	}

	@Override
	public Object getItem(int location_p) {
		return _game.getPlayers().get(location_p);
	}

	@Override
	public long getItemId(int position_p) {
		return position_p;
	}

	@Override
	public View getView(int position_p, View convertView_p, ViewGroup parent_p) {
		ViewHolder holder = null;
		if (convertView_p == null) {
			convertView_p = _inflater.inflate(R.layout.list_players_item, null);
			holder = new ViewHolder();
			holder.txtPlayerName = (TextView) convertView_p
					.findViewById(R.id.list_players_item_player_name_text);
			holder.txtMACAddress = (TextView) convertView_p
					.findViewById(R.id.list_players_item_mac_address_text);
			holder.txtCharacterName = (TextView) convertView_p
					.findViewById(R.id.list_players_item_character_name_text);
			holder.txtCharacterRank = (TextView) convertView_p
					.findViewById(R.id.list_players_item_character_rank_text);
			convertView_p.setTag(holder);
		} else {
			holder = (ViewHolder) convertView_p.getTag();
		}

		Player player = (Player) getItem(position_p);
		if (player != null) {
			holder.txtPlayerName.setText(player.getName());
			holder.txtMACAddress.setText(player.getMACAddress());

			if (player.getCharacter() != null) {
				holder.txtCharacterName.setText(player.getCharacter().getLastName() + " "
						+ player.getCharacter().getFirstName());
				holder.txtCharacterRank.setText("Rank " + player.getCharacter().getRank());
			} else {
				holder.txtCharacterName.setText(R.string.no_character_found);
				holder.txtCharacterRank.setText("");
			}
		}
		return convertView_p;
	}

	private class ViewHolder {
		public TextView txtPlayerName;
		public TextView txtMACAddress;
		public TextView txtCharacterName;
		public TextView txtCharacterRank;
	}

}
