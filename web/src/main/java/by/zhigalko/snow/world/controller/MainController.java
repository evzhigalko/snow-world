package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class MainController {
    public static final int PAGE_SIZE = 6;
    private final ApplicationContext context;
    private final ServiceEquipmentFactory serviceEquipmentFactory;

    @Autowired
    public MainController(ApplicationContext context, ServiceEquipmentFactory serviceEquipmentFactory) {
        this.context = context;
        this.serviceEquipmentFactory = serviceEquipmentFactory;
    }

    @GetMapping("/snowboard")
    public String handleSnowboardGroup() {
        return "snowboard-group";
    }

    @GetMapping("/ski")
    public String handleSkiGroup() {
        return "ski-group";
    }

    @GetMapping("/clothes")
    public String handleClothesGroup() {
        return "clothes-group";
    }

    @GetMapping("/contacts")
    public String handleContacts() {
        return "static/contacts";
    }

    @GetMapping("/form/login")
    public String handleLogin() {
        return "login";
    }

    @GetMapping("/form/registration")
    public String handleRegistration() {
        return "registration";
    }

    @GetMapping("/admin/")
    public String handleAdministration() {
        return "administration/admin";
    }

    @GetMapping("/snowboard/catalog/{page}")
    public String handleSnowboardCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SNOWBOARD_LIST);
        paginate(model, pageNumber, service);
        return "catalog/snowboard-list";
    }

    @GetMapping("/snowboard/boot/catalog/{page}")
    public String handleSnowboardBootCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SNOWBOARD_BOOT_LIST);
        paginate(model, pageNumber, service);
        return "catalog/snowboard-boot-list";
    }

    @GetMapping("/snowboard/helmet/catalog/{page}")
    public String handleSnowboardHelmetCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SNOWBOARD_HELMET_LIST);
        paginate(model, pageNumber, service);
        return "catalog/snowboard-helmet-list";
    }

    @GetMapping("/ski/catalog/{page}")
    public String handleSkiCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_LIST);
        paginate(model, pageNumber, service);
        BaseItemServiceImpl<? extends Item> skiPoleService = serviceEquipmentFactory.getService(Page.SKI_POLE_LIST);
        List<EquipmentSize> skiPoleSizeList = skiPoleService.findAllSizes();
        model.addAttribute("skiPoleSizeList", skiPoleSizeList);
        return "catalog/ski-list";
    }

    @GetMapping("/ski/boot/catalog/{page}")
    public String handleSkiBootCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_BOOT_LIST);
        paginate(model, pageNumber, service);
        return "catalog/ski-boot-list";
    }

    @GetMapping("/ski/helmet/catalog/{page}")
    public String handleSkiHelmetCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_HELMET_LIST);
        paginate(model, pageNumber, service);
        return "catalog/ski-helmet-list";
    }

    @GetMapping("/clothes/jacket/catalog/{page}")
    public String handleJacketCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.JACKET_LIST);
        paginate(model, pageNumber, service);
        return "catalog/jacket-list";
    }

    @GetMapping("/clothes/pants/catalog/{page}")
    public String handlePantsCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.PANTS_LIST);
        paginate(model, pageNumber, service);
        return "catalog/pants-list";
    }

    @GetMapping("/clothes/cap/catalog/{page}")
    public String handleCapCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.CAP_LIST);
        paginate(model, pageNumber, service);
        return "catalog/cap-list";
    }

    @GetMapping("/clothes/mask/catalog/{page}")
    public String handleMaskCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.MASK_LIST);
        paginate(model, pageNumber, service);
        return "catalog/mask-list";
    }

    @GetMapping("/clothes/mittens/catalog/{page}")
    public String handleMittensCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.MITTENS_LIST);
        paginate(model, pageNumber, service);
        return "catalog/mittens-list";
    }

    @GetMapping("/clothes/gloves/catalog/{page}")
    public String handleGlovesCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.GLOVES_LIST);
        paginate(model, pageNumber, service);
        return "catalog/gloves-list";
    }

    private void paginate(Model model, int pageNumber, BaseItemServiceImpl<? extends Item> service) {
        int page = (pageNumber - 1) * PAGE_SIZE;
        List<? extends Item> list = service.findAll(page, PAGE_SIZE);
        long entityNumber = service.count();
        long pagesNumber = (long) Math.ceil(entityNumber * 1.0 / PAGE_SIZE);
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("list", list);
    }
}
