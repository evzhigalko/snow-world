package by.zhigalko.snowworld.model;

import java.util.Arrays;

public enum BucketName {
    SNOWBOARD("snowboard"),
    SKI("ski"),
    CLOTHES("clothes");

    private final String name;

    BucketName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BucketName of(String bucketName) {
        return Arrays.stream(values())
                .filter(value -> bucketName.contains(value.getName()))
                .findFirst().orElse(BucketName.CLOTHES);
    }
}
