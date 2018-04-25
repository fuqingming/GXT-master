package com.jy.jryjy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jgcj.library.backhandler.OnBackPressedInterface;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.jy.jryjy.base.BaseFragment;
import com.jy.jryjy.bean.response.ResponseHallBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 *
 * */
public class FragmentTrade extends BaseFragment  implements OnBackPressedInterface {

	Unbinder unbinder;

	@BindView(R.id.webview)
	WebView m_viewWeb;
	@BindView(R.id.progressbar)
	ProgressBar progressBar;


	@Override
	protected int setLayoutResourceID() {
		return R.layout.fragment_trade;
	}

	@Override
	protected void init() {
		super.init();
		unbinder = ButterKnife.bind(this, getContentView());
	}

	@Override
	protected void setUpView() {
		Utils.initCommonTitle(getContentView(),"交易");
		m_viewWeb.loadUrl("http://gxt.mqcll.cn/index/User/transaction");//加载url
//		m_viewWeb.loadUrl("https://www.baidu.com/");
		m_viewWeb.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端
		m_viewWeb.setWebChromeClient(webChromeClient);
		m_viewWeb.setWebViewClient(webViewClient);

		WebSettings webSettings=m_viewWeb.getSettings();
		webSettings.setJavaScriptEnabled(true);//允许使用js

		/**
		 * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
		 * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
		 * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
		 * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
		 */
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

		//支持屏幕缩放
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
	}

	//WebViewClient主要帮助WebView处理各种通知、请求事件
	private WebViewClient webViewClient=new WebViewClient(){
		@Override
		public void onPageFinished(WebView view, String url) {//页面加载完成
			progressBar.setVisibility(View.GONE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i("ansen","拦截url:"+url);
			if(url.equals("http://www.google.com/")){
				Toast.makeText(getMContext(),"国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
				return true;//表示我已经处理过了
			}
			return super.shouldOverrideUrlLoading(view, url);
		}

	};

	//WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
	private WebChromeClient webChromeClient=new WebChromeClient(){
		//不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
		@Override
		public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
			AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
			localBuilder.setMessage(message).setPositiveButton("确定",null);
			localBuilder.setCancelable(false);
			localBuilder.create().show();

			//注意:
			//必须要这一句代码:result.confirm()表示:
			//处理结果为确定状态同时唤醒WebCore线程
			//否则不能继续点击按钮
			result.confirm();
			return true;
		}

		//获取网页标题
		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			Log.i("ansen","网页标题:"+title);
		}

		//加载进度回调
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			progressBar.setProgress(newProgress);
		}
	};

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		Log.i("ansen","是否有上一个页面:"+m_viewWeb.canGoBack());
//		if (m_viewWeb.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){//点击返回按钮的时候判断有没有上一页
//			m_viewWeb.goBack(); // goBack()表示返回webView的上一页面
//			return true;
//		}
//		return super.onKeyDown(keyCode,event);
//	}



	/**
	 * JS调用android的方法
	 * @param str
	 * @return
	 */
	@JavascriptInterface //仍然必不可少
	public void  getClient(String str){
		Log.i("ansen","html调用客户端:"+str);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		//释放资源
		m_viewWeb.destroy();
		m_viewWeb=null;
	}


	@Override
	public boolean onBackPressed() {
		if (m_viewWeb.canGoBack()){
			m_viewWeb.goBack();
			return true;
		}
		return false;
	}
}