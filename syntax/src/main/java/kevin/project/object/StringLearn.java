package kevin.project.object;

public class StringLearn {

    public static void replaceLearn() {
        String a = "[\"/_lucky_/file/download?fileName=2024/04/20240429151350681o90.56.04.png\"]";
        System.out.println(a.replaceAll("/_lucky_/file/download\\?fileName=","test/"));
    }

    public static void main(String[] args) {
        replaceLearn();
    }
}
