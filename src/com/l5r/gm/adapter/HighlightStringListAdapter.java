package com.l5r.gm.adapter;

import android.R.color;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l5r.gm.R;

public class HighlightStringListAdapter extends BaseAdapter {

	private Context _context;

	private LayoutInflater _inflater;

	private String[] _list;

	private int _selectedIndex = -1;

	private int _selectedColor;

	public HighlightStringListAdapter(Context context_p, String[] list_p) {
		_context = context_p;
		_list = list_p;
		_inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_selectedColor = _context.getResources().getColor(R.color.grey);
	}

	public void setList(String[] list_p) {
		_list = list_p;
		notifyDataSetChanged();
	}

	public void setSelected(int index_p) {
		_selectedIndex = index_p;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return _list.length;
	}

	@Override
	public Object getItem(int position_p) {
		return _list[position_p];
	}

	@Override
	public long getItemId(int location_p) {
		return location_p;
	}

	@Override
	public View getView(int position_p, View convertView_p, ViewGroup parent_p) {
		ViewHolder holder = null;
		if (convertView_p == null) {
			convertView_p = _inflater.inflate(R.layout.list_plain, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView_p.findViewById(R.id.list_plain_text);
			convertView_p.setTag(holder);
		} else {
			holder = (ViewHolder) convertView_p.getTag();
		}

		holder.txtName.setText(_list[position_p]);
		if (_selectedIndex != -1 && position_p == _selectedIndex) {
			convertView_p.setBackgroundColor(_selectedColor);
		} else {
			convertView_p.setBackgroundColor(color.background_light);
		}
		return convertView_p;
	}

	private class ViewHolder {
		public TextView txtName;
	}

}
