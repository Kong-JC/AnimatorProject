package com.example.particleanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView _myGLSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _myGLSurfaceView = (MyGLSurfaceView)findViewById(R.id.myGLView);
        _myGLSurfaceView.requestFocus();
        _myGLSurfaceView.setFocusableInTouchMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        _myGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _myGLSurfaceView.onPause();
    }
}
