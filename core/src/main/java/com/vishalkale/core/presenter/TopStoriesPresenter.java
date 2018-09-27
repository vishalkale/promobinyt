package com.vishalkale.core.presenter;

import com.vishalkale.core.R;
import com.vishalkale.core.model.DataManager;
import com.vishalkale.core.model.data.TopStoryResponse;
import com.vishalkale.core.presenter.contract.TopStoriesContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesPresenter extends BasePresenter<TopStoriesContract.View>
        implements TopStoriesContract.ViewActions {

    private final DataManager mDataManager;

    public TopStoriesPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void loadTopStories() {
        mView.showProgress(true);
        mDataManager.getTopStories(new Callback<TopStoryResponse>() {
            @Override
            public void onResponse(Call<TopStoryResponse> call, Response<TopStoryResponse> response) {
                if (!isViewAttached())
                    return;
                mView.showProgress(false);
                if (response.isSuccessful()) {
                    TopStoryResponse topStoryResponse
                            = response.body();
                    if (topStoryResponse != null && topStoryResponse.results != null) {
                        mView.onLoadStories(topStoryResponse.results);
                    }else{
                        mView.showError(R.string.error_network);
                    }
                } else {
                    mView.showError(R.string.error_network);
                }

            }

            @Override
            public void onFailure(Call<TopStoryResponse> call, Throwable t) {
                if (!isViewAttached())
                    return;
                mView.showProgress(false);
                mView.showError(R.string.error_network);
            }
        });
    }


//    @Override
//    public void doLogin(String username, String password) {
//        mView.showProgress(true);
//        mDataManager.login(new LoginData(username,password), new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(!isViewAttached())
//                    return;
//                mView.showProgress(false);
//                if (response.isSuccessful()) {
//                    User user = response.body();
//                    boolean userCreated = mDataManager.insertUser(user);
//                    if (userCreated) {
//                        mDataManager.insertUserPref(user);
//                        mView.onLoginSuccess();
//                    } else {
//                        mView.showError(R.string.error_login);
//                    }
//                } else {
//                    mView.showError(R.string.error_login);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                if(!isViewAttached())
//                    return;
//                mView.showProgress(false);
//                mView.showError(R.string.error_network);
//            }
//        });
//    }

}