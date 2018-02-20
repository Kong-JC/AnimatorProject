package com.example.tweenanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView _imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _imageView = (ImageView) this.findViewById(R.id.imageView);
    }

    // 通过加载配置文件实现透明度动画
    void onAlphaAnimationByFile(View view) {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        _imageView.startAnimation(alphaAnimation);
    }

    // 通过代码实现透明度动画
    void onAlphaAnimationByCode(View view) {
        Animation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(2000);                //动画的持续的时间
        alphaAnimation.setRepeatCount(2);                //动画的重复次数
        _imageView.startAnimation(alphaAnimation);       //开始动画
    }

    // 通过加载配置文件实现旋转动画
    void onRotateAnimationByFile(View view){
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        _imageView.startAnimation(rotateAnimation);
    }

    // 通过代码实现旋转动画
    void onRotateAnimationByCode(View view){
        // 以view中心为旋转点
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        _imageView.startAnimation(rotateAnimation);
    }

    // 通过加载配置文件实现放缩动画
    void onScaleAnimationByFile(View view){
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        _imageView.startAnimation(scaleAnimation);
    }

    // 通过代码实现放缩动画
    void onScaleAnimationByCode(View view){
        // 以view中心为缩放点，由初始状态放大两倍
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(3000);
        _imageView.startAnimation(scaleAnimation);
    }

    // 通过加载配置文件实现位移动画
    void onTransAnimationByFile(View view){
        Animation transAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        _imageView.startAnimation(transAnimation);
    }

    // 通过代码实现位移动画
    void onTransAnimationByCode(View view){
        //从当前位置，向下和向右各平移300px
        TranslateAnimation transAnimation = new TranslateAnimation(0.0f, 300.0f, 0.0f, 300.0f);
        transAnimation.setDuration(3000);
        _imageView.startAnimation(transAnimation);
    }

    // 组合动画
    void onComboAnimation(View view){

        AnimationSet animationSet = new AnimationSet(true);
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setDuration(3000);
        animationSet.setStartOffset(2000);
        _imageView.startAnimation(animationSet);



    }
}
