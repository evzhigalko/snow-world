package by.zhigalko.snow.world.service.util;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.service.BaseItemService;
import by.zhigalko.snow.world.service.impl.*;
import by.zhigalko.snow.world.service.impl.SkiBootService;
import by.zhigalko.snow.world.service.impl.SkiHelmetService;
import by.zhigalko.snow.world.service.impl.SkiPoleService;
import by.zhigalko.snow.world.service.impl.SkiService;
import by.zhigalko.snow.world.service.impl.SnowboardBootService;
import by.zhigalko.snow.world.service.impl.SnowboardHelmetService;
import by.zhigalko.snow.world.service.impl.SnowboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
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
