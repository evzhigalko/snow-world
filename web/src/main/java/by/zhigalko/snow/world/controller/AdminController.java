package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.dto.item.request.*;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.service.admin.AdminItemService;
import by.zhigalko.snow.world.service.item.BaseItemService;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@SessionAttributes({"user", "ROLE"})
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
    public String showAdminPage(Authentication authentication, Model model) {
        List<UserResponse> userList = userService.findAllUsers();
        model.addAttribute("usersList", userList);
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        model.addAttribute("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        return "administration/admin";
    }

    @PostMapping("/admin/snowboard/catalog/{id}")
    public String changeSnowboardOnAdminPage(@RequestParam(value = "cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/snowboard/catalog/" + pageNumber;
    }

    @PostMapping("/admin/snowboard/boot/catalog/{id}")
    public String changeSnowboardBootOnAdminPage(@RequestParam("cost") String cost,
                                                 @RequestParam("availability") String availableToRental,
                                                 @PathVariable("id") UUID id,
                                                 @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_BOOT);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/snowboard/boot/catalog/" + pageNumber;
    }

    @PostMapping("/admin/snowboard/helmet/catalog/{id}")
    public String changeSnowboardHelmetOnAdminPage(@RequestParam("cost") String cost,
                                                   @RequestParam("availability") String availableToRental,
                                                   @PathVariable("id") UUID id,
                                                   @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_HELMET);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/snowboard/helmet/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/catalog/{id}")
    public String changeSkiOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/ski/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/helmet/catalog/{id}")
    public String changeSkiHelmetOnAdminPage(@RequestParam("cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_HELMET);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/ski/helmet/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/boot/catalog/{id}")
    public String changeSkiBootOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_BOOT);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/ski/boot/catalog/" + pageNumber;
    }

    @PostMapping("/admin/ski/pole/catalog/{id}")
    public String changeSkiPoleOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_POLE);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/ski/pole/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/cap/catalog/{id}")
    public String changeCapOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.CAP);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/clothes/cap/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/gloves/catalog/{id}")
    public String changeGlovesOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.GLOVE);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/clothes/gloves/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/jacket/catalog/{id}")
    public String changeJacketOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.JACKET);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/clothes/jacket/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/mask/catalog/{id}")
    public String changeMaskOnAdminPage(@RequestParam("cost") String cost,
                                        @RequestParam("availability") String availableToRental,
                                        @PathVariable("id") UUID id,
                                        @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MASK);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/clothes/mask/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/mittens/catalog/{id}")
    public String changeMittensOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MITTEN);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/clothes/mittens/catalog/" + pageNumber;
    }

    @PostMapping("/admin/clothes/pants/catalog/{id}")
    public String changePantsOnAdminPage(@RequestParam("cost") String cost,
                                         @RequestParam("availability") String availableToRental,
                                         @PathVariable("id") UUID id,
                                         @SessionAttribute("pageNumber") int pageNumber) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.PANTS);
        adminItemService.updateItem(cost, availableToRental, id, service);
        return "redirect:/clothes/pants/catalog/" + pageNumber;
    }

    @GetMapping("/admin/create/new/{item}")
    public String showAdminCreateItemPage(Model model, @PathVariable("item") String item) {
        Product product = Product.valueOf(item.toUpperCase());
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(product);
        List<EquipmentSize> allSizes = service.findAllByProductGroup(ProductGroup.getProduct(item));
        model.addAttribute("allSizes", allSizes);
        model.addAttribute("item", item);
        return "administration/create-new-item";
    }

    @PostMapping(value = "/admin/create/new/snowboard")
    public ModelAndView createNewSnowboard(@RequestParam("file") MultipartFile filePart,
                                           SnowboardRequest snowboardRequest) {
        System.out.println(snowboardRequest);
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(snowboardRequest, filePart, "snowboard");
            if (isSaved) {
                mav.setViewName("redirect:/snowboard/catalog/1");
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
                                               SnowboardBootRequest snowboardBootRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(snowboardBootRequest, filePart, "snowboard_boot");
            if (isSaved) {
                mav.setViewName("redirect:/snowboard/boot/catalog/1");
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
                                                 SnowboardHelmetRequest snowboardHelmetRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(snowboardHelmetRequest, filePart, "snowboard_helmet");
            if (isSaved) {
                mav.setViewName("redirect:/snowboard/helmet/catalog/1");
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
                                     SkiRequest skiRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(skiRequest, filePart, "ski");
            if (isSaved) {
                mav.setViewName("redirect:/ski/catalog/1");
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
                                               SkiBootRequest skiBootRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(skiBootRequest, filePart, "ski_boot");
            if (isSaved) {
                mav.setViewName("redirect:/ski/boot/catalog/1");
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
                                                 SkiHelmetRequest skiHelmetRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(skiHelmetRequest, filePart, "ski_helmet");
            if (isSaved) {
                mav.setViewName("redirect:/ski/helmet/catalog/1");
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
                                         SkiPoleRequest skiPoleRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(skiPoleRequest, filePart, "ski_pole");
            if (isSaved) {
                mav.setViewName("redirect:/ski/catalog/1");
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
        Product product = Product.valueOf(item.toUpperCase());
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(product);
        List<EquipmentSize> allSizes = service.findAllByProductGroup(ProductGroup.EQUIPMENT);
        model.addAttribute("allSizes", allSizes);
        model.addAttribute("item", item);
        return "administration/create-new-item";
    }

    @PostMapping(value = "/admin/create/new/clothes/jacket")
    public ModelAndView createNewJacket(@RequestParam("file") MultipartFile filePart,
                                        JacketRequest jacketRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(jacketRequest, filePart, "jacket");
            if (isSaved) {
                mav.setViewName("redirect:/clothes/jacket/catalog/1");
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
                                        CapRequest capRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(capRequest, filePart, "cap");
            if (isSaved) {
                mav.setViewName("redirect:/clothes/cap/catalog/1");
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
                                     GloveRequest gloveRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(gloveRequest, filePart, "glove");
            if (isSaved) {
                mav.setViewName("redirect:/clothes/gloves/catalog/1");
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
                                       MittenRequest mittenRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(mittenRequest, filePart, "mitten");
            if (isSaved) {
                mav.setViewName("redirect:/clothes/mittens/catalog/1");
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
                                        MaskRequest maskRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(maskRequest, filePart, "mask");
            if (isSaved) {
                mav.setViewName("redirect:/clothes/mask/catalog/1");
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
                                      PantsRequest pantsRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(pantsRequest, filePart, "pants");
            if (isSaved) {
                mav.setViewName("redirect:/clothes/pants/catalog/1");
            } else {
                mav.setViewName("redirect:/admin/create/new/clothes/pants");
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping("/admin/delete/snowboard/{id}")
    public String deleteSnowboard(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("snowboard", id);
        return "redirect:/snowboard/catalog/1";
    }

    @PostMapping("/admin/delete/snowboard/helmet/{id}")
    public String deleteSnowboardHelmet(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("snowboard_helmet", id);
        return "redirect:/snowboard/helmet/catalog/1";
    }

    @PostMapping("/admin/delete/snowboard/boot/{id}")
    public String deleteSnowboardBoot(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("snowboard_boot", id);
        return "redirect:/snowboard/boot/catalog/1";
    }

    @PostMapping("/admin/delete/ski/{id}")
    public String deleteSki(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("ski", id);
        return "redirect:/ski/catalog/1";
    }

    @PostMapping("/admin/delete/ski/boot/{id}")
    public String deleteSkiBoot(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("ski_boot", id);
        return "redirect:/ski/boot/catalog/1";
    }

    @PostMapping("/admin/delete/ski/helmet/{id}")
    public String deleteSkiHelmet(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("ski_helmet", id);
        return "redirect:/ski/helmet/catalog/1";
    }

    @PostMapping("/admin/delete/ski/pole/{id}")
    public String deleteSkiPole(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("ski_pole", id);
        return "redirect:/ski/pole/catalog/1";
    }

    @PostMapping("/admin/delete/clothes/cap/{id}")
    public String deleteCap(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("cap", id);
        return "redirect:/clothes/cap/catalog/1";
    }

    @PostMapping("/admin/delete/clothes/gloves/{id}")
    public String deleteGlove(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("glove", id);
        return "redirect:/clothes/gloves/catalog/1";
    }

    @PostMapping("/admin/delete/clothes/mask/{id}")
    public String deleteMask(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("mask", id);
        return "redirect:/clothes/mask/catalog/1";
    }

    @PostMapping("/admin/delete/clothes/mittens/{id}")
    public String deleteMitten(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("mitten", id);
        return "redirect:/clothes/mittens/catalog/1";
    }

    @PostMapping("/admin/delete/clothes/jacket/{id}")
    public String deleteJacket(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("jacket", id);
        return "redirect:/clothes/jacket/catalog/1";
    }

    @PostMapping("/admin/delete/clothes/pants/{id}")
    public String deletePants(@PathVariable("id") UUID id) {
        adminItemService.deleteItem("pants", id);
        return "redirect:/clothes/pants/catalog/1";
    }
}
