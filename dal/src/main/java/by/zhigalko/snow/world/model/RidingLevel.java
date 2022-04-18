package by.zhigalko.snow.world.model;

public enum RidingLevel {
    BEGINNER("Новичок"),
    MEDIUM("Средний"),
    HIGH("Высокий"),
    EXPERT("Профессионал");

    private final String name;

    RidingLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
