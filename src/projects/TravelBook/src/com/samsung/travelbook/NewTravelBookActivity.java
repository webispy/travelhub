package com.samsung.travelbook;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NewTravelBookActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_travel_book);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(R.layout.action_bar_title);
		((TextView) findViewById(R.id.action_bar_title))
				.setText("Travel Book 생성");
		((TextView) findViewById(R.id.action_bar_subtitle))
				.setText("새 Travel Book의 이름과 목적지 설정");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_travel_book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("Jun", "value " + item.getItemId());
		switch (item.getItemId()) {
		case R.id.move_to_get_action_items: {
			// Intent intent = new Intent(getApplicationContext(),
			// TravelScheduleAllocationActivity.class);
			Intent intent = new Intent(getApplicationContext(),
					ActionItemsAddActivity.class);
			startActivity(intent);
			break;
		}
		}
		return false;
	}

}
