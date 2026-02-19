package kevin.project.optional;

import java.util.Optional;

public class OptionalLearn {
    public static void testOptional() {
        // Example: wrap a nullable object and map it if present, else return null
        String nullableString = "hello";
        String result = Optional.ofNullable(nullableString)
                .map(String::toUpperCase)
                .orElse(null);
        System.out.println("Result with value: " + result);

        // Example with null value
        String nullString = null;
        String resultNull = Optional.ofNullable(nullString)
                .map(String::toUpperCase)
                .orElse(null);
        System.out.println("Result with null: " + resultNull);
    }

    public static void nullableLearn() {
        Optional.ofNullable(null).ifPresent(System.out::println);
    }

    public static void main(String[] args) {

    }
}
