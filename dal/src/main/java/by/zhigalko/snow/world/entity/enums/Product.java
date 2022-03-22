package by.zhigalko.snow.world.entity.enums;

<<<<<<< HEAD
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
=======
public enum ProductGroup {
    EQUIPMENT,
    BOOTS,
    SKI,
    SKI_POLE,
    SNOWBOARD;

    public static ProductGroup getProduct(String item) {
        switch (item) {
            case "jacket":
            case "pants":
            case "mask":
            case "cap":
            case "mitten":
            case "glove":
            case "ski_helmet":
            case "snowboard_helmet":
                return EQUIPMENT;
            case "ski_boot":
            case "snowboard_boot":
                return BOOTS;
            case "ski":
                return SKI;
            case "ski_pole":
                return SKI_POLE;
            case "snowboard":
                return SNOWBOARD;
        }
        return null;
>>>>>>> b0b6416fe573512e4c6fa94e42262b09349dd95c
    }
}
