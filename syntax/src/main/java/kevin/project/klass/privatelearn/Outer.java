package kevin.project.klass.privatelearn;

public class Outer {

    private class Inner implements PrivateAccessable {
        public void print() {
            System.out.println("inner");
        }
    }


    public PrivateAccessable getInner() {
        return new Inner();
    }
}
