package kevin.project.object.nestedclass;

public class Outer {

    private static final String OUTER_STATIC = "outer static";

    private final String OUTER_NON_STATIC = "outer non static";

    public static class NestedStatic {

        public void print() {
            System.out.println("nested static " + OUTER_STATIC);
        }
    }

    public class NestedNonStatic {

        public void print() {
            System.out.println("nested non static " + OUTER_NON_STATIC);
        }
    }
}
