package com.l5r.gm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.l5r.gm.R;
import com.l5r.gm.fragment.GameFragment;
import com.l5r.gm.fragment.GamesFragment;
import com.l5r.gm.listener.OnGameSelectedListener;
import com.l5r.gm.listener.OnPlayerSelectedListener;
import com.l5r.gm.model.Constants;

public class MainActivity extends Activity implements OnGameSelectedListener,
		OnPlayerSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new GamesFragment())
					.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onGameSelected(int position_p) {
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.GAME_POSITION, position_p);
		GameFragment gameFragment = new GameFragment();
		gameFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.container, gameFragment)
				.addToBackStack(null).commit();
	}

	@Override
	public void onPlayerSelected(int position_p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
			super.onBackPressed();
		} else {
			getFragmentManager().popBackStack();
		}
	}

}
