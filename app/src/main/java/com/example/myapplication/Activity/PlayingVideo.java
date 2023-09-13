package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.example.myapplication.Model.DataObjectUrlVideo;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.API;
import com.example.myapplication.Retrofit.RetrofitClient;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayingVideo extends AppCompatActivity {
    ExoPlayer player;
    private  static  final  String SHARED_PREF_NAME = "mypref";

    private  static  final  String KEY_USERID = "userId";

    private  static  final  String KEY_PROFILEID = "profileId";

    private  static  final  String KEY_ACCESSTOKEN ="accessToken";

//    String UrlVideo = "https://vod-zlr2.tv360.vn/video1/2019/03/08/11/f1c03aae/f1c03aae-5151-4afd-b5f7-9dd1b55eabf1_m.m3u8?timestamp=1694569440&token=b1c2f8d5b853f55cfba19480e9161f3f&uid=3817362093";

    API apiserver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_video);
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String userID = sharedPref.getString(KEY_USERID,"");
        String profileID = sharedPref.getString(KEY_PROFILEID,"");
        String accessToken = sharedPref.getString(KEY_ACCESSTOKEN,"");
        String m_andoid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        apiserver = RetrofitClient.getlink(profileID,userID, m_andoid,"Bearer " + accessToken).create(API.class);
        Call<DataObjectUrlVideo> data = apiserver.getlinka(getIntent().getStringExtra("id"), getIntent().getStringExtra("type"));
        Log.d("TAG : " + getIntent().getStringExtra("id"),"id");

        Log.d("TAG : " + getIntent().getStringExtra("type"),"type");

        data.enqueue(new Callback<DataObjectUrlVideo>() {
            @Override
            public void onResponse(Call<DataObjectUrlVideo> call, Response<DataObjectUrlVideo> response) {
                DataObjectUrlVideo urlVideo = response.body();
                Log.d("TAG : " + response.body(),"ok");
                StyledPlayerView styledPlayerView = findViewById(R.id.playvideo);
                player = new ExoPlayer.Builder(PlayingVideo.this).build();
                styledPlayerView.setPlayer(player);
                MediaItem mediaItem = MediaItem.fromUri(urlVideo.getData().getUrlStreaming());
                player.setMediaItem(mediaItem);
                player.prepare();
                player.setPlayWhenReady(true);
            }

            @Override
            public void onFailure(Call<DataObjectUrlVideo> call, Throwable t) {
                call.cancel();
            }
        });
    }
}