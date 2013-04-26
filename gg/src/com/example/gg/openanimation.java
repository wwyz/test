package com.example.gg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class openanimation extends Activity{

@Override
public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.open);
	new Handler().postDelayed(new Runnable(){
	@Override
		public void run(){
		ImageView image;
		image = (ImageView) findViewById(R.id.imageView1);
		image.setImageResource(R.drawable.sf);
	
		Intent intent=new Intent(openanimation.this,StartActivity.class);
		startActivity(intent);
		openanimation.this.finish();
		
	}
	}, 2000);
	
}
	
}
