package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.item.clothes.*;
import by.zhigalko.snow.world.service.item.ski.SkiBootService;
import by.zhigalko.snow.world.service.item.ski.SkiHelmetService;
import by.zhigalko.snow.world.service.item.ski.SkiPoleService;
import by.zhigalko.snow.world.service.item.ski.SkiService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardBootService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardHelmetService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardService;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ServiceEquipmentFactory {
    private final ApplicationContext context;

    @Autowired
    public ServiceEquipmentFactory(ApplicationContext context) {
        this.context = context;
    }

    public BaseItemService<? extends Item> getService(Product product) {
        BaseItemService<? extends Item> service = null;
        switch (product) {
            case SNOWBOARD:
                service = context.getBean("snowboardService", SnowboardService.class);
                break;
            case SNOWBOARD_BOOT:
                service = context.getBean("snowboardBootService", SnowboardBootService.class);
                break;
            case SNOWBOARD_HELMET:
                service = context.getBean("snowboardHelmetService", SnowboardHelmetService.class);
                break;
            case SKI:
                service = context.getBean("skiService", SkiService.class);
                break;
            case SKI_POLE:
                service = context.getBean("skiPoleService", SkiPoleService.class);
                break;
            case SKI_BOOT:
                service = context.getBean("skiBootService", SkiBootService.class);
                break;
            case SKI_HELMET:
                service = context.getBean("skiHelmetService", SkiHelmetService.class);
                break;
            case JACKET:
                service = context.getBean("jacketService", JacketService.class);
                break;
            case PANTS:
                service = context.getBean("pantsService", PantsService.class);
                break;
            case MASK:
                service = context.getBean("maskService", MaskService.class);
                break;
            case CAP:
                service = context.getBean("capService", CapService.class);
                break;
            case MITTEN:
                service = context.getBean("mittenService", MittenService.class);
                break;
            case GLOVE:
                service = context.getBean("gloveService", GloveService.class);
                break;
        }
        log.info("Got service " + service.getClass().getName());
        return service;
    }
}
