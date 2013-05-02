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

    private String api;
    private JSONObject feed;
    
    


    public YoutubeFeed(String url) throws JSONException{

       setApi(url);
       
       String youtubeFeed = readYoutubeFeed(api);
       JSONTokener jsonParser = new JSONTokener(youtubeFeed);  
       
       // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。  
       // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）  
       JSONObject wholeJson = (JSONObject) jsonParser.nextValue();  
       // 接下来的就是JSON对象的操作了  
       
       JSONObject feed = wholeJson.getJSONObject("feed");
       
       setFeed(feed);
       
       
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
                //store title and link
                Video video = new Video(videoTitle, videoLink);
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
    
    public String readYoutubeFeed(String url) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
          HttpResponse response = client.execute(httpGet);
          StatusLine statusLine = response.getStatusLine();
          int statusCode = statusLine.getStatusCode();
          if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
              builder.append(line);
            }
          } else {
            Log.e(YoutubeFeed.class.toString(), "Failed to download file");
          }
        } catch (ClientProtocolException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return builder.toString();
      }

    public String getApi()
    {

        return api;
    }

    public void setApi(String wholeApi)
    {

        this.api = wholeApi;
    }
    
    public JSONObject getFeed()
    {
    
        return feed;
    }

    
    public void setFeed(JSONObject feed)
    {
        this.feed = feed;

    }
}
