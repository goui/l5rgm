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

	public NavigationDrawerItemsAdapter(Context context_p, List<DrawerItem> drawerItems_p) {
		_drawerItems = drawerItems_p;
		inflater = (LayoutInflater) context_p.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public boolean isEnabled(int position_p) {
		return !_drawerItems.get(position_p).isSection();
	}

	@Override
	public int getCount() {
		return _drawerItems.size();
	}

	@Override
	public Object getItem(int position_p) {
		return _drawerItems.get(position_p);
	}

	@Override
	public long getItemId(int position_p) {
		return position_p;
	}

	@Override
	public int getItemViewType(int position_p) {
		return _drawerItems.get(position_p).isSection() ? SECTION_TYPE : ITEM_TYPE;
	}

	@Override
	public View getView(int position_p, View convertView_p, ViewGroup parent_p) {
		int type = getItemViewType(position_p);
		SectionHolder sectionHolder = null;
		ItemHolder itemHolder = null;

		switch (type) {
		case SECTION_TYPE:
			if (convertView_p == null || !(convertView_p.getTag() instanceof SectionHolder)) {
				convertView_p = inflater.inflate(R.layout.navigation_drawer_item_group, null);
				sectionHolder = new SectionHolder();
				sectionHolder.txtView = (TextView) convertView_p
						.findViewById(R.id.navigation_drawer_item_group_text_view);
				convertView_p.setTag(sectionHolder);
			} else {
				sectionHolder = (SectionHolder) convertView_p.getTag();
			}

			DrawerItem section = (DrawerItem) getItem(position_p);
			if (section != null) {
				sectionHolder.txtView.setText(section.getName());
			}

			break;
		case ITEM_TYPE:
			if (convertView_p == null || !(convertView_p.getTag() instanceof ItemHolder)) {
				convertView_p = inflater.inflate(R.layout.navigation_drawer_item_child, null);
				itemHolder = new ItemHolder();
				itemHolder.imgView = (ImageView) convertView_p
						.findViewById(R.id.navigation_drawer_item_child_image_view);
				itemHolder.txtView = (TextView) convertView_p
						.findViewById(R.id.navigation_drawer_item_child_text_view);
				convertView_p.setTag(sectionHolder);
			} else {
				itemHolder = (ItemHolder) convertView_p.getTag();
			}

			DrawerItem item = (DrawerItem) getItem(position_p);
			if (item != null) {
				itemHolder.imgView.setImageResource(item.getImageID());
				itemHolder.txtView.setText(item.getName());
			}

			break;
		}

		return convertView_p;
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
