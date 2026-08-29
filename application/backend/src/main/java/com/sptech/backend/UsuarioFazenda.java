package com.sptech.backend;

public class UsuarioFazenda {
    private Usuario usuario;
    private Fazenda fazenda;

    public UsuarioFazenda() {
    }

    public UsuarioFazenda(Usuario usuario, Fazenda fazenda) {
        this.usuario = usuario;
        this.fazenda = fazenda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }
}
