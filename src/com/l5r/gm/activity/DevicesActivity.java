package com.l5r.gm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.l5r.gm.R;
import com.l5r.gm.adapter.DevicesListAdapter;
import com.l5r.gm.model.Constants;

public class DevicesActivity extends Activity {

	private BluetoothAdapter _bluetoothAdapter;

	private List<BluetoothDevice> _alreadyPairedDevices;

	private Button _btnScan;

	private ListView _alreadyPairedDevicesListView;

	private ListView _newDevicesListView;

	private DevicesListAdapter _alreadyPairedDevicesListAdapter;

	private DevicesListAdapter _newDevicesListAdapter;

	private TextView _txtNewDevices;

	private BroadcastReceiver _broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context_p, Intent intent_p) {
			String action = intent_p.getAction();

			// When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {

				// Get the BluetoothDevice object from the Intent
				BluetoothDevice device = intent_p.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				// If it's already paired, skip it, because it's been listed
				// already
				if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
					_newDevicesListAdapter.addItem(device);
				}
			}

			// When discovery is finished
			else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				setProgressBarIndeterminateVisibility(false);
			}

		}

	};

	private OnItemClickListener _onDeviceClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapter_p, View view_p, int position_p, long id_p) {

			// Cancel discovery because it's costly and we're about to
			// connect
			_bluetoothAdapter.cancelDiscovery();

			BluetoothDevice device = (BluetoothDevice) adapter_p.getItemAtPosition(position_p);

			// Create the result Intent and include the MAC address
			Intent intent = new Intent();
			intent.putExtra(Constants.DEVICE_NAME, device.getName());
			intent.putExtra(Constants.DEVICE_ADDRESS, device.getAddress());

			// Set result and finish this Activity
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_devices);

		setResult(Activity.RESULT_CANCELED);

		// Get the local Bluetooth adapter
		_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		_txtNewDevices = (TextView) findViewById(R.id.activity_devices_new_devices_text);

		// Get a set of currently paired devices
		_alreadyPairedDevices = new ArrayList<BluetoothDevice>();
		_alreadyPairedDevices.addAll(_bluetoothAdapter.getBondedDevices());
		_alreadyPairedDevicesListView = (ListView) findViewById(R.id.activity_devices_paired_devices_list);
		_alreadyPairedDevicesListAdapter = new DevicesListAdapter(this, _alreadyPairedDevices);
		_alreadyPairedDevicesListView.setAdapter(_alreadyPairedDevicesListAdapter);
		_alreadyPairedDevicesListView.setOnItemClickListener(_onDeviceClickListener);

		_newDevicesListView = (ListView) findViewById(R.id.activity_devices_new_devices_list);
		_newDevicesListAdapter = new DevicesListAdapter(this, new ArrayList<BluetoothDevice>());
		_newDevicesListView.setAdapter(_newDevicesListAdapter);
		_newDevicesListView.setOnItemClickListener(_onDeviceClickListener);

		_btnScan = (Button) findViewById(R.id.activity_devices_scan_button);
		_btnScan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view_p) {
				doDiscovery();
				view_p.setVisibility(View.GONE);
			}
		});

		// Register for broadcasts when a device is discovered
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(_broadcastReceiver, filter);

		// Register for broadcasts when discovery has finished
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(_broadcastReceiver, filter);

	}

	/**
	 * Start device discover with the BluetoothAdapter
	 */
	private void doDiscovery() {
		setProgressBarIndeterminateVisibility(true);
		_txtNewDevices.setVisibility(View.VISIBLE);

		// If we're already discovering, stop it
		if (_bluetoothAdapter.isDiscovering()) {
			_bluetoothAdapter.cancelDiscovery();
		}

		// Request discover from BluetoothAdapter
		_bluetoothAdapter.startDiscovery();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Make sure we're not doing discovery anymore
		if (_bluetoothAdapter != null) {
			_bluetoothAdapter.cancelDiscovery();
		}

		// Unregister broadcast listeners
		unregisterReceiver(_broadcastReceiver);
	}
}
