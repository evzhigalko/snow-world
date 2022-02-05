package by.zhigalko.snow.world.dal.entity.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Navbar {
    SNOWBOARD_GROUP("/snowboard","/WEB-INF/jsp/snowboard-group.jsp"),
    SKI_GROUP("/ski","/WEB-INF/jsp/ski-group.jsp"),
    CLOTHES_GROUP("/clothes","/WEB-INF/jsp/clothes-group.jsp");

    private final String url;
    private final String forwardPage;

    Navbar(String url, String forwardPage) {
        this.url = url;
        this.forwardPage = forwardPage;
    }

    public static Navbar getEnum(String urlPath) {
        return Arrays.stream(values())
                .filter(p -> urlPath.contains(p.url))
                .findFirst()
                .orElse(null);
    }
}
