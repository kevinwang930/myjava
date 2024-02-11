package kevin.project.hadoop;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://127.0.0.1:8020");
        conf.set("dfs.client.use.datanode.hostname", "true");
//        conf.set("fs.default.name", "hdfs://namenode");
//        conf.set("dfs.namenode.rpc-address", "127.0.0.1:8020");
//        conf.set("dfs.datanode.address", "127.0.0.1:9866");
//        conf.set("dfs.datanode.http.address", "127.0.0.1:9864");
//        conf.set("dfs.datanode.ipc.address", "127.0.0.1:9867");
        conf.set("dfs.block.size", "1048576");
        conf.set("dfs.replication", "1");
//        conf.set("dfs.namenode.datanode.registration.ip-hostname-check", "false");


        FileSystem fs = FileSystem.get(conf);

        // Example: write a file to HDFS
        Path filePath = new Path("/test");
        System.out.println(fs.exists(filePath));
        Path file = new Path(filePath, "LICENSE1234.txt");
        System.out.println(fs.exists(filePath));
        try (FSDataOutputStream os = fs.create(file)) {
            os.writeUTF("hello, this is kevin");
        }
        try (FSDataInputStream is = fs.open(file)) {
            System.out.println(IOUtils.toString(is, StandardCharsets.UTF_8));
        }
//        System.out.println(fs.exists(filePath));
        fs.close();
    }
}
