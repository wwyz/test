package com.example.gg;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
 
public class Youtube extends ListActivity {
  
	static final String[] MOBILE_OS = new String[] { 
		"WoDota", "Top10","Danteng", "DotaCinema" };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
 
	}
  
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		if(selectedValue=="DotaCinema"){
//			YoutubeFeed yFeed = null;
//			try {
//				 yFeed=new YoutubeFeed("https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json");
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			List<Video> videos=yFeed.getVideoPlaylist();
//			
//			
		String value="https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json";
			Intent i =new Intent(Youtube.this,inside_listview.class);
			i.putExtra("set", value);
			startActivity(i);
			Youtube.this.finish();
			
				}

	
	}
 
}