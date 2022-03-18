package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentAllSizesService;
import by.zhigalko.snow.world.service.item.clothes.*;
import by.zhigalko.snow.world.service.item.ski.SkiBootService;
import by.zhigalko.snow.world.service.item.ski.SkiHelmetService;
import by.zhigalko.snow.world.service.item.ski.SkiPoleService;
import by.zhigalko.snow.world.service.item.ski.SkiService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardBootService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardHelmetService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceEquipmentFactory {
    private final ApplicationContext context;

    @Autowired
    public ServiceEquipmentFactory(ApplicationContext context) {
        this.context = context;
    }

    public BaseItemServiceImpl<? extends Item> getService(Page page) {
        BaseItemServiceImpl<? extends Item> service = null;
        switch (page) {
            case SNOWBOARD_LIST:
                service = context.getBean("snowboardService", SnowboardService.class);
                break;
            case SNOWBOARD_BOOT_LIST:
                service = context.getBean("snowboardBootService", SnowboardBootService.class);
                break;
            case SNOWBOARD_HELMET_LIST:
                service = context.getBean("snowboardHelmetService", SnowboardHelmetService.class);
                break;
            case SKI_LIST:
                service = context.getBean("skiService", SkiService.class);
                break;
            case SKI_POLE_LIST:
                service = context.getBean("skiPoleService", SkiPoleService.class);
                break;
            case SKI_BOOT_LIST:
                service = context.getBean("skiBootService", SkiBootService.class);
                break;
            case SKI_HELMET_LIST:
                service = context.getBean("skiHelmetService", SkiHelmetService.class);
                break;
            case JACKET_LIST:
                service = context.getBean("jacketService", JacketService.class);
                break;
            case PANTS_LIST:
                service = context.getBean("pantsService", PantsService.class);
                break;
            case MASK_LIST:
                service = context.getBean("maskService", MaskService.class);
                break;
            case CAP_LIST:
                service = context.getBean("capService", CapService.class);
                break;
            case MITTENS_LIST:
                service = context.getBean("mittenService", MittenService.class);
                break;
            case GLOVES_LIST:
                service = context.getBean("gloveService", GloveService.class);
                break;
        }
        return service;
    }

    public EquipmentAllSizesService getAllSizesService(String itemName) {
        EquipmentAllSizesService allSizesService = null;
        switch (itemName) {
            case "snowboard":
                allSizesService = context.getBean("snowboardService", SnowboardService.class);
                break;
            case "snowboard_boot":
                allSizesService = context.getBean("snowboardBootService", SnowboardBootService.class);
                break;
            case "snowboard_helmet":
                allSizesService = context.getBean("snowboardHelmetService", SnowboardHelmetService.class);
                break;
            case "ski":
                allSizesService = context.getBean("skiService", SkiService.class);
                break;
            case "ski_boot":
                allSizesService = context.getBean("skiBootService", SkiBootService.class);
                break;
            case "ski_helmet":
                allSizesService = context.getBean("skiHelmetService", SkiHelmetService.class);
                break;
            case "ski_pole":
                allSizesService = context.getBean("skiPoleService", SkiPoleService.class);
                break;
            case "jacket":
                allSizesService = context.getBean("jacketService", JacketService.class);
                break;
            case "pants":
                allSizesService = context.getBean("pantsService", PantsService.class);
                break;
            case "glove":
                allSizesService = context.getBean("gloveService", GloveService.class);
                break;
            case "mitten":
                allSizesService = context.getBean("mittenService", MittenService.class);
                break;
            case "mask":
                allSizesService = context.getBean("maskService", MaskService.class);
                break;
            case "cap":
                allSizesService = context.getBean("capService", CapService.class);
                break;
        }
        return allSizesService;
    }
}
