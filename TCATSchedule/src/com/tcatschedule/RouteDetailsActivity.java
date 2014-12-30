package com.tcatschedule;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RouteDetailsActivity extends Activity {
	private final static String TAG = "RouteDetailsActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(com.tcatschedule.R.layout.route_details);
		String fileName = getIntent().getExtras().getString("fileName");
		TextView dayView = (TextView) findViewById(R.id.route_details_day);
	    TextView tripView = (TextView) findViewById(R.id.route_details_direction);
	    dayView.setText(fileName.split("_")[0]);
	    tripView.setText(fileName.split("_")[1]);
	    TableLayout table = (TableLayout) findViewById(R.id.route_details_table_header);
	    TableLayout scrollableTable = (TableLayout) findViewById(R.id.route_details_table);
	    try{
	    BufferedReader bRead = new BufferedReader(new InputStreamReader(getResources().getAssets().open(fileName)));
	    int i=0;
	    String temp = bRead.readLine();
	    i++;
	    temp= bRead.readLine();
	    i++;
	    int width = 0;
	    if(temp!=null){
	    	String splt[] = temp.split("~");
	    	table.setWeightSum(splt.length);
	    	Display display = getWindowManager().getDefaultDisplay();
	    	
	    	width=display.getWidth()/splt.length;
	    	
	    	
	    	if(splt.length>=1){
	        	TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
		                TableRow.LayoutParams.WRAP_CONTENT);
	        	TableRow row = new TableRow(this);
	        	row.setBackgroundColor(Color.LTGRAY);
		        row.setLayoutParams(lp);
		        for(int j=0;j<splt.length;j++){
		        	TextView tv = new TextView(this);
		        	tv.setBackgroundColor(Color.BLACK);
		        	tv.setTextColor(Color.WHITE);
		        	tv.setLayoutParams(new TableRow.LayoutParams(width,TableRow.LayoutParams.WRAP_CONTENT));
		        	//tv.setWidth(0);
		        	//tv.setHeight()
		        	tv.setText(splt[j]);
		        	row.addView(tv);
		        }
		        
		        table.addView(row);
		        
	        }
	        
	        temp=bRead.readLine();
	    	
	    	
	    }
	    while(temp!=null){
	    	
	        String splt[] = temp.split("~");
	        if(splt.length>=1){
	        	TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
		                TableRow.LayoutParams.WRAP_CONTENT);
	        	TableRow row = new TableRow(this);
		        row.setLayoutParams(lp);
		        for(int j=0;j<splt.length;j++){
		        	TextView tv = new TextView(this);
		        	tv.setLayoutParams(new TableRow.LayoutParams(width,TableRow.LayoutParams.WRAP_CONTENT));
		        	//tv.setWidth(0);
		        	//tv.setHeight()
		        	tv.setText(splt[j]);
		        	row.addView(tv);
		        }
		        
		        scrollableTable.addView(row);
		        
	        }
	        
	        temp=bRead.readLine();
	    }
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}
}
