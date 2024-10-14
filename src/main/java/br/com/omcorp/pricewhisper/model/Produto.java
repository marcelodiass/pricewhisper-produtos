package br.com.omcorp.pricewhisper.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Não é possível cadastrar um produto sem nome!")
    private String nome;

    private String descricao;

    @NotNull(message = "Não é possível cadastrar um produto sem o preço de custo!")
    @DecimalMin(value = "0", message = "O preço de custo não pode ser negativo!")
    private BigDecimal precoCusto;

    @NotNull(message = "Não é possível cadastrar um produto sem o preço de venda!")
    @DecimalMin(value = "0", message = "O preço de venda não pode ser negativo!")
    private BigDecimal precoVenda;

    @NotNull(message = "Não é possível cadastrar um produto sem o preço mínimo!")
    @DecimalMin(value = "0", message = "O preço mínimo não pode ser negativo!")
    private BigDecimal precoMinimo;

    @NotNull(message = "Não é possível cadastrar um produto sem estoque!")
    @DecimalMin(value = "0", message = "O estoque não pode ser negativo!")
    private Integer estoque;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_categoria")
    @Valid
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_modelo")
    @Valid
    private Modelo modelo;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, BigDecimal precoCusto, BigDecimal precoVenda, BigDecimal precoMinimo, Integer estoque, Categoria categoria, Modelo modelo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.precoMinimo = precoMinimo;
        this.estoque = estoque;
        this.categoria = categoria;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public BigDecimal getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(BigDecimal precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public @Valid Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(@Valid Categoria categoria) {
        this.categoria = categoria;
    }

    public @Valid Modelo getModelo() {
        return modelo;
    }

    public void setModelo(@Valid Modelo modelo) {
        this.modelo = modelo;
    }
}
