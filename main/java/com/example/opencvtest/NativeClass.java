package com.example.opencvtest;

public class NativeClass {

    public native static String getMessageFromJNI();
}
//F:\anchit\OpencvTest\app\build\intermediates\javac\debug\compileDebugJavaWithJavac\classes\com\example\opencvtest


//javah -d jni -classpath ../../build/intermediates/javac/debug/compileDebugJavaWithJavac/classes  com.example.opencvtest.NativeClass