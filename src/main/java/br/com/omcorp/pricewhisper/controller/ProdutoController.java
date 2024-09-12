package br.com.omcorp.pricewhisper.controller;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.model.Produto;
import br.com.omcorp.pricewhisper.repository.CategoriaRepository;
import br.com.omcorp.pricewhisper.repository.ModeloRepository;
import br.com.omcorp.pricewhisper.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repProdutos;
    @Autowired
    private CategoriaRepository repCategorias;
    @Autowired
    private ModeloRepository repModelo;


    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/produtos-interface")
    public ModelAndView produtosInterface() {
        List<Produto> listaProdutos = repProdutos.findAll();

        ModelAndView mv = new ModelAndView("produtos-interface");
        mv.addObject("produtos", listaProdutos);

        return mv;
    }

    @GetMapping("/novo-produto")
    public ModelAndView novoProduto() {
        List<Categoria> listaCategorias = repCategorias.findAll();
        List<Modelo> listaModelos = repModelo.findAll();

        ModelAndView mv = new ModelAndView("form-produto");
        mv.addObject("produto", new Produto());
        mv.addObject("categorias", listaCategorias);
        mv.addObject("modelos", listaModelos);

        return mv;
    }

    @PostMapping("/inserir-produto")
    public ModelAndView inserirProduto(@Valid Produto produto, BindingResult bd) {

        if (bd.hasErrors()) {
            return novoProduto();
        } else {
            repProdutos.save(produto);
            return new ModelAndView("redirect:/produtos-interface");
        }
    }

    @GetMapping("/editar-produto/{id}")
    public ModelAndView editarProduto(@PathVariable Long id) {

        Optional<Produto> op = repProdutos.findById(id);

        if (op.isPresent()) {
            Produto produto = op.get();

            List<Categoria> listaCategorias = repCategorias.findAll();
            List<Modelo> listaModelos = repModelo.findAll();

            ModelAndView mv = new ModelAndView("form-produto-editar");
            mv.addObject("produto", produto);
            mv.addObject("categorias", listaCategorias);
            mv.addObject("modelos", listaModelos);

            return mv;
        } else {
            return produtosInterface();
        }
    }

    @PostMapping("/atualizar-produto/{id}")
    public ModelAndView atualizarProduto(@PathVariable Long id, @Valid Produto produto, BindingResult bd) {
        if (bd.hasErrors()) {
            return novoProduto();
        } else {
            Optional<Produto> op = repProdutos.findById(id);

            if (op.isPresent()) {
                Produto produtoNovo = op.get();

                produtoNovo.setNome(produto.getNome());
                produtoNovo.setDescricao(produto.getDescricao());
                produtoNovo.setPrecoCusto(produto.getPrecoCusto());
                produtoNovo.setPrecoVenda(produto.getPrecoVenda());
                produtoNovo.setPrecoMinimo(produto.getPrecoMinimo());
                produtoNovo.setEstoque(produto.getEstoque());
                produtoNovo.setModelo(produto.getModelo());
                produtoNovo.setCategoria(produto.getCategoria());

                repProdutos.save(produtoNovo);
                return produtosInterface();
            } else {
                return editarProduto(id);
            }

        }
    }

    @GetMapping("/deletar-produto/{id}")
    public ModelAndView deletarProduto(@PathVariable Long id) {
        Optional<Produto> op = repProdutos.findById(id);

        if (op.isPresent()) {
            repProdutos.deleteById(id);
            return produtosInterface();
        } else {
            return produtosInterface();
        }
    }
}
