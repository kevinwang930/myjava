package kevin.project.classloader;

import javax.tools.*;
import javax.tools.JavaFileObject.Kind;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;

@SuppressWarnings({ "unchecked", "boxing", "hiding", "rawtypes" })
public class MemoryClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        String className = "test.MyClass";
        String javaSource = "package test; public class MyClass { public static void test() { System.out.println(\"Hello World\"); } }";
        // use a parent class loader that can resolve the classes referenced in the source
        ClassLoader parentClassLoader = MemoryClassLoader.class.getClassLoader();
        Class<?> clazz = new MemoryClassLoader(parentClassLoader).compileAndLoad(className, javaSource);
        clazz.getMethod("test").invoke(null);
    }

    public MemoryClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> compileAndLoad(String className, String javaSource) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StringWriter errorWriter = new StringWriter();
        ByteArrayOutputStream compiledBytesOutputStream = new ByteArrayOutputStream();

        SimpleJavaFileObject sourceFile = new SimpleJavaFileObject(URI.create("file:///" + className.replace('.', '/') + ".java"), Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncErrors) {
                return javaSource;
            }
        };

        SimpleJavaFileObject classFile = new SimpleJavaFileObject(URI.create("file:///" + className.replace('.', '/') + ".class"), Kind.CLASS) {
            @Override
            public OutputStream openOutputStream() throws IOException {
                return compiledBytesOutputStream;
            }
        };

        ForwardingJavaFileManager fileManager = new ForwardingJavaFileManager(compiler.getStandardFileManager(null, null, null)) {
            @Override
            public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                return classFile;
            }
        };

        // compile class
        if (!compiler.getTask(errorWriter, fileManager, null, null, null, Arrays.asList(sourceFile)).call()) {
            throw new Exception(errorWriter.toString());
        }

        // load class
        byte[] bytes = compiledBytesOutputStream.toByteArray();
        return super.defineClass(className, bytes, 0, bytes.length);
    }
}
