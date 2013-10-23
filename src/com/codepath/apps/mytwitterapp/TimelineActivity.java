
package com.codepath.apps.mytwitterapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TimelineActivity extends Activity {
  
  private final static String COUNT = "25";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_timeline);
    
    RequestParams params = new RequestParams();
    params.put("count", COUNT);
    
    MyTwitterApp.getRestClient().getHomeTimeLine(params, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONArray jsonTweets) {

        ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
        
        ListView lvTweets = (ListView)findViewById(R.id.lvTweets);
        TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
        lvTweets.setAdapter(adapter);
      }
      
      public void onFailure(Throwable e, JSONObject errorResponse) {
        Log.d("DEBUG", "ERROR");
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.timeline, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_compose:
        startComposeActivity(item);
        //Toast.makeText(getBaseContext(), "Options", Toast.LENGTH_LONG).show();
        break;
      default:
        break;
    }
    return true;
  }
  
  private void startComposeActivity(MenuItem item) {
    Intent intent = new Intent(getApplicationContext(), ComposeActivity.class);
    startActivity(intent);
  }
  

}
