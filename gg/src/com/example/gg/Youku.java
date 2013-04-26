package com.example.gg;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Youku extends Activity{
	ImageButton youtube;
	ImageButton youku;
	GridView gridView;
	 
	static final String[] MOBILE_OS = new String[] { 
		"Android", "iOS","Windows", "Blackberry" };
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youku);

        
        

		gridView = (GridView) findViewById(R.id.gridView1);
 
		gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));
 
		gridView.setOnItemClickListener(new OnItemClickListener() {
	
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
			
		}});
 
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



	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
