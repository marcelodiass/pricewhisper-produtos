package br.com.omcorp.pricewhisper.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "MODELO")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Não é possível cadastrar um modelo sem nome!")
    private String nome;

    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_marca")
    @Valid
    private Marca marca;

    public Modelo() {
    }

    public Modelo(Long id, String nome, String descricao, Marca marca) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
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

    public @Valid Marca getMarca() {
        return marca;
    }

    public void setMarca(@Valid Marca marca) {
        this.marca = marca;
    }
}
