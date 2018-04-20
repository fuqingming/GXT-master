package com.jy.jgcjj;

import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.jy.jgcjj.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 付庆明
 * */
public class FragmentLive extends BaseFragment {
	Unbinder unbinder;
	private FragmentTabHost m_tabHost = null;

	// 各tab页对应的fragment
	private Class<?> m_fragmentArray[] = {FragmentLiveVideo.class,
										  FragmentLiveTrain.class};

	@Override
	protected int setLayoutResourceID() {
		return R.layout.fragment_live_yjy;
	}

	@Override
	protected void init() {
		super.init();
		unbinder = ButterKnife.bind(this, getContentView());
	}

	@Override
	protected void setUpView() {
		super.setUpView();

		initView(getContentView());
	}

	/**
	 * 初始化组件
	 */
	private void initView(View vContent)
	{
		m_tabHost = vContent.findViewById(android.R.id.tabhost);
		m_tabHost.setup(getMContext(), getChildFragmentManager(), R.id.realtabcontent);
		m_tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
		for (int i=0; i<m_fragmentArray.length; i++)
		{
			View vTab = null;
			if(i == 0)
			{
				vTab = getMLayoutInflater().inflate(R.layout.live_top_tab_left_item, null);
				TextView tvText = vTab.findViewById(R.id.tv_text);
				tvText.setText("直播");
			}
			else
			{
				vTab = getMLayoutInflater().inflate(R.layout.live_top_tab_right_item, null);
				TextView tvText = vTab.findViewById(R.id.tv_text);
				tvText.setText("解盘室");
			}

			// 给每个Tab按钮文字和内容
			TabHost.TabSpec tabSpec = m_tabHost.newTabSpec(String.valueOf(i)).setIndicator(vTab);

			// 将Tab按钮添加进Tab选项卡中
			m_tabHost.addTab(tabSpec, m_fragmentArray[i], getArguments());
		}
	}

	@Override
	public void onResume() {
		super.onResume();
//		if(SPUtils.getInstance(GlobalVariables.serverSp).getInt(GlobalVariables.serverClassSelected) == GlobalVariables.CLASS_OPEN){
//			m_tabHost.setCurrentTab(GlobalVariables.CLASS_OPEN);
//		}else if(SPUtils.getInstance(GlobalVariables.serverSp).getInt(GlobalVariables.serverClassSelected) == GlobalVariables.CLASS_ADVANCED){
//			m_tabHost.setCurrentTab(GlobalVariables.CLASS_ADVANCED);
//		}else{
//			m_tabHost.setCurrentTab(GlobalVariables.CLASS_OPEN);
//		}
	}

	public void setCurrentTab(Class<?> fragmentClass)
	{
		m_tabHost.setCurrentTabByTag(fragmentClass.getName());
	}

	@Subscribe
	public void onEvent(int data) {
		m_tabHost.setCurrentTab(data);
	}
}