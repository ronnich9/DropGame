package com.fut.log;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler {

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;

	private Drop drop;
	private Thread thread;
	protected Handler handler;
	protected View gameView;
	protected AdView adView;



	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what) {
					case SHOW_ADS:
					{
						adView.setVisibility(View.VISIBLE);
						break;
					}
					case HIDE_ADS:
					{
						adView.setVisibility(View.GONE);
						break;
					}
				}
			}
		};


		RelativeLayout layout = new RelativeLayout(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useImmersiveMode = true;




//		gsm = new GameStateManager(this);
		drop = new Drop(this);
		thread = new Thread(drop);
		thread.start();
		thread.setPriority(Thread.MAX_PRIORITY);

		View gameView = initializeForView(drop, config);
		layout.addView(gameView);



		adView = new AdView(this);

		AdSize adsize = new AdSize(900, 750);
		adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
		adView.setAdUnitId("ca-app-pub-7413250512006579/1122249211");

		AdRequest.Builder builder = new AdRequest.Builder();
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		adParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout.addView(adView, adParams);
		adView.loadAd(builder.build());

		setContentView(layout);






	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}
}
