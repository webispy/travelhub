package com.samsung.travelbook;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TravelBookHubActivity extends Activity {

	private ArrayList<TravelBookHubItemData> travelBookHubItemDataArray = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_book_hub);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(R.layout.action_bar_title);
		((TextView) findViewById(R.id.action_bar_title))
		.setText("Travel Book 허브");
		((TextView) findViewById(R.id.action_bar_subtitle))
		.setText("현재까지 등록된 Travel Book : 14건");

		// TextView dummyTextView = (TextView) rootView
		// .findViewById(R.id.section_label);
		// dummyTextView.setText(Integer.toString(getArguments().getInt(
		// ARG_SECTION_NUMBER)));
		ListView travelBookHubListView = (ListView) findViewById(R.id.travel_book_hub);
		travelBookHubItemDataArray = new ArrayList<TravelBookHubItemData>();

		TravelBookHubListAdapter travelBookHubListAdapter = new TravelBookHubListAdapter(
				this, R.layout.travel_book_hub_item, travelBookHubItemDataArray);

		TravelBookHubItemData item = new TravelBookHubItemData();

		travelBookHubListAdapter.add(item);
		travelBookHubListAdapter.add(item);
		travelBookHubListAdapter.add(item);
		travelBookHubListAdapter.add(item);
		travelBookHubListView.setAdapter(travelBookHubListAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.travel_book_hub, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("Jun", "value " + item.getItemId());
		switch (item.getItemId()) {
		case R.id.action_create: {
			// Intent intent = new Intent(getApplicationContext(),
			// TravelScheduleAllocationActivity.class);
			Intent intent = new Intent(getApplicationContext(),
					NewTravelBookActivity.class);
			startActivity(intent);
			break;
		}
		}
		return false;
	}

	public class TravelBookHubListAdapter extends
			ArrayAdapter<TravelBookHubItemData> {

		private Context context = null;
		private int layout = 0;

		public TravelBookHubListAdapter(Context context, int layout,
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
