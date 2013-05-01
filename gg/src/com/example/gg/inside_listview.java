package com.example.gg;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class inside_listview extends ListActivity{
	private YoutubeFeed yFeed;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String set = intent.getStringExtra("set");

	
		//setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
		Toast.makeText(this, set, Toast.LENGTH_SHORT).show();
		System.out.println(set);
		try {
			 yFeed=new YoutubeFeed("https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json");
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			
			System.out.println("123");
			
			e.printStackTrace();
		}
	
	//	List<Video> videos=yFeed.getVideoPlaylist();
//	List<String> titles=new ArrayList();
//	for(Video v:videos){
//		titles.add(v.getTitle());
//	}
	
	
//	String[] title_string=(String[]) titles.toArray();
//	setListAdapter(new MobileArrayAdapter(this, title_string));

	
	
	
	
	}
 
}
