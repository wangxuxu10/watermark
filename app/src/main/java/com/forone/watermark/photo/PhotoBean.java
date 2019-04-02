package com.forone.watermark.photo;

import android.database.Cursor;

import java.io.Serializable;

/**
 * @author xuxu.wang01
 * @time 2019-04-02 15:18
 * @email chao.zhang12@ucarinc.com
 * @tel 2790
 * @desc
 */
public class PhotoBean implements Serializable {

    public long _id;
    public String _data;
    public long _size;
    public String _display_name;
    public String mime_type;
    public String title;
    public long date_added;
    public long date_modified;
    public String description;
    public long picasa_id;
    public String isprivate;
    public double latitude;
    public double longitude;
    public long datetaken;
    public String orientation;
    public String mini_thumb_magic;
    public long bucket_id;
    public String bucket_display_name;
    public long width;
    public long height;

    @Override
    public String toString() {
        return "PhotoBean{" +
                "_id=" + _id +
                ", _data='" + _data + '\'' +
                ", _size=" + _size +
                ", _display_name='" + _display_name + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", title='" + title + '\'' +
                ", date_added=" + date_added +
                ", date_modified=" + date_modified +
                ", description='" + description + '\'' +
                ", picasa_id=" + picasa_id +
                ", isprivate='" + isprivate + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", datetaken=" + datetaken +
                ", orientation='" + orientation + '\'' +
                ", mini_thumb_magic='" + mini_thumb_magic + '\'' +
                ", bucket_id=" + bucket_id +
                ", bucket_display_name='" + bucket_display_name + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public static PhotoBean parseToBean(Cursor cursor) {
        if (null == cursor) {
            return null;
        }
        PhotoBean photoBean = new PhotoBean();
        photoBean._id = cursor.getLong(0);
        photoBean._data = cursor.getString(1);
        photoBean._size = cursor.getLong(2);
        photoBean._display_name = cursor.getString(3);
        photoBean.mime_type = cursor.getString(4);
        photoBean.title = cursor.getString(5);
        photoBean.date_added = cursor.getLong(6);
        photoBean.date_modified = cursor.getLong(7);
        photoBean.description = cursor.getString(8);
        photoBean.picasa_id = cursor.getLong(9);
        photoBean.isprivate = cursor.getString(10);
        photoBean.latitude = cursor.getDouble(11);
        photoBean.longitude = cursor.getDouble(12);
        photoBean.datetaken = cursor.getLong(13);
        photoBean.orientation = cursor.getString(14);
        photoBean.mini_thumb_magic = cursor.getString(15);
        photoBean.bucket_id = cursor.getLong(16);
        photoBean.bucket_display_name = cursor.getString(17);
        photoBean.width = cursor.getLong(18);
        photoBean.height = cursor.getLong(19);
        return photoBean;
    }
}
