LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := com_example_opencvtest_NativeClass.cpp

LOCAL_LBLIBS += -llog
LOCAL_MODULE := MyLibs

include $(BUILD_SHARED_LIBRARY)