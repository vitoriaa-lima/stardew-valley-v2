package com.sptech.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        // Validar nome
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getNome().length() > 100) {
            return ResponseEntity.status(400).build();
        }

        // Validar e-mail
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        if (usuario.getEmail().length() > 100) {
            return ResponseEntity.status(400).build();
        }

        if (!usuario.getEmail().contains("@")) {
            return ResponseEntity.status(400).build();
        }

        // Verificar se e-mail já existe
        String sqlEmail = "SELECT COUNT(*) FROM usuario WHERE email = ?";

        Integer qtdEmail = jdbcTemplate.queryForObject(sqlEmail,  Integer.class, usuario.getEmail());

        if (qtdEmail > 0) {
            return ResponseEntity.status(409).build();
        }


        // Validar data de nascimento
        if (usuario.getDtNascimento() == null) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getDtNascimento().isAfter(LocalDate.now())) {
            return ResponseEntity.status(400).build();
        }


        // Validar perfeição
        if (usuario.getPerfeicao() == null) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getQtdPerfeicao() == null) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getQtdPerfeicao() < 0) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getPerfeicao() && usuario.getQtdPerfeicao() == 0) {
            return ResponseEntity.status(400).build();
        }
        if (!usuario.getPerfeicao() && usuario.getQtdPerfeicao() != 0) {
            return ResponseEntity.status(400).build();
        }


        // Validar senha
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getSenha().length() < 6 ||
                usuario.getSenha().length() > 45) {
            return ResponseEntity.status(400).build();
        }


        // Validar personagem favorito
        if (usuario.getPersonagemFavorito() == null) {
            return ResponseEntity.status(400).build();
        }
        if (usuario.getPersonagemFavorito().getId() == null) {
            return ResponseEntity.status(400).build();
        }

        String sqlPersonagem = "SELECT COUNT(*) FROM personagem WHERE id_personagem = ?;";
        Integer qtdPersonagem = jdbcTemplate.queryForObject(sqlPersonagem, Integer.class, usuario.getPersonagemFavorito().getId());

        if (qtdPersonagem == 0) {
            return ResponseEntity.status(400).build();
        }


        // Validar fazendas
        if (usuario.getFazendas() == null ||
                usuario.getFazendas().isEmpty()) {
            return ResponseEntity.status(400).build();
        }
        for (Fazenda fazenda : usuario.getFazendas()) {

            if (fazenda == null || fazenda.getId() == null) {
                return ResponseEntity.status(400).build();
            }
        }

        String sqlFazenda = "SELECT COUNT(*) FROM fazenda WHERE id_fazenda = ?;";
        for (Fazenda fazenda : usuario.getFazendas()) {
            Integer qtdFazenda = jdbcTemplate.queryForObject(sqlFazenda, Integer.class, fazenda.getId());
            if (qtdFazenda == 0) {
                return ResponseEntity.status(400).build();
            }
        }

        // Validar fazendas repetidas
        for (int i = 0; i < usuario.getFazendas().size(); i++) {
            for (int j = i + 1; j < usuario.getFazendas().size(); j++) {

                if (usuario.getFazendas().get(i).getId().equals(usuario.getFazendas().get(j).getId())) {
                    return ResponseEntity.status(400).build();
                }
            }
        }

        String sql = "INSERT INTO usuario (nome,email, dt_nascimento, perfeicao, qtd_perfeicao, senha, fk_personagem_favorito) VALUES (?, ?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setDate(3, Date.valueOf(usuario.getDtNascimento()));
            ps.setInt(4, usuario.getPerfeicao() ? 1 : 0);
            ps.setInt(5, usuario.getQtdPerfeicao());
            ps.setString(6, usuario.getSenha());
            ps.setInt(7, usuario.getPersonagemFavorito().getId());
            return ps;
        }, keyHolder);

        Integer idGerado = keyHolder.getKeyAs(Integer.class);
        usuario.setId(idGerado);

        String sqlUsuarioFazenda = "INSERT INTO usuario_fazenda (fk_usuario, fk_fazenda) VALUES (?, ?);";
        for (Fazenda fazenda : usuario.getFazendas()) {
            jdbcTemplate.update(
                    sqlUsuarioFazenda,
                    usuario.getId(),
                    fazenda.getId()
            );
        }

        return ResponseEntity.status(201).body(usuario);
    }
}
