# cmake参考 https://www.jianshu.com/p/528eeb266f83

cmake_minimum_required(VERSION 3.4.1)


# 需要引入我们头文件,以这个配置的目录为基准
include_directories(src/main/jniLibs/include)

# 添加共享库（so）搜索路径
LINK_DIRECTORIES(${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi)


# 指定源文件目录，把当前工程目录下的 src/main/cpp 目录的下的所有 .cpp 和 .c 文件赋值给 AUDIO_SRC_LIST
AUX_SOURCE_DIRECTORY(${CMAKE_SOURCE_DIR}/src/main/cpp/audio AUDIO_SRC_LIST)
AUX_SOURCE_DIRECTORY(${CMAKE_SOURCE_DIR}/src/main/cpp/video VIDEO_SRC_LIST)

AUX_SOURCE_DIRECTORY(${CMAKE_SOURCE_DIR}/src/main/cpp/push PUSH_SRC_LIST)
AUX_SOURCE_DIRECTORY(${CMAKE_SOURCE_DIR}/src/main/cpp/push/librtmp RTMP_SRC_LIST)


#file(GLOB VIDEO_SRC_LIST "${CMAKE_SOURCE_DIR}/src/main/cpp/video/video-player.cpp")

add_library(
        # 编译生成的库的名称叫native-lib，对应System.loadLibrary("native-lib");
        music-player
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ${AUDIO_SRC_LIST}
)


add_library(
        # 编译生成的库的名称叫native-lib，对应System.loadLibrary("native-lib");
        video-player
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ${VIDEO_SRC_LIST}
)

add_library(
        # 编译生成的库的名称叫 push_handle，对应System.loadLibrary("push_handle");
        push_handle
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ${PUSH_SRC_LIST}
)


target_link_libraries(
        video-player
        # 编解码(最重要的库)
        avcodec-57
        # 设备信息
        avdevice-57
        # 滤镜特效处理库
        avfilter-6
        # 封装格式处理库
        avformat-57
        # 工具库(大部分库都需要这个库的支持)
        avutil-55
        # 后期处理
        postproc-54
        # 音频采样数据格式转换库
        swresample-2
        # 视频像素数据格式转换
        swscale-4
        # 链接 android ndk 自带的一些库
        android
        # Links the target library to the log library
        # included in the NDK.
        log)

target_link_libraries(
        music-player
        # 编解码(最重要的库)
        avcodec-57
        # 设备信息
        avdevice-57
        # 滤镜特效处理库
        avfilter-6
        # 封装格式处理库
        avformat-57
        # 工具库(大部分库都需要这个库的支持)
        avutil-55
        # 后期处理
        postproc-54
        # 音频采样数据格式转换库
        swresample-2
        # 视频像素数据格式转换
        swscale-4
        # 链接 android ndk 自带的一些库
        android
        # Links the target library to the log library
        # included in the NDK.
        # 链接 OpenSLES
        OpenSLES
        log)

target_link_libraries(
        push_handle
        # 编解码(最重要的库)
        avcodec-57
        # 设备信息
        avdevice-57
        # 滤镜特效处理库
        avfilter-6
        # 封装格式处理库
        avformat-57
        # 工具库(大部分库都需要这个库的支持)
        avutil-55
        # 后期处理
        postproc-54
        # 音频采样数据格式转换库
        swresample-2
        # 视频像素数据格式转换
        swscale-4
        # 链接 android ndk 自带的一些库
        android
        # Links the target library to the log library
        # included in the NDK.
        # 链接 OpenSLES
        OpenSLES
        log)


