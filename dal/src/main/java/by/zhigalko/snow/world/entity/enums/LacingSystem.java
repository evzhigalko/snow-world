package by.zhigalko.snow.world.entity.enums;

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
}
