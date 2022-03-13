package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.enums.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @GetMapping({"/snowboard", "/ski", "/clothes", "/contacts", "/form/login", "/form/registration", "/admin/"})
    public String handleNavbar(HttpServletRequest request) {
        Page page = Page.getEnum(request.getRequestURI());
        return page.getForwardPage();
    }
}
