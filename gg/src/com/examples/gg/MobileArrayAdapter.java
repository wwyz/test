package com.examples.gg;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;
 
	public MobileArrayAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.list_mobile, values);
		this.context = context;
		this.values = values;
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
		String s = values.get(position);
 
		System.out.println(s);
 
		if (s.equals("WoDota")) {
			imageView.setImageResource(R.drawable.wodota);
		} else if (s.equals("Top10")) {
			imageView.setImageResource(R.drawable.top10);
		} else if (s.equals("Danteng")) {
			imageView.setImageResource(R.drawable.tianti);
		} else if (s.equals("DotaCinema")){
			imageView.setImageResource(R.drawable.danteng);
		}else{
			
		}
 
		return rowView;
	}
}