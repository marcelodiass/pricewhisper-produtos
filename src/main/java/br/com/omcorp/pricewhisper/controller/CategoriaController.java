package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping()
    public ModelAndView categoriasList() {
        List<Categoria> categorias = service.getAll();

        ModelAndView mv = new ModelAndView("categorias-list");
        mv.addObject("categorias", categorias);

        return mv;
    }

    @GetMapping("/form_nova_categoria")
    public ModelAndView formNovaCategoria() {
        ModelAndView mv = new ModelAndView("form-categoria");
        mv.addObject("categoria", new Categoria());
        return mv;
    }

    @GetMapping("/form_editar_categoria/{id}")
    public ModelAndView formEditarCategoria(@PathVariable Long id) {
        Optional<Categoria> op = service.getById(id);

        if (op.isEmpty()) {
            return new ModelAndView("redirect:/categorias");
        }
        
        Categoria categoria = op.get();

        ModelAndView mv = new ModelAndView("form-categoria-editar");
        mv.addObject("categoria", categoria);

        return mv;
    }
    
    /*
    @Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "form-categoria";
    }

    @PostMapping("/api/save")
    public String saveCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form-categoria";
        }
        // Salvar a categoria no banco de dados
        return "redirect:/categorias";
    }
}
     */

    @PostMapping("/api/save")
    public ModelAndView saveCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("form-categoria");
            mv.addObject("categoria", categoria);
            return mv;
        }

        service.save(categoria);
        return new ModelAndView("redirect:/categorias?registrado=true");
    }

    @PostMapping("/api/update/{id}")
    public ModelAndView updateCategoria(@PathVariable Long id, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("form-categoria-editar");
            mv.addObject("categoria", categoria);
            return mv;
        }

        Optional<Categoria> op = service.getById(id);

        if (op.isEmpty()) {
            ModelAndView mv = new ModelAndView("redirect:/categorias/form_editar_categoria/" + id);
            mv.addObject("categoria", new Categoria());
            return mv;
        }

        Categoria categoriaNova = op.get();
        service.update(categoriaNova, categoria);
        
        return new ModelAndView("redirect:/categorias?atualizado=true");
    }

    @GetMapping("/api/delete/{id}")
    public ModelAndView deleteCategoria(@PathVariable Long id) {
        Optional<Categoria> op = service.getById(id);

        if (op.isPresent()) {
            service.delete(id);
        }

        return new ModelAndView("redirect:/categorias?deletado=true");
    }

}
