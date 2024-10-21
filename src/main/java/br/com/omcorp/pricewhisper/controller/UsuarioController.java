package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Usuario;
import br.com.omcorp.pricewhisper.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login-page";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register-page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuario) {
        usuarioService.register(usuario);

        return "redirect:/login?success=true";
    }
}
