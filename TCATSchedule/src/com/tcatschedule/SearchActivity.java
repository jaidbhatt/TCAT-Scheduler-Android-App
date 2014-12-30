package com.tcatschedule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SearchActivity extends Activity {

	private static String TAG = "SearchActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.tcatschedule.R.layout.search_layout);
		Log.d(TAG, "onCreate");
	}
}
