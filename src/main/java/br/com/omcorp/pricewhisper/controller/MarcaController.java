package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.repository.MarcaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository repMarca;

    @GetMapping()
    public ModelAndView marcasList() {
        List<Marca> marcas = repMarca.findAll();

        ModelAndView mv = new ModelAndView("marcas-list");
        mv.addObject("marcas", marcas);

        return mv;
    }

    @GetMapping("/form_nova_marca")
    public ModelAndView formNovaMarca() {
        ModelAndView mv = new ModelAndView("form-marca");
        mv.addObject("marca", new Marca());

        return mv;
    }

    @GetMapping("/form_editar_marca/{id}")
    public ModelAndView formEditarMarca(@PathVariable Long id) {

        Optional<Marca> op = repMarca.findById(id);

        if (op.isPresent()) {
            Marca marca = op.get();

            ModelAndView mv = new ModelAndView("form-marca-editar");
            mv.addObject("marca", marca);

            return mv;
        } else {
            return new ModelAndView("redirect:/marcas");
        }
    }

    @PostMapping("/api/save")
    public ModelAndView saveMarca(@Valid Marca marca, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/marcas/form_nova_marca");
        } else {
            repMarca.save(marca);
            return new ModelAndView("redirect:/marcas");
        }
    }

    @PostMapping("/api/update/{id}")
    public ModelAndView updateMarca(@PathVariable Long id, @Valid Marca marca, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/marcas/form_nova_marca");
        } else {
            Optional<Marca> op = repMarca.findById(id);

            if (op.isPresent()) {
                Marca marcaNova = op.get();

                marcaNova.setNome(marca.getNome());
                marcaNova.setDescricao(marca.getDescricao());

                repMarca.save(marcaNova);
                return new ModelAndView("redirect:/marcas");
            } else {
                return new ModelAndView("redirect:/marcas/form_editar_marca/" + id);
            }

        }
    }

    @GetMapping("/api/delete/{id}")
    public ModelAndView deleteMarca(@PathVariable Long id) {
        Optional<Marca> op = repMarca.findById(id);

        if (op.isPresent()) {
            repMarca.deleteById(id);
        }
        return new ModelAndView("redirect:/marcas");
    }
}
