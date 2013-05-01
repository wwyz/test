package com.example.gg;


public class Video
{
    public static String playlistTitle;
    





    public String title;
    public String url;
    
    
    
    public Video(String videoTitle, String videoUrl){
        title = videoTitle;
        url = videoUrl;
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



    
    public String getUrl()
    {
    
        return url;
    }



    
    public void setUrl(String url)
    {
    
        this.url = url;
    }
}
