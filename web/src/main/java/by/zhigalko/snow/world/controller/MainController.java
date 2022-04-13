package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.dto.CartDto;
import by.zhigalko.snow.world.dto.OrderDetailsDto;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.dto.request.OrderRequest;
import by.zhigalko.snow.world.dto.response.OrderResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.model.ProductGroup;
import by.zhigalko.snow.world.service.CartService;
import by.zhigalko.snow.world.service.BaseItemService;
import by.zhigalko.snow.world.service.EmailService;
import by.zhigalko.snow.world.service.impl.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.util.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Controller
@SessionAttributes({"pageNumber", "cart", "cartItems", "order", "orderDetails"})
public class MainController {
    public static final int PAGE_SIZE = 6;
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final CartService cartService;
    private final OrderService orderService;
    private final EmailService emailService;

    @Autowired
    public MainController(ServiceEquipmentFactory serviceEquipmentFactory, CartService cartService, OrderService orderService, EmailService emailService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.cartService = cartService;
        this.orderService = orderService;
        this.emailService = emailService;
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
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/snowboard/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/snowboard/boot/{id}")
    public String addSnowboardBootToCart(@SessionAttribute("cart")  CartDto cartDto,
                                         @PathVariable("id") UUID itemId,
                                         @SessionAttribute("pageNumber") int pageNumber,
                                         Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_BOOT);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/snowboard/boot/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/snowboard/helmet/{id}")
    public String addSnowboardHelmetToCart(@SessionAttribute("cart")  CartDto cartDto,
                                           @PathVariable("id") UUID itemId,
                                           @SessionAttribute("pageNumber") int pageNumber,
                                           Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SNOWBOARD_HELMET);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/snowboard/helmet/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/{id}")
    public String addSkiToCart(@SessionAttribute("cart")  CartDto cartDto,
                               @PathVariable("id") UUID itemId,
                               @SessionAttribute("pageNumber") int pageNumber,
                               Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/ski/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/pole/{id}")
    public String addSkiPoleToCart(@SessionAttribute("cart") CartDto cartDto,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_POLE);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/ski/pole/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/boot/{id}")
    public String addSkiBootToCart(@SessionAttribute("cart") CartDto cartDto,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_BOOT);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/ski/boot/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/ski/helmet/{id}")
    public String addSkiHelmetToCart(@SessionAttribute("cart") CartDto cartDto,
                                     @PathVariable("id") UUID itemId,
                                     @SessionAttribute("pageNumber") int pageNumber,
                                     Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.SKI_HELMET);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/ski/helmet/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/cap/{id}")
    public String addCapToCart(@SessionAttribute("cart") CartDto cartDto,
                               @PathVariable("id") UUID itemId,
                               @SessionAttribute("pageNumber") int pageNumber,
                               Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.CAP);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/clothes/cap/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/gloves/{id}")
    public String addGlovesToCart(@SessionAttribute("cart") CartDto cartDto,
                                  @PathVariable("id") UUID itemId,
                                  @SessionAttribute("pageNumber") int pageNumber,
                                  Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.GLOVE);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/clothes/gloves/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/jacket/{id}")
    public String addJacketToCart(@SessionAttribute("cart") CartDto cartDto,
                                  @PathVariable("id") UUID itemId,
                                  @SessionAttribute("pageNumber") int pageNumber,
                                  Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.JACKET);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/clothes/jacket/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/mask/{id}")
    public String addMaskToCart(@SessionAttribute("cart") CartDto cartDto,
                                @PathVariable("id") UUID itemId,
                                @SessionAttribute("pageNumber") int pageNumber,
                                Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MASK);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/clothes/mask/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/mittens/{id}")
    public String addMittensToCart(@SessionAttribute("cart") CartDto cartDto,
                                   @PathVariable("id") UUID itemId,
                                   @SessionAttribute("pageNumber") int pageNumber,
                                   Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.MITTEN);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/clothes/mittens/catalog/" + pageNumber;
    }

    @GetMapping("/cart/add/clothes/pants/{id}")
    public String addPantsToCart(@SessionAttribute("cart") CartDto cartDto,
                                 @PathVariable("id") UUID itemId,
                                 @SessionAttribute("pageNumber") int pageNumber,
                                 Model model) {
        BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(Product.PANTS);
        addToCart(cartDto, itemId, model, service);
        return "redirect:/clothes/pants/catalog/" + pageNumber;
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

//    @PostMapping("/order/create/new")
//    public String createOrder(OrderRequest orderRequest, Model model) {
//        CartDto cartDto = cartService.findCartById(UUID.fromString(orderRequest.getCartId()));
//        cartService.save(cartDto);
//        OrderResponse order = orderService.save(orderRequest);
//        model.addAttribute("order", order);
//        return "order";
//    }

    @PostMapping("/order/new/payment")
    public String createPayment(OrderDetailsDto orderDetailsDto, Model model) {
        model.addAttribute("orderDetails", orderDetailsDto);
        return "payment";
    }

    @PostMapping("/order/payment/success")
    public String successPayment() {
        return "success-payment";
    }

    @PostMapping("/order/send")
    public String sendOrder(@SessionAttribute("orderDetails") OrderDetailsDto orderDetailsDto,
                            @SessionAttribute("cart") CartDto cartDto,
                            Model model) {
        orderService.setOrderDetails(orderDetailsDto);
        emailService.sendMessage(orderDetailsDto);
        CartDto cartDtoWithoutItems = cartService.clearItems(cartDto);
        model.addAttribute("cart", cartDto);
        model.addAttribute("cartItems", cartDtoWithoutItems.getItems());
        return "success-order";
    }

    private void addToCart(CartDto cartDto, UUID itemId, Model model, BaseItemService<? extends Item> service) {
        CartDto cartDtoWithItem = cartService.addToCart(service, cartDto, itemId);
        model.addAttribute("cart", cartDtoWithItem);
        Set<ItemResponse> items = cartService.getItems(cartDtoWithItem.getId());
        model.addAttribute("cartItems", items);
    }

    private void paginate(Model model, int pageNumber, BaseItemService<? extends Item> service) {
        List<? extends ItemResponse> list = service.findAll(pageNumber - 1, PAGE_SIZE);
        int pagesNumber = BaseItemServiceImpl.totalPages;
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("list", list);
    }
}
