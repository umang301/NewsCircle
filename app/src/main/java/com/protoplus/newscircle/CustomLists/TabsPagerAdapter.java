package com.protoplus.newscircle.CustomLists;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.Util.Helper;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
	CharSequence mTitles[];
	int mNumbOfTabsumb;
	String className;
	String URL[];
	int cid;
	String category;
	public TabsPagerAdapter(FragmentManager fm ,CharSequence mTitles[], int mNumbOfTabsumb,String className,int cid,String category,String... URL) {
		super(fm);
		this.category = category;
		this.cid = cid;
		this.URL = URL;
		this.className=className;
		this.mTitles=mTitles;
		this.mNumbOfTabsumb=mNumbOfTabsumb;
	}
	@Override
	public Fragment getItem(int index) {
		Helper.setCurrentURL(URL[index]);
		Helper.setCid(this.cid);
		Helper.setCategory(category);
		ContextData.setPosition(index);
		try {
			Class s=Class.forName(className);
			Helper.isAppOpen = true;
			return (Fragment)s.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}

	@Override
	public int getCount() {
		return mNumbOfTabsumb;
	}

}
