CREATE DATABASE IF NOT EXISTS ta_limpo
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ta_limpo;

-- ==========================================
-- TABELA: marca
-- ==========================================
CREATE TABLE marca (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_marca_nome (nome)
) ENGINE=InnoDB;


-- ==========================================
-- TABELA: veiculo
-- ==========================================
CREATE TABLE veiculo (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(100) NOT NULL,
    marca_id BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_veiculo_marca
        FOREIGN KEY (marca_id)
        REFERENCES marca(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;


-- ==========================================
-- TABELA: usuario
-- ==========================================
CREATE TABLE usuario (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    permissao ENUM('admin', 'lavador', 'consultor') NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_usuario_username (username)
) ENGINE=InnoDB;


-- ==========================================
-- TABELA: lavagem
-- ==========================================
CREATE TABLE lavagem (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    veiculo_id BIGINT UNSIGNED NOT NULL,
    placa VARCHAR(10) NOT NULL,
    data DATE NOT NULL DEFAULT (CURDATE()),
    observacao TEXT,
    status ENUM('aguardando', 'lavando', 'finalizado')
        NOT NULL DEFAULT 'aguardando',

    PRIMARY KEY (id),

    CONSTRAINT fk_lavagem_veiculo
        FOREIGN KEY (veiculo_id)
        REFERENCES veiculo(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;
