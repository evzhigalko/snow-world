package by.zhigalko.snow.world.entity.enums;

import java.util.Arrays;

public enum ProductGroup {
    SNOWBOARD("Сноуборд"),
    SNOWBOARD_BOOT("Ботинки сноубордные"),
    SNOWBOARD_HELMET("Шлем сноубордный"),
    SKI("Лыжи"),
    SKI_BOOT("Ботинки лыжные"),
    SKI_POLE("Палки лыжные"),
    SKI_HELMET("Шлем лыжный"),
    JACKET("Куртка"),
    PANTS("Штаны"),
    CAP("Шапка"),
    MASK("Маска"),
    MITTEN("Варежки"),
    GLOVE("Перчатки");

    private final String name;

    ProductGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
