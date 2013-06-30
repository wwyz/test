package com.examples.gg;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class Inside_activity extends SideMenuActivity{
	
	private ArrayList<Video> videolist;
	private String query;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//getting data from previous activity
		Intent intent =getIntent();
		videolist = intent.getParcelableArrayListExtra ("videolist");
		query = intent.getStringExtra("query");

		//display inside_listview2 fragment
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		getSupportActionBar().setTitle("Inside a playlist");
		//passing data to the fragment
		inside_listview2 ip = new inside_listview2();
		ip.setVideolist(videolist);
		ip.setQuery(query);
		
		ft.replace(R.id.content_frame, ip);
		
		ft.commit();
		
		
		
	}
}
