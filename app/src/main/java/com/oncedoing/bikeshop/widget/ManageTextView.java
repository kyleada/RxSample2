package com.oncedoing.bikeshop.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class ManageTextView extends TextView {

    ObjectAnimator objectAnimator;
    ObjectAnimator objectAnimator2;
    AnimatorSet set1 = new AnimatorSet();
    AnimatorSet set2 = new AnimatorSet();

    public ManageTextView(Context context) {
        super(context);
    }

    public ManageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ManageTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(MotionEvent.ACTION_DOWN == event.getAction()){
            objectAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.8f);
            objectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.8f);
            set1.play(objectAnimator).with(objectAnimator2);
            set1.setDuration(500);
            set1.start();

        }else if(MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()){
            if(set1.isRunning()){
                set1.cancel();
            }
//            objectAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1.0f);
//            objectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f);
//            set2.play(objectAnimator).with(objectAnimator2);
//            set2.setDuration(300);
//            set2.start();

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        if(set2.isRunning()){
            set2.cancel();
        }
        super.onDetachedFromWindow();
    }
}
