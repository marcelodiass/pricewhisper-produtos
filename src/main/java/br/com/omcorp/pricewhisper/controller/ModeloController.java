package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.model.Produto;
import br.com.omcorp.pricewhisper.repository.MarcaRepository;
import br.com.omcorp.pricewhisper.repository.ModeloRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ModeloController {

    @Autowired
    private ModeloRepository repModelo;

    @Autowired
    private MarcaRepository repMarca;

    @GetMapping("/modelos-interface")
    public ModelAndView modelosInterface() {
        List<Modelo> modelos = repModelo.findAll();

        ModelAndView mv = new ModelAndView("modelos-interface");
        mv.addObject("modelos", modelos);

        return mv;
    }

    @GetMapping("/novo-modelo")
    public ModelAndView novoModelo() {
        List<Marca> marcas = repMarca.findAll();

        ModelAndView mv = new ModelAndView("form-modelo");
        mv.addObject("modelo", new Modelo());
        mv.addObject("marcas", marcas);

        return mv;
    }

    @PostMapping("/inserir-modelo")
    public ModelAndView inserirModelo(@Valid Modelo modelo, BindingResult bd) {
        if (bd.hasErrors()) {
            return novoModelo();
        } else {
            repModelo.save(modelo);
            return modelosInterface();
        }
    }

    @GetMapping("/editar-modelo/{id}")
    public ModelAndView editarModelo(@PathVariable Long id) {

        Optional<Modelo> op = repModelo.findById(id);

        if (op.isPresent()) {
            Modelo modelo = op.get();

            List<Marca> marcas = repMarca.findAll();

            ModelAndView mv = new ModelAndView("form-modelo-editar");
            mv.addObject("modelo", modelo);
            mv.addObject("marcas", marcas);

            return mv;
        } else {
            return modelosInterface();
        }
    }

    @PostMapping("/atualizar-modelo/{id}")
    public ModelAndView atualizarModelo(@PathVariable Long id, @Valid Modelo modelo, BindingResult bd) {
        if (bd.hasErrors()) {
            return novoModelo();
        } else {
            Optional<Modelo> op = repModelo.findById(id);

            if (op.isPresent()) {
                Modelo modeloNovo = op.get();

                modeloNovo.setNome(modelo.getNome());
                modeloNovo.setDescricao(modelo.getDescricao());
                modeloNovo.setMarca(modelo.getMarca());

                repModelo.save(modeloNovo);
                return modelosInterface();
            } else {
                return editarModelo(id);
            }

        }
    }

    @GetMapping("/deletar-modelo/{id}")
    public ModelAndView deletarModelo(@PathVariable Long id) {
        Optional<Modelo> op = repModelo.findById(id);

        if (op.isPresent()) {
            repModelo.deleteById(id);
            return modelosInterface();
        } else {
            return modelosInterface();
        }
    }
}
