package com.moongoal.towersfacebook;

import org.json.JSONException;
import org.json.JSONObject;

import com.admob.android.ads.AdManager;
import com.admob.android.ads.AdView;
import com.facebook.android.AsyncFacebookRunner;
import com.moongoal.towersfacebook.TowersFacebook;
import com.moongoal.towersfacebook.BaseDialogListener;
import com.moongoal.towersfacebook.BaseRequestListener;
import com.moongoal.towersfacebook.TowersFacebook;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.R;
import com.moongoal.towersfacebook.SessionEvents;
import com.moongoal.towersfacebook.SessionStore;
import com.facebook.android.Util;
import com.moongoal.towersfacebook.SessionEvents.LogoutListener;
import com.moongoal.towersfacebook.ScoreResults.SampleAuthListener;
import com.moongoal.towersfacebook.ScoreResults.SampleLogoutListener;
import com.moongoal.towersfacebook.SessionEvents.AuthListener;
import com.moongoal.towersfacebook.LoginButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScoreResults extends Activity {
	TextView textScore;
    private LoginButton mLoginButton;
    private Button mPostButton, mDeleteButton, mPlayAgainButton;
    private TextView mText;
    public static final String APP_ID = "178978105455214";
    public int score;
    
    private Facebook mFacebook;
    private AsyncFacebookRunner mAsyncRunner;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		
        if (APP_ID == null) {
            Util.showAlert(this, "Warning", "Facebook Applicaton ID must be " +
                    "specified before running this example: see Example.java");
        }
        
        mLoginButton = (LoginButton) findViewById(R.id.login);
        mPostButton = (Button) findViewById(R.id.postButton);
        mText = (TextView) findViewById(R.id.txt);
        mPlayAgainButton = (Button) findViewById(R.id.playAgainButton);

        
       	mFacebook = new Facebook(APP_ID);
      	mAsyncRunner = new AsyncFacebookRunner(mFacebook);
       	
        SessionStore.restore(mFacebook, this);
        SessionEvents.addAuthListener(new SampleAuthListener());
        SessionEvents.addLogoutListener(new SampleLogoutListener());
        mLoginButton.init(this, mFacebook);

        mPostButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Bundle parameters = new Bundle();
                String message = "I just played Towers Puzzle Game and my score was " + score + ". Can you beat me?  ";
                parameters.putString("message", message);

                mFacebook.dialog(ScoreResults.this, "stream.publish", parameters,
                        new SampleDialogListener());
            }
        });
        mPostButton.setVisibility(mFacebook.isSessionValid() ?
                View.VISIBLE :
                View.INVISIBLE);
        
		// get the intent
		Intent i = getIntent();
		score = i.getIntExtra("score", 0);

        mPlayAgainButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        		Intent myIntent = new Intent(ScoreResults.this, TowersFacebook.class);
        		startActivity(myIntent);
            }
        });

		
		// get the the text view and update it
		textScore = (TextView)findViewById(R.id.scoreTextResult);
		textScore.setText(String.valueOf(score));
		
		AdManager.setTestDevices( new String[] {
				AdManager.TEST_EMULATOR, // Android emulator
				"24D0C878854B54A8DD2DE80CA1540DC1", // My Nexus One Test Phone
				} );
		
		AdView adView = (AdView)findViewById(R.id.ad);
		adView.requestFreshAd();
		
		AdView adView1 = (AdView)findViewById(R.id.ad1);
		adView1.requestFreshAd();
		
		AdView adView2 = (AdView)findViewById(R.id.ad2);
		adView2.requestFreshAd();
		
		AdView adView3 = (AdView)findViewById(R.id.ad3);
		adView3.requestFreshAd();
		
		

	}

	public class SampleAuthListener implements AuthListener {

	    public void onAuthSucceed() {
	        mText.setText("You have logged in! ");
	        //mRequestButton.setVisibility(View.VISIBLE);
	        //mUploadButton.setVisibility(View.VISIBLE);
	        mPostButton.setVisibility(View.VISIBLE);
	    }

	    public void onAuthFail(String error) {
	        mText.setText("Login Failed: " + error);
	    }
	}
	
    public class SampleLogoutListener implements LogoutListener {
        public void onLogoutBegin() {
            mText.setText("Logging out...");
        }

        public void onLogoutFinish() {
            mText.setText("You have logged out! ");
            //mRequestButton.setVisibility(View.INVISIBLE);
            //mUploadButton.setVisibility(View.INVISIBLE);
            mPostButton.setVisibility(View.INVISIBLE);
        }
    }
    
    public class WallPostRequestListener extends BaseRequestListener {

        public void onComplete(final String response) {
            Log.d("Facebook-Example", "Got response: " + response);
            String message = "<empty>";
            //String message = "I just played Towers Puzzle Game and my score was " + score + " Can you beat me?";
            try {
                JSONObject json = Util.parseJson(response);
                message = json.getString("message");
                
            } catch (JSONException e) {
                Log.w("Facebook-Example", "JSON Error in response");
            } catch (FacebookError e) {
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
            }
            final String text = "Your Wall Post: " + message;
            ScoreResults.this.runOnUiThread(new Runnable() {
                public void run() {
                    mText.setText(text);
                }
            });
        }
    }
    
    public class WallPostDeleteListener extends BaseRequestListener {

        public void onComplete(final String response) {
            if (response.equals("true")) {
                Log.d("Facebook-Example", "Successfully deleted wall post");
                ScoreResults.this.runOnUiThread(new Runnable() {
                    public void run() {
                        mDeleteButton.setVisibility(View.INVISIBLE);
                        mText.setText("Deleted Wall Post");
                    }
                });
            } else {
                Log.d("Facebook-Example", "Could not delete wall post");
            }
        }
    }
    
    public class SampleDialogListener extends BaseDialogListener {

        public void onComplete(Bundle values) {
            final String postId = values.getString("post_id");
            if (postId != null) {
                Log.d("Facebook-Example", "Dialog Success! post_id=" + postId);
                mAsyncRunner.request(postId, new WallPostRequestListener());
                //mDeleteButton.setOnClickListener(new OnClickListener() {
                //    public void onClick(View v) {
                //        mAsyncRunner.request(postId, new Bundle(), "DELETE",
                //                new WallPostDeleteListener());
                //    }
                //});
                //mDeleteButton.setVisibility(View.VISIBLE);
            } else {
                Log.d("Facebook-Example", "No wall post made");
            }
        }
    }    


    

	
}

