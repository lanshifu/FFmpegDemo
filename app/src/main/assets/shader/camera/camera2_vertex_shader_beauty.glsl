attribute vec4 aPosition;
uniform mat4 uMVPMatrix;
attribute vec4 aTexCoord;
varying vec2 vTextureCoord; //传给片元着色器
varying vec4 vPosition; //传给片元着色器
void main()
{
    vTextureCoord = (uMVPMatrix * aTexCoord).xy;
    gl_Position = aPosition;
    vPosition = aPosition;
}

//
//
//attribute vec4 vPosition;
//attribute vec2 vTextureCoord;
//varying vec2 textureCoordinate;
//attribute vec4 aTextureCoordinate;
//
//varying vec2 blurCoord1s[14];
//const highp float mWidth=720.0;
//const highp float mHeight=1280.0;
//uniform mat4 uMVPMatrix;
//void main( )
//{
//
//
//    gl_Position = uMVPMatrix * vPosition;
//    textureCoordinate = vTextureCoord;
//
//    highp float mul_x = 2.0 / mWidth;
//    highp float mul_y = 2.0 / mHeight;
//
//    // 14个采样点
//    blurCoord1s[0] = vTextureCoord + vec2( 0.0 * mul_x, -10.0 * mul_y );
//    blurCoord1s[1] = vTextureCoord + vec2( 8.0 * mul_x, -5.0 * mul_y );
//    blurCoord1s[2] = vTextureCoord + vec2( 8.0 * mul_x, 5.0 * mul_y );
//    blurCoord1s[3] = vTextureCoord + vec2( 0.0 * mul_x, 10.0 * mul_y );
//    blurCoord1s[4] = vTextureCoord + vec2( -8.0 * mul_x, 5.0 * mul_y );
//    blurCoord1s[5] = vTextureCoord + vec2( -8.0 * mul_x, -5.0 * mul_y );
//    blurCoord1s[6] = vTextureCoord + vec2( 0.0 * mul_x, -6.0 * mul_y );
//    blurCoord1s[7] = vTextureCoord + vec2( -4.0 * mul_x, -4.0 * mul_y );
//    blurCoord1s[8] = vTextureCoord + vec2( -6.0 * mul_x, 0.0 * mul_y );
//    blurCoord1s[9] = vTextureCoord + vec2( -4.0 * mul_x, 4.0 * mul_y );
//    blurCoord1s[10] = vTextureCoord + vec2( 0.0 * mul_x, 6.0 * mul_y );
//    blurCoord1s[11] = vTextureCoord + vec2( 4.0 * mul_x, 4.0 * mul_y );
//    blurCoord1s[12] = vTextureCoord + vec2( 6.0 * mul_x, 0.0 * mul_y );
//    blurCoord1s[13] = vTextureCoord + vec2( 4.0 * mul_x, -4.0 * mul_y );
//}