package com.tcatschedule;

import android.R;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class MainActivity extends TabActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.tcatschedule.R.layout.activity_main);
		TabHost tabHost = getTabHost();
		Intent intentNews = new Intent().setClass(this, RouteActivity.class);
		TextView tv=new TextView(tabHost.getContext());
		tv.setText("Route number");
		tv.setTextColor(Color.WHITE);
		TabSpec tabSpecRoute = tabHost
				  .newTabSpec("Route number")
				  .setIndicator(makeTabIndicator("Route number",this))
				  .setContent(intentNews);
		Intent intentTwitter = new Intent().setClass(this, SearchActivity.class);
		TextView tv1=new TextView(tabHost.getContext());
		tv1.setText("By stopage");
		//tv1.setTextColor(1);
		tv1.setTextColor(Color.WHITE);
		TabSpec tabSpecSearch = tabHost
				  .newTabSpec("By stopage")
				  .setIndicator(makeTabIndicator("By stopage",this))
				  .setContent(intentTwitter);
		
		
		tabHost.addTab(tabSpecRoute);
		tabHost.addTab(tabSpecSearch);
		
		tabHost.setCurrentTabByTag("Route number");
	}

	private View makeTabIndicator(String text, Context context) {

	    // Inflate the layout file we defined above
	    View view = LayoutInflater.from(context).inflate(com.tcatschedule.R.layout.tab_indicator, null);   

	    TextView tv = (TextView) view.findViewById(com.tcatschedule.R.id.tabsText);
	    tv.setText(text);

	    return view;

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
