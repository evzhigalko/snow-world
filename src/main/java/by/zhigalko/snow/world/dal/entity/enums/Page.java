package by.zhigalko.snow.world.dal.entity.enums;

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
    GLOVES_LIST("/clothes/gloves/catalog/","/WEB-INF/jsp/catalog/gloves-list.jsp");

    private String url;
    private String forwardPage;

    Page(String url, String forwardPage) {
        this.url = url;
        this.forwardPage = forwardPage;
    }

    public static Page getEnumClass(String url) {
        return Arrays.stream(Page.class.getEnumConstants())
//                .filter(e->e.)
//                .filter(e -> e.name.equals(value))
                .findFirst().orElse(null);
    }
}
