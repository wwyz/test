package com.examples.gg;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayer extends YouTubeBaseActivity 
 implements YouTubePlayer.OnInitializedListener,  YouTubePlayer.OnFullscreenListener{

 private YouTubePlayerView ytpv;
 private YouTubePlayer ytp;
 private EditText et;
 private String video;
 private boolean isfullscreen;
 
 private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
	      ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
	      : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  System.out.println("Inside VideoPlayer");
  
  setContentView(R.layout.videoplayer);
  
	Intent intent = getIntent();
	video = intent.getStringExtra("video");
  
  ytpv = (YouTubePlayerView) findViewById(R.id.youtubeplayer);
  ytpv.initialize("AIzaSyAuEa3bIKbSYiXVWbHU_zueVzEBv9p2r_Y", this);
  
 }

 @Override
 public void onInitializationFailure(Provider arg0,YouTubeInitializationResult arg1) {
  Toast.makeText(this, arg1.toString(), Toast.LENGTH_LONG).show();
 }

 @Override
 public void onInitializationSuccess(Provider provider, YouTubePlayer player,boolean wasRestored) {
  ytp = player;
  Toast.makeText(this, "Initialization  Success", Toast.LENGTH_LONG).show();
  ytp.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
  ytp.setOnFullscreenListener(this);
  if (!wasRestored) {
      player.cueVideo(video);
  }
 }
 
 @Override
 public void onFullscreen(boolean isFullscreen) {
	 System.out.println("change!!!!!!!!!!!!!!!!!!!!!!!!" + isFullscreen);
	 isfullscreen = isFullscreen;
	 if (isfullscreen) setRequestedOrientation(LANDSCAPE_ORIENTATION);
	 else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 }
 
//Check screen orientation or screen rotate event here
 @Override
 public void onConfigurationChanged(Configuration newConfig) {
     super.onConfigurationChanged(newConfig);

     if (isfullscreen){
     // Checks the orientation of the screen for landscape and portrait and set portrait mode always
    	 	 System.out.println("FULL!!!!!!!!!!!!!!!!!!!!!!!!");
    		 setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
     }else  System.out.println("NOT FULL!!!!!!!!!!!!!!!!!!!!!!!!");
 }
 
}