package by.zhigalko.snow.world.entity.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Page {
    SNOWBOARD_LIST ("/snowboard/catalog/","/WEB-INF/jsp/catalog/snowboard-list.jsp"),
    SNOWBOARD_BOOT_LIST("/snowboard/boot/catalog/","/WEB-INF/jsp/catalog/snowboard-boot-list.jsp"),
    SNOWBOARD_HELMET_LIST("/snowboard/helmet/catalog/", "/WEB-INF/jsp/catalog/snowboard-helmet-list.jsp"),
    SKI_LIST("/ski/catalog/","/WEB-INF/jsp/catalog/ski-list.jsp"),
    SKI_BOOT_LIST("/ski/boot/catalog/","/WEB-INF/jsp/catalog/ski-boot-list.jsp"),
    SKI_HELMET_LIST("/ski/helmet/catalog/", "/WEB-INF/jsp/catalog/ski-helmet-list.jsp"),
    JACKET_LIST("/clothes/jacket/catalog/","/WEB-INF/jsp/catalog/jacket-list.jsp"),
    PANTS_LIST("/clothes/pants/catalog/","/WEB-INF/jsp/catalog/pants-list.jsp"),
    CAP_LIST("/clothes/cap/catalog/","/WEB-INF/jsp/catalog/cap-list.jsp"),
    MASK_LIST("/clothes/mask/catalog/","/WEB-INF/jsp/catalog/mask-list.jsp"),
    MITTENS_LIST( "/clothes/mittens/catalog/","/WEB-INF/jsp/catalog/mittens-list.jsp"),
    GLOVES_LIST("/clothes/gloves/catalog/","/WEB-INF/jsp/catalog/gloves-list.jsp"),
    SNOWBOARD_GROUP("/snowboard","/WEB-INF/jsp/snowboard-group.jsp"),
    SKI_GROUP("/ski","/WEB-INF/jsp/ski-group.jsp"),
    CLOTHES_GROUP("/clothes","/WEB-INF/jsp/clothes-group.jsp"),
    CONTACTS("/contacts","/WEB-INF/static/contacts.jsp"),
    LOGIN_FORM("/form/login","/WEB-INF/jsp/login.jsp"),
    REGISTRATION_FORM("/form/registration", "/WEB-INF/jsp/registration.jsp"),
    ADMIN_PAGE("/admin/", "/WEB-INF/jsp/admin/admin.jsp");

    private final String url;
    private final String forwardPage;

    Page(String url, String forwardPage) {
        this.url = url;
        this.forwardPage = forwardPage;
    }

    public static Page getEnum(String urlPath) {
        return Arrays.stream(values())
                .filter(p -> urlPath.contains(p.url))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
