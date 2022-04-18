package by.zhigalko.snowworld.model;

import java.util.Arrays;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    UNISEX("Унисекс");

    private final String name;

    public String getName() {
        return name;
    }

    Gender(String name) {
        this.name = name;
    }
    public static Gender getEnum(String value) {
        return Arrays.stream(values())
                .filter(e -> e.getName().equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
