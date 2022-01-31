package by.zhigalko.snow.world.dal.entity.enums;

import java.util.Arrays;

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

    public static RidingLevel getEnumClass(String value) {
        return Arrays.stream(RidingLevel.class.getEnumConstants())
                .filter(e -> e.name.equals(value))
                .findFirst().orElse(null);
    }
}
