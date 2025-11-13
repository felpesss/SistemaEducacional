-- ============================================
-- SISTEMA EDUCACIONAL - BANCO DE DADOS COMPLETO
-- ============================================

DROP DATABASE IF EXISTS SistemaEducacional;
CREATE DATABASE SistemaEducacional;
USE SistemaEducacional;

-- ============================================
-- TABELAS OBRIGATÓRIAS: USUÁRIOS E GRUPOS
-- ============================================

-- Tabela de grupos de usuários (controle de permissões)
CREATE TABLE grupos_usuarios (
    id_grupo VARCHAR(50) PRIMARY KEY,
    nome_grupo VARCHAR(100) NOT NULL,
    descricao TEXT,
    nivel_acesso INT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de usuários
CREATE TABLE usuarios (
    id_usuario VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    id_grupo VARCHAR(50) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_grupo) REFERENCES grupos_usuarios(id_grupo),
    INDEX idx_email (email),
    INDEX idx_cpf (cpf),
    INDEX idx_grupo (id_grupo)
);

-- ============================================
-- TABELAS DO SISTEMA EDUCACIONAL
-- ============================================

CREATE TABLE Aluno (
    RA VARCHAR(50) PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Telefone VARCHAR(20),
    DataNascimento DATE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por VARCHAR(50),
    INDEX idx_aluno_cpf (CPF),
    INDEX idx_aluno_email (Email),
    FOREIGN KEY (criado_por) REFERENCES usuarios(id_usuario)
);

CREATE TABLE Curso (
    ID_Curso VARCHAR(50) PRIMARY KEY,
    Nome VARCHAR(255) UNIQUE NOT NULL,
    CargaHoraria INT NOT NULL,
    Modalidade VARCHAR(50) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por VARCHAR(50),
    INDEX idx_curso_nome (Nome),
    FOREIGN KEY (criado_por) REFERENCES usuarios(id_usuario)
);

CREATE TABLE Nota (
    id_nota VARCHAR(50) PRIMARY KEY,
    RA_Aluno VARCHAR(50) NOT NULL,
    ID_Curso VARCHAR(50) NOT NULL,
    Valor DOUBLE NOT NULL CHECK (Valor >= 0 AND Valor <= 10),
    DataNota DATE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por VARCHAR(50),
    INDEX idx_nota_aluno (RA_Aluno),
    INDEX idx_nota_curso (ID_Curso),
    INDEX idx_nota_data (DataNota),
    FOREIGN KEY (RA_Aluno) REFERENCES Aluno(RA),
    FOREIGN KEY (ID_Curso) REFERENCES Curso(ID_Curso),
    FOREIGN KEY (criado_por) REFERENCES usuarios(id_usuario)
);

-- ============================================
-- TABELA DE AUDITORIA
-- ============================================

CREATE TABLE auditoria (
    id_auditoria VARCHAR(50) PRIMARY KEY,
    tabela VARCHAR(100) NOT NULL,
    operacao VARCHAR(20) NOT NULL,
    id_registro VARCHAR(50),
    usuario VARCHAR(50),
    data_operacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dados_anteriores TEXT,
    dados_novos TEXT,
    INDEX idx_auditoria_tabela (tabela),
    INDEX idx_auditoria_data (data_operacao)
);

-- ============================================
-- FUNCTIONS PARA GERAÇÃO DE IDs
-- ============================================

DELIMITER $$

-- Function para gerar ID de usuário
CREATE FUNCTION gerar_id_usuario()
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE novo_id VARCHAR(50);
    DECLARE contador INT;
    
    SELECT COUNT(*) + 1 INTO contador FROM usuarios;
    SET novo_id = CONCAT('USR', LPAD(contador, 8, '0'), '_', UNIX_TIMESTAMP());
    
    RETURN novo_id;
END$$

-- Function para gerar ID de grupo
CREATE FUNCTION gerar_id_grupo(prefixo VARCHAR(10))
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE novo_id VARCHAR(50);
    DECLARE contador INT;
    
    SELECT COUNT(*) + 1 INTO contador FROM grupos_usuarios;
    SET novo_id = CONCAT(prefixo, '_', LPAD(contador, 5, '0'));
    
    RETURN novo_id;
END$$

-- Function para gerar RA de aluno
CREATE FUNCTION gerar_ra_aluno()
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE novo_ra VARCHAR(50);
    DECLARE contador INT;
    
    SELECT COUNT(*) + 1 INTO contador FROM Aluno;
    SET novo_ra = CONCAT('RA', YEAR(CURDATE()), LPAD(contador, 6, '0'));
    
    RETURN novo_ra;
END$$

-- Function para gerar ID de curso
CREATE FUNCTION gerar_id_curso()
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE novo_id VARCHAR(50);
    DECLARE contador INT;
    
    SELECT COUNT(*) + 1 INTO contador FROM Curso;
    SET novo_id = CONCAT('CRS', LPAD(contador, 6, '0'));
    
    RETURN novo_id;
END$$

-- Function para gerar ID de nota
CREATE FUNCTION gerar_id_nota()
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE novo_id VARCHAR(50);
    SET novo_id = CONCAT('NOT', LPAD(FLOOR(RAND() * 999999), 6, '0'), '_', UNIX_TIMESTAMP());
    RETURN novo_id;
END$$

-- Function para gerar ID de auditoria
CREATE FUNCTION gerar_id_auditoria()
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE novo_id VARCHAR(50);
    SET novo_id = CONCAT('AUD', UNIX_TIMESTAMP(), '_', FLOOR(RAND() * 9999));
    RETURN novo_id;
END$$

DELIMITER ;

-- ============================================
-- TRIGGERS
-- ============================================

DELIMITER $$

-- Trigger 1: Auditoria ao inserir aluno
CREATE TRIGGER trg_auditoria_insert_aluno
AFTER INSERT ON Aluno
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (id_auditoria, tabela, operacao, id_registro, usuario, dados_novos)
    VALUES (
        gerar_id_auditoria(),
        'Aluno',
        'INSERT',
        NEW.RA,
        NEW.criado_por,
        CONCAT('Nome:', NEW.Nome, ' CPF:', NEW.CPF, ' Email:', NEW.Email)
    );
END$$

-- Trigger 2: Validação de nota antes de inserir
CREATE TRIGGER trg_validacao_nota
BEFORE INSERT ON Nota
FOR EACH ROW
BEGIN
    IF NEW.Valor < 0 OR NEW.Valor > 10 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Nota deve estar entre 0 e 10';
    END IF;
    
    IF NEW.DataNota > CURDATE() THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Data da nota não pode ser futura';
    END IF;
END$$

-- Trigger 3: Auditoria ao atualizar usuário
CREATE TRIGGER trg_auditoria_update_usuario
AFTER UPDATE ON usuarios
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (id_auditoria, tabela, operacao, id_registro, usuario, dados_anteriores, dados_novos)
    VALUES (
        gerar_id_auditoria(),
        'usuarios',
        'UPDATE',
        NEW.id_usuario,
        NEW.id_usuario,
        CONCAT('Email:', OLD.email, ' Grupo:', OLD.id_grupo),
        CONCAT('Email:', NEW.email, ' Grupo:', NEW.id_grupo)
    );
END$$

DELIMITER ;

-- ============================================
-- VIEWS
-- ============================================

-- View 1: Relatório de desempenho dos alunos
CREATE VIEW vw_desempenho_alunos AS
SELECT 
    a.RA,
    a.Nome,
    a.Email,
    COUNT(DISTINCT n.ID_Curso) as total_cursos,
    ROUND(AVG(n.Valor), 2) as media_geral,
    MAX(n.Valor) as nota_maxima,
    MIN(n.Valor) as nota_minima
FROM Aluno a
LEFT JOIN Nota n ON a.RA = n.RA_Aluno
GROUP BY a.RA, a.Nome, a.Email;

-- View 2: Estatísticas dos cursos
CREATE VIEW vw_estatisticas_cursos AS
SELECT 
    c.ID_Curso,
    c.Nome,
    c.Modalidade,
    c.CargaHoraria,
    COUNT(DISTINCT n.RA_Aluno) as total_alunos,
    ROUND(AVG(n.Valor), 2) as media_curso,
    COUNT(n.id_nota) as total_notas
FROM Curso c
LEFT JOIN Nota n ON c.ID_Curso = n.ID_Curso
GROUP BY c.ID_Curso, c.Nome, c.Modalidade, c.CargaHoraria;

-- ============================================
-- PROCEDURES
-- ============================================

DELIMITER $$

-- Procedure 1: Inserir novo aluno com geração automática de RA
CREATE PROCEDURE sp_inserir_aluno(
    IN p_nome VARCHAR(255),
    IN p_cpf VARCHAR(14),
    IN p_email VARCHAR(255),
    IN p_telefone VARCHAR(20),
    IN p_data_nascimento DATE,
    IN p_criado_por VARCHAR(50),
    OUT p_ra VARCHAR(50)
)
BEGIN
    SET p_ra = gerar_ra_aluno();
    
    INSERT INTO Aluno (RA, Nome, CPF, Email, Telefone, DataNascimento, criado_por)
    VALUES (p_ra, p_nome, p_cpf, p_email, p_telefone, p_data_nascimento, p_criado_por);
    
    SELECT CONCAT('Aluno cadastrado com RA: ', p_ra) as mensagem;
END$$

-- Procedure 2: Cadastrar nota com validações
CREATE PROCEDURE sp_cadastrar_nota(
    IN p_ra_aluno VARCHAR(50),
    IN p_id_curso VARCHAR(50),
    IN p_valor DOUBLE,
    IN p_data_nota DATE,
    IN p_criado_por VARCHAR(50)
)
BEGIN
    DECLARE v_id_nota VARCHAR(50);
    DECLARE v_aluno_existe INT;
    DECLARE v_curso_existe INT;
    
    -- Verifica se aluno existe
    SELECT COUNT(*) INTO v_aluno_existe FROM Aluno WHERE RA = p_ra_aluno;
    IF v_aluno_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Aluno não encontrado';
    END IF;
    
    -- Verifica se curso existe
    SELECT COUNT(*) INTO v_curso_existe FROM Curso WHERE ID_Curso = p_id_curso;
    IF v_curso_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Curso não encontrado';
    END IF;
    
    SET v_id_nota = gerar_id_nota();
    
    INSERT INTO Nota (id_nota, RA_Aluno, ID_Curso, Valor, DataNota, criado_por)
    VALUES (v_id_nota, p_ra_aluno, p_id_curso, p_valor, p_data_nota, p_criado_por);
    
    SELECT CONCAT('Nota cadastrada com ID: ', v_id_nota) as mensagem;
END$$

DELIMITER ;

-- ============================================
-- CRIAÇÃO DE USUÁRIOS E PERMISSÕES
-- ============================================

-- Remover usuários se existirem
DROP USER IF EXISTS 'admin_sistema'@'localhost';
DROP USER IF EXISTS 'professor'@'localhost';
DROP USER IF EXISTS 'aluno_user'@'localhost';

-- Usuário Administrador
CREATE USER 'admin_sistema'@'localhost' IDENTIFIED BY 'Admin@123';
GRANT ALL PRIVILEGES ON SistemaEducacional.* TO 'admin_sistema'@'localhost';

-- Usuário Professor (pode inserir e consultar)
CREATE USER 'professor'@'localhost' IDENTIFIED BY 'Prof@123';
GRANT SELECT, INSERT, UPDATE ON SistemaEducacional.Aluno TO 'professor'@'localhost';
GRANT SELECT, INSERT, UPDATE ON SistemaEducacional.Curso TO 'professor'@'localhost';
GRANT SELECT, INSERT, UPDATE ON SistemaEducacional.Nota TO 'professor'@'localhost';
GRANT SELECT ON SistemaEducacional.vw_desempenho_alunos TO 'professor'@'localhost';
GRANT SELECT ON SistemaEducacional.vw_estatisticas_cursos TO 'professor'@'localhost';
GRANT EXECUTE ON PROCEDURE SistemaEducacional.sp_inserir_aluno TO 'professor'@'localhost';
GRANT EXECUTE ON PROCEDURE SistemaEducacional.sp_cadastrar_nota TO 'professor'@'localhost';

-- Usuário Aluno (apenas consulta)
CREATE USER 'aluno_user'@'localhost' IDENTIFIED BY 'Aluno@123';
GRANT SELECT ON SistemaEducacional.Aluno TO 'aluno_user'@'localhost';
GRANT SELECT ON SistemaEducacional.Curso TO 'aluno_user'@'localhost';
GRANT SELECT ON SistemaEducacional.Nota TO 'aluno_user'@'localhost';
GRANT SELECT ON SistemaEducacional.vw_desempenho_alunos TO 'aluno_user'@'localhost';

FLUSH PRIVILEGES;

-- ============================================
-- DADOS INICIAIS
-- ============================================

-- Inserir grupos de usuários
INSERT INTO grupos_usuarios (id_grupo, nome_grupo, descricao, nivel_acesso) VALUES
('GRP_ADM', 'Administradores', 'Acesso total ao sistema', 1),
('GRP_PROF', 'Professores', 'Gerenciar alunos, cursos e notas', 2),
('GRP_ALU', 'Alunos', 'Visualizar informações próprias', 3);

-- Inserir usuários (senha: "123456" em MD5)
INSERT INTO usuarios (id_usuario, nome, email, senha, cpf, id_grupo) VALUES
(gerar_id_usuario(), 'Admin Sistema', 'admin@sistema.edu', 'e10adc3949ba59abbe56e057f20f883e', '11111111111', 'GRP_ADM'),
(gerar_id_usuario(), 'Professor Silva', 'professor@sistema.edu', 'e10adc3949ba59abbe56e057f20f883e', '22222222222', 'GRP_PROF'),
(gerar_id_usuario(), 'João Aluno', 'joao@aluno.edu', 'e10adc3949ba59abbe56e057f20f883e', '33333333333', 'GRP_ALU');

-- Obter ID do usuário admin para usar como criador
SET @admin_id = (SELECT id_usuario FROM usuarios WHERE email = 'admin@sistema.edu' LIMIT 1);

-- Inserir cursos usando function de geração de ID
INSERT INTO Curso (ID_Curso, Nome, CargaHoraria, Modalidade, criado_por) VALUES
(gerar_id_curso(), 'Engenharia de Software', 3200, 'Presencial', @admin_id),
(gerar_id_curso(), 'Banco de Dados', 800, 'EAD', @admin_id),
(gerar_id_curso(), 'Redes de Computadores', 900, 'Hibrido', @admin_id),
(gerar_id_curso(), 'Desenvolvimento Web', 900, 'EAD', @admin_id),
(gerar_id_curso(), 'Inteligência Artificial', 2200, 'Hibrido', @admin_id);

-- Inserir alguns alunos de exemplo usando procedure
CALL sp_inserir_aluno('João Silva', '12345678901', 'joao@teste.com', '11999999999', '2000-05-10', @admin_id, @ra1);
CALL sp_inserir_aluno('Maria Santos', '98765432100', 'maria@teste.com', '11988888888', '2001-08-15', @admin_id, @ra2);
CALL sp_inserir_aluno('Pedro Costa', '45678912300', 'pedro@teste.com', '11977777777', '1999-02-20', @admin_id, @ra3);

-- ============================================
-- CONSULTAS DE VERIFICAÇÃO
-- ============================================

SELECT '=== GRUPOS DE USUÁRIOS ===' as Info;
SELECT * FROM grupos_usuarios;

SELECT '=== USUÁRIOS ===' as Info;
SELECT id_usuario, nome, email, id_grupo, ativo FROM usuarios;

SELECT '=== CURSOS ===' as Info;
SELECT * FROM Curso;

SELECT '=== ALUNOS ===' as Info;
SELECT * FROM Aluno;

SELECT '=== VIEW: ESTATÍSTICAS DOS CURSOS ===' as Info;
SELECT * FROM vw_estatisticas_cursos;

SELECT '=== FUNÇÕES E TRIGGERS CRIADOS ===' as Info;
SHOW FUNCTION STATUS WHERE Db = 'SistemaEducacional';
SHOW TRIGGERS FROM SistemaEducacional;

SELECT '=== PROCEDURES CRIADAS ===' as Info;
SHOW PROCEDURE STATUS WHERE Db = 'SistemaEducacional';

SELECT '=== USUÁRIOS DO BANCO ===' as Info;
SELECT User, Host FROM mysql.user WHERE User IN ('admin_sistema', 'professor', 'aluno_user');
