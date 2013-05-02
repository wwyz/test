package com.examples.gg;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.app.ProgressDialog;
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
		ProgressDialog pd;
	
		if(selectedValue=="DotaCinema"){
			pd = ProgressDialog.show(Youtube.this,"This is the title","This is the detail text",true,false,null);

	    	
			new YoutubeFeed2(getApplicationContext(),pd).execute("https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json");
						
//			YoutubeFeed yFeed = null;
//			try {
//				 yFeed=new YoutubeFeed("https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json");
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			List<Video> videos=yFeed.getVideoPlaylist();
//			
////			
//			
//			new Thread(new Runnable(){
//			public void run(){
//				YoutubeFeed yFeed;
//				try {
//				
//				 yFeed=new YoutubeFeed("https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=20&amp&v=2&alt=json");
//					List<Video> videos=yFeed.getVideoPlaylist();
//					List<String> titles=new ArrayList();
//					for(Video v:videos){
//						titles.add(v.getTitle());
//						System.out.println(v.getTitle());
//					}
//					
//					System.out.println("mark");
//					String[] mStringArray = new String[titles.size()];
//					mStringArray = titles.toArray(mStringArray);
//			
//					for(int i = 0; i < mStringArray.length ; i++){
//					    System.out.println(mStringArray[i]);
//					}
//					pd.dismiss();
//					Intent i =new Intent(Youtube.this,inside_listview.class);
//					i.putExtra("array", mStringArray);
//					startActivity(i);
//					Youtube.this.finish();
//					
//				}catch (JSONException e) {
//				// TODO Auto-generated catch block
//				
//				System.out.println("123");
//				
//				e.printStackTrace();
//			}
//			}
//			}).start();
			
//		String value="https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=5&amp&v=2&alt=json";
//			Intent i =new Intent(Youtube.this,inside_listview.class);
//			i.putExtra("set", value);
//			startActivity(i);
//			Youtube.this.finish();
//			
				}

	
	}
 
}