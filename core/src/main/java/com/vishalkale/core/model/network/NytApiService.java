package com.vishalkale.core.model.network;

import com.vishalkale.core.model.data.TopStoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NytApiService {

    @GET("home.json?api_key=927443a508bc4aa5b2d929cb1d85bb27")
    Call<TopStoryResponse> getTopStories();

}
