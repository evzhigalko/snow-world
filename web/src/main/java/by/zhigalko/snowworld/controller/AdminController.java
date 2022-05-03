package by.zhigalko.snowworld.controller;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.response.UserResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.model.ProductGroup;
import by.zhigalko.snowworld.service.AdminItemService;
import by.zhigalko.snowworld.service.ItemService;
import by.zhigalko.snowworld.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@SessionAttributes({"user", "ROLE"})
public class AdminController {
    private final UserService userService;
    private final AdminItemService adminItemService;
    private final ItemService itemService;

    @Autowired
    public AdminController(UserService userService, AdminItemService adminItemService, ItemService itemService) {
        this.userService = userService;
        this.adminItemService = adminItemService;
        this.itemService = itemService;
    }

    @GetMapping("/admin")
    public String showAdminPage(Authentication authentication, Model model) {
        List<UserResponse> userList = userService.findAllUsers();
        model.addAttribute("usersList", userList);
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        model.addAttribute("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        return "administration/admin";
    }

    @PostMapping("/update/snowboard/{id}")
    public String changeSnowboardOnAdminPage(@RequestParam(value = "cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/snowboard/" + pageNumber;
    }

    @PostMapping("/update/snowboard-boot/{id}")
    public String changeSnowboardBootOnAdminPage(@RequestParam("cost") String cost,
                                                 @RequestParam("availability") String availableToRental,
                                                 @PathVariable("id") UUID id,
                                                 @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/snowboard-boot/" + pageNumber;
    }

    @PostMapping("/update/snowboard-helmet/{id}")
    public String changeSnowboardHelmetOnAdminPage(@RequestParam("cost") String cost,
                                                   @RequestParam("availability") String availableToRental,
                                                   @PathVariable("id") UUID id,
                                                   @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/snowboard-helmet/" + pageNumber;
    }

    @PostMapping("/update/ski/{id}")
    public String changeSkiOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski/" + pageNumber;
    }

    @PostMapping("/update/ski-helmet/{id}")
    public String changeSkiHelmetOnAdminPage(@RequestParam("cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski-helmet/" + pageNumber;
    }

    @PostMapping("/update/ski-boot/{id}")
    public String changeSkiBootOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski-boot/" + pageNumber;
    }

    @PostMapping("/update/ski-pole/{id}")
    public String changeSkiPoleOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski-pole/" + pageNumber;
    }

    @PostMapping("/update/cap/{id}")
    public String changeCapOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/clothes/cap/" + pageNumber;
    }

    @PostMapping("/update/gloves/{id}")
    public String changeGlovesOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/clothes/gloves/" + pageNumber;
    }

    @PostMapping("/update/jacket/{id}")
    public String changeJacketOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/clothes/jacket/" + pageNumber;
    }

    @PostMapping("/update/mask/{id}")
    public String changeMaskOnAdminPage(@RequestParam("cost") String cost,
                                        @RequestParam("availability") String availableToRental,
                                        @PathVariable("id") UUID id,
                                        @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/clothes/mask/" + pageNumber;
    }

    @PostMapping("/update/mittens/{id}")
    public String changeMittensOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/clothes/mittens/" + pageNumber;
    }

    @PostMapping("/update/pants/{id}")
    public String changePantsOnAdminPage(@RequestParam("cost") String cost,
                                         @RequestParam("availability") String availableToRental,
                                         @PathVariable("id") UUID id,
                                         @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/clothes/pants/" + pageNumber;
    }

    @GetMapping("/new/{item}")
    public String showAdminCreateItemPage(Model model, @PathVariable("item") String item) {
        List<EquipmentSize> allSizes = itemService.findAllByProductGroup(ProductGroup.getProduct(item));
        model.addAttribute("allSizes", allSizes);
        model.addAttribute("item", item);
        return "administration/create-new-item";
    }

    @PostMapping(value = "/new/snowboard")
    public ModelAndView createNewSnowboard(@RequestParam("file") MultipartFile filePart,
                                           ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SNOWBOARD.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/snowboard/1");
            } else {
                mav.setViewName("redirect:/new/snowboard");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/snowboard-boot")
    public ModelAndView createNewSnowboardBoot(@RequestParam("file") MultipartFile filePart,
                                               ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SNOWBOARD_BOOT.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/snowboard-boot/1");
            } else {
                mav.setViewName("redirect:/new/snowboard-boot");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/snowboard-helmet")
    public ModelAndView createNewSnowboardHelmet(@RequestParam("file") MultipartFile filePart,
                                                 ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SNOWBOARD_HELMET.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/snowboard-helmet/1");
            } else {
                mav.setViewName("redirect:/new/snowboard-helmet");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/ski")
    public ModelAndView createNewSki(@RequestParam("file") MultipartFile filePart,
                                     ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski/1");
            } else {
                mav.setViewName("redirect:/new/ski");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/ski-boot")
    public ModelAndView createNewSkiBoot(@RequestParam("file") MultipartFile filePart,
                                               ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI_BOOT.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski-boot/1");
            } else {
                mav.setViewName("redirect:/new/ski-boot");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/ski-helmet")
    public ModelAndView createNewSkiHelmet(@RequestParam("file") MultipartFile filePart,
                                                 ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, "ski_helmet");
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski-helmet/1");
            } else {
                mav.setViewName("redirect:/new/ski-helmet");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/ski-pole")
    public ModelAndView createNewSkiPole(@RequestParam("file") MultipartFile filePart,
                                         ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI_POLE.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski-pole/1");
            } else {
                mav.setViewName("redirect:/new/ski-pole");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/jacket")
    public ModelAndView createNewJacket(@RequestParam("file") MultipartFile filePart,
                                        ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.JACKET.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/clothes/jacket/1");
            } else {
                mav.setViewName("redirect:/new/jacket");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/cap")
    public ModelAndView createNewCap(@RequestParam("file") MultipartFile filePart,
                                        ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.CAP.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/clothes/cap/1");
            } else {
                mav.setViewName("redirect:/new/cap");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/glove")
    public ModelAndView createNewGlove(@RequestParam("file") MultipartFile filePart,
                                     ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.GLOVE.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/clothes/gloves/1");
            } else {
                mav.setViewName("redirect:/new/glove");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/mitten")
    public ModelAndView createNewMitten(@RequestParam("file") MultipartFile filePart,
                                       ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.MITTEN.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/clothes/mittens/1");
            } else {
                mav.setViewName("redirect:/new/mitten");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/mask")
    public ModelAndView createNewMask(@RequestParam("file") MultipartFile filePart,
                                        ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.MASK.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/clothes/mask/1");
            } else {
                mav.setViewName("redirect:/new/mask");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/new/pants")
    public ModelAndView createNewPants(@RequestParam("file") MultipartFile filePart,
                                      ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.PANTS.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/clothes/pants/1");
            } else {
                mav.setViewName("redirect:/new/pants");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping("/delete/snowboard/{id}")
    public String deleteSnowboard(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SNOWBOARD.toString().toLowerCase(), id);
        return "redirect:/catalog/snowboard/1";
    }

    @PostMapping("/delete/snowboard-helmet/{id}")
    public String deleteSnowboardHelmet(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SNOWBOARD_HELMET.toString().toLowerCase(), id);
        return "redirect:/catalog/snowboard-helmet/1";
    }

    @PostMapping("/delete/snowboard-boot/{id}")
    public String deleteSnowboardBoot(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SNOWBOARD_BOOT.toString().toLowerCase(), id);
        return "redirect:/catalog/snowboard-boot/1";
    }

    @PostMapping("/delete/ski/{id}")
    public String deleteSki(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SKI.toString().toLowerCase(), id);
        return "redirect:/catalog/ski/1";
    }

    @PostMapping("/delete/ski-boot/{id}")
    public String deleteSkiBoot(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SKI_BOOT.toString().toLowerCase(), id);
        return "redirect:/catalog/ski-boot/1";
    }

    @PostMapping("/delete/ski-helmet/{id}")
    public String deleteSkiHelmet(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SKI_HELMET.toString().toLowerCase(), id);
        return "redirect:/catalog/ski-helmet/1";
    }

    @PostMapping("/delete/ski-pole/{id}")
    public String deleteSkiPole(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.SKI_POLE.toString().toLowerCase(), id);
        return "redirect:/catalog/ski-pole/1";
    }

    @PostMapping("/delete/cap/{id}")
    public String deleteCap(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.CAP.toString().toLowerCase(), id);
        return "redirect:/catalog/clothes/cap/1";
    }

    @PostMapping("/delete/gloves/{id}")
    public String deleteGlove(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.GLOVE.toString().toLowerCase(), id);
        return "redirect:/catalog/clothes/gloves/1";
    }

    @PostMapping("/delete/mask/{id}")
    public String deleteMask(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.MASK.toString().toLowerCase(), id);
        return "redirect:/catalog/clothes/mask/1";
    }

    @PostMapping("/delete/mittens/{id}")
    public String deleteMitten(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.MITTEN.toString().toLowerCase(), id);
        return "redirect:/catalog/clothes/mittens/1";
    }

    @PostMapping("/delete/jacket/{id}")
    public String deleteJacket(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.JACKET.toString().toLowerCase(), id);
        return "redirect:/catalog/clothes/jacket/1";
    }

    @PostMapping("/delete/pants/{id}")
    public String deletePants(@PathVariable("id") UUID id) {
        adminItemService.deleteItem(Product.PANTS.toString().toLowerCase(), id);
        return "redirect:/catalog/clothes/pants/1";
    }
}
