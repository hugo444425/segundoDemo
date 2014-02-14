package com.example.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CountryInfoFragment extends Fragment {
	private WebView webView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Activity activity = getActivity();
		if(activity instanceof CountrieDetailActivity){
			String country = ((CountrieDetailActivity)getActivity()).getCountry();
			loadWebViewContent(country);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_country_info, container, false);
		webView = (WebView)view.findViewById(R.id.webView);
		return view;
	}
	
	public void loadWebViewContent(String country){
		webView.loadUrl("http://es.m.wikipedia.org/wiki/"+ country);
		webView.setWebViewClient(new WebViewClient(){
			public boolean ShouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				return false;
			}
		});
	}
	
}
