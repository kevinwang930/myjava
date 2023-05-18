package kevin.project.klass;

class Number {
    int n;

    Number(int n) {
        this.n = n;
    }

    boolean eq(Number other) {
        if (this.n == other.n) {
            System.out.println("The two numbers are equal");
            return true;
        } else {
            System.out.println("The two numbers are equal");
            return false;
        }
    }
}

class ColorNumber extends Number {
    String color;

    ColorNumber(int n, String color) {
        super(n);
        this.color = color;
    }


    boolean eq(ColorNumber other) {
        if (this.n == other.n && this.color == other.color) {
            System.out.println("The two ColorNumbers are equal");
            return true;
        } else {
            System.out.println("The two ColorNumbers are equal");
            return false;
        }
    }
}

public class InheritanceLearn {

    public static void iLearn() {
        Number number = new Number(1);
        ColorNumber cnumber = new ColorNumber(1, "black");
        number.eq(cnumber);
        cnumber.eq(number);
    }

    public static void main(String[] args) {
        iLearn();
    }
}
