package com.samsung.travelbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.w3c.dom.Document;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

public class TravelScheduleAllocationActivity extends FragmentActivity
		implements OnInfoWindowClickListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	final LatLng HAMBURG = new LatLng(13.4770676, 144.7494423);
	final LatLng KIEL = new LatLng(13.4770676, 144.7494423);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_schedule_allocation);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(R.layout.action_bar_title);
		((TextView) findViewById(R.id.action_bar_title))
		.setText("날짜별 일정 배치");
		((TextView) findViewById(R.id.action_bar_subtitle))
		.setText("이번 여행의 액션 아이템을 일정별로 배치");
		
		// ForGMapV2Direction Library
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.onCreate(savedInstanceState);
		mapView.onResume(); // without this, map showed but was empty

		map = mapView.getMap();
		map.getUiSettings().setMyLocationButtonEnabled(false);
		map.setMyLocationEnabled(true);

		try {
			MapsInitializer.initialize(this);
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}

		// Gets to GoogleMap from the MapView and does initialization
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
				new LatLng(13.4770676, 144.7494423), 12);
		// map.animateCamera(cameraUpdate);
		map.moveCamera(cameraUpdate);

		LatLng MELBOURNE = new LatLng(13.4770676, 144.7494423);
		Marker melbourne = map.addMarker(new MarkerOptions()
				.position(MELBOURNE)
				.title("CAPRICCIOA Restaurant")
				.snippet("바닷가재가 제일 맛있는 집")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.hot_place_marker_01)));

		LatLng hitonHotel = new LatLng(13.506321, 144.785702);
		Marker mark_hitonHotel = map.addMarker(new MarkerOptions()
				.position(hitonHotel)
				.title("CAPRICCIOA Restaurant")
				.snippet("힐튼 호텔")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.hot_place_marker_02)));

		map.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
		map.setOnInfoWindowClickListener(this);
		
		GMapV2Direction md = new GMapV2Direction();

		Document doc = md.getDocument(MELBOURNE, hitonHotel,
				GMapV2Direction.MODE_DRIVING);
		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(8).color(
				0xFFFF8300);

		for (int i = 0; i < directionPoint.size(); i++) {
			rectLine.add(directionPoint.get(i));
		}

		map.addPolyline(rectLine);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.travel_schedule_allocation, menu);
		return true;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "Day 1 (6/23)";
			case 1:
				return "Day 2 (6/24)";
			case 2:
				return "Day 3 (6/25)";
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		DragSortListView listView;
		ArrayAdapter<TravelBookHubItemData> adapter;
		ArrayList<TravelBookHubItemData> travelBookActionItemDataArray = null;

		String names[] = { "맹구", "배용준", "땡칠이", "장동건", "강수정", "송창식", "황당해",
				"고은아" };

		private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
			@Override
			public void drop(int from, int to) {
				if (from != to) {
					// String item = adapter.getItem(from);
					// adapter.remove(item);
					// adapter.insert(item, to);
				}
			}
		};

		private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
			@Override
			public void remove(int which) {
				adapter.remove(adapter.getItem(which));
			}
		};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_travel_schedule_allocation_dummy,
					container, false);

			listView = (DragSortListView) rootView.findViewById(R.id.listview);
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(names));

			travelBookActionItemDataArray = new ArrayList<TravelBookHubItemData>();

			TravelBookActionItemListAdapter travelBookHubListAdapter = new TravelBookActionItemListAdapter(
					getActivity(), R.layout.travel_book_action_item,
					travelBookActionItemDataArray);

			TravelBookHubItemData item = new TravelBookHubItemData();
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);
			// travelBookActionItemDataArray.add(item);

			listView.setAdapter(travelBookHubListAdapter);
			listView.setDropListener(onDrop);
			listView.setRemoveListener(onRemove);

			DragSortController controller = new DragSortController(listView);
			controller.setDragHandleId(R.id.myreorder);
			// controller.setClickRemoveId(R.id.);
			controller.setRemoveEnabled(false);
			controller.setSortEnabled(true);
			controller.setDragInitMode(1);
			// controller.setRemoveMode(removeMode);

			listView.setFloatViewManager(controller);
			listView.setOnTouchListener(controller);
			listView.setDragEnabled(true);

			return rootView;
		}

		public class TravelBookActionItemListAdapter extends
				ArrayAdapter<TravelBookHubItemData> {

			private Context context = null;
			private int layout = 0;

			public TravelBookActionItemListAdapter(Context context, int layout,
					ArrayList<TravelBookHubItemData> objects) {
				super(context, layout, objects);
				this.context = context;
				this.layout = layout;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				View row = null;
				if (convertView == null) {
					LayoutInflater inflater = (LayoutInflater) this.context
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					row = inflater.inflate(layout, null);
				} else {
					row = convertView;
				}

				return row;
			}
		}
	}
}
