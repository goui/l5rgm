package com.l5r.gm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.l5r.gm.R;
import com.l5r.gm.fragment.CharacterCreationFragment;
import com.l5r.gm.fragment.CharacterOverallFragment;
import com.l5r.gm.model.Constants;
import com.l5r.gm.model.GamesModel;
import com.l5r.gm.object.Character;

public class CharacterActivity extends Activity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private Character _character;

	private List<Fragment> _fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character);

		// in case the user press back
		setResult(Activity.RESULT_CANCELED);

		// getting the extra to know if this is a character creation and whom
		// player is it
		boolean isCharacterCreation = getIntent()
				.getBooleanExtra(Constants.IS_CHARACTER_NULL, true);
		int gamePosition = getIntent().getIntExtra(Constants.GAME_POSITION, 0);
		int playerPosition = getIntent().getIntExtra(Constants.PLAYER_POSITION, 0);
		_character = GamesModel.getInstance().getGames().get(gamePosition).getPlayers()
				.get(playerPosition).getCharacter();

		createFragments(isCharacterCreation);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	private void createFragments(boolean isCharacterCreation_p) {
		_fragments = new ArrayList<Fragment>();
		if (isCharacterCreation_p) {
			_fragments.add(new CharacterCreationFragment());
		}
		_fragments.add(new CharacterOverallFragment());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_character_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_character_activity_ok) {
			setResult(Activity.RESULT_CANCELED);
			finish();
			return true;
		}
		if (id == R.id.action_character_activity_cancel) {
			setResult(Activity.RESULT_OK);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm_p) {
			super(fm_p);
		}

		@Override
		public Fragment getItem(int position_p) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return _fragments.get(position_p);
		}

		@Override
		public int getCount() {
			return _fragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position_p) {
			return _fragments.get(position_p).toString();
		}
	}

}
