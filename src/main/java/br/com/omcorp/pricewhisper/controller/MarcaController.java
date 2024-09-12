package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.repository.MarcaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MarcaController {

    @Autowired
    private MarcaRepository repMarca;

    @GetMapping("/marcas-interface")
    public ModelAndView marcasInterface() {
        List<Marca> marcas = repMarca.findAll();

        ModelAndView mv = new ModelAndView("marcas-interface");
        mv.addObject("marcas", marcas);

        return mv;
    }

    @GetMapping("/nova-marca")
    public ModelAndView novaMarca() {
        ModelAndView mv = new ModelAndView("form-marca");
        mv.addObject("marca", new Marca());

        return mv;
    }

    @PostMapping("/inserir-marca")
    public ModelAndView inserirMarca(@Valid Marca marca, BindingResult bd) {
        if (bd.hasErrors()) {
            return novaMarca();
        } else {
            repMarca.save(marca);
            return marcasInterface();
        }
    }

    @GetMapping("/editar-marca/{id}")
    public ModelAndView editarMarca(@PathVariable Long id) {

        Optional<Marca> op = repMarca.findById(id);

        if (op.isPresent()) {
            Marca marca = op.get();

            ModelAndView mv = new ModelAndView("form-marca-editar");
            mv.addObject("marca", marca);

            return mv;
        } else {
            return marcasInterface();
        }
    }

    @PostMapping("/atualizar-marca/{id}")
    public ModelAndView atualizarMarca(@PathVariable Long id, @Valid Marca marca, BindingResult bd) {
        if (bd.hasErrors()) {
            return novaMarca();
        } else {
            Optional<Marca> op = repMarca.findById(id);

            if (op.isPresent()) {
                Marca marcaNova = op.get();

                marcaNova.setNome(marca.getNome());
                marcaNova.setDescricao(marca.getDescricao());

                repMarca.save(marcaNova);
                return marcasInterface();
            } else {
                return editarMarca(id);
            }

        }
    }

    @GetMapping("/deletar-marca/{id}")
    public ModelAndView deletarMarca(@PathVariable Long id) {
        Optional<Marca> op = repMarca.findById(id);

        if (op.isPresent()) {
            repMarca.deleteById(id);
            return marcasInterface();
        } else {
            return marcasInterface();
        }
    }
}
