package com.example.gg;

import java.util.Random;

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
	
	ImageView image;
	image = (ImageView) findViewById(R.id.imageView1);
	
	int i1 = (int) (Math.random()*3);
	if(i1==1){
	image.setImageResource(R.drawable.sf);
	}else if(i1==0){
		image.setImageResource(R.drawable.xiaogou);
		
	}else {
		image.setImageResource(R.drawable.xiaohei);
		
		
	}
	new Handler().postDelayed(new Runnable(){
	@Override
		public void run(){
	
		
		
		Intent intent=new Intent(openanimation.this,ViewPagerExample.class);
		startActivity(intent);
		openanimation.this.finish();
		
	}
	}, 3000);
	
}
	
}
