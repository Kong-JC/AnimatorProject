package com.example.animatorlistener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void onStartAnimation(View view) {
        ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        ObjectAnimator oa = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);

        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d(getApplication().toString(),"《龙珠》动画片开始了");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d(getApplication().toString(),"《龙珠》动画片结束了");

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.d(getApplication().toString(),"《龙珠》动画片被取消了");

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.d(getApplication().toString(),"OMG,电视台疯了，《龙珠》动画片被重复播放了");

            }
        });

        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.d(getApplication().toString(),"《龙珠》动画片结束了!!!!!!!!!!");

            }
        });

        oa.setDuration(1000);
        oa.setRepeatCount(3);
        oa.start();

    }
}
