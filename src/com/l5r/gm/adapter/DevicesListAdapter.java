package com.l5r.gm.adapter;

import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l5r.gm.R;

public class DevicesListAdapter extends BaseAdapter {

	private Context _context;

	private LayoutInflater _inflater;

	private List<BluetoothDevice> _devices;

	public DevicesListAdapter(Context context_p, List<BluetoothDevice> devices_p) {
		_context = context_p;
		_devices = devices_p;
		_inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return _devices.size();
	}

	@Override
	public Object getItem(int location_p) {
		return _devices.get(location_p);
	}

	@Override
	public long getItemId(int position_p) {
		return position_p;
	}

	public void addItem(BluetoothDevice device_p) {
		_devices.add(device_p);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position_p, View convertView_p, ViewGroup parent_p) {
		ViewHolder holder = null;
		if (convertView_p == null) {
			convertView_p = _inflater.inflate(R.layout.list_devices_item, null);
			holder = new ViewHolder();
			holder.txtDeviceName = (TextView) convertView_p
					.findViewById(R.id.list_devices_item_device_name_text);
			holder.txtMACAddress = (TextView) convertView_p
					.findViewById(R.id.list_devices_item_mac_address_text);
			convertView_p.setTag(holder);
		} else {
			holder = (ViewHolder) convertView_p.getTag();
		}

		BluetoothDevice device = (BluetoothDevice) getItem(position_p);
		if (device != null) {
			holder.txtDeviceName.setText(device.getName());
			holder.txtMACAddress.setText(device.getAddress());
		}
		return convertView_p;
	}

	private class ViewHolder {
		public TextView txtDeviceName;
		public TextView txtMACAddress;
	}

}
