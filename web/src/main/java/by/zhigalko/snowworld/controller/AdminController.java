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

    @GetMapping("/administration")
    public String showAdminPage(Authentication authentication, Model model) {
        List<UserResponse> userList = userService.findAllUsers();
        model.addAttribute("usersList", userList);
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        model.addAttribute("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        return "administration";
    }

    @PatchMapping("/catalog/snowboards/{id}")
    public String changeSnowboardOnAdminPage(@RequestParam(value = "cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/snowboards/" + pageNumber;
    }

    @PatchMapping("/catalog/snowboard-boots/{id}")
    public String changeSnowboardBootOnAdminPage(@RequestParam("cost") String cost,
                                                 @RequestParam("availability") String availableToRental,
                                                 @PathVariable("id") UUID id,
                                                 @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/snowboard-boots/" + pageNumber;
    }

    @PatchMapping("/catalog/snowboard-helmets/{id}")
    public String changeSnowboardHelmetOnAdminPage(@RequestParam("cost") String cost,
                                                   @RequestParam("availability") String availableToRental,
                                                   @PathVariable("id") UUID id,
                                                   @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/snowboard-helmets/" + pageNumber;
    }

    @PatchMapping("/catalog/ski/{id}")
    public String changeSkiOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski/" + pageNumber;
    }

    @PatchMapping("/catalog/ski-helmets/{id}")
    public String changeSkiHelmetOnAdminPage(@RequestParam("cost") String cost,
                                             @RequestParam("availability") String availableToRental,
                                             @PathVariable("id") UUID id,
                                             @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski-helmets/" + pageNumber;
    }

    @PatchMapping("/catalog/ski-boots/{id}")
    public String changeSkiBootOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski-boots/" + pageNumber;
    }

    @PatchMapping("/catalog/ski-poles/{id}")
    public String changeSkiPoleOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/ski-poles/" + pageNumber;
    }

    @PatchMapping("/catalog/caps/{id}")
    public String changeCapOnAdminPage(@RequestParam("cost") String cost,
                                       @RequestParam("availability") String availableToRental,
                                       @PathVariable("id") UUID id,
                                       @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/caps/" + pageNumber;
    }

    @PatchMapping("/catalog/gloves/{id}")
    public String changeGlovesOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/gloves/" + pageNumber;
    }

    @PatchMapping("/catalog/jackets/{id}")
    public String changeJacketOnAdminPage(@RequestParam("cost") String cost,
                                          @RequestParam("availability") String availableToRental,
                                          @PathVariable("id") UUID id,
                                          @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/jackets/" + pageNumber;
    }

    @PatchMapping("/catalog/masks/{id}")
    public String changeMaskOnAdminPage(@RequestParam("cost") String cost,
                                        @RequestParam("availability") String availableToRental,
                                        @PathVariable("id") UUID id,
                                        @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/masks/" + pageNumber;
    }

    @PatchMapping("/catalog/mittens/{id}")
    public String changeMittensOnAdminPage(@RequestParam("cost") String cost,
                                           @RequestParam("availability") String availableToRental,
                                           @PathVariable("id") UUID id,
                                           @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/mittens/" + pageNumber;
    }

    @PatchMapping("/catalog/pants/{id}")
    public String changePantsOnAdminPage(@RequestParam("cost") String cost,
                                         @RequestParam("availability") String availableToRental,
                                         @PathVariable("id") UUID id,
                                         @SessionAttribute("pageNumber") int pageNumber) {
        adminItemService.updateItem(cost, availableToRental, id);
        return "redirect:/catalog/pants/" + pageNumber;
    }

    @GetMapping("/catalog/{item}")
    public String showAdminCreateItemPage(Model model, @PathVariable("item") String item) {
        List<EquipmentSize> allSizes = itemService.findAllByProductGroup(ProductGroup.getProduct(item));
        model.addAttribute("allSizes", allSizes);
        model.addAttribute("item", item);
        return "create-new-item";
    }

    @PostMapping(value = "/catalog/snowboards")
    public ModelAndView createNewSnowboard(@RequestParam("file") MultipartFile filePart,
                                           ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SNOWBOARD.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/snowboards/1");
            } else {
                mav.setViewName("redirect:/catalog/snowboards");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/snowboard-boots")
    public ModelAndView createNewSnowboardBoot(@RequestParam("file") MultipartFile filePart,
                                               ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SNOWBOARD_BOOT.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/snowboard-boots/1");
            } else {
                mav.setViewName("redirect:/catalog/snowboard-boots");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/snowboard-helmets")
    public ModelAndView createNewSnowboardHelmet(@RequestParam("file") MultipartFile filePart,
                                                 ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SNOWBOARD_HELMET.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/snowboard-helmets/1");
            } else {
                mav.setViewName("redirect:/catalog/snowboard-helmets");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/ski")
    public ModelAndView createNewSki(@RequestParam("file") MultipartFile filePart,
                                     ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski/1");
            } else {
                mav.setViewName("redirect:/catalog/ski");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/ski-boots")
    public ModelAndView createNewSkiBoot(@RequestParam("file") MultipartFile filePart,
                                               ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI_BOOT.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski-boots/1");
            } else {
                mav.setViewName("redirect:/catalog/ski-boots");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/ski-helmets")
    public ModelAndView createNewSkiHelmet(@RequestParam("file") MultipartFile filePart,
                                                 ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI_HELMET.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski-helmets/1");
            } else {
                mav.setViewName("redirect:/catalog/ski-helmets");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/ski-poles")
    public ModelAndView createNewSkiPole(@RequestParam("file") MultipartFile filePart,
                                         ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.SKI_POLE.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/ski-poles/1");
            } else {
                mav.setViewName("redirect:/catalog/ski-poles");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/jackets")
    public ModelAndView createNewJacket(@RequestParam("file") MultipartFile filePart,
                                        ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.JACKET.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/jackets/1");
            } else {
                mav.setViewName("redirect:/catalog/jackets");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/caps")
    public ModelAndView createNewCap(@RequestParam("file") MultipartFile filePart,
                                        ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.CAP.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/caps/1");
            } else {
                mav.setViewName("redirect:/catalog/caps");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/gloves")
    public ModelAndView createNewGlove(@RequestParam("file") MultipartFile filePart,
                                     ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.GLOVE.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/gloves/1");
            } else {
                mav.setViewName("redirect:/catalog/gloves");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/mittens")
    public ModelAndView createNewMitten(@RequestParam("file") MultipartFile filePart,
                                       ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.MITTEN.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/mittens/1");
            } else {
                mav.setViewName("redirect:/catalog/mittens");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/masks")
    public ModelAndView createNewMask(@RequestParam("file") MultipartFile filePart,
                                        ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.MASK.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/masks/1");
            } else {
                mav.setViewName("redirect:/catalog/masks");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value = "/catalog/pants")
    public ModelAndView createNewPants(@RequestParam("file") MultipartFile filePart,
                                      ItemRequest itemRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isSaved = adminItemService.addNewItem(itemRequest, filePart, Product.PANTS.toString().toLowerCase());
            if (isSaved) {
                mav.setViewName("redirect:/catalog/pants/1");
            } else {
                mav.setViewName("redirect:/catalog/pants");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @DeleteMapping("/catalog/{item}/{id}")
    public String deleteItem(@PathVariable("id") UUID id,
                             @PathVariable("item") String item) {
        adminItemService.deleteItem(id);
        return "redirect:/catalog/" + item + "/1";
    }
}
