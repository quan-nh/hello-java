
## Functional Interface

### Function
Take an argument (object of type T) and returns an object (object of type R)
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

### BiFunction
Take two arguments and returns an object
```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```

### UnaryOperator
Take one argument, and returns a result of the same type of its arguments.
```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
}
```

### BinaryOperator
Take two arguments of the same type and returns a result of the same type of its arguments.
```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
}
```

### Supplier
Take no arguments and returns a result
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

### Consumer
Take an argument and returns nothing
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

### BiConsumer
Take two arguments and returns nothing
```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```

### Predicate
Accept an argument and returns a boolean. Usually, it used to apply in a filter for a collection of objects.
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

### BiPredicate
Accept two arguments and returns a boolean
```java
@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```