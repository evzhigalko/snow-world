package by.zhigalko.snow.world.entity.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Navbar {
    SNOWBOARD_GROUP("/snowboard","/WEB-INF/jsp/snowboard-group.jsp"),
    SKI_GROUP("/ski","/WEB-INF/jsp/ski-group.jsp"),
    CLOTHES_GROUP("/clothes","/WEB-INF/jsp/clothes-group.jsp"),
    CONTACTS("/contacts","/WEB-INF/static/contacts.jsp"),
    LOGIN("/form/login","/WEB-INF/jsp/login.jsp"),
    REGISTRATION("/form/registration", "/WEB-INF/jsp/registration.jsp");

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
                .orElseThrow(IllegalArgumentException::new);
    }
}
