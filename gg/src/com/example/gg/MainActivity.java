package com.example.gg;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
	ImageButton imageButton;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addListenerOnButton();
        
        
//        Intent playIntent = new Intent(Intent.ACTION_VIEW);
//        playIntent.setData(Uri.parse("http://www.youtube.com/watch?v=sMM0R19IisI"));
//        playIntent.putExtra("force_fullscreen", true);
//        
//        
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=sMM0R19IisI"));
//        intent.putExtra("force_fullscreen",true); 
//        startActivity(intent);
       // Intent intent = new Intent(this, MainActivity.class);


        //MainActivity.this.startActivity(playIntent);
    }


	public void addListenerOnButton() {
		 
		imageButton = (ImageButton) findViewById(R.id.imageButton1);
 
		imageButton.setOnClickListener(new OnClickListener() {
 
		
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,"ImageButton is clicked!", Toast.LENGTH_SHORT).show();
			 				
			}
 
		});
 
	}
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
