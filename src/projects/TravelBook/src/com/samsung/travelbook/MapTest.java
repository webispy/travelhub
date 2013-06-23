package com.samsung.travelbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapTest extends Activity {

	static final LatLng HAMBURG = new LatLng(13.4770676, 144.7494423);
	static final LatLng KIEL = new LatLng(13.4770676, 144.7494423);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_test);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
//		Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
//				.title("Hamburg"));
//		Marker kiel = map.addMarker(new MarkerOptions()
//				.position(KIEL)
//				.title("Kiel")
//				.snippet("Kiel is cool")
//				.icon(BitmapDescriptorFactory
//						.fromResource(R.drawable.ic_launcher)));

		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_test, menu);
		return true;
	}

}
