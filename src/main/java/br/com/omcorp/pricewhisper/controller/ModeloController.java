package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.repository.ModeloRepository;
import br.com.omcorp.pricewhisper.services.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    @Autowired
    private ModeloRepository repModelo;

    @Autowired
    private MarcaService marcaService;

    @GetMapping()
    public ModelAndView modelosList() {
        List<Modelo> modelos = repModelo.findAll();

        ModelAndView mv = new ModelAndView("modelos-list");
        mv.addObject("modelos", modelos);

        return mv;
    }

    @GetMapping("/form_novo_modelo")
    public ModelAndView formNovoModelo() {
        List<Marca> marcas = marcaService.getAll();

        ModelAndView mv = new ModelAndView("form-modelo");
        mv.addObject("modelo", new Modelo());
        mv.addObject("marcas", marcas);

        return mv;
    }

    @GetMapping("/form_editar_modelo/{id}")
    public ModelAndView formEditarModelo(@PathVariable Long id) {

        Optional<Modelo> op = repModelo.findById(id);

        if (op.isPresent()) {
            Modelo modelo = op.get();

            List<Marca> marcas = marcaService.getAll();

            ModelAndView mv = new ModelAndView("form-modelo-editar");
            mv.addObject("modelo", modelo);
            mv.addObject("marcas", marcas);

            return mv;
        } else {
            return new ModelAndView("redirect:/modelos");
        }
    }

    @PostMapping("/api/save")
    public ModelAndView saveModelo(@Valid Modelo modelo, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/modelos/form_novo_modelo");
        } else {
            repModelo.save(modelo);
            return new ModelAndView("redirect:/modelos");
        }
    }

    @PostMapping("/api/update/{id}")
    public ModelAndView updateModelo(@PathVariable Long id, @Valid Modelo modelo, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/modelos/form_novo_modelo");
        } else {
            Optional<Modelo> op = repModelo.findById(id);

            if (op.isPresent()) {
                Modelo modeloNovo = op.get();

                modeloNovo.setNome(modelo.getNome());
                modeloNovo.setDescricao(modelo.getDescricao());
                modeloNovo.setMarca(modelo.getMarca());

                repModelo.save(modeloNovo);
                return new ModelAndView("redirect:/modelos");
            } else {
                return new ModelAndView("redirect:/modelos/form_editar_modelo/" + id);
            }

        }
    }

    @GetMapping("/api/delete/{id}")
    public ModelAndView deleteModelo(@PathVariable Long id) {
        Optional<Modelo> op = repModelo.findById(id);

        if (op.isPresent()) {
            repModelo.deleteById(id);
        }
        return new ModelAndView("redirect:/modelos");
    }
}
