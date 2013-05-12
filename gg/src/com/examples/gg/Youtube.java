package com.examples.gg;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.json.JSONTokener;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
 
public class Youtube extends ListActivity {
  
	static ArrayList<String> MOBILE_OS;
	
	private String query = "https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=10&amp&v=2&orderby=published&alt=json";
	Context appContext;
	ProgressDialog pd;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] Options = new String[] { 
				"TopFails", "Top10","Danteng", "DotaCinema" };
		
		MOBILE_OS = new ArrayList<String>(Arrays.asList(Options));
	
		setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
 
		appContext = this.getApplicationContext();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		pd = ProgressDialog.show(Youtube.this,"Loading","Dota spark is working hard to load videos for you!",true,false,null);


		if(selectedValue=="DotaCinema"){
			
						new YoutubeGetRequest().execute(query);		
		}else{

			new YoutubeGetRequest().execute(query);		
	
			
		}

	
	}
	
	
	
	private class YoutubeGetRequest extends AsyncTask<String, String, String>{
	    private JSONObject feed;
	    public YoutubeGetRequest(){
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
	        super.onPostExecute(result);
	        //Do anything with response..
	        //System.out.println(result);
	        
	        try
	        {   
	            processJSON(result);
	        } catch (JSONException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        List<String> titles=new ArrayList<String>();   
	        List<String> ids = new ArrayList<String>();
	        
	        YoutubeFeed ytf = null;
			try {
				ytf = new YoutubeFeed(result);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        ArrayList<Video> videos = ytf.getVideoPlaylist();
	        for(Video v:videos){
//	            System.out.println(v.getVideoId());
	        	titles.add(v.getTitle());
	        	ids.add(v.getVideoId());

	        }
	        
	        String[] mStringArray = new String[titles.size()];
			mStringArray = titles.toArray(mStringArray);
			
	        String[] idsArray = new String[ids.size()];
	        idsArray = ids.toArray(idsArray);

			for(int i = 0; i < mStringArray.length ; i++){
			    System.out.println(mStringArray[i]);
			}
	        
	        //we can get more videos from this playlist if there are more
	        try
	        {
	            System.out.println("***************************Next api: "+ytf.getNextApi());
	        } catch (JSONException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        pd.dismiss();
	        Intent i = new Intent(appContext, inside_listview.class);
            i.putParcelableArrayListExtra ("videolist", videos);
	        
//	        i.putExtra("titles", mStringArray);
//	        i.putExtra("videos",  idsArray);
	        try {
				i.putExtra("query", ytf.getNextApi());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        appContext.startActivity(i);


	    }
	    
	    private void processJSON(String json) throws JSONException{
	        JSONTokener jsonParser = new JSONTokener(json);  
	        // 豁､譌ｶ霑俶悴隸ｻ蜿紋ｻｻ菴彬son譁�悽�檎峩謗･隸ｻ蜿門ｰｱ譏ｯ荳�ｸｪJSONObject蟇ｹ雎｡縲� 
	        // 螯よ棡豁､譌ｶ逧�ｯｻ蜿紋ｽ咲ｽｮ蝨ｨ"name" : 莠�ｼ碁ぅ荵�extValue蟆ｱ譏ｯ"yuanzhifei89"��tring�� 
	        JSONObject wholeJson = (JSONObject) jsonParser.nextValue();  
	        // 謗･荳区擂逧�ｰｱ譏ｯJSON蟇ｹ雎｡逧�桃菴應ｺ� 
	        this.feed = wholeJson.getJSONObject("feed");
	        
	        
	    }
	    
	   
	}
 
}