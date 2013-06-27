package com.examples.gg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.gg.Swipe.DummySectionFragment;
import com.viewpagerindicator.TabPageIndicator;

public class SampleTabsStyled extends FragmentActivity {
    private static final String[] CONTENT = new String[] {"Playlists", "Videos", "Live"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_tabs);

        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
			switch (position) {
			case 0:
				Fragment byPlaylist = new Youtube();
				Bundle pArgs = new Bundle();
				pArgs.putString("SECTION", "PLAYLIST");
				byPlaylist.setArguments(pArgs);
				return byPlaylist;
			case 1:
				Fragment byUploader = new Youtube();
				Bundle uArgs = new Bundle();
				uArgs.putString("SECTION", "UPLOADER");
				byUploader.setArguments(uArgs);
				return byUploader;
			case 2:
				Fragment fragment2 = new DummySectionFragment();
				Bundle args2 = new Bundle();
				args2.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment2.setArguments(args2);
				return fragment2;
			}
			//System.out.println("Position: " + position);
			return null;
        	


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
    
    
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
}
