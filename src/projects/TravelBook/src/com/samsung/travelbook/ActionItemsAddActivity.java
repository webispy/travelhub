package com.samsung.travelbook;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActionItemsAddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_items_add);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(R.layout.action_bar_title);
		((TextView) findViewById(R.id.action_bar_title))
				.setText("액션 아이템 추가");
		((TextView) findViewById(R.id.action_bar_subtitle))
				.setText("런던의 숙소, 교통, 식당, 유명 관광지를 추가하세요");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_items_add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("Jun", "value " + item.getItemId());
		switch (item.getItemId()) {
		case R.id.add_travel_item: {
			Intent intent = new Intent(getApplicationContext(),
					SearchTravelItemActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.move_to_allocate_schedule: {
			Intent intent = new Intent(getApplicationContext(),
					TravelScheduleAllocationActivity.class);
			startActivity(intent);
			break;
		}
		}
		return false;
	}

}
