package kevin.project.klass.privatelearn;

public class Outside {

    public static void main(String[] args) {
        Outer outer = new Outer();
        PrivateAccessable inner = outer.getInner();
        inner.print();
    }
}
