package kevin.project.object;


public class ClassLearn {

    private int i;

    public void classTypeLearn() {
        Class<?> clazz = String.class;
        System.out.println(clazz.isAssignableFrom(String.class));
    }

    public void testInnerClass() {
        InnerClass innerClass = new InnerClass();
        innerClass.showOuter();
    }

    public void instanceLearn() {
        Integer a = 3;
        if (a instanceof Object obj) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) {
        ClassLearn classLearn = new ClassLearn();
        classLearn.classTypeLearn();
        classLearn.testInnerClass();
        classLearn.instanceLearn();
    }


    class InnerClass {
        public void showOuter() {
            System.out.println(i);
        }
    }
}
