package kevin.project;

public class Factorial {
    private Long temp = 0L;

    public Long factorial(Long n) {
        if (n == 1) {
            return 1L;
        }
        Long temp = 1L;
        for (Long i = 1L; i <= n; i++) {
            temp = temp *i;
        }
        return temp;
    }

    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println(factorial.factorial(4L));
    }
}
