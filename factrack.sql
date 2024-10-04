DROP DATABASE IF EXISTS factrack;

CREATE DATABASE factrack;

USE factrack;

CREATE TABLE setor (
    codigo_setor INT PRIMARY KEY AUTO_INCREMENT,
    nome_setor VARCHAR(20),
    descricao_setor VARCHAR(40)
);

CREATE TABLE funcfab (
    codigo_funcfab INT PRIMARY KEY AUTO_INCREMENT,
    nome_funcfab VARCHAR(30),
    
    codigo_fk_setor INT,
    CONSTRAINT fk_setor_funcfab FOREIGN KEY (codigo_fk_setor) REFERENCES setor(codigo_setor)
);

CREATE TABLE funcesc (
    codigo_funcesc INT PRIMARY KEY AUTO_INCREMENT,
    nome_funcesc VARCHAR(30),
    email_funcesc VARCHAR(30),
    senha_funcesc VARCHAR(30)
);

CREATE TABLE problema (
    codigo_problema INT PRIMARY KEY AUTO_INCREMENT,
    tipo_problema VARCHAR(30),
    
    codigo_fk_funcfab INT,
    codigo_fk_setor INT,

    CONSTRAINT fk_funcfab_problema FOREIGN KEY (codigo_fk_funcfab) REFERENCES funcfab(codigo_funcfab),
    CONSTRAINT fk_setor_problema FOREIGN KEY (codigo_fk_setor) REFERENCES setor(codigo_setor)
);
