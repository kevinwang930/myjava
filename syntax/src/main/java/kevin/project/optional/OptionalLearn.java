package kevin.project.optional;

import java.util.Optional;

public class OptionalLearn {
    public static void testOptional() {
        Optional<String> stringOptional = Optional.of("test");

    }

    public static void nullableLearn() {
        Optional.ofNullable(null).ifPresent(System.out::println);
    }

    public static void main(String[] args) {

    }
}
