package com.examples.gg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;
 
public class ViewPagerExample extends FragmentActivity {
    private MyAdapter mAdapter;
    private ViewPager mPager;
    Button btn1;
    Button btn2;
    Button btn3;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.navigation_1);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        
        setButton(btn1,"1",40,40);
        setButton(btn2,"",20,20);
        setButton(btn3,"",20,20);
        
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new OnPageChangeListener(){
        	 
            @Override
            public void onPageScrollStateChanged(int position) {}
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                btnAction(position);
                
            }

        });
    }
    public void startsparknow(View v){
   	 Intent intent=new Intent();
   	 intent.setClass(ViewPagerExample.this, animation.class);
   	 startActivity(intent);
   	 this.finish();
    }
    private void setButton(Button btn,String text,int h, int w){
        btn.setWidth(w);
        btn.setHeight(h);
        btn.setText(text);
    }
 
    private void btnAction(int action){
        switch(action){
          case 0: setButton(btn1,"1",40,40); setButton(btn2,"",20,20);setButton(btn3,"",20,20);break;
 
          case 1: setButton(btn2,"2",40,40); setButton(btn1,"",20,20);setButton(btn3,"",20,20); break;
          
          case 2: setButton(btn3,"3",40,40); setButton(btn1,"",20,20);setButton(btn2,"",20,20); break;
          
          
        }
    }
    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
 
        @Override
        public int getCount() {
            return 3;
        }
 
        @Override
        public Fragment getItem(int position) {
            switch (position) {
            case 0:
                return new navigation_1();
            case 1:
                return new ImageFragment(R.drawable.baihumeimei);
            case 2:
                return new ImageFragment(R.drawable.kaer);
          
            default:
                return null;
            }
        }


    }
}