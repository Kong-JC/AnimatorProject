package com.example.particleanimation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.opengl.GLES20;

public class Particle {

    private FloatBuffer   _velocityBuffer;//顶点速度数据缓冲
    float _scale;

    String _vertexShader;	//顶点着色器
    String _fragmentShader;	//片元着色器

    int _program;			//程序id
    int _muMVPMatrixHandle;	//总变换矩阵引用
    int _uPointSizeHandle;	//顶点尺寸参数引用
    int _uColorHandle;		//顶点颜色参数引用
    int _uTimeHandle;		//顶点颜色参数引用
    int _vCount =0;

    int _maVelocityHandle; 	//顶点速度属性引用
    float _timeLive =0;
    long _timeStamp =0;

    public Particle(MyGLSurfaceView mv, float scale, int vCount)
    {
        this._scale=scale;
        this._vCount =vCount;
        initVertexData(vCount);
        initShader(mv);
    }

    //初始化顶点数据的initVertexData方法
    public void initVertexData(int vCount){
        float[] velocity=new float[vCount*3];
        for(int i=0;i<vCount;i++){
            double fwj=2*Math.PI*Math.random();
            double yj=0.35*Math.PI*Math.random()+0.15*Math.PI;
            final double vTotal=1.5+1.5*Math.random();		//总的速度
            double vy=vTotal*Math.sin(yj);		//y方向上的速度
            double vx=vTotal*Math.cos(yj)*Math.sin(fwj);	//x方向上的速度
            double vz=vTotal*Math.cos(yj)*Math.cos(fwj);	//z方向上的速度
            velocity[i*3]=(float)vx;
            velocity[i*3+1]=(float)vy;
            velocity[i*3+2]=(float)vz;
        }

        //创建顶点速度数据缓冲
        ByteBuffer vbb = ByteBuffer.allocateDirect(velocity.length*4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        _velocityBuffer = vbb.asFloatBuffer();//转换为int型缓冲
        _velocityBuffer.put(velocity);//向缓冲区中放入顶点坐标数据
        _velocityBuffer.position(0);//设置缓冲区起始位置
    }

    //初始化着色器的initShader方法
    public void initShader(MyGLSurfaceView mv)
    {
        //加载顶点着色器的脚本内容
        _vertexShader=ShaderUtil.loadFromAssetsFile("vertex_yh.sh", mv.getResources());
        ShaderUtil.checkGlError("==ss==");
        //加载片元着色器的脚本内容
        _fragmentShader=ShaderUtil.loadFromAssetsFile("frag_yh.sh", mv.getResources());
        //基于顶点着色器与片元着色器创建程序
        ShaderUtil.checkGlError("==ss==");
        _program = ShaderUtil.createProgram(_vertexShader, _fragmentShader);
        //获取程序中顶点速度属性引用id
        _maVelocityHandle = GLES20.glGetAttribLocation(_program, "aVelocity");
        //获取程序中总变换矩阵引用id
        _muMVPMatrixHandle = GLES20.glGetUniformLocation(_program, "uMVPMatrix");
        //获取顶点尺寸参数引用
        _uPointSizeHandle = GLES20.glGetUniformLocation(_program, "uPointSize");
        //获取顶点颜色参数引用
        _uColorHandle = GLES20.glGetUniformLocation(_program, "uColor");
        //获取顶点颜色参数引用
        _uTimeHandle =GLES20.glGetUniformLocation(_program, "uTime");

    }
    public void drawSelf()
    {
        long currTimeStamp=System.nanoTime()/1000000;
        if(currTimeStamp- _timeStamp >=10){
            _timeLive +=0.02f;
            _timeStamp =currTimeStamp;
        }

        //制定使用某套着色器程序
        GLES20.glUseProgram(_program);
        //将最终变换矩阵传入着色器程序
        GLES20.glUniformMatrix4fv(_muMVPMatrixHandle, 1, false, MatrixState.getFinalMatrix(), 0);
        //将顶点尺寸传入着色器程序
        GLES20.glUniform1f(_uPointSizeHandle, _scale);
        //将时间传入着色器程序
        GLES20.glUniform1f(_uTimeHandle, _timeLive);
        //将顶点颜色传入着色器程序
        GLES20.glUniform3fv(_uColorHandle, 1, new float[]{1,1,1}, 0);
        //传入顶点速度数据
        GLES20.glVertexAttribPointer(
                _maVelocityHandle,
                3,
                GLES20.GL_FLOAT,
                false,
                3*4,
                _velocityBuffer
        );
        //允许顶点位置数据数组
        GLES20.glEnableVertexAttribArray(_maVelocityHandle);
        //绘制点
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, _vCount);
    }
}
