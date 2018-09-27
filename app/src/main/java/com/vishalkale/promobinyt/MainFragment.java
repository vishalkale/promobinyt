package com.vishalkale.promobinyt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.vishalkale.core.model.DataManager;
import com.vishalkale.core.model.data.TopStory;
import com.vishalkale.core.presenter.TopStoriesPresenter;
import com.vishalkale.core.presenter.contract.TopStoriesContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements
        TopStoriesContract.View, TopStoryAdapter.OnItemClickListener {

    @BindView(R.id.loading_progress_view)
    ProgressBar loadingProgressView;
    @BindView(R.id.rv_top_stories)
    RecyclerView recyclerView;
    @BindView(R.id.no_top_stories)
    ViewGroup noTopStories;
    List<TopStory> topStories;
    private TopStoriesPresenter topStoriesPresenter;
    private TopStoryAdapter topStoryAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topStoriesPresenter = new TopStoriesPresenter(new DataManager(getContext()));
        topStories = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        topStoriesPresenter.attachView(this);
        init();
        topStoriesPresenter.loadTopStories();
        return view;
    }

    private void init() {
        topStoryAdapter = new TopStoryAdapter(Glide.with(this),topStories);
        topStoryAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(topStoryAdapter);
    }

    @Override
    public void onLoadStories(List<TopStory> list) {
        recyclerView.setVisibility(View.VISIBLE);
        noTopStories.setVisibility(View.GONE);
        topStories.clear();
        topStories.addAll(list);
        topStoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            loadingProgressView.setVisibility(View.VISIBLE);
        } else {
            loadingProgressView.setVisibility(View.GONE);
        }

    }

    @Override
    public void showError(int msgId) {
        loadingProgressView.setVisibility(View.GONE);
        Snackbar.make(recyclerView,getString(msgId),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(int position) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, DetailFragment.newInstance(topStories.get(position).url))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        topStoriesPresenter.detachView();
        super.onDestroy();
    }

}

