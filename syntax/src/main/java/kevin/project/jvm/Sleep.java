package kevin.project.jvm;

public class Sleep {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            System.out.println( "sleep interrupted");
            e.printStackTrace();
        }
    }
}
