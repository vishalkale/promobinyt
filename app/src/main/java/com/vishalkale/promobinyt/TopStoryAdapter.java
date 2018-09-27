package com.vishalkale.promobinyt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.vishalkale.core.model.data.Multimedia;
import com.vishalkale.core.model.data.TopStory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final RequestManager mGlide;
    private OnItemClickListener onItemClickListener;
    private List<TopStory> mDataset;

    TopStoryAdapter(RequestManager glide, List<TopStory> dataset) {
        mDataset = dataset;
        mGlide=glide;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_top_story, parent, false);
        return new TopStoryViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TopStoryViewHolder topStoryViewHolder = (TopStoryViewHolder) holder;
        topStoryViewHolder.titleText.setText(mDataset.get(position).title);
        topStoryViewHolder.abstractText.setText(mDataset.get(position)._abstract);
        topStoryViewHolder.articleImage.setVisibility(View.GONE);
        for(Multimedia media : mDataset.get(position).multimedia)
            if(media.format.equalsIgnoreCase("mediumThreeByTwo210")){
                topStoryViewHolder.articleImage.setVisibility(View.VISIBLE);
                mGlide.load(media.url).into(
                        topStoryViewHolder.articleImage);
                break;
            }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class TopStoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener onItemClickListener;

        @BindView(R.id.txt_abstract)
        TextView abstractText;
        @BindView(R.id.txt_title)
        TextView titleText;
        @BindView(R.id.img_article)
        ImageView articleImage;

        TopStoryViewHolder(View v, OnItemClickListener listener) {
            super(v);
            this.onItemClickListener = listener;
            v.setOnClickListener(this);
            ButterKnife.bind(this,v);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}