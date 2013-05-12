package com.examples.gg;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;
	private ArrayList<Video> videos;
 
	public VideoArrayAdapter(Context context, ArrayList<String> values, ArrayList<Video> videos) {
		super(context, R.layout.list_mobile, values);
		this.context = context;
		this.values = values;
		this.videos = videos;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values.get(position));
 
		// Change icon based on name
			
        new DownloadImage(videos.get(position).getThumbnailUrl()).execute(imageView);

 
		return rowView;
	}
	
	private class DownloadImage extends AsyncTask<Object, String, Bitmap> {
		private ImageView imageView;
		private Bitmap thumbnail = null;
		private String url = null;
		
		public DownloadImage (String url) {
			this.url = url;
		}

		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			
			 imageView = (ImageView)  params[0];
			
			 try {
		            InputStream in = (InputStream) new URL(url).getContent();
		            thumbnail = BitmapFactory.decodeStream(in);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		   
			 return thumbnail;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
            if(result != null && imageView != null){
            	imageView.setVisibility(View.VISIBLE);
            	imageView.setImageBitmap(result);
            }else{
            	imageView.setVisibility(View.GONE);
            }
        }
		
		
		
	}
}