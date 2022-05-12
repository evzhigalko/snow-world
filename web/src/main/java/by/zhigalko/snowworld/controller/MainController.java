package by.zhigalko.snowworld.controller;

import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.dto.OrderDetailsDto;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.model.ProductGroup;
import by.zhigalko.snowworld.service.CartService;
import by.zhigalko.snowworld.service.ItemService;
import by.zhigalko.snowworld.service.EmailService;
import by.zhigalko.snowworld.service.impl.ItemServiceImpl;
import by.zhigalko.snowworld.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Controller
@SessionAttributes({"pageNumber", "cart", "cartItems", "order", "orderDetailsDto"})
public class MainController {
    public static final int PAGE_SIZE = 6;
    private final CartService cartService;
    private final OrderService orderService;
    private final EmailService emailService;
    private final ItemService itemService;

    @Autowired
    public MainController(CartService cartService, OrderService orderService, EmailService emailService, ItemService itemService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.emailService = emailService;
        this.itemService = itemService;
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

    @GetMapping("/catalog/snowboards/{page}")
    public String handleSnowboardCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SNOWBOARD);
        return "catalog/snowboard-list";
    }

    @GetMapping("/catalog/snowboard-boots/{page}")
    public String handleSnowboardBootCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SNOWBOARD_BOOT);
        return "catalog/snowboard-boot-list";
    }

    @GetMapping("/catalog/snowboard-helmets/{page}")
    public String handleSnowboardHelmetCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SNOWBOARD_HELMET);
        return "catalog/snowboard-helmet-list";
    }

    @GetMapping("/catalog/ski/{page}")
    public String handleSkiCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SKI);
        return "catalog/ski-list";
    }

    @GetMapping("/catalog/ski-boots/{page}")
    public String handleSkiBootCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SKI_BOOT);
        return "catalog/ski-boot-list";
    }

    @GetMapping("/catalog/ski-helmets/{page}")
    public String handleSkiHelmetCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SKI_HELMET);
        return "catalog/ski-helmet-list";
    }

    @GetMapping("/catalog/ski-poles/{page}")
    public String handleSkiPoleCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.SKI_POLE);
        List<EquipmentSize> skiPoleSizeList = itemService.findAllByProductGroup(ProductGroup.SKI_POLE);
        model.addAttribute("skiPoleSizeList", skiPoleSizeList);
        return "catalog/ski-pole-list";
    }

    @GetMapping("/catalog/jackets/{page}")
    public String handleJacketCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.JACKET);
        return "catalog/jacket-list";
    }

    @GetMapping("/catalog/pants/{page}")
    public String handlePantsCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.PANTS);
        return "catalog/pants-list";
    }

    @GetMapping("/catalog/caps/{page}")
    public String handleCapCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.CAP);
        return "catalog/cap-list";
    }

    @GetMapping("/catalog/masks/{page}")
    public String handleMaskCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.MASK);
        return "catalog/mask-list";
    }

    @GetMapping("/catalog/mittens/{page}")
    public String handleMittensCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.MITTEN);
        return "catalog/mittens-list";
    }

    @GetMapping("/catalog/gloves/{page}")
    public String handleGlovesCatalog(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        paginate(model, pageNumber, Product.GLOVE);
        return "catalog/gloves-list";
    }

    @GetMapping("/cart")
    public String showCart(@SessionAttribute("cart") CartDto cartDto,
                           Model model) {
        model.addAttribute("cart", cartDto);
        model.addAttribute("cartItems", cartService.getItems(cartDto.getId()));
        return "cart";
    }

    @GetMapping("/cart/add/snowboard/{id}")
    public String addSnowboardToCart(@SessionAttribute("cart") CartDto cartDto,
                                     @PathVariable("id") UUID itemId,
                                     @SessionAttribute("pageNumber") int pageNumber,
                                     Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/snowboards/" + pageNumber;
    }

    @GetMapping("/cart/add/snowboard-boot/{id}")
    public String addSnowboardBootToCart(@SessionAttribute("cart")  CartDto cartDto,
                                         @PathVariable("id") UUID itemId,
                                         @SessionAttribute("pageNumber") int pageNumber,
                                         Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/snowboard-boots/" + pageNumber;
    }

    @GetMapping("/cart/add/snowboard-helmet/{id}")
    public String addSnowboardHelmetToCart(@SessionAttribute("cart")  CartDto cartDto,
                                           @PathVariable("id") UUID itemId,
                                           @SessionAttribute("pageNumber") int pageNumber,
                                           Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/snowboard-helmets/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/{id}")
    public String addSkiToCart(@SessionAttribute("cart")  CartDto cartDto,
                               @PathVariable("id") UUID itemId,
                               @SessionAttribute("pageNumber") int pageNumber,
                               Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/ski/" + pageNumber;
    }

    @GetMapping("/cart/add/ski-pole/{id}")
    public String addSkiPoleToCart(@SessionAttribute("cart") CartDto cartDto,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/ski-poles/" + pageNumber;
    }

    @GetMapping("/cart/add/ski-boot/{id}")
    public String addSkiBootToCart(@SessionAttribute("cart") CartDto cartDto,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/ski-boots/" + pageNumber;
    }

    @GetMapping("/cart/add/ski-helmet/{id}")
    public String addSkiHelmetToCart(@SessionAttribute("cart") CartDto cartDto,
                                     @PathVariable("id") UUID itemId,
                                     @SessionAttribute("pageNumber") int pageNumber,
                                     Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/ski-helmets/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/cap/{id}")
    public String addCapToCart(@SessionAttribute("cart") CartDto cartDto,
                               @PathVariable("id") UUID itemId,
                               @SessionAttribute("pageNumber") int pageNumber,
                               Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/caps/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/gloves/{id}")
    public String addGlovesToCart(@SessionAttribute("cart") CartDto cartDto,
                                  @PathVariable("id") UUID itemId,
                                  @SessionAttribute("pageNumber") int pageNumber,
                                  Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/gloves/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/jacket/{id}")
    public String addJacketToCart(@SessionAttribute("cart") CartDto cartDto,
                                  @PathVariable("id") UUID itemId,
                                  @SessionAttribute("pageNumber") int pageNumber,
                                  Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/jackets/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/mask/{id}")
    public String addMaskToCart(@SessionAttribute("cart") CartDto cartDto,
                                @PathVariable("id") UUID itemId,
                                @SessionAttribute("pageNumber") int pageNumber,
                                Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/masks/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/mittens/{id}")
    public String addMittensToCart(@SessionAttribute("cart") CartDto cartDto,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/mittens/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/pants/{id}")
    public String addPantsToCart(@SessionAttribute("cart") CartDto cartDto,
                                 @PathVariable("id") UUID itemId,
                                 @SessionAttribute("pageNumber") int pageNumber,
                                 Model model) {
        addToCart(cartDto, itemId, model);
        return "redirect:/catalog/pants/" + pageNumber;
    }

    @GetMapping("/cart/delete/item/{id}")
    public String deleteFromCart(@PathVariable("id") UUID id,
                                 @SessionAttribute("cart") CartDto cartDto,
                                 @SessionAttribute("cartItems") Set<ItemResponse> cartItems,
                                 Model model) {
        CartDto cartDtoAfterRemoving = cartService.removeFromCart(cartDto, id, cartItems);
        model.addAttribute("cart", cartDtoAfterRemoving);
        model.addAttribute("cartItems", cartService.getItems(cartDtoAfterRemoving.getId()));
        return "redirect:/cart";
    }

    @PostMapping("/order/new")
    public String createOrder(OrderRequest orderRequest, Model model) {
        CartDto cartDto = cartService.findCartById(UUID.fromString(orderRequest.getCartId()));
        cartService.save(cartDto);
        OrderResponse order = orderService.save(orderRequest);
        model.addAttribute("order", order);
        model.addAttribute("orderDetailsDto", new OrderDetailsDto());
        return "order";
    }

    @PostMapping("/order/payment")
    public String createPayment(@Valid @ModelAttribute("orderDetailsDto") OrderDetailsDto orderDetailsDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "order";
        }
        return "payment";
    }

    @PostMapping("/order/payment/success")
    public String successPayment() {
        return "success-payment";
    }

    @PostMapping("/order/send")
    public String sendOrder(@SessionAttribute("orderDetailsDto") OrderDetailsDto orderDetailsDto,
                            @SessionAttribute("cart") CartDto cartDto,
                            Model model) {
        orderService.setOrderDetails(orderDetailsDto);
        emailService.sendMessage(orderDetailsDto);
        CartDto cartDtoWithoutItems = cartService.clearItems(cartDto);
        model.addAttribute("cart", cartDto);
        model.addAttribute("cartItems", cartDtoWithoutItems.getItems());
        return "success-order";
    }

    private void addToCart(CartDto cartDto, UUID itemId, Model model) {
        CartDto cartDtoWithItem = cartService.addToCart(cartDto, itemId);
        model.addAttribute("cart", cartDtoWithItem);
        Set<ItemResponse> items = cartService.getItems(cartDtoWithItem.getId());
        model.addAttribute("cartItems", items);
    }

    private void paginate(Model model, int pageNumber, Product product) {
        Set<ItemResponse> items = itemService.findAll(product, pageNumber - 1, PAGE_SIZE);
        int pagesNumber = ItemServiceImpl.totalPages;
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("list", items);
    }
}
