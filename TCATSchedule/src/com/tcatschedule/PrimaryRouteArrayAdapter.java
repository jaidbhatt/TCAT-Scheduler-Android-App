package com.tcatschedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PrimaryRouteArrayAdapter extends ArrayAdapter<String> {
	private static final String TAG = "PrimaryRouteArrayAdapter";
	private List<String> routeFileNames = new ArrayList<String>();
	private Context mContext = null;
	public PrimaryRouteArrayAdapter(Context context,int rowResourceId, List<String> routeFileList/*String routeNum*/){
		super(context, rowResourceId,routeFileList/*R.id.primary_route_listview*/);
		//Log.d(TAG, "Constructor route num : "+routeNum);
		this.mContext = context;
		this.routeFileNames = routeFileList/*fetchFileNamesByRouteNum(routeNum)*/;
		//Log.d(TAG, "Constructor route file list size : "+this.routeFileNames.size());
	}
	
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "getView called with : "+routeFileNames.get(position));
	    LayoutInflater inflater = (LayoutInflater) mContext
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.primary_route_selection_list_row, parent, false);
	    TextView dayView = (TextView) rowView.findViewById(R.id.primary_route_selection_row_day);
	    TextView tripView = (TextView) rowView.findViewById(R.id.primary_route_selection_row_trip_direction);
	    dayView.setText(routeFileNames.get(position).split("_")[0]);
	    tripView.setText(routeFileNames.get(position).split("_")[1]);
	    return rowView;
	  }
}
