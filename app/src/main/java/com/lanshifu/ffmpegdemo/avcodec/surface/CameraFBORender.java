package com.lanshifu.ffmpegdemo.avcodec.surface;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;


import com.lanshifu.ffmpegdemo.utils.DisplayUtils;
import com.lanshifu.ffmpegdemo.utils.GLUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class CameraFBORender implements EglSurfaceView.Render,SurfaceTexture.OnFrameAvailableListener {

    private static final String TAG = "CameraFBORender";

    private static final String VERTEXT_SHADER_CODE =
            "attribute vec4 av_Position;\n" +//顶点位置
            "attribute vec2 af_Position;\n" +//纹理位置
            "varying vec2 v_texPo;\n" +//纹理位置  与fragment_shader交互
            "uniform mat4 u_Matrix;\n" +//矩阵变换
            "\n" +
            "void main() {\n" +
            "    v_texPo = af_Position;\n" +
            "    gl_Position = av_Position * u_Matrix;\n" +
            "}";

    private static final String FRAMENT_SHADER_CODE = "" +
            "#extension GL_OES_EGL_image_external : require \n" +//申明使用扩展纹理
            "precision mediump float;\n" +//精度 为float
            "varying vec2 v_texPo;\n" +//纹理位置  接收于vertex_shader
            "uniform samplerExternalOES  sTexture;\n" +//加载流数据(摄像头数据)
            "void main() {\n" +
            "    gl_FragColor=texture2D(sTexture, v_texPo);\n" +
            "}";

    //顶点
    private float[] vertexPoint = {
            -1f, -1f, 0f,
            1f, -1f, 0f,
            -1f, 1f, 0f,
            1f, 1f, 0f
    };

    //纹理
    private float[] texturePoint = {
            0f, 1f, 0f,
            1f, 1f, 0f,
            0f, 0f, 0f,
            1f, 0f, 0f
    };

    //位置
    private FloatBuffer vertexBuffer;
    //纹理
    private FloatBuffer textureBuffer;
    private int program;
    //坐标数组中每个顶点的坐标数
    private int coordinateVertex = 3;

    //顶点位置
    private int vPosition;
    //纹理位置
    private int tPosition;
    private int uMatrix;

    private int fboId;
    private int fboTextureId;

    private int cameraTextureId;

    private int vboId;

    private float[] matrix = new float[16];
    private int screenWidth;
    private int screenHeight;

    private Context context;
    private SurfaceTexture surfaceTexture;
    private CameraRender cameraRender;
    private OnSurfaceListener onSurfaceListener;


    public CameraFBORender(Context context) {
        this.context = context;
        screenWidth = DisplayUtils.getScreenWidth(context);
        screenHeight = DisplayUtils.getScreenHeight(context);
        cameraRender = new CameraRender(context);

        vertexBuffer = ByteBuffer.allocateDirect(vertexPoint.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertexPoint);
        vertexBuffer.position(0);

        textureBuffer = ByteBuffer.allocateDirect(texturePoint.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(texturePoint);
        textureBuffer.position(0);
    }

    public void setOnSurfaceListener(OnSurfaceListener onSurfaceListener) {
        this.onSurfaceListener = onSurfaceListener;
    }

    @Override
    public void onSurfaceCreated() {
        program = GLUtil.createProgram(VERTEXT_SHADER_CODE, FRAMENT_SHADER_CODE);
        if (program > 0) {
            //获取顶点坐标字段
            vPosition = GLES20.glGetAttribLocation(program, "av_Position");
            //获取纹理坐标字段
            tPosition = GLES20.glGetAttribLocation(program, "af_Position");
            uMatrix = GLES20.glGetUniformLocation(program, "u_Matrix");

            //创建vbo
            createVBO();
            //创建fbo
            createFBO(screenWidth, screenHeight);
            //创建相机预览扩展纹理
            createCameraTexture();
        }
        cameraRender.onSurfaceCreated();
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        cameraRender.onSurfaceChanged(width, height);
    }

    @Override
    public void onDrawFrame() {
        //调用触发onFrameAvailable，更新预览图像
        updateTextImage();
        //清空颜色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        //设置背景颜色
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        //使用程序
        GLES20.glUseProgram(program);

        //重点1、绑定fbo （0代表屏幕，其它代表fbo id）
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fboId);

        //重点2、摄像头预览扩展纹理赋值
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, cameraTextureId);

        GLES20.glEnableVertexAttribArray(vPosition);
        GLES20.glEnableVertexAttribArray(tPosition);

        //给变换矩阵赋值
        GLES20.glUniformMatrix4fv(uMatrix, 1, false, matrix, 0);

        //重点3、使用VBO设置纹理和顶点值，和普通设置有一点不同
        useVboSetVertext();

        //绘制 GLES20.GL_TRIANGLE_STRIP:复用坐标
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisableVertexAttribArray(vPosition);
        GLES20.glDisableVertexAttribArray(tPosition);

        //解绑fbo
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);

        //上面是离屏渲染，现在真正渲染到屏幕
        cameraRender.onDraw(fboTextureId);
    }

    public void updateTextImage() {
        surfaceTexture.updateTexImage();
    }

    /**
     * 创建vbo
     */
    private void createVBO() {
        //1. 创建VBO
        int[] vbos = new int[1];
        GLES20.glGenBuffers(vbos.length, vbos, 0);
        vboId = vbos[0];
        //2. 绑定VBO
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboId);
        //3. 分配VBO需要的缓存大小
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexPoint.length * 4 + texturePoint.length * 4, null, GLES20.GL_STATIC_DRAW);
        //4. 为VBO设置顶点数据的值
        GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER, 0, vertexPoint.length * 4, vertexBuffer);
        GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER, vertexPoint.length * 4, texturePoint.length * 4, textureBuffer);
        //5. 解绑VBO
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    /**
     * 创建fbo
     *
     * @param w
     * @param h
     */
    private void createFBO(int w, int h) {
        //1. 创建FBO
        int[] fbos = new int[1];
        GLES20.glGenFramebuffers(1, fbos, 0);
        fboId = fbos[0];
        //2. 绑定FBO，（0代表屏幕，其它代表fbo id）
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fboId);

        //3. 创建FBO纹理
        int[] textureIds = new int[1];
        //创建纹理
        GLES20.glGenTextures(1, textureIds, 0);
        fboTextureId = textureIds[0];
        //绑定纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, fboTextureId);
        //环绕（超出纹理坐标范围）  （s==x t==y GL_REPEAT 重复）
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        //过滤（纹理像素映射到坐标点）  （缩小、放大：GL_LINEAR线性）
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        //4. 把纹理绑定到FBO
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0,
                GLES20.GL_TEXTURE_2D, fboTextureId, 0);

        //5. 设置FBO分配内存大小
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, w, h,
                0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);

        //6. 检测是否绑定成功
        if (GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER)
                != GLES20.GL_FRAMEBUFFER_COMPLETE) {
            Log.d(TAG, "createFBO: 纹理绑定FBO失败");
        }
        //7. 解绑纹理和FBO
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
    }

    /**
     * 创建摄像头预览扩展纹理
     */
    private void createCameraTexture() {
        int[] textureIds = new int[1];
        GLES20.glGenTextures(1, textureIds, 0);
        cameraTextureId = textureIds[0];
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, cameraTextureId);
        //环绕（超出纹理坐标范围）  （s==x t==y GL_REPEAT 重复）
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        //过滤（纹理像素映射到坐标点）  （缩小、放大：GL_LINEAR线性）
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        surfaceTexture = new SurfaceTexture(cameraTextureId);
        surfaceTexture.setOnFrameAvailableListener(this);

        if (onSurfaceListener != null) {
            //回调给CameraManager获取surfaceTexture：通过camera.setPreviewTexture(surfaceTexture);
            onSurfaceListener.onSurfaceCreate(surfaceTexture, fboTextureId);
        }

        // 解绑扩展纹理
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);
    }


    /**
     * 使用vbo（Vertex Buffer Object）设置顶点位置，好处是可以一次性的发送一大批数据到显卡上，而不是每个顶点发送一次
     */
    private void useVboSetVertext() {
        //1. 绑定VBO
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboId);
        //2. 设置顶点数据
        GLES20.glVertexAttribPointer(vPosition, 3, GLES20.GL_FLOAT, false, 12, 0);
        GLES20.glVertexAttribPointer(tPosition, 3, GLES20.GL_FLOAT, false, 12,
                vertexPoint.length * 4);
        //3. 解绑VBO
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {

    }

    public interface OnSurfaceListener {
        void onSurfaceCreate(SurfaceTexture surfaceTexture, int fboTextureId);
    }

    /***
     * 设置滤镜类型
     * @param type :int
     */
    public void setType(int type) {
        cameraRender.setType(type);
    }

    public int getType() {
        return cameraRender.getType();
    }

    /**
     * 设置滤镜颜色
     *
     * @param color :float[]
     */
    public void setColor(float[] color) {
        cameraRender.setColor(color);
    }

    public float[] getColor() {
        return cameraRender.getColor();
    }

    /**
     * 初始化矩阵
     */
    public void resetMatrix() {
        //初始化
        Matrix.setIdentityM(matrix, 0);
    }

    /**
     * 旋转
     *
     * @param angle
     * @param x
     * @param y
     * @param z
     */
    public void setAngle(float angle, float x, float y, float z) {
        //旋转
        Matrix.rotateM(matrix, 0, angle, x, y, z);
    }
}
