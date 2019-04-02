package com.forone.watermark.photo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * @author xuxu.wang01
 * @time 2019-04-02 15:34
 * @email chao.zhang12@ucarinc.com
 * @tel 2790
 * @desc
 */
public class PhotoListAdapter extends RecyclerView.Adapter {

    private List<PhotoBean> mPhotoList;

    private IPhotoItemClickListener mPhotoItemClickListener;

    public PhotoListAdapter(List<PhotoBean> mPhotoList) {
        this.mPhotoList = mPhotoList;
    }

    public void addPhotoList(List<PhotoBean> photos) {
        mPhotoList.addAll(photos);
        notifyDataSetChanged();
    }

    public void setOnItemClick(IPhotoItemClickListener photoItemClickListener){
        this.mPhotoItemClickListener = photoItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("testxxx", "onCreateViewHolder() index = " + i);
        ImageView imageView = new ImageView(viewGroup.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
        PhotoViewHolder photoViewHolder = new PhotoViewHolder(imageView);
        photoViewHolder.setOnItemClick(mPhotoItemClickListener);
        return photoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("testxxx", "onBindViewHolder() index = " + i);
        ((PhotoViewHolder) viewHolder).updateView(mPhotoList.get(i));
    }

    @Override
    public int getItemCount() {
        return null == mPhotoList ? 0 : mPhotoList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mPhoto;

        IPhotoItemClickListener mPhotoItemClickListener;
        PhotoBean mBean;
        public PhotoViewHolder(ImageView imageView) {
            super(imageView);
            mPhoto = imageView;
            mPhoto.setOnClickListener(this);
        }

        public void setOnItemClick(IPhotoItemClickListener photoItemClickListener){
            this.mPhotoItemClickListener = photoItemClickListener;
        }

        public void updateView(PhotoBean photoBean) {
            mBean = photoBean;
            Glide.with(mPhoto.getContext()).load(new File(photoBean._data)).into(mPhoto);
        }

        @Override
        public void onClick(View v) {
            if (null!= mPhotoItemClickListener){
                mPhotoItemClickListener.onItemClicked(mBean);
            }
        }
    }

    interface IPhotoItemClickListener {
        void onItemClicked(PhotoBean photoBean);
    }
}
