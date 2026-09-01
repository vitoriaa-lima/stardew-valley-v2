CREATE TABLE personagem (
    id_personagem INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE fazenda (
    id_fazenda INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    dt_nascimento DATE,
    perfeicao BOOLEAN,
    qtd_perfeicao INT,
    senha VARCHAR(45) NOT NULL,
    fk_personagem_favorito INT,
    CONSTRAINT fk_usuario_personagem
        FOREIGN KEY (fk_personagem_favorito)
            REFERENCES personagem(id_personagem)
);

CREATE TABLE usuario_fazenda (
    fk_usuario INT NOT NULL,
    fk_fazenda INT NOT NULL,
    PRIMARY KEY (fk_usuario, fk_fazenda),
    CONSTRAINT fk_usuario_fazenda_usuario
        FOREIGN KEY (fk_usuario)
            REFERENCES usuario(id_usuario),
    CONSTRAINT fk_usuario_fazenda_fazenda
        FOREIGN KEY (fk_fazenda)
            REFERENCES fazenda(id_fazenda)
);

INSERT INTO fazenda (nome) VALUES
    ('Padrão'),
    ('Entre Riachos'),
    ('Remota'),
    ('Colina'),
    ('Floresta'),
    ('Praia');

INSERT INTO personagem (nome) VALUES
    ('Abigail'),
    ('Alex'),
    ('Caroline'),
    ('Clint'),
    ('Demetrius'),
    ('Anão'),
    ('Elliott'),
    ('Emily'),
    ('Evelyn'),
    ('George'),
    ('Gus'),
    ('Haley'),
    ('Harvey'),
    ('Jas'),
    ('Jodi'),
    ('Kent'),
    ('Krobus'),
    ('Leah'),
    ('Leo'),
    ('Lewis'),
    ('Linus'),
    ('Marnie'),
    ('Maru'),
    ('Pam'),
    ('Penny'),
    ('Pierre'),
    ('Robin'),
    ('Sam'),
    ('Sebastian'),
    ('Shane'),
    ('Vincent'),
    ('Willy'),
    ('Mago'),
    ('Sandy');
