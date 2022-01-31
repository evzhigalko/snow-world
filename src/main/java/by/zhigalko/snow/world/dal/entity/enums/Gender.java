package by.zhigalko.snow.world.dal.entity.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    UNISEX("Унисекс");

    private final String name;

    Gender(String name) {
        this.name = name;
    }
}
