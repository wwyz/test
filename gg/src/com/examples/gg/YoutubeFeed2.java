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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;


class YoutubeFeed2 extends AsyncTask<String, String, String>{
    private JSONObject feed;
    ProgressDialog pd;
    Context context;
    public YoutubeFeed2(Context context,ProgressDialog pd){
        this.context = context;
        this.pd=pd;
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
        
        List<String> titles=new ArrayList();   
        List<Video> videos = getVideoPlaylist();
        for(Video v:videos){
//            System.out.println(v.getVideoId());
        	titles.add(v.getTitle());

        }
        
        String[] mStringArray = new String[titles.size()];
		mStringArray = titles.toArray(mStringArray);

		for(int i = 0; i < mStringArray.length ; i++){
		    System.out.println(mStringArray[i]);
		}
        
        //we can get more videos from this playlist if there are more
        try
        {
            System.out.println(getNextApi());
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        pd.dismiss();
        Intent i = new Intent(context, inside_listview.class);
        i.putExtra("array", mStringArray);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);


    }
    
    private void processJSON(String json) throws JSONException{
        JSONTokener jsonParser = new JSONTokener(json);  
        // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。  
        // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）  
        JSONObject wholeJson = (JSONObject) jsonParser.nextValue();  
        // 接下来的就是JSON对象的操作了  
        this.feed = wholeJson.getJSONObject("feed");
        
        
    }
    
    public List<Video> getVideoPlaylist(){
        List<Video> videos = new ArrayList<Video>();
        
        try {

            //get title of the playlist
            String plTitle = feed.getJSONObject("title").getString("$t");
            Video.setPlaylistTitle(plTitle);
            //System.out.println(plTitle);
            //get the playlist
            JSONArray playlist = feed.getJSONArray("entry");
            //System.out.println("Length: "+ playlist.length());
            
            for(int i=0;i<playlist.length();i++){
                //get a video in the playlist            // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。  
                // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）  
                JSONObject oneVideo = playlist.getJSONObject(i);
                //get the title of this video
                String videoTitle = oneVideo.getJSONObject("title").getString("$t");
                String videoLink = oneVideo.getJSONObject("content").getString("src");
                String videoId = videoLink.substring(26, videoLink.indexOf("?"));
                //store title and link
                Video video = new Video(videoTitle, videoId);
                //System.out.println(video.getTitle());
                //push it to the list
                videos.add(video);
                //System.out.println(videoTitle+"***"+videoLink);
                
                
            }
            
            
            //check if there are more videos
            //String result = getNextApi();
            //System.out.println("Result: "+result);

        } catch (JSONException ex) {  
            // 异常处理代码  
            ex.printStackTrace();
        }  
        
        
        return videos;      
    }
    
    public void isFullId(String link){

        
        
    }
    
    public String getNextApi() throws JSONException{
        JSONArray link = feed.getJSONArray("link");
        for(int i=0;i<link.length();i++){
            JSONObject jo = link.getJSONObject(i);
            //System.out.println(jo.getString("rel"));
            if(jo.getString("rel").equals("next")){
                //there are more videos in this playlist
                String nextUrl = jo.getString("href");
                return nextUrl;
            }
        }
        
        return null;
        
    }
}