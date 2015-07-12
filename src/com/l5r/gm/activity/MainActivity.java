package com.l5r.gm.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.l5r.gm.R;
import com.l5r.gm.fragment.GameFragment;
import com.l5r.gm.fragment.GamesFragment;
import com.l5r.gm.fragment.NavigationDrawerFragment;
import com.l5r.gm.fragment.PlayerFragment;
import com.l5r.gm.listener.OnGameSelectedListener;
import com.l5r.gm.listener.OnPlayerSelectedListener;
import com.l5r.gm.model.Constants;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnGameSelectedListener,
		OnPlayerSelectedListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment _navigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence _strTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		_navigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		_strTitle = getTitle();

		// Set up the drawer.
		_navigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new GamesFragment())
					.commit();
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (_navigationDrawerFragment.isDrawerOpen()) {
			menu.clear();
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!_navigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.menu_navigation_drawer, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	private void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(_strTitle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position_p) {
		// TODO main activity on navigation drawer item selected

	}

	@Override
	public void onGameSelected(int gamePosition_p) {
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.GAME_POSITION, gamePosition_p);
		GameFragment gameFragment = new GameFragment();
		gameFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.container, gameFragment)
				.addToBackStack(null).commit();
	}

	@Override
	public void onPlayerSelected(int gamePosition_p, int playerPosition_p) {
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.GAME_POSITION, gamePosition_p);
		bundle.putInt(Constants.PLAYER_POSITION, playerPosition_p);
		PlayerFragment playerFragment = new PlayerFragment();
		playerFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.container, playerFragment)
				.addToBackStack(null).commit();
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
