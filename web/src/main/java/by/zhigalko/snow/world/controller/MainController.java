package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@Controller
@SessionAttributes("pageNumber")
public class MainController {
    public static final int PAGE_SIZE = 6;
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final UserService userService;

    @Autowired
    public MainController(ServiceEquipmentFactory serviceEquipmentFactory, UserService userService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.userService = userService;
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

    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, @RequestParam("username") String username,
                              @RequestParam("password") String password) {
        ModelAndView mav = new ModelAndView();
        try {
            User user = userService.findByUsernameAndPassword(username, password);
            if (user!=null) {
                if (user.getRole().getRoleName().equals(RoleName.ADMIN)) {
                    request.getSession().setAttribute("ROLE", user.getRole().getRoleName());
                    mav.setViewName("redirect:admin");
                } else {
                    mav.setViewName("redirect:welcome");
                }
            }
            request.getSession().setAttribute("user", user);
        } catch (NoResultException e) {
            request.getSession().invalidate();
            mav.setViewName("login");
            mav.addObject("error", "Имя пользователя или пароль не корректны");
        }
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/form/registration")
    public ModelAndView handleRegistration() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registerUser(HttpServletRequest request, @ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView();
        try {
            User userFromService = userService.createUser(request, user);
            boolean userExists = userService.findByUsernameAndEmail(userFromService.getUsername(), userFromService.getEmail());
            if (!userExists) {
                boolean isSaved = userService.save(userFromService);
                if(isSaved) {
                    mav.setViewName("login");
                    mav.addObject("message", "Вы успешно зарегистрировались");
                    log.info("Пользователь " + userFromService.getUsername() + " успешно зарегистрирован");
                }
            } else {
                throw new ValidationException("Пользователь с таким именем или электронной почтой уже существует");
            }
        } catch (ValidationException e) {
            mav.setViewName("registration");
            mav.addObject("error", e.getMessage());
            log.info(e.getMessage());
            return mav;
        }
        return mav;
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
        List<EquipmentSize> skiPoleSizeList = service.findAllSizes();
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
        int page = (pageNumber - 1) * PAGE_SIZE;
        List<? extends Item> list = service.findAll(page, PAGE_SIZE);
        long entityNumber = service.count();
        long pagesNumber = (long) Math.ceil(entityNumber * 1.0 / PAGE_SIZE);
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("list", list);
    }
}
