package com.jy.jgcjj;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.util.Utils;
import com.jgcj.library.view.NoScrollViewPager;
import com.jgcj.library.view.ViewPagerIndicator;
import com.jy.jgcjj.base.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * */
public class NewsActivity extends BaseAppCompatActivity {

	// ViewPager
	@BindView(R.id.view_pager)
	NoScrollViewPager viewPager;
	private FragmentPagerAdapter pagerAdapter;

	// ViewPagerIndicator
	@BindView(R.id.indicator)
	ViewPagerIndicator viewPagerIndicator;
	private List<String> titles = Arrays.asList("导师析盘", "战绩回顾", "股轩资讯", "股轩大学堂");

	// Fragment
	private List<Fragment> fragments = new ArrayList<>();

	@Override
	protected int setLayoutResourceId() {
		return R.layout.activity_news;
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void setUpView() {
		Utils.initCommonTitle(this,"头条新闻",true);

		viewPagerIndicator.setTabItemTitles(titles);
		viewPagerIndicator.setVisibleTabCount(4);

		// 导师盘析 Fragment
		fragments.add(new FragmentNewsAnalysis());
		// 战绩回顾 Fragment
		fragments.add(new FragmentNewsRecord());
		// 股轩资讯 Fragment
		fragments.add(new FragmentNewsInformation());
		// 股轩大学堂 Fragment
		fragments.add(new FragmentNewsUniversity());

		// PagerAdapter
		pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				Log.d("",""+fragments.size());
				return fragments.size();
			}

			@Override
			public Fragment getItem(int position) {
				Log.d("",""+fragments.size());
				return fragments.get(position);
			}
		};

		// 设置数据适配器
		viewPager.setAdapter(pagerAdapter);
		viewPagerIndicator.setViewPager(viewPager, 0);
	}

	@Override
	public void onResume() {
		super.onResume();
		int position = SPUtils.getInstance(GlobalVariables.serverSp).getInt(GlobalVariables.INTENT_NEWS_SIGN);
		viewPagerIndicator.setViewPager(viewPager, position);
	}
}