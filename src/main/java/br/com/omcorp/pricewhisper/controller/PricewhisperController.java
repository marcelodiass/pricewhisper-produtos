package br.com.omcorp.pricewhisper.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PricewhisperController {
    
    @GetMapping
    public ModelAndView home(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView mv = new ModelAndView("index");
        if (userDetails != null) {
            mv.addObject("username", userDetails.getUsername());
        }

        return mv;
    }
    
}
