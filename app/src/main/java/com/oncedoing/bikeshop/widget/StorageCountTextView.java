package com.oncedoing.bikeshop.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.oncedoing.bikeshop.R;

/**
 * Created by Administrator on 2016/3/20.
 */
public class StorageCountTextView extends TextView {
    public StorageCountTextView(Context context) {
        super(context);
    }

    public StorageCountTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StorageCountTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StorageCountTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        String sInfoFormat = getResources().getString(R.string.bike_storage_count);
        String sFinalInfo=String.format(sInfoFormat, text);
        super.setText(sFinalInfo, type);
    }
}
