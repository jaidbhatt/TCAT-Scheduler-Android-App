package com.tcatschedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class CustomOnItemSelectedListener implements OnItemSelectedListener{
	public static final String TAG="CustomOnItemSelectedListener";
	private ListView primaryRouteListView;
	public CustomOnItemSelectedListener(ListView listView){
		primaryRouteListView = listView;
	}
	@Override
	public void onItemSelected(final AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		final List<String> list = fetchFileNamesByRouteNum(parent.getContext(), parent.getItemAtPosition(position).toString());
		if(list.size()>1){
			primaryRouteListView.setVisibility(View.VISIBLE);
			PrimaryRouteArrayAdapter adapter = new PrimaryRouteArrayAdapter(parent.getContext(), R.id.primary_route_listview, list);
			primaryRouteListView.setAdapter(adapter);
			primaryRouteListView.setClickable(true);
			primaryRouteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			  @Override
			  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

			    //Object o = primaryRouteListView.getItemAtPosition(position);
				  
				  Intent intent = new Intent(parent.getContext(), RouteDetailsActivity.class);
				  Bundle data = new Bundle();
				  data.putString("fileName", list.get(position));
				  intent.putExtras(data);
				  arg1.getContext().startActivity(intent);
			    /* write you handling code like...
			    String st = "sdcard/";
			    File f = new File(st+o.toString());
			    // do whatever u want to do with 'f' File object
			    */  
			  }
			});
		}
		else{
			Intent intent = new Intent(parent.getContext(), RouteDetailsActivity.class);
			  Bundle data = new Bundle();
			  data.putString("fileName", list.get(position));
			  intent.putExtras(data);
			  parent.getContext().startActivity(intent);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onNothingSelected");
	}
	
	private static List<String> fetchFileNamesByRouteNum(Context context,String routeNum){
		List<String> list = new ArrayList<String>();
		try{
			String files[]= context.getResources().getAssets().list("");
			for(int i=0;i<files.length;i++){
				if(files[i].contains(routeNum)){
					list.add(files[i]);
				}
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		Log.d(TAG, "Size  : "+list.size());
		return list;
	}
}
