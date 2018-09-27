package com.vishalkale.promobinyt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

public class DetailFragment extends Fragment {

    private static final String URL_ARG = "url";
    @BindView(R.id.webview)
    WebView webView;
    private String url;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(URL_ARG, url);
        DetailFragment fragment =new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if (args != null) {
            url= args.getString(URL_ARG);
        }
        webView.loadUrl(url);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

