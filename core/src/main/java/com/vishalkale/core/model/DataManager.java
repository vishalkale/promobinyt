package com.vishalkale.core.model;

import android.content.Context;

import com.vishalkale.core.model.data.TopStory;
import com.vishalkale.core.model.data.TopStoryResponse;
import com.vishalkale.core.model.network.NytApiService;
import com.vishalkale.core.model.network.NytApiServiceFactory;

import java.util.List;

import retrofit2.Callback;

public class DataManager {

    private NytApiService nytApiService;
    private Context mContext;

    public DataManager(Context context) {
        nytApiService = NytApiServiceFactory.makeNytApiService();
        mContext = context;
    }


    public void getTopStories( Callback<TopStoryResponse> callback) {
        nytApiService.getTopStories().enqueue(callback);
    }

}