package by.zhigalko.snow.world.dal.entity.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LacingSystem {
    BOA("Кнопка"),
    LACES("Шнурки");

    private final String mechanism;

    LacingSystem(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getMechanism() {
        return mechanism;
    }

    public static LacingSystem getEnumClass(String value) {
        return Arrays.stream(LacingSystem.class.getEnumConstants())
                .filter(e -> e.mechanism.equals(value))
                .findFirst().orElse(null);
    }
}
