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
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class Youtube extends ListFragment {
  
	static ArrayList<String> MOBILE_OS;
	
	private String query = "https://gdata.youtube.com/feeds/api/playlists/PL981BABEC1803C00D?start-index=1&amp&max-results=10&amp&v=2&orderby=published&alt=json";
	Context appContext;
	ProgressDialog pd;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		String[] Options = new String[] { 
				"TopFails", "Top10","Danteng", "DotaCinema" };
		
		MOBILE_OS = new ArrayList<String>(Arrays.asList(Options));
		
		View view = inflater.inflate(android.R.layout.list_content, null);
	    ListView ls = (ListView) view.findViewById(android.R.id.list);

	    ls.setAdapter(new MobileArrayAdapter(inflater.getContext(), MOBILE_OS));
	    
	    ls.setDivider(null);
	    ls.setDividerHeight(0);

		appContext = inflater.getContext();
		
		return view;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		pd = ProgressDialog.show(appContext,"Loading","Dota spark is working hard to load videos for you!",true,false,null);


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
	        // 雎�ｽ､隴鯉ｽｶ髴台ｿｶ謔ｴ髫ｸ�ｻ陷ｿ邏具ｽｻ�ｻ闖ｴ蠖ｬson隴�ｿｽ謔ｽ�ｽ讙主ｳｩ隰暦ｽ･髫ｸ�ｻ陷ｿ髢�ｽｰ�ｱ隴擾ｽｯ闕ｳ�ｽ�ｸ�ｪJSONObject陝�ｽｹ髮趣ｽ｡邵ｲ�ｽ 
	        // 陞ｯ繧域｣｡雎�ｽ､隴鯉ｽｶ騾ｧ�ｽ�ｯ�ｻ陷ｿ邏具ｽｽ蜥ｲ�ｽ�ｮ陜ｨ�ｨ"name" : 闔�ｿｽ�ｼ遒√≦闕ｵ�ｽextValue陝�ｽｱ隴擾ｽｯ"yuanzhifei89"�ｽ�ｽtring�ｽ�ｽ 
	        JSONObject wholeJson = (JSONObject) jsonParser.nextValue();  
	        // 隰暦ｽ･闕ｳ蛹ｺ謫る��ｽ�ｰ�ｱ隴擾ｽｯJSON陝�ｽｹ髮趣ｽ｡騾ｧ�ｽ譯�抄諛会ｽｺ�ｽ 
	        this.feed = wholeJson.getJSONObject("feed");
	        
	        
	    }
	    
	   
	}
 
}