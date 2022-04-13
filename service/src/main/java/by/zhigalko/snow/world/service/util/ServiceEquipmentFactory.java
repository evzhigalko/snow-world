package by.zhigalko.snow.world.service.util;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.service.BaseItemService;
import by.zhigalko.snow.world.service.impl.*;
import by.zhigalko.snow.world.service.impl.SkiBootServiceImpl;
import by.zhigalko.snow.world.service.impl.SkiHelmetServiceImpl;
import by.zhigalko.snow.world.service.impl.SkiPoleServiceImpl;
import by.zhigalko.snow.world.service.impl.SkiServiceImpl;
import by.zhigalko.snow.world.service.impl.SnowboardBootServiceImpl;
import by.zhigalko.snow.world.service.impl.SnowboardHelmetServiceImpl;
import by.zhigalko.snow.world.service.impl.SnowboardServiceImpl;
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
                service = context.getBean("snowboardService", SnowboardServiceImpl.class);
                break;
            case SNOWBOARD_BOOT:
                service = context.getBean("snowboardBootService", SnowboardBootServiceImpl.class);
                break;
            case SNOWBOARD_HELMET:
                service = context.getBean("snowboardHelmetService", SnowboardHelmetServiceImpl.class);
                break;
            case SKI:
                service = context.getBean("skiService", SkiServiceImpl.class);
                break;
            case SKI_POLE:
                service = context.getBean("skiPoleService", SkiPoleServiceImpl.class);
                break;
            case SKI_BOOT:
                service = context.getBean("skiBootService", SkiBootServiceImpl.class);
                break;
            case SKI_HELMET:
                service = context.getBean("skiHelmetService", SkiHelmetServiceImpl.class);
                break;
            case JACKET:
                service = context.getBean("jacketService", JacketServiceImpl.class);
                break;
            case PANTS:
                service = context.getBean("pantsService", PantsServiceImpl.class);
                break;
            case MASK:
                service = context.getBean("maskService", MaskServiceImpl.class);
                break;
            case CAP:
                service = context.getBean("capService", CapServiceImpl.class);
                break;
            case MITTEN:
                service = context.getBean("mittenService", MittenServiceImpl.class);
                break;
            case GLOVE:
                service = context.getBean("gloveService", GloveServiceImpl.class);
                break;
        }
        log.info("Got service " + service.getClass().getName());
        return service;
    }
}
