package com.l5r.gm.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.fragment.NavigationDrawerFragment.DrawerItem;

public class NavigationDrawerItemsAdapter extends BaseAdapter {

	private final static int SECTION_TYPE = 0;

	private final static int ITEM_TYPE = 1;

	private List<DrawerItem> _drawerItems;

	private LayoutInflater inflater;

	public NavigationDrawerItemsAdapter(Context context, List<DrawerItem> drawerItems) {
		_drawerItems = drawerItems;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public boolean isEnabled(int position) {
		return !_drawerItems.get(position).isSection();
	}

	@Override
	public int getCount() {
		return _drawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return _drawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return _drawerItems.get(position).isSection() ? SECTION_TYPE : ITEM_TYPE;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		SectionHolder sectionHolder = null;
		ItemHolder itemHolder = null;

		switch (type) {
		case SECTION_TYPE:
			if (convertView == null || !(convertView.getTag() instanceof SectionHolder)) {
				convertView = inflater.inflate(R.layout.navigation_drawer_item_group, null);
				sectionHolder = new SectionHolder();
				sectionHolder.txtView = (TextView) convertView
						.findViewById(R.id.navigation_drawer_item_group_text_view);
				convertView.setTag(sectionHolder);
			} else {
				sectionHolder = (SectionHolder) convertView.getTag();
			}

			DrawerItem section = (DrawerItem) getItem(position);
			if (section != null) {
				sectionHolder.txtView.setText(section.getName());
			}

			break;
		case ITEM_TYPE:
			if (convertView == null || !(convertView.getTag() instanceof ItemHolder)) {
				convertView = inflater.inflate(R.layout.navigation_drawer_item_child, null);
				itemHolder = new ItemHolder();
				itemHolder.imgView = (ImageView) convertView
						.findViewById(R.id.navigation_drawer_item_child_image_view);
				itemHolder.txtView = (TextView) convertView
						.findViewById(R.id.navigation_drawer_item_child_text_view);
				convertView.setTag(sectionHolder);
			} else {
				itemHolder = (ItemHolder) convertView.getTag();
			}

			DrawerItem item = (DrawerItem) getItem(position);
			if (item != null) {
				itemHolder.imgView.setImageResource(item.getImageID());
				itemHolder.txtView.setText(item.getName());
			}

			break;
		}

		return convertView;
	}

	/**
	 * Inner class representing a section view. ViewHolder pattern to increase
	 * performances.
	 */
	private class SectionHolder {
		public TextView txtView;
	}

	/**
	 * Inner class representing an item view. ViewHolder pattern to increase
	 * performances.
	 */
	private class ItemHolder {
		public ImageView imgView;
		public TextView txtView;
	}

}
