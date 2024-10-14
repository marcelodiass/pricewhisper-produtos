package br.com.omcorp.pricewhisper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PricewhisperController {
    
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
    
}
