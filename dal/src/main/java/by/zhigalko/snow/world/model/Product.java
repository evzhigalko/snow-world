package by.zhigalko.snow.world.model;

public enum Product {
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

    Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
