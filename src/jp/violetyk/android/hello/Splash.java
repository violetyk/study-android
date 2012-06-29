package jp.violetyk.android.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Splash extends Activity {
	private final int SPLASH_DISPLAY_LENGTH = 3000;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
        // タイトルを無くす設定
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent himekuriIntent = new Intent(Splash.this, HelloAndroidActivity.class);
				Splash.this.startActivity(himekuriIntent);
				// アクティビティの移動時のアニメーション効果。
				overridePendingTransition(R.anim.fadein, R.anim.fadeout);
				Splash.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

}
