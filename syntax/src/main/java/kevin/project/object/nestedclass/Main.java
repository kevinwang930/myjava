package kevin.project.object.nestedclass;

public class Main {


    public static void main(String[] args) {
        Outer.NestedStatic nestedStatic = new Outer.NestedStatic();
        nestedStatic.print();
        new Outer().new NestedNonStatic().print();
    }

}
