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
@RequestMapping("/personagens")
@CrossOrigin(origins="http://127.0.0.1:5500")
public class PersonagemController {
     private final JdbcTemplate jdbcTemplate;

     public PersonagemController(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
     }

     @GetMapping
     public ResponseEntity<List<Personagem>> listar(){
          String verificar = "SELECT COUNT(*) FROM personagem;";

          Integer countPersonagem = jdbcTemplate.queryForObject(verificar, Integer.class);

          if (countPersonagem == null || countPersonagem == 0){
               return ResponseEntity.status(404).build();
          }

          String sql = "SELECT * FROM personagem;";

          List<Personagem> personagens = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Personagem.class));
          return ResponseEntity.status(200).body(personagens);
     }
}