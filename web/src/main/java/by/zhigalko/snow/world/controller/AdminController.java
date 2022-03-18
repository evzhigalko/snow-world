package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.service.common.AdminItemService;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentAllSizesService;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@SessionAttributes("item")
public class AdminController {
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final UserService userService;
    private final AdminItemService adminItemService;

    @Autowired
    public AdminController(ServiceEquipmentFactory serviceEquipmentFactory, UserService userService, AdminItemService adminItemService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.userService = userService;
        this.adminItemService = adminItemService;
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

    @GetMapping("/admin/create/new/{item}")
    public String showAdminCreateItemPage(Model model, @PathVariable("item") String item) {
        EquipmentAllSizesService allSizesService = serviceEquipmentFactory.getAllSizesService(item);
        List<EquipmentSize> allSizes = allSizesService.findAllSizes();
        model.addAttribute("allSizes", allSizes);
        model.addAttribute("item", item);
        return "administration/create-new-item";
    }

    @PostMapping(value = "/admin/create/new/snowboard")
    public ModelAndView createNewSnowboard(@RequestParam("file") MultipartFile filePart,
                                           HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "snowboard");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/snowboard");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/snowboard-boot")
    public ModelAndView createNewSnowboardBoot(@RequestParam("file") MultipartFile filePart,
                                           HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "snowboard_boot");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/snowboard-boot");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/snowboard-helmet")
    public ModelAndView createNewSnowboardHelmet(@RequestParam("file") MultipartFile filePart,
                                               HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "snowboard_helmet");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/snowboard-helmet");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/ski")
    public ModelAndView createNewSki(@RequestParam("file") MultipartFile filePart,
                                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "ski");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/ski");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/ski-boot")
    public ModelAndView createNewSkiBoot(@RequestParam("file") MultipartFile filePart,
                                               HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "ski_boot");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/ski-boot");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/ski-helmet")
    public ModelAndView createNewSkiHelmet(@RequestParam("file") MultipartFile filePart,
                                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "ski_helmet");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/ski-helmet");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/ski-pole")
    public ModelAndView createNewSkiPole(@RequestParam("file") MultipartFile filePart,
                                         HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "ski_pole");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/ski-pole");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("/admin/create/new/clothes/{item}")
    public String showAdminCreateClothesItemPage(Model model, @PathVariable("item") String item) {
        EquipmentAllSizesService allSizesService = serviceEquipmentFactory.getAllSizesService(item);
        List<EquipmentSize> allSizes = allSizesService.findAllSizes();
        model.addAttribute("allSizes", allSizes);
        model.addAttribute("item", item);
        return "administration/create-new-item";
    }

    @PostMapping(value = "/admin/create/new/clothes/jacket")
    public ModelAndView createNewJacket(@RequestParam("file") MultipartFile filePart,
                                        HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "jacket");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/jacket");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/clothes/cap")
    public ModelAndView createNewCap(@RequestParam("file") MultipartFile filePart,
                                        HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "cap");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/cap");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/clothes/glove")
    public ModelAndView createNewGlove(@RequestParam("file") MultipartFile filePart,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "glove");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/glove");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    @PostMapping(value = "/admin/create/new/clothes/mitten")
    public ModelAndView createNewMitten(@RequestParam("file") MultipartFile filePart,
                                       HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "mitten");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/mitten");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/clothes/mask")
    public ModelAndView createNewMask(@RequestParam("file") MultipartFile filePart,
                                        HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "mask");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/mask");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/admin/create/new/clothes/pants")
    public ModelAndView createNewPants(@RequestParam("file") MultipartFile filePart,
                                      HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(request, filePart, "pants");
            if (isSaved) {
                mav.setViewName("administration/admin");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/pants");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    private void changeItem(@RequestParam("cost") String cost,
                            @RequestParam("availability") String availableToRental,
                            @PathVariable("id") UUID id,
                            BaseItemServiceImpl service) {
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
