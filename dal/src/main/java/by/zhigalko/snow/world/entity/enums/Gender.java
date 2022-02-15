package by.zhigalko.snow.world.entity.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    UNISEX("Унисекс");

    private final String name;

    Gender(String name) {
        this.name = name;
    }
    public static Gender getEnum(String value) {
        return Arrays.stream(values())
                .filter(e -> e.getName().equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
