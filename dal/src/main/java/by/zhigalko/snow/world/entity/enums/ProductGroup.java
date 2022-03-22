package by.zhigalko.snow.world.entity.enums;

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
    }
}
