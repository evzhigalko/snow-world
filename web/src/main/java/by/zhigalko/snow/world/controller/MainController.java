package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.service.cart.CartService;
import by.zhigalko.snow.world.service.item.BaseItemService;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Controller
@SessionAttributes({"pageNumber"})
public class MainController {
    public static final int PAGE_SIZE = 6;
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final CartService cartService;

    @Autowired
    public MainController(ServiceEquipmentFactory serviceEquipmentFactory, CartService cartService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.cartService = cartService;
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
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD);
        paginate(model, pageNumber, service);
        return "catalog/snowboard-list";
    }

    @GetMapping("/snowboard/boot/catalog/{page}")
    public String handleSnowboardBootCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_BOOT);
        paginate(model, pageNumber, service);
        return "catalog/snowboard-boot-list";
    }

    @GetMapping("/snowboard/helmet/catalog/{page}")
    public String handleSnowboardHelmetCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_HELMET);
        paginate(model, pageNumber, service);
        return "catalog/snowboard-helmet-list";
    }

    @GetMapping("/ski/catalog/{page}")
    public String handleSkiCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI);
        paginate(model, pageNumber, service);
        return "catalog/ski-list";
    }

    @GetMapping("/ski/boot/catalog/{page}")
    public String handleSkiBootCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_BOOT);
        paginate(model, pageNumber, service);
        return "catalog/ski-boot-list";
    }

    @GetMapping("/ski/helmet/catalog/{page}")
    public String handleSkiHelmetCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_HELMET);
        paginate(model, pageNumber, service);
        return "catalog/ski-helmet-list";
    }

    @GetMapping("/ski/pole/catalog/{page}")
    public String handleSkiPoleCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_POLE);
        paginate(model, pageNumber, service);
        List<EquipmentSize> skiPoleSizeList = service.findAllByProductGroup(ProductGroup.SKI_POLE);
        model.addAttribute("skiPoleSizeList", skiPoleSizeList);
        return "catalog/ski-pole-list";
    }

    @GetMapping("/clothes/jacket/catalog/{page}")
    public String handleJacketCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.JACKET);
        paginate(model, pageNumber, service);
        return "catalog/jacket-list";
    }

    @GetMapping("/clothes/pants/catalog/{page}")
    public String handlePantsCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.PANTS);
        paginate(model, pageNumber, service);
        return "catalog/pants-list";
    }

    @GetMapping("/clothes/cap/catalog/{page}")
    public String handleCapCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.CAP);
        paginate(model, pageNumber, service);
        return "catalog/cap-list";
    }

    @GetMapping("/clothes/mask/catalog/{page}")
    public String handleMaskCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MASK);
        paginate(model, pageNumber, service);
        return "catalog/mask-list";
    }

    @GetMapping("/clothes/mittens/catalog/{page}")
    public String handleMittensCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MITTEN);
        paginate(model, pageNumber, service);
        return "catalog/mittens-list";
    }

    @GetMapping("/clothes/gloves/catalog/{page}")
    public String handleGlovesCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.GLOVE);
        paginate(model, pageNumber, service);
        return "catalog/gloves-list";
    }

    @GetMapping("/cart")
    public String showCart(@SessionAttribute("cart") Cart cart,
                           Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cartService.getItems(cart.getId()));
        return "cart";
    }

    @GetMapping("/cart/add/snowboard/{id}")
    public String addSnowboardToCart(@SessionAttribute("cart") Cart cart,
                                     @PathVariable("id") UUID itemId,
                                     @SessionAttribute("pageNumber") int pageNumber,
                                     Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD);
        addToCart(cart, itemId, model, service);
        return "redirect:/snowboard/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/snowboard/boot/{id}")
    public String addSnowboardBootToCart(@SessionAttribute("cart") Cart cart,
                                         @PathVariable("id") UUID itemId,
                                         @SessionAttribute("pageNumber") int pageNumber,
                                         Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_BOOT);
        addToCart(cart, itemId, model, service);
        return "redirect:/snowboard/boot/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/snowboard/helmet/{id}")
    public String addSnowboardHelmetToCart(@SessionAttribute("cart") Cart cart,
                                           @PathVariable("id") UUID itemId,
                                           @SessionAttribute("pageNumber") int pageNumber,
                                           Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_HELMET);
        addToCart(cart, itemId, model, service);
        return "redirect:/snowboard/helmet/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/{id}")
    public String addSkiToCart(@SessionAttribute("cart") Cart cart,
                               @PathVariable("id") UUID itemId,
                               @SessionAttribute("pageNumber") int pageNumber,
                               Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI);
        addToCart(cart, itemId, model, service);
        return "redirect:/ski/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/pole/{id}")
    public String addSkiPoleToCart(@SessionAttribute("cart") Cart cart,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_POLE);
        addToCart(cart, itemId, model, service);
        return "redirect:/ski/pole/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/boot/{id}")
    public String addSkiBootToCart(@SessionAttribute("cart") Cart cart,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_BOOT);
        addToCart(cart, itemId, model, service);
        return "redirect:/ski/boot/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/helmet/{id}")
    public String addSkiHelmetToCart(@SessionAttribute("cart") Cart cart,
                                     @PathVariable("id") UUID itemId,
                                     @SessionAttribute("pageNumber") int pageNumber,
                                     Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_HELMET);
        addToCart(cart, itemId, model, service);
        return "redirect:/ski/helmet/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/cap/{id}")
    public String addCapToCart(@SessionAttribute("cart") Cart cart,
                               @PathVariable("id") UUID itemId,
                               @SessionAttribute("pageNumber") int pageNumber,
                               Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.CAP);
        addToCart(cart, itemId, model, service);
        return "redirect:/clothes/cap/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/gloves/{id}")
    public String addGlovesToCart(@SessionAttribute("cart") Cart cart,
                                  @PathVariable("id") UUID itemId,
                                  @SessionAttribute("pageNumber") int pageNumber,
                                  Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.GLOVE);
        addToCart(cart, itemId, model, service);
        return "redirect:/clothes/gloves/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/jacket/{id}")
    public String addJacketToCart(@SessionAttribute("cart") Cart cart,
                                  @PathVariable("id") UUID itemId,
                                  @SessionAttribute("pageNumber") int pageNumber,
                                  Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.JACKET);
        addToCart(cart, itemId, model, service);
        return "redirect:/clothes/jacket/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/mask/{id}")
    public String addMaskToCart(@SessionAttribute("cart") Cart cart,
                                @PathVariable("id") UUID itemId,
                                @SessionAttribute("pageNumber") int pageNumber,
                                Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MASK);
        addToCart(cart, itemId, model, service);
        return "redirect:/clothes/mask/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/mittens/{id}")
    public String addMittensToCart(@SessionAttribute("cart") Cart cart,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MITTEN);
        addToCart(cart, itemId, model, service);
        return "redirect:/clothes/mittens/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/pants/{id}")
    public String addPantsToCart(@SessionAttribute("cart") Cart cart,
                                 @PathVariable("id") UUID itemId,
                                 @SessionAttribute("pageNumber") int pageNumber,
                                 Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.PANTS);
        addToCart(cart, itemId, model, service);
        return "redirect:/clothes/pants/catalog/" + pageNumber;
    }

    private void addToCart(Cart cart, UUID itemId, Model model, BaseItemService<? extends Item> service) {
        Cart cartWithItem = cartService.addToCart(service, cart, service.findById(itemId));
        model.addAttribute("cart", cartWithItem);
        Set<Item> items = cartService.getItems(cartWithItem.getId());
        model.addAttribute("cartItems", items);
    }

    private void paginate(Model model, int pageNumber, BaseItemService<? extends Item> service) {
        org.springframework.data.domain.Page<? extends Item> pageList = service.findAll(pageNumber - 1, PAGE_SIZE);
        int pagesNumber = pageList.getTotalPages();
        List<? extends Item> list = pageList.stream().collect(Collectors.toList());
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("list", list);
    }
}
