#  pwd
cd jni/src/main/java

# compileJava
"$JAVA_HOME"/bin/javac -h c -d target Simple.java

# compile lib

cc -g -shared -fpic -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin c/kevin_project_jni_Simple.c -o  lib/libSimple.dylib

# run java

"$JAVA_HOME"/bin/java -Djava.library.path="$LD_LIBRARY_PATH":./lib -cp target kevin.project.jni.Simple


