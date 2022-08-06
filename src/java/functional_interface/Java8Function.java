package functional_interface;

import java.util.function.Function;

// @FunctionalInterface
// public interface Function<T, R> {
//     R apply(T t);
// }
//
// T – Type of the input to the function.
// R – Type of the result of the function.

// https://mkyong.com/java8/java-8-function-examples/
// https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html
public class Java8Function {
    public static void main(String[] args) {

        Function<String, Integer> func = x -> x.length();
        Function<Integer, Integer> func2 = x -> x * 2;

        Integer apply = func.apply("mkyong");   // 6
        Integer result = func.andThen(func2).apply("mkyong");   // 12

        System.out.println(apply);
        System.out.println(result);

    }
}
