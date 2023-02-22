package kevin.project.jvm.classLoading;

class Test {

    static int i ;
    static int j ;
    static {
        i = i + 1;
    }

    static Test obj = new Test();

    int b = i;

    static {
        j = i;
    }
}

public class sequenceLearn {

    public void staticLoadingLearn() {
        System.out.println(Test.i);
        System.out.println(Test.j);
        System.out.println(Test.obj.i);
        System.out.println(Test.obj.b);

    }

    public static void main(String[] args) {
        sequenceLearn sequenceLearn = new sequenceLearn();
        sequenceLearn.staticLoadingLearn();
    }
}
