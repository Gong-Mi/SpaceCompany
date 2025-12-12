/* Minimal jni.h for compilation testing */

#ifndef _MOCK_JNI_H
#define _MOCK_JNI_H

#include <stddef.h> // For size_t

typedef int jint;
typedef long long jlong;
typedef float jfloat;
typedef double jdouble;
typedef unsigned char jboolean;
typedef unsigned short jchar;
typedef short jshort;
typedef void* jobject;
typedef jobject jclass;
typedef jobject jstring;
typedef jobject jarray;
typedef jarray jobjectArray;
typedef jarray jbooleanArray;
typedef jarray jbyteArray;
typedef jarray jcharArray;
typedef jarray jshortArray;
typedef jarray jintArray;
typedef jarray jlongArray;
typedef jarray jfloatArray;
typedef jarray jdoubleArray;
typedef jobject jthrowable;
typedef jobject jweak;

// Forward declaration of the function table struct
struct JNINativeInterface_;

// Define JNIEnv as a pointer to the function table
typedef const struct JNINativeInterface_* JNIEnv;
typedef struct _JavaVM JavaVM;

struct JNINativeInterface_ {
    void *reserved0;
    void *reserved1;
    void *reserved2;
    void *reserved3;
    
    // We define just the functions we use.
    // Use void* for env argument to avoid circular dependency in mock header
    jstring (*NewStringUTF)(void*, const char*);
    const char* (*GetStringUTFChars)(void*, jstring, jboolean*);
    void (*ReleaseStringUTFChars)(void*, jstring, const char*);
};

#define JNIEXPORT
#define JNICALL

#endif // _MOCK_JNI_H
