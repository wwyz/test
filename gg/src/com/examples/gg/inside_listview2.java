package com.examples.gg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListFragment;
import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;



public class inside_listview2 extends SherlockListFragment{
private LoadMoreListView myLoadMoreListView;
private ArrayList<String> titles;
private ArrayList<String> videos;
private ArrayList<String> thumbList;
private ArrayList<Video> videolist;
public ArrayList<Video> getVideolist() {
	return videolist;
}

public void setVideolist(ArrayList<Video> videolist) {
	this.videolist = videolist;
}

private String query;
public String getQuery() {
	return query;
}

public void setQuery(String query) {
	this.query = query;
}

private boolean isMoreVideos;
private LayoutInflater li;
// Declare Variable
DrawerLayout mDrawerLayout;
ListView mDrawerList;
ActionBarDrawerToggle mDrawerToggle;
ArrayList<Item> items = new ArrayList<Item>();
ActionBar mActionBar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.li = inflater;
		
		View view =  li.inflate(R.layout.loadmore_list, null);
		//myLoadMoreListView = view.findViewById(R.id.list);
		
		//setContentView(R.layout.loadmore_list2);
		
		isMoreVideos = true;
		//Intent intent = getIntent();
//		String set = intent.getStringExtra("set");
		
		titles = new ArrayList<String>();
		videos = new ArrayList<String>();
		thumbList = new ArrayList<String>();
		
		//videolist = intent.getParcelableArrayListExtra ("videolist");
		//query = intent.getStringExtra("query");
		
        for(Video v:videolist){
        	System.out.println("Title: " + v.getTitle());
        	System.out.println("video: " + v.getVideoId());
        	System.out.println("thumb: " + v.getThumbnailUrl());
        	titles.add(v.getTitle());
        	videos.add(v.getVideoId());
        	thumbList.add(v.getThumbnailUrl());
        }
		
		for (String v: videos){
				
			System.out.println("ID: "+ v);
		}

		
		if(titles!=null){
			setListAdapter(new VideoArrayAdapter(inflater.getContext(), titles, videolist));
		}
		

		return view;
	
	}
	@Override
	public void onResume() {
	  //onResume happens after onStart and onActivityCreate
		myLoadMoreListView =  (LoadMoreListView) this.getListView();
		myLoadMoreListView.setDivider(null);
		if(query != null){
		myLoadMoreListView
		.setOnLoadMoreListener(new OnLoadMoreListener() {
			public void onLoadMore() {
				// Do the work to load more items at the end of list
				// hereru

					new LoadMoreTask().execute(query);
				
			}
		});
		}
	  super.onResume() ; 
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, videos.get(position), Toast.LENGTH_SHORT).show();
		
        Intent i = new Intent(li.getContext(), VideoPlayer.class);
        i.putExtra("video", videolist.get(position));
        startActivity(i);
		
	}	
	

	private class LoadMoreTask extends AsyncTask<String, String, String>{
	    public LoadMoreTask(){
	        ;
	    }
	    @Override
	    protected String doInBackground(String... uri) {
	    	
	    
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response;
	        String responseString = null;
	        try {
	            response = httpclient.execute(new HttpGet(uri[0]));
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	            } else{
	                //Closes the connection.
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	            //TODO Handle problems..
	        } catch (IOException e) {
	            //TODO Handle problems..
	        }
	        return responseString;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	        //Do anything with response..
	        //System.out.println(result);
	    	YoutubeFeed ytf = null;
	        try
	        {   
	            ytf = new YoutubeFeed(result);
	        } catch (JSONException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        
	        
	        List<Video> newVideos = ytf.getVideoPlaylist();
	        for(Video v:newVideos){
//	            System.out.println(v.getVideoId());
	        	titles.add(v.getTitle());
	        	videos.add(v.getVideoId());
	        	videolist.add(v);
	        }
	        try {
				query = ytf.getNextApi();
				if(query == null){
					isMoreVideos = false;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			((LoadMoreListView) getListView()).onLoadMoreComplete();
			
			if (!isMoreVideos) {
				((LoadMoreListView) getListView()).onNoMoreItems();
				
				myLoadMoreListView.setOnLoadMoreListener(null);
			}

			super.onPostExecute(result);

	    }
	    
		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			((LoadMoreListView) getListView()).onLoadMoreComplete();
		}
	    

	    
	   
	}
	
	
 
}
