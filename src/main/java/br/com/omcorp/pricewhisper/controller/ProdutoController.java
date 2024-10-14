package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.model.Produto;
import br.com.omcorp.pricewhisper.services.CategoriaService;
import br.com.omcorp.pricewhisper.services.ModeloService;
import br.com.omcorp.pricewhisper.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModeloService modeloService;

    @GetMapping()
    public ModelAndView produtosList() {
        List<Produto> listaProdutos = service.getAll();

        ModelAndView mv = new ModelAndView("produtos-list");
        mv.addObject("produtos", listaProdutos);

        return mv;
    }

    @GetMapping("/form_novo_produto")
    public ModelAndView formNovoProduto() {
        List<Categoria> listaCategorias = categoriaService.getAll();
        List<Modelo> listaModelos = modeloService.getAll();

        ModelAndView mv = new ModelAndView("form-produto");
        mv.addObject("produto", new Produto());
        mv.addObject("categorias", listaCategorias);
        mv.addObject("modelos", listaModelos);

        return mv;
    }

    @GetMapping("/form_editar_produto/{id}")
    public ModelAndView formEditarProduto(@PathVariable Long id) {
        Optional<Produto> op = service.getById(id);

        if (op.isEmpty()) {
            return new ModelAndView("redirect:/produtos");
        }
        
        Produto produto = op.get();

        List<Categoria> listaCategorias = categoriaService.getAll();
        List<Modelo> listaModelos = modeloService.getAll();

        ModelAndView mv = new ModelAndView("form-produto-editar");
        mv.addObject("produto", produto);
        mv.addObject("categorias", listaCategorias);
        mv.addObject("modelos", listaModelos);

        return mv;
    }

    @PostMapping("/api/save")
    public ModelAndView saveProduto(@Valid Produto produto, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/produtos/form_novo_produto");
        }
        
        service.save(produto);
        return new ModelAndView("redirect:/produtos");
    }

    @PostMapping("/api/update/{id}")
    public ModelAndView updateProduto(@PathVariable Long id, @Valid Produto produto, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("redirect:/produtos/form_novo_produto");
        }
        
        Optional<Produto> op = service.getById(id);

        if (op.isEmpty()) {
            return new ModelAndView("redirect:/produtos/form_editar_produto/" + id);
        }

        Produto produtoNovo = op.get();
        service.update(produtoNovo, produto);
        return new ModelAndView("redirect:/produtos");
    }

    @GetMapping("/api/delete/{id}")
    public ModelAndView deleteProduto(@PathVariable Long id) {
        Optional<Produto> op = service.getById(id);

        if (op.isPresent()) {
            service.delete(id);
        }
        
        return new ModelAndView("redirect:/produtos");
    }
}
