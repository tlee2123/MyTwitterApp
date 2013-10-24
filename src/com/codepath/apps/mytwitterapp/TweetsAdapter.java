package com.codepath.apps.mytwitterapp;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mytwitterapp.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsAdapter extends ArrayAdapter<Tweet> {
  
  private Long maxId = 0L;
  private Long sinceId = 0L;

  public TweetsAdapter(Context context, List<Tweet> tweets) {
    super(context, 0, tweets);
  }
  
  public Long getMaxId() {
    return this.maxId;
  }
  
  public Long getSinceId() {
    return this.sinceId;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    View view = convertView;
    if (view == null) {
      LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.tweet_item, null);
      
      holder = new ViewHolder();
      holder.view = (ImageView)view.findViewById(R.id.lvProfile);
      holder.tvTime = (TextView)view.findViewById(R.id.tvTime);
      holder.tvName = (TextView)view.findViewById(R.id.tvName);
      holder.tvBody = (TextView)view.findViewById(R.id.tvBody);
      view.setTag(holder);
    } else {
      holder = (ViewHolder)view.getTag();
    }
    
    Tweet tweet = getItem(position);
    if (position == 0) {
      this.maxId = tweet.getId() - 1;
      this.sinceId = tweet.getId();
    }
    
    if (position > 0) {
      this.sinceId = tweet.getId();
    }
    
    Log.i(String.valueOf(tweet.getId()), String.valueOf(position));
    //Log.i("TWEET_ID", String.valueOf(tweet.getId()));
    //Log.i("MAX_ID", String.valueOf(this.maxId));
    //Log.i("SINCE_ID", String.valueOf(this.sinceId));
    
    ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), holder.view);
    
    holder.tvTime.setText(tweet.getCreationTime());
    
    String formattedName = "<b>" + tweet.getUser().getName() + "</b>" +
        " <small><font color='#777777'>@" + tweet.getUser().getScreenName() + "</font></small>";
    holder.tvName.setText(Html.fromHtml(formattedName));
    
    holder.tvBody.setText(Html.fromHtml(tweet.getBody()));
    
    return view;
  }
  
  static class ViewHolder {
    ImageView view;
    TextView tvTime;
    TextView tvName;
    TextView tvBody;
  }
}
