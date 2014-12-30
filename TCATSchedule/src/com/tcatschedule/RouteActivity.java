package com.tcatschedule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class RouteActivity extends Activity {
	private static String TAG = "RouteActivity";
	private static LinkedHashMap<String, LinkedHashMap<String,List<String>>> routeMap = new LinkedHashMap<String, LinkedHashMap<String,List<String>>>();
	private static ArrayList<String> routeList = new ArrayList<String>();
	private Spinner spinner1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(com.tcatschedule.R.layout.route_layout);
		loadRouteHashMap();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, routeList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setAdapter(dataAdapter);
		spinner1.setSelection(2);
		ListView primaryListView = (ListView) findViewById(R.id.primary_route_listview);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener(primaryListView));
		
		
	}
	private void loadRouteHashMap(){
		
		try{
			String files[]= getResources().getAssets().list("");
			for(int i=0;i<files.length;i++){
				if(files[i].contains("Route")){
					//Log.d(TAG, files[i]);
					if(!routeList.contains(files[i].split(" ")[0]+" "+files[i].split(" ")[1])){
						routeList.add(files[i].split(" ")[0]+" "+files[i].split(" ")[1]);
					}
					/*BufferedReader bRead = new BufferedReader(new InputStreamReader(getResources().getAssets().open(files[i])));
					String temp = bRead.readLine();
					while(temp!=null){
						Log.d(TAG, temp);
						temp = bRead.readLine();
					}*/
				}
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}
