package com.sptech.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fazendas")
@CrossOrigin(origins="http://127.0.0.1:5500")
public class FazendaController {
    private final JdbcTemplate jdbcTemplate;

    public FazendaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Fazenda>> listar(){
        String verificar = "SELECT COUNT(*) FROM fazenda;";

        Integer countFazenda = jdbcTemplate.queryForObject(verificar, Integer.class);

        if (countFazenda == null || countFazenda == 0){
            return ResponseEntity.status(404).build();
        }

        String sql = "SELECT * FROM fazenda;";

        List<Fazenda> fazendas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Fazenda.class));
        return ResponseEntity.status(200).body(fazendas);
    }
}