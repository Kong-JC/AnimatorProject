package com.example.objectanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView _imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _imageView = (ImageView) this.findViewById(R.id.imageView);

    }

    // 第一个按钮（渐变）
    void onAlphaAnimation(View view) {

        ObjectAnimator oa = ObjectAnimator.ofFloat(_imageView, "alpha", 0.1f, 1.0f);
        oa.setDuration(2000);
        oa.start();

    }

    // 第二个按钮（旋转）
    void onRotateAnimation(View view) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(_imageView, "rotation", 0f, 360f);
        oa.setDuration(1000);
        oa.start();
    }

    // 第三个按钮（放缩）
    void onScaleAnimation(View view) {
        ObjectAnimator.ofFloat(_imageView, "scaleX", 1f, 3f, 1f).setDuration(2000).start();
        ObjectAnimator.ofFloat(_imageView, "scaleY", 1f, 3f, 1f).setDuration(2000).start();
    }

    // 第四个按钮（位移）
    void onTransAnimation(View view) {
        ObjectAnimator.ofFloat(_imageView, "translationX", 0f, 300f, 0f).setDuration(1000).start();
        ObjectAnimator.ofFloat(_imageView, "translationY", 0f, 300f, 0f).setDuration(1000).start();
    }

    // 组合动画
    void onComboAnimation(View view) {


        ObjectAnimator alpha = ObjectAnimator.ofFloat(_imageView, "alpha", 0.1f, 1.0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(_imageView, "rotation", 0f, 360f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(_imageView, "scaleX", 1f, 3f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(_imageView, "scaleY", 1f, 3f, 1f);

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(alpha).with(scaleX).with(scaleY).after(rotate);
        animationSet.setDuration(5000);
        animationSet.start();
    }
}
