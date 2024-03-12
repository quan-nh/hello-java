import java.util.Arrays;

public enum TestEnum {
    MONDAY(1), TUESDAY(2);

    private final int value; // like class, enum can have field

    // construct is private
    // You cannot invoke an enum constructor yourself
    TestEnum(int value) {
        this.value = value;
    }

    // overwrite toString
    @Override
    public String toString() {
        return "TestEnum{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args){
        var monday = TestEnum.MONDAY;

        // call enum instance method
        System.out.println(monday.name());     // MONDAY
        System.out.println(monday.toString()); // TestEnum{value=1}
        System.out.println(monday.ordinal());  // 0
        System.out.println(monday.value);     // 1

        // get all enum values
        for (TestEnum e : TestEnum.values()) {
            System.out.println(e);
        }

        // from string to enum
        var tuesday = TestEnum.valueOf("TUESDAY");
        System.out.println(tuesday); // TestEnum{value=1}

        // from another value e.g. int, you have to do custom method
  }
}
