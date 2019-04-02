package com.forone.watermark.photo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.forone.watermark.R;

import java.util.ArrayList;

/**
 * @author xuxu.wang01
 * @time 2019-04-02 15:03
 * @email chao.zhang12@ucarinc.com
 * @tel 2790
 * @desc
 */
public class PhotoSelectPageActivity extends AppCompatActivity implements PhotoListAdapter.IPhotoItemClickListener {

    private static final long MIN_SIZE = 1024* 100;

    RecyclerView mPhotoListView;

    PhotoListAdapter mPhotoListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_select_page);
        initView();
        loadPhotos();
    }

    private void initView() {
        mPhotoListAdapter = new PhotoListAdapter(new ArrayList<PhotoBean>());
        mPhotoListView = findViewById(R.id.rv_photo_list_view);
        mPhotoListView.setLayoutManager(new GridLayoutManager(this, 4));
        mPhotoListView.setAdapter(mPhotoListAdapter);
        mPhotoListAdapter.setOnItemClick(this);
    }

    private void loadPhotos() {

        String[] stringArgs = new String[]{String.valueOf(MIN_SIZE)};
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, "_size > ?", stringArgs, "datetaken DESC");
        Log.d("testxxxx", "cursor count = " + cursor.getCount());
        ArrayList<PhotoBean> photoList = new ArrayList<>();
        while (cursor.moveToNext()) {
            PhotoBean photoBean = PhotoBean.parseToBean(cursor);
            photoList.add(photoBean);
            Log.d("testxxxx", "photo bean  = " + photoBean);
        }
        mPhotoListAdapter.addPhotoList(photoList);
    }

    @Override
    public void onItemClicked(PhotoBean photoBean) {
        if (null == photoBean){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("photo", photoBean);
        setResult(1, intent);
        this.finish();
    }
}
