package com.forone.watermark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.forone.watermark.photo.PhotoBean;
import com.forone.watermark.photo.PhotoSelectPageActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String APP_FILE_PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/water_mark/";

    private ImageView mImageView;

    private Button mBtnInsert, mBtnSave, mBtnLoad;

    private EditText mEdtInput;

    private Bitmap mBitmap;

    private PhotoBean mPhotoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImageView = findViewById(R.id.img_image);
        mBtnInsert = findViewById(R.id.btn_insert_text);
        mBtnSave = findViewById(R.id.btn_save_image);
        mEdtInput = findViewById(R.id.edt_water_mark_input);
        mBtnLoad = findViewById(R.id.btn_load_image);
        mBtnSave.setOnClickListener(this);
        mBtnInsert.setOnClickListener(this);
        mBtnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnInsert) {
            insertText();
        } else if (v == mBtnSave) {
            saveImage();
        } else if (v == mBtnLoad) {
            loadImage();
        }
    }

    private void insertText() {
        drawBitmap();
        if (null != mBitmap) {
            mImageView.setImageBitmap(mBitmap);
        }
    }

    private void saveImage() {

        File appFiles = new File(APP_FILE_PATH);
        if (!appFiles.exists()) {
            appFiles.mkdirs();
        }
        File file = new File(APP_FILE_PATH + "water_mark_" + System.currentTimeMillis() + ".png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null == fos) {
                return;
            }
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void loadImage() {
        Intent intent = new Intent(this, PhotoSelectPageActivity.class);
        startActivityForResult(intent, 0);
    }


    private void drawBitmap() {

        if (null == mPhotoBean) {
            Toast.makeText(this, "请重新选择", Toast.LENGTH_SHORT).show();
            return;
        }

        mBitmap = BitmapFactory.decodeFile(mPhotoBean._data).copy(Bitmap.Config.ARGB_8888, true);


        int width_bg = mBitmap.getWidth();
        int height_bg = mBitmap.getHeight();

        Canvas mCanvas = new Canvas(mBitmap);

        Paint mTextPaint = new Paint();

        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAlpha(50);
        mTextPaint.setTextSize(width_bg / 21);
        int i = 0;
        Path path;
        for (; i < 5; i++) {
            path = new Path();
            path.moveTo(0, height_bg);
            path.lineTo(width_bg, 0);
            mCanvas.drawTextOnPath(mEdtInput.getText().toString(), path, 1500, 500 - 500 * i, mTextPaint);
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        path = new Path();
        path.moveTo(0.6f * width_bg, 0.8f * height_bg);
        path.lineTo(0.8f * width_bg, 0.6f * height_bg);
        mCanvas.drawTextOnPath(year + "." + (month + 1) + "." + day, path, 0, 0, mTextPaint);
        mCanvas.save();
        mCanvas.restore();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                mPhotoBean = (PhotoBean) data.getSerializableExtra("photo");
                drawBitmap();
                if (null != mBitmap) {
                    mImageView.setImageBitmap(mBitmap);
                }
                break;
            default:
                break;
        }
    }
}