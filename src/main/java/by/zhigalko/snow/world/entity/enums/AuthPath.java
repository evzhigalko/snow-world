package by.zhigalko.snow.world.entity.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum AuthPath {
    LOGIN("/login"),
    LOGOUT("/logout");

    private final String path;

    AuthPath(String path) {
        this.path = path;
    }

    public static AuthPath getEnum(String authPath) {
        return Arrays.stream(values())
                .filter(p -> authPath.contains(p.path))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
