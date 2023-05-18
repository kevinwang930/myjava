package kevin.project.scope.p2;

import kevin.project.scope.p1.Src;

public class Ext extends Src {
    @Override
    protected void protectMethod() {
        System.out.println("in overwrite method");
        super.protectMethod();
    }

    void packagePrivateMethod() {
        System.out.println("in overwrite method");
    }


    public static void main(String[] args) {
        Ext ext = new Ext();
        ext.protectMethod();
        ext.packagePrivateMethod();
    }
}
