package kevin.project.fileSystem;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

public class FileOperationLearn {

    private String pwd = System.getProperty("user.dir");

    public void basicLearn() {
        Path cp = Paths.get(pwd);
        Path pom = cp.getParent().resolve("pom.xml");
        System.out.println(FilenameUtils.getBaseName(pom.toString()));
        System.out.println(FilenameUtils.getExtension(pom.toString()));
    }
    public void fileListLearn() {
        File file = new File(pwd);
        String[] files = file.list();
        for (String s : files) {
            System.out.println(s);
        }


    }

    public void fileWalkingLearn() throws IOException {
        Files.walk(Paths.get(pwd)).forEach(System.out::println);
        Files.walkFileTree(Paths.get(pwd), new SimpleFileVisitor<>() {


            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(Paths.get(pwd).relativize(file));

                return FileVisitResult.CONTINUE;
            }
        });
        Collection<File> files = FileUtils.listFiles(FileUtils.getFile(pwd), TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE);
        files.forEach(System.out::println);
    }

    public void renameLearn() throws URISyntaxException, IOException {
        File file1 = new File(getClass().getResource("/test.txt").toURI().getPath());
        System.out.println(file1.getCanonicalPath());
        file1.renameTo(Paths.get(file1.getCanonicalPath()).getParent().resolve("test1.txt").toFile());
    }

    public void renameOfficeLearn() {
        File file = new File("/home/kevin/Documents/test.odt");
        File destFile = new File("/home/kevin/Documents/test1.odt");
        file.renameTo(destFile);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        FileOperationLearn fileOperationLearn = new FileOperationLearn();
        fileOperationLearn.fileListLearn();
        fileOperationLearn.fileWalkingLearn();
//        fileOperationLearn.renameLearn();
        fileOperationLearn.basicLearn();
        fileOperationLearn.renameOfficeLearn();
    }
}
