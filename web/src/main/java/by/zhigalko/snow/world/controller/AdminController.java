package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
public class AdminController {
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final UserService userService;

    @Autowired
    public AdminController(ServiceEquipmentFactory serviceEquipmentFactory, UserService userService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("usersList", userList);
        return "administration/admin";
    }

    @PostMapping("/admin/snowboard/catalog/{id}")
    public String changeSnowboardOnAdminPage(@RequestParam("cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SNOWBOARD_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/snowboard/catalog/" + pageNumber;
    }

    @PostMapping("/admin/snowboard/boot/catalog/{id}")
    public String changeSnowboardBootOnAdminPage(@RequestParam("cost") String cost,
                                                 @RequestParam("availability") String availableToRental,
                                                 @PathVariable("id") UUID id,
                                                 @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SNOWBOARD_BOOT_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/snowboard/boot/catalog/" + pageNumber;
    }

    @PostMapping("/admin/snowboard/helmet/catalog/{id}")
    public String changeSnowboardHelmetOnAdminPage(@RequestParam("cost") String cost,
                                                   @RequestParam("availability") String availableToRental,
                                                   @PathVariable("id") UUID id,
                                                   @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SNOWBOARD_HELMET_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/snowboard/helmet/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/catalog/{id}")
    public String changeSkiOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/ski/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/helmet/catalog/{id}")
    public String changeSkiHelmetOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_HELMET_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/ski/helmet/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/boot/catalog/{id}")
    public String changeSkiBootOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.SKI_BOOT_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/ski/boot/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/cap/catalog/{id}")
    public String changeCapOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.CAP_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/clothes/cap/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/gloves/catalog/{id}")
    public String changeGlovesOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.GLOVES_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/clothes/gloves/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/jacket/catalog/{id}")
    public String changeJacketOnAdminPage(@RequestParam("cost") String cost,
                                         @RequestParam("availability") String availableToRental,
                                         @PathVariable("id") UUID id,
                                         @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.JACKET_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/clothes/jacket/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/mask/catalog/{id}")
    public String changeMaskOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.MASK_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/clothes/mask/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/mittens/catalog/{id}")
    public String changeMittensOnAdminPage(@RequestParam("cost") String cost,
                                        @RequestParam("availability") String availableToRental,
                                        @PathVariable("id") UUID id,
                                        @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.MITTENS_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/clothes/mittens/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/pants/catalog/{id}")
    public String changePantsOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemServiceImpl<? extends Item> service = serviceEquipmentFactory.getService(Page.PANTS_LIST);
        changeItem(cost, availableToRental, id, service);
        return "redirect:/clothes/pants/catalog/" + pageNumber;
    }

    private void changeItem(@RequestParam("cost") String cost, @RequestParam("availability") String availableToRental, @PathVariable("id") UUID id, BaseItemServiceImpl service) {
        Item item = service.findById(id);
        if (!cost.isEmpty()) {
            service.updateCost(item, Double.parseDouble(cost));
            log.info(">>> Changed item" + item + " : " + "cost " + cost);
        }
        if (!availableToRental.isEmpty()) {
            service.updateAvailable(item, Boolean.parseBoolean(availableToRental));
            log.info(">>> Changed item" + item + " : " + "available " + availableToRental);
        }
    }
}
