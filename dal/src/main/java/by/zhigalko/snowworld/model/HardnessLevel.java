package by.zhigalko.snowworld.model;

public enum HardnessLevel {
    ONE("1/10"),
    TWO("2/10"),
    THREE("3/10"),
    FOUR("4/10"),
    FIVE("5/10"),
    SIX("6/10"),
    SEVEN("7/10"),
    EIGHT("8/10"),
    NINE("9/10"),
    TEN("10/10");

    private final String name;

    HardnessLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
