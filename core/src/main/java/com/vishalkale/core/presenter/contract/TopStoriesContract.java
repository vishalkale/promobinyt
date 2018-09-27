package com.vishalkale.core.presenter.contract;

import com.vishalkale.core.model.data.TopStory;

import java.util.List;

public interface TopStoriesContract {

    interface View {
        void showProgress(boolean show);

        void showError(int msgId);

        void onLoadStories(List<TopStory> results);
    }

    interface ViewActions {
        void loadTopStories();
    }
}