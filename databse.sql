DROP DATABASE IF EXISTS sistemaeducacional;
CREATE DATABASE sistemaeducacional;
USE sistemaeducacional;

/* tabela de alunos */
CREATE TABLE aluno (
    ra VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    datanascimento DATE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por VARCHAR(50)
);

/* tabela de auditoria */
CREATE TABLE auditoria (
    id_auditoria VARCHAR(50) PRIMARY KEY,
    tabela VARCHAR(100) NOT NULL,
    operacao VARCHAR(20) NOT NULL,
    id_registro VARCHAR(50),
    usuario VARCHAR(50),
    data_operacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dados_anteriores TEXT,
    dados_novos TEXT
);

/* inserir alunos de exemplo */
INSERT INTO aluno (ra, nome, cpf, email, telefone, datanascimento) VALUES
('RA2025000001', 'João Silva', '12345678901', 'joao@teste.com', '11999999999', '2000-05-10'),
('RA2025000002', 'Maria Santos', '98765432100', 'maria@teste.com', '11988888888', '2001-08-15'),
('RA2025000003', 'Pedro Costa', '45678912300', 'pedro@teste.com', '11977777777', '1999-02-20');

/* verificar dados */
SELECT * FROM aluno;
