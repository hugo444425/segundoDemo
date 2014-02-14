package com.example.demo2;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener{
	String country = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] arrayCountries = new String[]{
				"Brasil", "Mexico", "Colombia",
				"Argentina", "Peru", "Venezuela",
				"Chile", "Ecuador", "Guatemala", "Cuba"
		};
		ArrayList<String> countries = new ArrayList<String>(Arrays.asList(arrayCountries));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, countries);
		
		ListView list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		registerForContextMenu(list);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
			
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		 
		 };
		 
		  for (int i = 0; i < 10; i++) {
		        actionBar.addTab(
		        		actionBar.newTab()
		        				.setText("Tab " + (i + 1))
		                        .setTabListener(tabListener));
		    }
	}
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return onOptionsItemSelected(item);
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		country = ((TextView)info.targetView).getText().toString();
		getMenuInflater().inflate(R.menu.main, menu);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case R.id.action_share:
				if(!country.equals("")){
					String url = "http://es.m.wikipedia.org/wiki/"+ country;
					String msg = getString(R.string.msg_share, country, url);
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_SEND);
					intent.putExtra(Intent.EXTRA_TEXT, msg);
					intent.setType("text/plain");
					startActivity(Intent.createChooser(intent, getString(R.string.action_share)));
					
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}

	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean landscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		MenuItem share = menu.getItem(menu.size()-1);
		share.setVisible(landscape);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		country = (String) adapterView.getItemAtPosition(position);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			FragmentManager manager = getSupportFragmentManager();
			CountryInfoFragment fragment = (CountryInfoFragment) manager.findFragmentById(R.id.fragmentCountryInfo);
			fragment.loadWebViewContent(country);
		} else {
			Intent intent = new Intent(this, CountrieDetailActivity.class);
			intent.putExtra(CountrieDetailActivity.COUNTRY, country);
			startActivity(intent);
		}
	}

}
