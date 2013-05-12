package com.examples.gg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;


public class YoutubeFeed
{

    private JSONObject feed;
    
    


    public YoutubeFeed(String json) throws JSONException{
    	processJSON(json);
    }

    public ArrayList<Video> getVideoPlaylist(){
        ArrayList<Video> videos = new ArrayList<Video>();
        
        try {

            //get title of the playlist
            String plTitle = feed.getJSONObject("title").getString("$t");
            //System.out.println(plTitle);
            //get the playlist
            JSONArray playlist = feed.getJSONArray("entry");
            //System.out.println("Length: "+ playlist.length());
            
            for(int i=0;i<playlist.length();i++){
                //get a video in the playlist            // 豁､譌ｶ霑俶悴隸ｻ蜿紋ｻｻ菴彬son譁�悽�檎峩謗･隸ｻ蜿門ｰｱ譏ｯ荳�ｸｪJSONObject蟇ｹ雎｡縲� 
                // 螯よ棡豁､譌ｶ逧�ｯｻ蜿紋ｽ咲ｽｮ蝨ｨ"name" : 莠�ｼ碁ぅ荵�extValue蟆ｱ譏ｯ"yuanzhifei89"��tring�� 
                JSONObject oneVideo = playlist.getJSONObject(i);
                //get the title of this video
                String videoTitle = oneVideo.getJSONObject("title").getString("$t");
                String videoLink = oneVideo.getJSONObject("content").getString("src");
                String videoId = videoLink.substring(26, videoLink.indexOf("?"));
                String videoDesc = oneVideo.getJSONObject("media$group").getJSONObject("media$description").getString("$t");
                String thumbUrl = oneVideo.getJSONObject("media$group").getJSONArray("media$thumbnail").getJSONObject(0).getString("url");
                System.out.println(thumbUrl);
                System.out.println(videoDesc);
                //store title and link
                Video video = new Video();
                video.setTitle(videoTitle);
                video.setVideoId(videoId);
                video.setThumbnailUrl(thumbUrl);
                video.setVideoDesc(videoDesc);
                //System.out.println(video.getTitle());
                //push it to the list
                videos.add(video);
                //System.out.println(videoTitle+"***"+videoLink);
                
                
            }
            
            
            //check if there are more videos
            //String result = getNextApi();
            //System.out.println("Result: "+result);

        } catch (JSONException ex) {  
            // 蠑ょｸｸ螟�炊莉｣遐� 
            ex.printStackTrace();
        }  
        
        
        return videos;      
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



	public JSONObject getFeed() {
		return feed;
	}




	public void setFeed(JSONObject feed) {
		this.feed = feed;
	}
    
	
    private void processJSON(String json) throws JSONException{
        JSONTokener jsonParser = new JSONTokener(json);  
        JSONObject wholeJson = (JSONObject) jsonParser.nextValue();  
        this.feed = wholeJson.getJSONObject("feed");
    }

}
