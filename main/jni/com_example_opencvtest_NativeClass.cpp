#include <com_example_opencvtest_NativeClass.h>

JNIEXPORT jstring JNICALL Java_com_example_opencvtest_NativeClass_getMessageFromJNI
  (JNIEnv *env, jclass obj){
    return env->NewStringUTF("This is message from JNI");
  }

