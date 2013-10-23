
package com.codepath.apps.mytwitterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ComposeActivity extends Activity {
  
  EditText etTweet;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_compose);
    
    etTweet = (EditText)findViewById(R.id.editText1);
    Button submitButton = (Button)findViewById(R.id.button1);
    submitButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO Auto-generated method stub
        //Toast.makeText(getBaseContext(), etTweet.getText().toString(), Toast.LENGTH_LONG).show();
        RequestParams params = new RequestParams("status", etTweet.getText().toString());
        MyTwitterApp.getRestClient().postTweet(params, new AsyncHttpResponseHandler() {
          @Override
          public void onSuccess(String response) {
            Log.i(getApplicationContext().toString(), response);
            Intent intent = new Intent(getBaseContext(), TimelineActivity.class);
            startActivity(intent);
          }
        });
      }
      
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.compose, menu);
    return true;
  }

}
