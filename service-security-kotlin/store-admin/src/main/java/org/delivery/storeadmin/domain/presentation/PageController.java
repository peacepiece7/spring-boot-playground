package org.delivery.storeadmin.domain.presentation;

import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller // @RestController 아님!
@RequestMapping("")
public class PageController {

    @RequestMapping(path = {"/", "/main"})
    public ModelAndView main(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}\n", authentication);
        log.info("Authentication: {}\n", authentication.getPrincipal());
        if (authentication.getPrincipal() instanceof UserSession userSession) {
            model.addAttribute("email", userSession.getEmail());
            model.addAttribute("storeName", userSession.getStoreName());
            model.addAttribute("role", userSession.getRole());
        } else {
            model.addAttribute("email", "No Email");
            model.addAttribute("storeName", "no store name");
            model.addAttribute("role", "no role");
        }
        return new ModelAndView("main"); // resources/templates/main.html 와 바인딩 됩니다.
    }

    @RequestMapping(path = {"/order"})
    public ModelAndView order() {
        return new ModelAndView("order/order"); // resources/templates/order/order.html 와 바인딩 됩니다.
    }
}
