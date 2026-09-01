package com.sptech.backend;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private LocalDate dtNascimento;
    private Boolean perfeicao;
    private Integer qtdPerfeicao;
    private String senha;
    private Personagem personagemFavorito;
    private List<Fazenda> fazendas;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, LocalDate dtNascimento, Boolean perfeicao, Integer qtdPerfeicao, String senha, Personagem personagemFavorito, List<Fazenda> fazendas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dtNascimento = dtNascimento;
        this.perfeicao = perfeicao;
        this.qtdPerfeicao = qtdPerfeicao;
        this.senha = senha;
        this.personagemFavorito = personagemFavorito;
        this.fazendas = fazendas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Boolean getPerfeicao() {
        return perfeicao;
    }

    public void setPerfeicao(Boolean perfeicao) {
        this.perfeicao = perfeicao;
    }

    public Integer getQtdPerfeicao() {
        return qtdPerfeicao;
    }

    public void setQtdPerfeicao(Integer qtdPerfeicao) {
        this.qtdPerfeicao = qtdPerfeicao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Personagem getPersonagemFavorito() {
        return personagemFavorito;
    }

    public void setPersonagemFavorito(Personagem personagemFavorito) {
        this.personagemFavorito = personagemFavorito;
    }

    public List<Fazenda> getFazendas() {
        return fazendas;
    }

    public void setFazendas(List<Fazenda> fazendas) {
        this.fazendas = fazendas;
    }
}
