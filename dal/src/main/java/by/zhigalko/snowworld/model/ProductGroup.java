package by.zhigalko.snowworld.model;

public enum ProductGroup {
    EQUIPMENT,
    BOOTS,
    SKI,
    SKI_POLE,
    SNOWBOARD;

    public static ProductGroup getProduct(String item) {
        switch (item) {
            case "jackets":
            case "pants":
            case "masks":
            case "caps":
            case "mittens":
            case "gloves":
            case "ski-helmets":
            case "snowboard-helmets":
                return EQUIPMENT;
            case "ski-boots":
            case "snowboard-boots":
                return BOOTS;
            case "ski":
                return SKI;
            case "ski-poles":
                return SKI_POLE;
            case "snowboards":
                return SNOWBOARD;
        }
        return null;
    }
}
