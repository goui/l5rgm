package com.l5r.gm.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.l5r.gm.R;
import com.l5r.gm.adapter.NavigationDrawerItemsAdapter;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

	/**
	 * Remember the position of the selected item.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	/**
	 * Per the design guidelines, you should show the drawer on launch until the
	 * user manually expands it. This shared preference tracks this.
	 */
	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

	/**
	 * A pointer to the current callbacks instance (the Activity).
	 */
	private NavigationDrawerCallbacks _callbacks;

	/**
	 * Helper component that ties the action bar to the navigation drawer.
	 */
	private ActionBarDrawerToggle _drawerToggle;

	private DrawerLayout _drawerLayout;
	private ListView _drawerListView;
	private View _fragmentContainerView;

	private int _currentSelectedPosition = 0;
	private boolean _fromSavedInstanceState;
	private boolean _userLearnedDrawer;

	private List<DrawerItem> _drawerItems;

	public NavigationDrawerFragment() {
		_drawerItems = new ArrayList<DrawerItem>();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		// Read in the flag indicating whether or not the user has demonstrated
		// awareness of the
		// drawer. See PREF_USER_LEARNED_DRAWER for details.
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		_userLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		if (savedInstanceState != null) {
			_currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
			_fromSavedInstanceState = true;
		}

		// Select either the default item (0) or the last selected item.
		selectItem(_currentSelectedPosition);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		createDrawerItem();
		NavigationDrawerItemsAdapter adapter = new NavigationDrawerItemsAdapter(getActivity(),
				_drawerItems);
		_drawerListView.setAdapter(adapter);

		_drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
			}
		});

		_drawerListView.setItemChecked(_currentSelectedPosition, true);
	}

	private void createDrawerItem() {
		// TODO navigation drawer items
		DrawerItem section1 = new DrawerItem("SECTION 1", true, -1);
		DrawerItem item11 = new DrawerItem("Item 11", false, R.drawable.ic_input_white_48dp);
		DrawerItem item12 = new DrawerItem("Item 12", false, R.drawable.ic_note_add_white_48dp);
		DrawerItem item13 = new DrawerItem("Item 13", false, R.drawable.ic_pageview_white_48dp);

		DrawerItem section2 = new DrawerItem("SECTION 2", true, -1);
		DrawerItem item21 = new DrawerItem("Item 21", false, R.drawable.ic_visibility_white_48dp);

		_drawerItems.add(section1);
		_drawerItems.add(item11);
		_drawerItems.add(item12);
		_drawerItems.add(item13);
		_drawerItems.add(section2);
		_drawerItems.add(item21);
	}

	@Override
	public View onCreateView(LayoutInflater inflater_p, ViewGroup container_p,
			Bundle savedInstanceState_p) {
		View rootView = inflater_p.inflate(R.layout.fragment_navigation_drawer, container_p, false);
		_drawerListView = (ListView) rootView.findViewById(R.id.fragment_navigation_drawer_list);
		return rootView;
	}

	public boolean isDrawerOpen() {
		return _drawerLayout != null && _drawerLayout.isDrawerOpen(_fragmentContainerView);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation
	 * drawer interactions.
	 * 
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		_fragmentContainerView = getActivity().findViewById(fragmentId);
		_drawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer
		// opens
		_drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the navigation drawer and the action bar app icon.
		_drawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		_drawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /*
										 * "open drawer" description for
										 * accessibility
										 */
		R.string.navigation_drawer_close /*
										 * "close drawer" description for
										 * accessibility
										 */
		) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}

				getActivity().invalidateOptionsMenu(); // calls
														// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				if (!_userLearnedDrawer) {
					// The user manually opened the drawer; store this flag to
					// prevent auto-showing
					// the navigation drawer automatically in the future.
					_userLearnedDrawer = true;
					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(getActivity());
					sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
				}

				getActivity().invalidateOptionsMenu(); // calls
														// onPrepareOptionsMenu()
			}
		};

		// TODO If the user hasn't 'learned' about the drawer, open it to
		// introduce
		// them to the drawer,
		// per the navigation drawer design guidelines.
		// if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
		// mDrawerLayout.openDrawer(mFragmentContainerView);
		// }

		// Defer code dependent on restoration of previous instance state.
		_drawerLayout.post(new Runnable() {
			@Override
			public void run() {
				_drawerToggle.syncState();
			}
		});

		_drawerLayout.setDrawerListener(_drawerToggle);
	}

	private void selectItem(int position) {
		_currentSelectedPosition = position;
		if (_drawerListView != null) {
			_drawerListView.setItemChecked(position, true);
		}
		if (_drawerLayout != null) {
			_drawerLayout.closeDrawer(_fragmentContainerView);
		}
		if (_callbacks != null) {
			_callbacks.onNavigationDrawerItemSelected(position);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			_callbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		_callbacks = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, _currentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		_drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// If the drawer is open, show the global app actions in the action bar.
		// See also
		// showGlobalContextActionBar, which controls the top-left area of the
		// action bar.
		if (_drawerLayout != null && isDrawerOpen()) {
			inflater.inflate(R.menu.main, menu);
			showGlobalContextActionBar();
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (_drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// TODO navigation drawer react on item click

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Per the navigation drawer design guidelines, updates the action bar to
	 * show the global app 'context', rather than just what's in the current
	 * screen.
	 */
	private void showGlobalContextActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(R.string.app_name);
	}

	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	/**
	 * Callbacks interface that all activities using this fragment must
	 * implement.
	 */
	public static interface NavigationDrawerCallbacks {
		/**
		 * Called when an item in the navigation drawer is selected.
		 */
		void onNavigationDrawerItemSelected(int position);
	}

	/**
	 * 
	 * @author Goui-MSI
	 * 
	 */
	public class DrawerItem {

		private boolean isSection;

		private String name;

		private int imageID;

		public DrawerItem(String name, boolean isSection, int imageID) {
			this.name = name;
			this.isSection = isSection;
			this.imageID = imageID;
		}

		public boolean isSection() {
			return isSection;
		}

		public String getName() {
			return name;
		}

		public int getImageID() {
			return imageID;
		}

	}
}
