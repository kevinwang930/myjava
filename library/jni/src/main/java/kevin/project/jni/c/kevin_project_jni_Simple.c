/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdio.h>
/* Header for class kevin_project_jni_Simple */
#include "kevin_project_jni_Simple.h"
/*
 * Class:     kevin_project_jni_Simple
 * Method:    plus
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_kevin_project_jni_Simple_plus
  (JNIEnv *env, jclass obj, jint a, jint b) {
  printf ("inside c code\n");
  return a + b;
}