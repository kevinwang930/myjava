package kevin.project.scope;

public class FunctionOrigin {

    private int number = 0;

    private void increaseNum() {
        number = number + 1;
    }

    private void showNumber(Integer number) {
        System.out.println("number is "+number);
    }

    private void functionalTest() {
        FunctionUtil.try1(this::increaseNum);
        FunctionUtil.try2(this::showNumber,number);
    }

    public static void main(String[] args) {
        FunctionOrigin functionOrigin = new FunctionOrigin();
        functionOrigin.functionalTest();
    }
}
