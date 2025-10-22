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
INSERT INTO Aluno (Nome, CPF, Email, DataNascimento) VALUES

('João Silva', '12345678901', 'joao@teste.com', '2000-05-10'),

('Ana Pereira', '23456789012', 'ana@teste.com', '2001-08-15'),

('Carlos Lima', '34567890123', 'carlos@teste.com', '1999-02-20'),

('Beatriz Souza', '45678901234', 'beatriz@teste.com', '2002-03-12'),

('Marcos Paulo', '56789012345', 'marcos@teste.com', '1998-07-21'),

('Larissa Costa', '67890123456', 'larissa@teste.com', '2001-10-04'),

('Fernanda Alves', '78901234567', 'fernanda@teste.com', '2000-01-19'),

('Ricardo Torres', '89012345678', 'ricardo@teste.com', '1999-11-25'),

('Gabriel Martins', '90123456789', 'gabriel@teste.com', '2003-06-02'),

('Amanda Rocha', '01234567890', 'amanda@teste.com', '2002-04-14'),

('Thiago Ramos', '11234567890', 'thiago@teste.com', '2000-09-09'),

('Patrícia Mendes', '12234567890', 'patricia@teste.com', '1998-02-27'),

('Juliana Freitas', '13234567890', 'juliana@teste.com', '2001-12-01'),

('Eduardo Nunes', '14234567890', 'eduardo@teste.com', '1999-05-30'),

('Tatiane Pires', '15234567890', 'tatiane@teste.com', '2000-03-05'),

('Vinícius Duarte', '16234567890', 'vinicius@teste.com', '2002-07-16'),

('Roberta Faria', '17234567890', 'roberta@teste.com', '2001-11-22'),

('Pedro Gomes', '18234567890', 'pedro@teste.com', '1998-09-28'),

('Luana Carvalho', '19234567890', 'luana@teste.com', '2003-02-17'),
('Marcela Ribeiro', '20234567890', 'marcela@teste.com', '2001-06-10'),

('Felipe Santos', '21234567890', 'felipe@teste.com', '1999-08-03'),

('Carolina Melo', '22234567890', 'carolina@teste.com', '2000-04-22'),

('Felipe batista', '23234567865', 'felipe1@teste.com', '2002-06-15'),

('Renata Prado', '24234567890', 'renata@teste.com', '2001-09-03'),

('André Costa', '25234567890', 'andre@teste.com', '1999-03-22'),

('Bruna Lopes', '26234567890', 'bruna@teste.com', '2002-05-14'),

('Caio Oliveira', '27234567890', 'caio@teste.com', '2000-12-08'),

('Letícia Moura', '28234567890', 'leticia@teste.com', '1998-06-27'),

('Danilo Ferreira', '29234567890', 'danilo@teste.com', '1999-09-19'),

('Camila Duarte', '30234567890', 'camila@teste.com', '2001-01-29'),

('Henrique Souza', '31234567890', 'henrique@teste.com', '2002-10-15'),

('Joana Lima', '32234567890', 'joana@teste.com', '1998-07-13'),

('Matheus Azevedo', '33234567890', 'matheus@teste.com', '2003-03-21'),

('Jéssica Rocha', '34234567890', 'jessica@teste.com', '2000-11-11'),

('Lucas Almeida', '35234567890', 'lucas@teste.com', '2001-04-09'),

('Paula Teixeira', '36234567890', 'paula@teste.com', '2000-02-19'),

('Bruno Campos', '37234567890', 'bruno@teste.com', '1999-08-02'),

('Tatiane Nogueira', '38234567890', 'tatiane.nogueira@teste.com', '2001-05-26'),

('Igor Mendes', '39234567890', 'igor@teste.com', '2002-03-03'),

('Sabrina Torres', '40234567890', 'sabrina@teste.com', '1998-12-20'),

('Rafael Pires', '41234567890', 'rafael@teste.com', '2000-01-05'),

('Cláudia Ribeiro', '42234567890', 'claudia@teste.com', '2003-09-30'),

('Murilo Barros', '43234567890', 'murilo@teste.com', '1999-10-17'),

('Alice Furtado', '44234567890', 'alice@teste.com', '2002-06-13'),

('João Pedro', '45234567890', 'joaopedro@teste.com', '2001-02-09'),

('Natália Tavares', '46234567890', 'natalia@teste.com', '1998-11-07'),

('Rodrigo Martins', '47234567890', 'rodrigo@teste.com', '2000-07-19'),

('Melissa Andrade', '48234567890', 'melissa@teste.com', '2003-04-05'),

('Caíque Nunes', '49234567890', 'caique@teste.com', '2001-08-25'),

('Débora Freitas', '50234567890', 'debora@teste.com', '1999-09-15'),

('Thiago Araujo', '51234567890', 'thiago.araujo@teste.com', '2002-12-02'),

('Marta Correia', '52234567890', 'marta@teste.com', '1998-03-28'),

('Felipe Almeida', '53234567890', 'felipe.almeida@teste.com', '2000-05-22'),

('Camilo Vasconcelos', '54234567890', 'camilo@teste.com', '2001-07-11'),

('Gustavo Lopes', '55234567890', 'gustavo@teste.com', '1999-01-17'),

('Evelyn Barbosa', '56234567890', 'evelyn@teste.com', '2002-10-29'),

('Simone Castro', '57234567890', 'simone@teste.com', '2003-08-18'),

('Alexandre Dias', '58234567890', 'alexandre@teste.com', '1998-04-02'),

('Priscila Rocha', '59234567890', 'priscila@teste.com', '2001-05-15'),

('Luciano Braga', '60234567890', 'luciano@teste.com', '2000-09-21'),

('Juliana Pinto', '61234567890', 'juliana.pinto@teste.com', '2003-02-13'),

('Nicolas Ferreira', '62234567890', 'nicolas@teste.com', '2002-06-24'),

('Carla Souza', '63234567890', 'carla@teste.com', '1999-12-07'),

('Diego Monteiro', '64234567890', 'diego@teste.com', '2000-08-12'),

('Renan Lopes', '65234567890', 'renan@teste.com', '1998-05-05'),

('Luciana Lima', '66234567890', 'luciana@teste.com', '2001-07-28'),

('Vinícius Costa', '67234567890', 'vinicius.costa@teste.com', '2002-09-09'),

('Brenda Martins', '68234567890', 'brenda@teste.com', '2000-02-01'),

('Arthur Ribeiro', '69234567890', 'arthur@teste.com', '2001-11-22'),

('Vera Lemos', '70234567890', 'vera@teste.com', '1999-10-30'),

('Paulo Sérgio', '71234567890', 'paulo@teste.com', '2003-01-14'),

('Larissa Nogueira', '72234567890', 'larissa.nogueira@teste.com', '2002-07-03'),

('Rogério Mendes', '73234567890', 'rogerio@teste.com', '1998-09-19'),

('Fátima Teixeira', '74234567890', 'fatima@teste.com', '2000-05-28'),
('Júlio César', '75234567890', 'julio@teste.com', '1999-02-10'),

('Carine Dias', '76234567890', 'carine@teste.com', '2001-08-09'),

('Luan Moreira', '77234567890', 'luan@teste.com', '2003-04-19'),

('Yasmin Gomes', '78234567890', 'yasmin@teste.com', '2002-11-23'),

('Tatiana Melo', '79234567890', 'tatiana.melo@teste.com', '2000-03-27'),

('Pedro Nogueira', '80234567890', 'pedronogueira@teste.com', '1998-12-14'),

('Helena Ramos', '81234567890', 'helena@teste.com', '2003-05-06'),

('César Lima', '82234567890', 'cesar@teste.com', '2000-07-24'),

('Lorena Pacheco', '83234567890', 'lorena@teste.com', '2001-09-17'),

('Alberto Tavares', '84234567890', 'alberto@teste.com', '1999-11-01'),

('Mirella Costa', '85234567890', 'mirella@teste.com', '2002-01-10'),

('Fernando Silva', '86234567890', 'fernando@teste.com', '1998-04-12'),

('Bárbara Azevedo', '87234567890', 'barbara@teste.com', '2001-06-05'),

('Jonas Cardoso', '88234567890', 'jonas@teste.com', '2003-10-01'),

('Sueli Farias', '89234567890', 'sueli@teste.com', '2000-02-14'),

('Caroline Dias', '90234567890', 'caroline@teste.com', '1999-08-26'),

('Heitor Souza', '91234567890', 'heitor@teste.com', '2001-12-09'),

('Mônica Oliveira', '92234567890', 'monica@teste.com', '2003-03-02'),

('Rafaela Lima', '93234567890', 'rafaela@teste.com', '1998-06-18'),

('Guilherme Melo', '94234567890', 'guilherme@teste.com', '2000-09-27'),

('Lívia Duarte', '95234567890', 'livia@teste.com', '2002-12-22'),

('José Neto', '96234567890', 'jose@teste.com', '1999-01-15'),

('Eduarda Rocha', '97234567890', 'eduarda@teste.com', '2003-08-02'),

('Cristiano Lima', '98234567890', 'cristiano@teste.com', '2000-07-11'),

('Jade Ferreira', '99234567890', 'jade@teste.com', '2001-04-01'),

('Nelson Carvalho', '00234567891', 'nelson@teste.com', '1998-11-11'),

('Érica Nunes', '01234567891', 'erica@teste.com', '2002-10-28'),

('Otávio Ribeiro', '02234567891', 'otavio@teste.com', '2000-06-20'),

('Rebeca Torres', '03234567891', 'rebeca@teste.com', '2003-02-25'),

('Alan Almeida', '04234567891', 'alan@teste.com', '1999-09-29'),

('Silvia Barros', '05234567891', 'silvia@teste.com', '2001-01-08'),

('Andressa Melo', '06234567891', 'andressa@teste.com', '2002-03-16'),

('Caetano Prado', '07234567891', 'caetano@teste.com', '2000-10-19'),

('Beatriz Lopes', '08234567891', 'beatriz.lopes@teste.com', '2003-06-14'),

('Eduardo Souza', '09234567891', 'eduardo.souza@teste.com', '1998-05-11'),

('Giovana Ramos', '10234567891', 'giovana@teste.com', '2001-02-17'),

('Renato Martins', '11234567891', 'renato@teste.com', '2000-09-24'),

('Cláudio Figueiredo', '12234567891', 'claudio@teste.com', '2003-12-04'),

('Tânia Gomes', '13234567891', 'tania@teste.com', '1998-03-23'),

('Paula Vieira', '14234567891', 'paula.vieira@teste.com', '2001-10-29'),

('Hugo Oliveira', '15234567891', 'hugo@teste.com', '2002-01-20'),

('Márcia Ferreira', '16234567891', 'marcia@teste.com', '1999-07-03'),

('Anderson Pires', '17234567891', 'anderson@teste.com', '2000-12-18'),

('Lara Campos', '18234567891', 'lara@teste.com', '2002-05-25'),

('Fábio Nascimento', '19234567891', 'fabio@teste.com', '2001-04-09'),

('Carol Silva', '20234567891', 'carol@teste.com', '2000-08-01');

INSERT INTO Curso (Nome, CargaHoraria, Modalidade) VALUES

('Engenharia de Software', 3200, 'Presencial'),

('Banco de Dados', 800, 'EAD'),

('Redes de Computadores', 900, 'Hibrido'),

('Análise e Desenvolvimento de Sistemas', 2400, 'Presencial'),

('Administração de Empresas', 3000, 'Presencial'),

('Gestão de Projetos', 800, 'EAD'),

('Matemática Aplicada', 2800, 'Presencial'),
('Ciência de Dados', 2000, 'EAD'),

('Engenharia Elétrica', 3600, 'Presencial'),

('Design Gráfico', 1200, 'Hibrido'),

('Arquitetura de Software', 1600, 'EAD'),

('Desenvolvimento Web', 900, 'EAD'),

('Inteligência Artificial', 2200, 'Hibrido'),

('Sistemas Embarcados', 1800, 'Presencial'),

('Cibersegurança', 1600, 'EAD'),

('Marketing Digital', 1000, 'Hibrido'),

('Gestão Financeira', 900, 'Presencial'),

('Recursos Humanos', 1100, 'EAD'),

('Computação Gráfica', 1300, 'Hibrido'),

('Engenharia Civil', 4000, 'Presencial'),

('Engenharia Mecânica', 3800, 'Presencial'),

('Jogos Digitais', 1400, 'Hibrido'),

('Big Data', 2000, 'EAD');

INSERT INTO Nota (RA, IDCurso, Valor, Data) VALUES

(1, 1, 8.5, '2025-06-20'),

(2, 2, 7.0, '2025-06-21'),

(3, 3, 9.0, '2025-06-22'),

(4, 4, 7.5, '2025-06-23'),

(5, 5, 6.8, '2025-06-24'),

(6, 6, 9.2, '2025-06-25'),

(7, 7, 8.0, '2025-06-26'),

(8, 8, 7.1, '2025-06-27'),

(9, 9, 9.5, '2025-06-28'),

(10, 10, 6.9, '2025-06-29'),

(11, 11, 8.8, '2025-06-30'),

(12, 12, 7.4, '2025-07-01'),

(13, 13, 9.0, '2025-07-02'),

(14, 14, 8.3, '2025-07-03'),

(15, 15, 6.7, '2025-07-04'),

(16, 16, 7.9, '2025-07-05'),

(17, 17, 8.6, '2025-07-06'),

(18, 18, 9.1, '2025-07-07'),

(19, 19, 7.3, '2025-07-08'),

(20, 20, 8.9, '2025-07-09'),

(21, 21, 7.8, '2025-07-10'),

(22, 22, 8.2, '2025-07-11'),

(23, 1, 9.4, '2025-07-12'),

(24, 2, 8.0, '2025-07-13'),

(25, 3, 7.6, '2025-07-14'),

(26, 4, 8.9, '2025-07-15'),

(27, 5, 9.3, '2025-07-16'),

(28, 6, 8.1, '2025-07-17'),

(29, 7, 7.7, '2025-07-18'),

(30, 8, 9.0, '2025-07-19'),

(31, 9, 6.9, '2025-07-20'),

(32, 10, 8.5, '2025-07-21'),

(33, 11, 9.2, '2025-07-22'),

(34, 12, 7.3, '2025-07-23'),

(35, 13, 8.8, '2025-07-24'),

(36, 14, 9.1, '2025-07-25'),

(37, 15, 7.5, '2025-07-26'),
(93, 5, 9.4, '2025-09-20'),

(94, 6, 8.6, '2025-09-21'),

(95, 7, 7.9, '2025-09-22'),

(96, 8, 8.8, '2025-09-23'),

(97, 9, 9.0, '2025-09-24'),

(98, 10, 7.2, '2025-09-25'),

(99, 11, 8.9, '2025-09-26'),

(100, 12, 9.3, '2025-09-27'),

(101, 13, 8.7, '2025-09-28'),

(102, 14, 7.8, '2025-09-29'),

(103, 15, 9.5, '2025-09-30'),

(104, 16, 8.1, '2025-10-01'),

(105, 17, 9.4, '2025-10-02'),

(106, 18, 8.5, '2025-10-03'),

(107, 19, 9.2, '2025-10-04'),

(108, 20, 8.3, '2025-10-05'),

(109, 21, 7.9, '2025-10-06'),

(110, 22, 9.1, '2025-10-07'),

(111, 1, 8.8, '2025-10-08'),

(112, 2, 7.5, '2025-10-09'),

(113, 3, 9.0, '2025-10-10'),

(114, 4, 8.7, '2025-10-11'),

(115, 5, 8.2, '2025-10-12'),

(116, 6, 9.4, '2025-10-13'),

(117, 7, 8.9, '2025-10-14'),

(118, 8, 7.8, '2025-10-15'),

(119, 9, 9.3, '2025-10-16');

