package by.zhigalko.snowworld.model;

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
            case "ski-helmet":
            case "snowboard-helmet":
                return EQUIPMENT;
            case "ski-boot":
            case "snowboard-boot":
                return BOOTS;
            case "ski":
                return SKI;
            case "ski-pole":
                return SKI_POLE;
            case "snowboard":
                return SNOWBOARD;
        }
        return null;
    }
}
