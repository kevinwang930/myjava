package kevin.project.object;

class Test {
    private String name;
    Test(String name) {
        this.name = name;
    }
    public void testMethod() {
        System.out.println("testMethod");
    }

    public void getName() {
        System.out.println(name);
    }
}
public class NewObjectLearn {
    public void testNew() {
        Test test = new Test("testNew") {
            @Override
            public void testMethod() {
                System.out.println("testMethod2");
            }

            @Override
            public void getName() {
                System.out.println("testNew");
                super.getName();
            }
        };
        test.getName();
    }

    public static void main(String[] args) {
        NewObjectLearn newObjectLearn = new NewObjectLearn();
        newObjectLearn.testNew();
    }
}
