package com.moongoal.towersfacebook;

import com.admob.android.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Instructions extends Activity {
	TextView textInstructions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instructions);
		
	
		// get the the text view and update it
		//textInstructions = (TextView)findViewById(R.id.instructions);
		//textInstructions.setText(about);
		
		AdView adView = (AdView)findViewById(R.id.ad4);
		adView.requestFreshAd();
		
		AdView adView1 = (AdView)findViewById(R.id.ad5);
		adView1.requestFreshAd();
		
		AdView adView2 = (AdView)findViewById(R.id.ad6);
		adView2.requestFreshAd();
		
		AdView adView3 = (AdView)findViewById(R.id.ad7);
		adView3.requestFreshAd();
	}
}