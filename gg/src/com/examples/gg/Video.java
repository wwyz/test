package com.examples.gg;



import android.os.Parcel;
import android.os.Parcelable;


public class Video implements Parcelable
{

    public String title;
    public String videoId;
    public String videoDesc;
    public String thumbnailUrl;
    public String author;
    public String uploaderThumUrl;
    public String getUploaderThumUrl() {
		return uploaderThumUrl;
	}

	public void setUploaderThumUrl(String uploaderThumUrl) {
		this.uploaderThumUrl = uploaderThumUrl;
	}

	public String playlistUrl;
    public String updateTime;
    
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPlaylistUrl() {
		return playlistUrl;
	}

	public void setPlaylistUrl(String playlistUrl) {
		this.playlistUrl = playlistUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Video(){

    }
	
	public Video(Parcel in){
		title = in.readString ();
		videoId = in.readString();
		videoDesc = in.readString();
		thumbnailUrl = in.readString();
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
    
    public String getVideoDesc() {
		return videoDesc;
	}


	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}


	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
    public static final Parcelable.Creator<Video> CREATOR
    = new Parcelable.Creator<Video>() 
   {
         public Video createFromParcel(Parcel in) 
         {
             return new Video(in);
         }

         public Video[] newArray (int size) 
         {
             return new Video[size];
         }
    };

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		// TODO Auto-generated method stub
		dest.writeString(title);
		dest.writeString(videoId);
		dest.writeString(videoDesc);
		dest.writeString(thumbnailUrl);
	}
}
