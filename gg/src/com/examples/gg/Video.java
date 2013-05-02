package com.examples.gg;


public class Video
{
    public static String playlistTitle;
    





    public String title;
    public String videoId;
    
    
    
    public Video(String videoTitle, String videoUrl){
        title = videoTitle;
        videoId = videoUrl;
    }


    public static String getPlaylistTitle()
    {
    
        return playlistTitle;
    }




    
    public static void setPlaylistTitle(String playlistTitle)
    {
    
        Video.playlistTitle = playlistTitle;
    }
    
    
    public String getTitle()
    {
    
        return title;
    }



    
    public void setTitle(String title)
    {
    
        this.title = title;
    }



    
    public String getVideoId()
    {
    
        return videoId;
    }



    
    public void setVideoId(String vid)
    {
    
        this.videoId = vid;
    }
}
