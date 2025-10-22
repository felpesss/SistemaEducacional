CREATE DATABASE IF NOT EXISTS SistemaEducacional;

USE SistemaEducacional;

CREATE TABLE IF NOT EXISTS Aluno (
    RA INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255),
    CPF VARCHAR(14) UNIQUE,
    Email VARCHAR(255),
    Telefone VARCHAR(20),
    DataNascimento VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS Curso (
    ID_Curso INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) UNIQUE,
    CargaHoraria INT,
    Modalidade VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Nota (
    RA_Aluno INT,
    ID_Curso INT,
    Valor DOUBLE,
    DataNota VARCHAR(10),
    PRIMARY KEY (RA_Aluno, ID_Curso),
    FOREIGN KEY (RA_Aluno) REFERENCES Aluno(RA),
    FOREIGN KEY (ID_Curso) REFERENCES Curso(ID_Curso)
);
