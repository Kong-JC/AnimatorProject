package com.example.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
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

    void onFrameAnimationByFile(View view) {
        _imageView.setImageResource(R.drawable.frame_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) _imageView.getDrawable();
        animationDrawable.start();
    }

    void onFrameAnimationByCode(View view) {
        AnimationDrawable animation = new AnimationDrawable();
        for (int i = 0; i < 70; i++) {
            int id = getResources().getIdentifier("flash1675_" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            // 调用addFrame()方法依次添加drawable对象
            animation.addFrame(drawable, 100);
        }
        // 添加到View中
        animation.setOneShot(false);
        _imageView.setImageDrawable(animation);
        // 启动动画
        animation.start();
    }
}
