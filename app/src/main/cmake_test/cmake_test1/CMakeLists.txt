# https://www.jianshu.com/p/528eeb266f83

cmake_minimum_required(VERSION 3.4.1)

#这个指令是定义工程名称的,定义完这个指令会隐式定义了两个变量：
#<projectname>_BINARY_DIR和<projectname>_SOURCE_DIR
PROJECT(cmake_test1)

#显式地定义变量 SRC_LIST
SET(SRC_LIST hello.cpp)

#向终端输出用户定义的信息，包含了三种类型：
#SEND_ERROR，产生错误，生成过程被跳过；
#STATUS，输出前缀为—-的信息；（由上面例子也可以看到会在终端输出相关信息）
#FATAL_ERROR，立即终止所有 CMake 过程；
MESSAGE(STATUS "打印：This is BINARY dir " ${HELLO_BINARY_DIR})  #终端打印的信息
MESSAGE(STATUS "打印This is SOURCE dir "${HELLO_SOURCE_DIR})

#将一组源文件 source 生成一个可执行文件。 source 可以是多个源文件，也可以是对应定义的变量
ADD_EXECUTABLE(out_hello ${SRC_LIST})


#将一组源文件 source 编译出一个库文件，并保存为 libname.so,3种库文件类型，不写的话，默认为 STATIC
#SHARED: 表示动态库、STATIC: 表示静态库、EXCLUDE_FROM_ALL: 表示这个库不被默认构建
add_library(hello_lib SHARED ${SRC_LIST})

#add_library 命令也可以用来导入第三方的库
#dd_library(libname [SHARED | STATIC | MODULE | UNKNOWN] IMPORTED)
add_library(libavcodec SHARED hello)

#find_library(<VAR> name1 path1 path2 ...)
#VAR 变量表示找到的库全路径
find_library(libX  X11 /usr/lib)
find_library(log-lib log /usr/lib)  #路径为空，应该是查找系统环境变量路径

if (NOT log-lib)
    message(STATUS "log-lib not found!!!!")

endif()

#为 target(hello_lib) 添加需要的链接的共享库
#指定 compress 工程需要用到 libjpeg 库和 log 库
#target_link_libraries(hello_lib libavcodec ${log-lib})



### log-lib 没有找到，需要手动安装？？？、