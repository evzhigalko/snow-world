package by.zhigalko.snow.world.entity.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Page {
    SNOWBOARD_LIST ("/snowboard/catalog/","catalog/snowboard-list"),
    SNOWBOARD_BOOT_LIST("/snowboard/boot/catalog/","catalog/snowboard-boot-list"),
    SNOWBOARD_HELMET_LIST("/snowboard/helmet/catalog/", "catalog/snowboard-helmet-list"),
    SKI_LIST("/ski/catalog/","catalog/ski-list"),
    SKI_BOOT_LIST("/ski/boot/catalog/","catalog/ski-boot-list"),
    SKI_HELMET_LIST("/ski/helmet/catalog/", "catalog/ski-helmet-list"),
    JACKET_LIST("/clothes/jacket/catalog/","catalog/jacket-list"),
    PANTS_LIST("/clothes/pants/catalog/","catalog/pants-list"),
    CAP_LIST("/clothes/cap/catalog/","catalog/cap-list"),
    MASK_LIST("/clothes/mask/catalog/","catalog/mask-list"),
    MITTENS_LIST( "/clothes/mittens/catalog/","catalog/mittens-list"),
    GLOVES_LIST("/clothes/gloves/catalog/","catalog/gloves-list"),
    SNOWBOARD_GROUP("/snowboard","snowboard-group"),
    SKI_GROUP("/ski","ski-group"),
    CLOTHES_GROUP("/clothes","clothes-group"),
    CONTACTS("/contacts","static/contacts"),
    LOGIN_FORM("/form/login","login"),
    REGISTRATION_FORM("/form/registration", "registration"),
    ADMIN_PAGE("/admin/", "administration/admin");

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
