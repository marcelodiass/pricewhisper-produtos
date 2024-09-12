package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repCategoria;

    @GetMapping("/categorias-interface")
    public ModelAndView categoriaInterface() {
        List<Categoria> categorias = repCategoria.findAll();

        ModelAndView mv = new ModelAndView("categorias-interface");
        mv.addObject("categorias", categorias);

        return mv;
    }

    @GetMapping("/nova-categoria")
    public ModelAndView novaCategoria() {
        ModelAndView mv = new ModelAndView("form-categoria");
        mv.addObject("categoria", new Categoria());

        return mv;
    }

    @PostMapping("/inserir-categoria")
    public ModelAndView inserirCategoria(@Valid Categoria categoria, BindingResult bd) {
        if (bd.hasErrors()) {
            return novaCategoria();
        } else {
            repCategoria.save(categoria);
            return categoriaInterface();
        }
    }

    @GetMapping("/editar-categoria/{id}")
    public ModelAndView editarCategoria(@PathVariable Long id) {

        Optional<Categoria> op = repCategoria.findById(id);

        if (op.isPresent()) {
            Categoria categoria = op.get();

            ModelAndView mv = new ModelAndView("form-categoria-editar");
            mv.addObject("categoria", categoria);

            return mv;
        } else {
            return categoriaInterface();
        }
    }

    @PostMapping("/atualizar-categoria/{id}")
    public ModelAndView atualizarCategoria(@PathVariable Long id, @Valid Categoria categoria, BindingResult bd) {
        if (bd.hasErrors()) {
            return novaCategoria();
        } else {
            Optional<Categoria> op = repCategoria.findById(id);

            if (op.isPresent()) {
                Categoria categoriaNova = op.get();

                categoriaNova.setNome(categoria.getNome());
                categoriaNova.setDescricao(categoria.getDescricao());

                repCategoria.save(categoriaNova);
                return categoriaInterface();
            } else {
                return editarCategoria(id);
            }

        }
    }

    @GetMapping("/deletar-categoria/{id}")
    public ModelAndView deletarCategoria(@PathVariable Long id) {
        Optional<Categoria> op = repCategoria.findById(id);

        if (op.isPresent()) {
            repCategoria.deleteById(id);
            return categoriaInterface();
        } else {
            return categoriaInterface();
        }
    }

}
