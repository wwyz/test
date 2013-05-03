package com.examples.gg;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class inside_listview extends ListActivity{
private String[] titles;
private String[] videos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
//		String set = intent.getStringExtra("set");
		titles = intent.getStringArrayExtra("titles");
		videos = intent.getStringArrayExtra("videos");
		
		for (String v: videos){
			
			
			System.out.println("ID: "+ v);
		}
		
		
		if(titles!=null){
			setListAdapter(new MobileArrayAdapter(this, titles));}
		
		
//			
//		}else{
//			
//		//setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
//	//	Toast.makeText(this, set, Toast.LENGTH_SHORT).show();
//		System.out.println(set);
//		
//		new Thread(new Runnable(){
//		public void run(){
//			YoutubeFeed yFeed;
//			try {
//			
//			 yFeed=new YoutubeFeed("https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json");
//				List<Video> videos=yFeed.getVideoPlaylist();
//			    titles=new ArrayList();
//				for(Video v:videos){
//					titles.add(v.getTitle());
//					System.out.println(v.getTitle());
//				}
//				
//				System.out.println("mark");
//				String[] mStringArray = new String[titles.size()];
//				mStringArray = titles.toArray(mStringArray);
//		
//				for(int i = 0; i < mStringArray.length ; i++){
//				    System.out.println(mStringArray[i]);
//				}
//				Intent i =new Intent(inside_listview.this,inside_listview.class);
//				i.putExtra("array", mStringArray);
//				startActivity(i);
//				inside_listview.this.finish();
//				
//			}catch (JSONException e) {
//			// TODO Auto-generated catch block
//			
//			System.out.println("123");
//			
//			e.printStackTrace();
//		}
//		}
//		}).start();
//		}		
//		String[] mStringArray = new String[titles.size()];
//		mStringArray = titles.toArray(mStringArray);
//
//		for(int i = 0; i < mStringArray.length ; i++){
//		    System.out.println(mStringArray[i]);
//		}
//		// String[] string_title = new String[] { 
//		//	"WoDota", "Top10","Danteng", "DotaCinema" };
//		setListAdapter(new MobileArrayAdapter(this, mStringArray));
//		
//		
//		
//	String[] title_string=(String[]) titles.toArray();
	
//
//	
//	
//	
	
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, videos[position], Toast.LENGTH_SHORT).show();
		
        Intent i = new Intent(inside_listview.this, VideoPlayer.class);
        i.putExtra("video", videos[position]);
        startActivity(i);
		
		}
 
}
