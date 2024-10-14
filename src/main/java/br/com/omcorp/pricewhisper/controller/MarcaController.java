package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.services.MarcaService;
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
    private MarcaService service;

    @GetMapping()
    public ModelAndView marcasList() {
        List<Marca> marcas = service.getAll();

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
        Optional<Marca> op = service.getById(id);

        if (op.isEmpty()) {
            return new ModelAndView("redirect:/marcas");
        }

        Marca marca = op.get();

        ModelAndView mv = new ModelAndView("form-marca-editar");
        mv.addObject("marca", marca);

        return mv;
    }

    @PostMapping("/api/save")
    public ModelAndView saveMarca(@Valid Marca marca, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/marcas/form_nova_marca");
        }

        service.save(marca);
        return new ModelAndView("redirect:/marcas?registrado=true");
    }

    @PostMapping("/api/update/{id}")
    public ModelAndView updateMarca(@PathVariable Long id, @Valid Marca marca, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/marcas/form_nova_marca");
        }

        Optional<Marca> op = service.getById(id);

        if (op.isEmpty()) {
            return new ModelAndView("redirect:/marcas/form_editar_marca/" + id);
        }

        Marca marcaNova = op.get();
        service.update(marcaNova, marca);
        return new ModelAndView("redirect:/marcas?atualizado=true");
    }

    @GetMapping("/api/delete/{id}")
    public ModelAndView deleteMarca(@PathVariable Long id) {
        Optional<Marca> op = service.getById(id);

        if (op.isPresent()) {
            service.delete(id);
        }
        
        return new ModelAndView("redirect:/marcas?deletado=true");
    }
}
