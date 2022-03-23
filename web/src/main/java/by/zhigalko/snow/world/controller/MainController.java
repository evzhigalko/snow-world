package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@SessionAttributes("pageNumber")
public class MainController {
    public static final int PAGE_SIZE = 6;
    private final ServiceEquipmentFactory serviceEquipmentFactory;

    @Autowired
    public MainController(ServiceEquipmentFactory serviceEquipmentFactory) {
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

    @GetMapping("/ski/pole/catalog/{page}")
    public String handleSkiPoleCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_POLE_LIST);
        paginate(model, pageNumber, service);
        List<EquipmentSize> skiPoleSizeList = service.findAllByProductGroup(ProductGroup.SKI_POLE);
        model.addAttribute("skiPoleSizeList", skiPoleSizeList);
        return "catalog/ski-pole-list";
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
        org.springframework.data.domain.Page<? extends Item> pageList = service.findAll(pageNumber - 1, PAGE_SIZE);
        int pagesNumber = pageList.getTotalPages();
        List<? extends Item> list = pageList.stream().collect(Collectors.toList());
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("list", list);
    }
}
