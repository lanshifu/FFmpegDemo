attribute vec4 aPosition; //应用传过来的顶点坐标
uniform mat4 uMVPMatrix;  //应用传过来的矩阵
attribute vec2 aTexCoord; //应用传的纹理坐标

varying vec2 vTextureCoord; //纹理坐标传给片元着色器
varying vec4 vPosition; //传递顶点坐标给片元着色器
void main()
{
//    vTextureCoord = (uMVPMatrix * aTexCoord).xy;
    vTextureCoord = aTexCoord;
    gl_Position = aPosition * uMVPMatrix;

    vPosition = aPosition;

}