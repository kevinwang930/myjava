package kevin.project.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://127.0.0.1:8020");

        FileSystem fs = FileSystem.get(conf);

        // Example: Read a file from HDFS
        Path filePath = new Path("/test");
        System.out.println(fs.exists(filePath));
        fs.create(filePath);
        System.out.println(fs.exists(filePath));
        fs.close();
    }
}
