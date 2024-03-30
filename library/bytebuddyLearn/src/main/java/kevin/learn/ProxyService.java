package kevin.learn;

public class ProxyService {
    public static String sayHello(String name) {
        return "from proxy:Hello " + name;
    }

    public String sayFoo(String name) {
        return "from proxy: hello " + name;
    }

    public Integer lengthOfName(String name) {
        return name.length();
    }
}