package com.example.particleanimation;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by apple on 2017/6/2.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    private Context _context;


    public MyGLSurfaceView(Context context) {
        super(context);
        init(context, null);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        _context = context;
        setEGLContextClientVersion(2);
        MyRenderer myRenderer = new MyRenderer();
        setRenderer(myRenderer);
    }

    public class MyRenderer implements Renderer {

        Particle particle;	//粒子系统


        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            //设置屏幕背景色RGBA
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            //创建焰火粒子系统
            particle = new Particle(MyGLSurfaceView.this,
                    2,        //点的大小
                    5000    //点的个数
            );

            //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //关闭背面剪裁
            GLES20.glEnable(GLES20.GL_CULL_FACE);
            //初始化变换矩阵
            MatrixState.setInitStack();
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {
            //设置视窗大小及位置
            GLES20.glViewport(0, 0, width, height);
            //计算GLSurfaceView的宽高比
            float ratio = (float) width / height;
            //调用此方法计算产生透视投影矩阵
            MatrixState.setProjectFrustum(-ratio, ratio, -1, 1, 1, 1000);
            //调用此方法产生摄像机9参数位置矩阵
            MatrixState.setCamera(0,0,20,0,0,0,0f,1.0f,0.0f);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
//清除深度缓冲与颜色缓冲
            GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            //保护现场
            MatrixState.pushMatrix();
            //绘制粒子系统
            particle.drawSelf();
            //恢复现场
            MatrixState.popMatrix();
        }
    }

}
