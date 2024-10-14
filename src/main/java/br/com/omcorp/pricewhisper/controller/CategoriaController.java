package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.repository.CategoriaRepository;
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
    private CategoriaRepository repCategoria;

    @GetMapping()
    public ModelAndView categoriasList() {
        List<Categoria> categorias = repCategoria.findAll();

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

        Optional<Categoria> op = repCategoria.findById(id);

        if (op.isPresent()) {
            Categoria categoria = op.get();

            ModelAndView mv = new ModelAndView("form-categoria-editar");
            mv.addObject("categoria", categoria);

            return mv;
        } else {
            return new ModelAndView("redirect:/categorias");
        }
    }

    @PostMapping("/api/save")
    public ModelAndView saveCategoria(@Valid Categoria categoria, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/form_nova_categoria");
        } else {
            repCategoria.save(categoria);
            return new ModelAndView("redirect:/categorias");
        }
    }

    @PostMapping("/api/update/{id}")
    public ModelAndView updateCategoria(@PathVariable Long id, @Valid Categoria categoria, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/categorias/form_nova_categoria");
        } else {
            Optional<Categoria> op = repCategoria.findById(id);

            if (op.isPresent()) {
                Categoria categoriaNova = op.get();

                categoriaNova.setNome(categoria.getNome());
                categoriaNova.setDescricao(categoria.getDescricao());

                repCategoria.save(categoriaNova);
                return new ModelAndView("redirect:/categorias");
            } else {
                return new ModelAndView("redirect:/categorias/form_editar_categoria/" + id);
            }

        }
    }

    @GetMapping("/api/delete/{id}")
    public ModelAndView deleteCategoria(@PathVariable Long id) {
        Optional<Categoria> op = repCategoria.findById(id);
        
        if (op.isPresent()) {
            repCategoria.deleteById(id);
        }
        
        return new ModelAndView("redirect:/categorias");
    }

}
