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
                //get a video in the playlist            // 璞侊饯璀岋蕉闇戜慷鎮撮毟锝昏溈绱嬶交锝昏彺褰瑂on璀侊拷鎮斤拷妾庡畅璎楋渐闅革交铚块杸锝帮奖璀忥蒋鑽筹拷锝革姜JSONObject锜囷焦闆庯健绺诧拷 
                // 铻倛妫¤眮锝よ瓕锝堕�锟斤蒋锝昏溈绱嬶浇鍜诧浇锝潹锝�name" : 鑾狅拷锝肩銇呰嵉锟絜xtValue锜嗭奖璀忥蒋"yuanzhifei89"锟斤拷tring锟斤拷 
                JSONObject oneVideo = playlist.getJSONObject(i);
                //get the title of this video
                String videoTitle = oneVideo.getJSONObject("title").getString("$t");
                String videoLink = oneVideo.getJSONObject("content").getString("src");
                String videoId = videoLink.substring(26, videoLink.indexOf("?"));
                String videoDesc = oneVideo.getJSONObject("media$group").getJSONObject("media$description").getString("$t");
                String thumbUrl = oneVideo.getJSONObject("media$group").getJSONArray("media$thumbnail").getJSONObject(0).getString("url");
                String updateTime = oneVideo.getJSONObject("updated").getString("$t");
                String author = oneVideo.getJSONArray("author").getJSONObject(0).getJSONObject("name").getString("$t");
                //System.out.println(thumbUrl);
                //System.out.println(videoDesc);
                //store title and link
                Video video = new Video();
                video.setTitle(videoTitle);
                video.setVideoId(videoId);
                video.setThumbnailUrl(thumbUrl);
                video.setVideoDesc(videoDesc);
                video.setUpdateTime(updateTime);
                video.setAuthor(author);
                //System.out.println(video.getTitle());
                //push it to the list
                videos.add(video);
                //System.out.println(videoTitle+"***"+videoLink);
                
                
            }
            
            
            //check if there are more videos
            //String result = getNextApi();
            //System.out.println("Result: "+result);

        } catch (JSONException ex) {  
            // 锠戙倗锝革礁铻燂拷鐐婅帀锝ｉ亹锟�
            ex.printStackTrace();
        }  
        
        
        return videos;      
    }
    
    public ArrayList<Video> getVideoPlaylist2(){
        ArrayList<Video> videos = new ArrayList<Video>();
        
        try {

            //get title of the playlist
            String plTitle = feed.getJSONObject("title").getString("$t");
            //System.out.println(plTitle);
            //get the playlist
            JSONArray playlist = feed.getJSONArray("entry");
            //System.out.println("Length: "+ playlist.length());
            
            for(int i=0;i<playlist.length();i++){
                //get a video in the playlist            
                JSONObject oneVideo = playlist.getJSONObject(i);
                //get the title of this video
                String videoTitle = oneVideo.getJSONObject("title").getString("$t");
                String videoLink = oneVideo.getJSONObject("content").getString("src");
                String videoId = videoLink.substring(26, videoLink.indexOf("?"));
                String author = oneVideo.getJSONArray("author").getJSONObject(0).getJSONObject("name").getString("$t");
                //String videoDesc = oneVideo.getJSONObject("media$group").getJSONObject("media$description").getString("$t");
                String videoDesc = oneVideo.getJSONObject("summary").getString("$t");
                String thumbUrl = oneVideo.getJSONObject("media$group").getJSONArray("media$thumbnail").getJSONObject(0).getString("url");
                String updateTime = oneVideo.getJSONObject("updated").getString("$t");
                //System.out.println(thumbUrl);
               // System.out.println(videoDesc);
                //store title and link
                Video video = new Video();
                video.setTitle(videoTitle);
                video.setVideoId(videoId);
                video.setThumbnailUrl(thumbUrl);
                video.setAuthor(author);
                video.setPlaylistUrl(videoLink + "&start-index=1&max-results=10&alt=json");
                video.setVideoDesc(videoDesc);
                video.setUpdateTime(updateTime);
                //System.out.println(video.getTitle());
                //push it to the list
                videos.add(video);
                //System.out.println(videoTitle+"***"+videoLink);
                
                
            }
            
            
            //check if there are more videos
            //String result = getNextApi();
            //System.out.println("Result: "+result);

        } catch (JSONException ex) {  
            // 锠戙倗锝革礁铻燂拷鐐婅帀锝ｉ亹锟�
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
